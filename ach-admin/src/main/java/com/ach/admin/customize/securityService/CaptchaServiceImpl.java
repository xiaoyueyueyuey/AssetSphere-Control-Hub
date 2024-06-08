package com.ach.admin.customize.securityService;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.util.IdUtil;
import com.ach.admin.common.cache.GuavaCacheService;
import com.ach.admin.common.cache.MapCache;
import com.ach.admin.common.cache.RedisCacheService;
import com.ach.admin.customize.async.AsyncTaskFactory;
import com.ach.admin.dto.login.CaptchaDTO;
import com.ach.admin.dto.login.ConfigDTO;
import com.ach.common.constant.Constants;
import com.ach.common.enums.common.ConfigKeyEnum;
import com.ach.common.enums.common.LoginStatusEnum;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.system.userLogin.CaptchaService;
import com.ach.infrastructure.config.XYBootConfig;
import com.ach.infrastructure.thread.ThreadPoolManager;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
@Slf4j
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {
    private final RedisCacheService redisCache;
    private final GuavaCacheService guavaCache;
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 获取验证码 data
     *
     * @return {@link ConfigDTO}
     */
    public ConfigDTO getConfig() {
        ConfigDTO configDTO = new ConfigDTO();
        boolean isCaptchaOn = isCaptchaOn();
        configDTO.setIsCaptchaOn(isCaptchaOn);
        configDTO.setDictionary(MapCache.dictionaryCache());
        return configDTO;
    }

    /**
     * 获取验证码 data
     *
     * @return 验证码
     */
    public CaptchaDTO generateCaptchaImg() {
        CaptchaDTO captchaDTO = new CaptchaDTO();
        boolean isCaptchaOn = isCaptchaOn();//应该是给前端判断是否要进行验证码校验的
        captchaDTO.setIsCaptchaOn(isCaptchaOn);
        if (isCaptchaOn) {
            String expression;
            String answer = null;
            BufferedImage image = null;
            // 生成验证码
            String captchaType = XYBootConfig.getCaptchaType();
            if (Constants.Captcha.MATH_TYPE.equals(captchaType)) {
                String capText = captchaProducerMath.createText();
                String[] expressionAndAnswer = capText.split("@");
                expression = expressionAndAnswer[0];
                answer = expressionAndAnswer[1];
                image = captchaProducerMath.createImage(expression);
            }
            if (Constants.Captcha.CHAR_TYPE.equals(captchaType)) {
                expression = answer = captchaProducer.createText();
                image = captchaProducer.createImage(expression);
            }
            if (image == null) {
                throw new ApiException(ErrorCode.Internal.LOGIN_CAPTCHA_GENERATE_FAIL);
            }
            // 保存验证码信息
            String imgKey = IdUtil.simpleUUID();
            redisCache.captchaCache.set(imgKey, answer);
            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            ImgUtil.writeJpg(image, os);
            captchaDTO.setCaptchaCodeKey(imgKey);
            captchaDTO.setCaptchaCodeImg(Base64.encode(os.toByteArray()));
        }
        return captchaDTO;
    }

    /**
     * 校验验证码
     *
     * @param username       用户名
     * @param captchaCode    验证码
     * @param captchaCodeKey 验证码对应的缓存key
     */
    // 验证码校验
    @Override
    public void validateCaptcha(String username, String captchaCode, String captchaCodeKey) {
        if (isCaptchaOn()) {
            String captcha = redisCache.captchaCache.getObjectById(captchaCodeKey);// 从缓存中获取验证码
            redisCache.captchaCache.delete(captchaCodeKey);
            if (captcha == null) {
                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                        ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE.message()));// 记录登录信息
                throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE);
            }
            if (!captchaCode.equalsIgnoreCase(captcha)) {
                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                        ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG.message()));// 记录登录信息
                throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG);
            }
        }
    }

    private boolean isCaptchaOn() {
        // 从缓存中获取验证码开关
        return Convert.toBool(guavaCache.configCache.get(ConfigKeyEnum.CAPTCHA.getValue()));
    }
}

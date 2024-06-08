package com.ach.admin.customize.securityService;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.ach.admin.common.cache.RedisCacheService;
import com.ach.common.constant.Constants;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.infrastructure.cache.redis.CacheKeyEnum;
import com.ach.infrastructure.user.web.SystemLoginUser;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author valarchie
 */
@Component
@Slf4j
@Data
@RequiredArgsConstructor
public class TokenService {
    private final RedisCacheService redisCache;
    /**
     * 用户信息token
     */
    @Value("${token.auth}")
    private String auth;
    /**
     * 防重令牌
     */
    @Value("${token.unrepeate}")
    private String unrepeate;
    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private String secret;
    /**
     * 自动刷新token的时间，当过期时间不足autoRefreshTime的值的时候，会触发刷新用户登录缓存的时间
     * 比如这个值是20,   用户是8点登录的， 8点半缓存会过期， 当过8.10分的时候，就少于20分钟了，便触发
     * 刷新登录用户的缓存时间
     */
    @Value("${token.autoRefreshTime}")
    private long autoRefreshTime;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SystemLoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getTokenFromRequest(request);//从请求头中获取token
        if (StrUtil.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);//解析token
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.Token.LOGIN_USER_KEY);//获取用户信息的后缀
                return redisCache.loginUserCache.getObjectOnlyInCacheById(uuid);//从缓存中获取用户信息
            } catch (SignatureException | MalformedJwtException | UnsupportedJwtException |
                     IllegalArgumentException jwtException) {
                log.error("parse token failed.", jwtException);
                throw new ApiException(jwtException, ErrorCode.Client.INVALID_TOKEN);//抛出异常,非法token
            } catch (Exception e) {
                log.error("fail to get cached user from redis", e);
                throw new ApiException(e, ErrorCode.Client.TOKEN_PROCESS_FAILED, e.getMessage());//无法从redis中获取用户信息
            }

        }
        return null;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createTokenAndPutUserInCache(SystemLoginUser loginUser) {
        loginUser.setCachedKey(IdUtil.fastUUID());//生成用户信息的后缀
        redisCache.loginUserCache.set(loginUser.getCachedKey(), loginUser);//将用户信息存入缓存
        return generateToken(MapUtil.of(Constants.Token.LOGIN_USER_KEY, loginUser.getCachedKey()));//将后缀返回给web
    }

    /**
     * 当超过20分钟，自动刷新token
     *
     * @param loginUser 登录用户
     */
    public void refreshToken(SystemLoginUser loginUser) {
        long currentTime = System.currentTimeMillis();
        if (currentTime > loginUser.getAutoRefreshCacheTime()) {
            loginUser.setAutoRefreshCacheTime(currentTime + TimeUnit.MINUTES.toMillis(autoRefreshTime));
            // 根据uuid将loginUser存入缓存
            redisCache.loginUserCache.set(loginUser.getCachedKey(), loginUser);
        }
    }


    /**
     * 从数据声明生成令牌,其实这里加密的不是用户信息而是获取用户信息的前缀
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    private String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(auth);
        if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.Token.PREFIX)) {
            token = StrUtil.stripIgnoreCase(token, Constants.Token.PREFIX, null);//去掉前缀Bearer
        }

        return token;
    }

    public String generateUnrepeatableToken() {
        String uuid = UUID.randomUUID().toString();
        String token = CacheKeyEnum.UNREPEATABLE_TOKEN_KEY + uuid;
        redisCache.unrepeatableTokenCache.set(token, "");
        return token;
    }

}

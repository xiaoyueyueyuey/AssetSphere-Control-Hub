package com.ach.common.exception;


import cn.hutool.core.util.StrUtil;
import com.ach.common.exception.error.ErrorCodeInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * 统一异常类
 *
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    protected ErrorCodeInterface errorCode;
    protected String message;
    protected HashMap<String, Object> payload;

    public ApiException(ErrorCodeInterface errorCode) {
        fillErrorCode(errorCode);
    }

    public ApiException(ErrorCodeInterface errorCode, Object... args) {
        fillErrorCode(errorCode, args);
    }

    /**
     * 注意  如果是try catch的情况下捕获异常 并转为ApiException的话  一定要填入Throwable e
     *
     * @param e         捕获到的原始异常
     * @param errorCode 错误码
     * @param args      错误详细信息参数
     */
    public ApiException(Throwable e, ErrorCodeInterface errorCode, Object... args) {
        super(e);
        fillErrorCode(errorCode, args);

    }

    private void fillErrorCode(ErrorCodeInterface errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = StrUtil.format(errorCode.message(), args);
//        try {
//            this.i18nMessage = MessageUtils.message(errorCode.i18nKey(), args);
//        } catch (Exception e) {
//            log.error("could not found i18nMessage entry for key: " + errorCode.i18nKey());
//        }
    }

    @Override
    public String getMessage() {
        return message;
    }
//    @Override
//    public String getLocalizedMessage() {
//        return i18nMessage != null ? i18nMessage : message;
//    }

}

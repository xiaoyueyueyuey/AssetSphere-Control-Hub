package com.ach.infrastructure.exception;


import com.ach.common.base.BaseResponseData;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.UncheckedExecutionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 *
 * 
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionInterceptor {

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public BaseResponseData<?> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("请求地址'{}',权限校验失败'{}'", request.getRequestURI(), e.getMessage());
        return BaseResponseData.fail(new ApiException(ErrorCode.Business.PERMISSION_NOT_ALLOWED_TO_OPERATE));
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponseData<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                   HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return BaseResponseData.fail(new ApiException(ErrorCode.Client.COMMON_REQUEST_METHOD_INVALID, e.getMethod()));
    }
    /**
     * 业务异常
     */
    @ExceptionHandler(ApiException.class)
    public BaseResponseData<?> handleServiceException(ApiException e) {
        log.error(e.getMessage(), e);
        return BaseResponseData.fail(e, e.getPayload());
    }

    /**
     * 捕获缓存类当中的错误
     */
    @ExceptionHandler(UncheckedExecutionException.class)
    public BaseResponseData<?> handleServiceException(UncheckedExecutionException e) {
        log.error(e.getMessage(), e);
        return BaseResponseData.fail(new ApiException(ErrorCode.Internal.GET_CACHE_FAILED, e.getMessage()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponseData<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return BaseResponseData.fail(new ApiException(ErrorCode.Internal.INTERNAL_ERROR, e.getMessage()));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public BaseResponseData<?> handleException(Exception e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return BaseResponseData.fail(new ApiException(ErrorCode.Internal.INTERNAL_ERROR, e.getMessage()));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public BaseResponseData<?> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return BaseResponseData.fail(new ApiException(ErrorCode.Client.COMMON_REQUEST_PARAMETERS_INVALID, message));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponseData<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResponseData.fail(new ApiException(ErrorCode.Client.COMMON_REQUEST_PARAMETERS_INVALID, message));
    }


}

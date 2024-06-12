package com.ach.common.exception.error;

/**
 *
 */
public interface ErrorCodeInterface {

    /**
     * 返回错误码
     *
     * @return 错误码
     */
    int code();
    /**
     * 返回具体的详细错误描述
     *
     * @return 错误描述
     */
    String message();

}

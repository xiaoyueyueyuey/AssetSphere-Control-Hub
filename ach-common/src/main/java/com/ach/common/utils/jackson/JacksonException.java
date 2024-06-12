package com.ach.common.utils.jackson;

/**
 * 
 */
public class JacksonException extends RuntimeException {

    public JacksonException(String message, Exception e) {
        super(message, e);
    }

}

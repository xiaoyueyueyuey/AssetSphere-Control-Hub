package com.ach.domain.system.userLogin;

public interface CaptchaService {
    void validateCaptcha(String username, String captchaCode, String captchaCodeKey);
}

package com.ach.infrastructure.annotations.ratelimit.implementation;


import com.ach.infrastructure.annotations.ratelimit.RateLimit;

/**
 * 
 */
public abstract class AbstractRateLimitChecker {

    /**
     * 检查是否超出限流
     *
     * @param rateLimiter RateLimit
     */
    public abstract void check(RateLimit rateLimiter);

}

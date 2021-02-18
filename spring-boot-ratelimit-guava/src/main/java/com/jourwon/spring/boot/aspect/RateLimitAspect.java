package com.jourwon.spring.boot.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.jourwon.spring.boot.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 限流切面
 *
 * @author JourWon
 * @date 2021/2/18
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {

    private static final ConcurrentMap<String, RateLimiter> RATE_LIMIT_CACHE = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.jourwon.spring.boot.annotation.RateLimit)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String key = method.getDeclaringClass().getName() + ":" + method.getName();
        System.out.println(key);
        // 通过 AnnotationUtils.findAnnotation 获取 RateLimit 注解,不可以通过method.getAnnotation(RateLimit.class);
        RateLimit rateLimit = AnnotationUtils.findAnnotation(method, RateLimit.class);
        if (rateLimit != null && rateLimit.qps() > RateLimit.NOT_LIMITED) {
            double qps = rateLimit.qps();
            if (RATE_LIMIT_CACHE.get(key) == null) {
                // 初始化 QPS
                RATE_LIMIT_CACHE.put(key, RateLimiter.create(qps));
            }

            log.debug("【{}】方法设置的QPS为:{}", key, RATE_LIMIT_CACHE.get(key).getRate());
            // 尝试获取令牌
            boolean flag = RATE_LIMIT_CACHE.get(key) != null && !RATE_LIMIT_CACHE.get(key).tryAcquire(rateLimit.timeout(), rateLimit.timeUnit());
            if (flag) {
                throw new RuntimeException("手速太快了，慢点儿吧~");
            }
        }
        return point.proceed();
    }

}

package com.w2m.w2m_technical_test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.w2m.w2m_technical_test.controller..*.*(..))")
    private void publicMethodsForLogging() {
    }

    @Around(value = "publicMethodsForLogging()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info(">> {}() - {}", methodName, Arrays.toString(args));
        Object result = joinPoint.proceed();
        logger.info("<< {}() - {}", methodName, result);
        return result;
    }

}

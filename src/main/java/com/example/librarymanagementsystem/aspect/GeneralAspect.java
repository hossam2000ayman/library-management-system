package com.example.librarymanagementsystem.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class GeneralAspect {


    @Pointcut("execution(* com.example.librarymanagementsystem.controller.*.*(..))")
    public void loggingOperation() {
    }

    @Before("loggingOperation()")
    public void beforeOperation(JoinPoint joinPoint) {
        log.info("Before method invoked: {}", joinPoint.getSignature());
        log.info("Method arguments: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggingOperation()", returning = "result")
    public void afterReturningOperation(JoinPoint joinPoint, Object result) {
        log.info("After Returning method invoked: {}", joinPoint.getSignature());
        log.info("Returned value: {}", result);
    }

    @AfterThrowing(pointcut = "loggingOperation()", throwing = "exception")
    public void afterThrowingOperation(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in method: {}", joinPoint.getSignature());
        log.error("Exception message: {}", exception.getMessage());
        log.error("Exception stack trace: ", exception);
    }

    @Around("loggingOperation()")
    public Object aroundOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("Method executed successfully: {}. Execution time: {} ms", joinPoint.getSignature(), elapsedTime);
            return result;
        } catch (Exception e) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("Exception in method: {}. Execution time: {} ms", joinPoint.getSignature(), elapsedTime);
            log.error("Exception message: {}", e.getMessage());
            log.error("Exception stack trace: ", e);
            throw e;
        }
    }
}

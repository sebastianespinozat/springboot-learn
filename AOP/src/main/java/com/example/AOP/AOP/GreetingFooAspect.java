package com.example.AOP.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());



    @Before("GreetingServicePountcuts.greetingFooLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes primero: {} invocado con los parametros {}", method, args);
    }


    @After("GreetingServicePountcuts.greetingFooLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues: {} invocado con los parametros  {}", method, args);
    }
}

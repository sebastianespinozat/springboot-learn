package com.example.AOP.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePountcuts {

    @Pointcut("execution(* com.example.AOP.services.GreetingService.*(..))")
    public void greetingLoggerPointCut(){}

    @Pointcut("execution(* com.example.AOP.services.GreetingService.*(..))")
    public void greetingFooLoggerPointCut(){}

}

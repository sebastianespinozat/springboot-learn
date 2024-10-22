package com.example.AOP.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    @Before("GreetingServicePountcuts.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: {} con los argumentos {}", method, args);
    }

    @After("GreetingServicePountcuts.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues: {} con los argumentos {}", method, args);
    }

    @AfterReturning("GreetingServicePountcuts.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de retornar: {} con los argumentos {}", method, args);
    }

    @AfterThrowing("GreetingServicePountcuts.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues de lanzar excepcion: {} con los argumentos {}", method, args);
    }

    @Around("GreetingServicePountcuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try{

            logger.info("El metodo{}() con los parametros {}", method, args);
            result = joinPoint.proceed();
            logger.info("El metodo{}() retorna el resultado {}", method, result);
            return result;
        } catch (Throwable e) {
            logger.error("El error de la llamada del metodo {}()", method);
            throw e;
        }

    }
}

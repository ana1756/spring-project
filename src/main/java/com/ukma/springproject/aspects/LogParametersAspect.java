package com.ukma.springproject.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogParametersAspect {

    @Around("@annotation(LogParameters)")
    public Object logParameters(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        String obj = proceed==null?"null":proceed.toString();

        String args = "";
        for(Object o : joinPoint.getArgs()) {args+=" " + o.toString() + " ";}
        log.info(joinPoint.getSignature() + " parameters: " + args + ", returns " + obj);
        return proceed;
    }

}

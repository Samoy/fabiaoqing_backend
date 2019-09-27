package com.samoy.fabiaoqing.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * ExceptionAspect
 *
 * @author Samoy
 * @date 2019/9/27
 */
@Component
@Aspect
@Slf4j
public class ExceptionAspect {
    @Pointcut("within(com.samoy.fabiaoqing.expection.GlobalExceptionHandler)")
    public void exceptionAspect() {

    }

    @Around(value = "exceptionAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        Exception exception = (Exception) joinPoint.getArgs()[1];
        log.error("在请求:{}时发生了异常:{}", request.getRequestURI(), exception.getLocalizedMessage());
        return joinPoint.proceed();
    }
}

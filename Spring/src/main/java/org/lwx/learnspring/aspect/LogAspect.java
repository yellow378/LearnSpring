package org.lwx.learnspring.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(org.lwx.learnspring.annotations.Loggable)")
    public void loggablePointcut() {
    }

    @Before("loggablePointcut()")
    public void beforeLoggable(JoinPoint joinPoint) {
        System.out.println("before method");
        System.out.println(joinPoint.getSignature());
    }
    @After("loggablePointcut()")
    public void afterLoggable(JoinPoint joinPoint) {
        System.out.println("after method");
        System.out.println(joinPoint.getSignature());
    }

}

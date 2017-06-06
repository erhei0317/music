package test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PersonAspect {

	@Pointcut("execution(* test.*.*(..))")  
    private void aspectjMethod(){};  
    
    @Before("aspectjMethod()")    
    public void beforeAdvice(JoinPoint joinPoint) {    
        System.out.println("wash hand");
    }  
}

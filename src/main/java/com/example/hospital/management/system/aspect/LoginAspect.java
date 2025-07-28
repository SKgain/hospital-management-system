package com.example.hospital.management.system.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    @Before("execution(* com.example.hospital.management.system.controller..*.*(..))")
    public void logging()
    {
        System.out.println("Logging aspect called");
    }

}

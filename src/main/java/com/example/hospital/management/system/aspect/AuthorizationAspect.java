package com.example.hospital.management.system.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Before("execution(* com.example.hospital.management.system.service..*.*(..))")
    public void Authorization()
    {
        System.out.println("Authorization aspect called");
    }
}

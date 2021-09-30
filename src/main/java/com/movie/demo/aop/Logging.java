package com.movie.demo.aop;

import com.movie.demo.data.UserProfile;
import com.movie.demo.security.data.AuthenticationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logging {

    private final Logger logger = LoggerFactory.getLogger(Logging.class);

    @Pointcut("within(com.movie.demo.controller.LoginController)")
    public void allLogLogin() {
    }

    @Pointcut("within(com.movie.demo.controller.SignUpController)")
    public void allLogSign() {
    }

    @AfterReturning("allLogLogin() ")
    public void AfterLoginAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] obj=joinPoint.getArgs();
        logger.info(((AuthenticationRequest) obj[0]).getName()+" loged in successfully");
    }

    @AfterThrowing("allLogLogin()")
    public void AfterLoginError() {
        logger.error("Name or password wrong!");
    }

    @AfterReturning("allLogSign()")
    public void AfterSignAdvice(JoinPoint joinPoint) {
        Object []obj=joinPoint.getArgs();
        logger.info(((UserProfile)obj[0]).getUserName()+" signed up successfully");
    }

    @AfterThrowing("allLogSign()")
    public void AfterSignError() {
        logger.error("Didn't sign up");
    }

}

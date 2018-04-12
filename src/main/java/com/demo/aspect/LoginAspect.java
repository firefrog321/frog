package com.demo.aspect;

import com.demo.domain.AccessLog;
import com.demo.domain.User;
import com.demo.service.AccessLogService;
import com.demo.utils.LoggerUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登陆管理
 * @author Mr.Deng
 * Created on 2018/4/12 17:08
 **/
@Aspect
@Controller
public class LoginAspect {

    @Pointcut("execution(* com.demo.controller.ArticleController.articleEditor(..))")
    public void point() {
    }


    @Around("point()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        if(user == null ) {
            return "login";
        }

        return proceedingJoinPoint.proceed();

    }
}

package com.demo.aspect;

import com.demo.domain.AccessLog;
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

/**
 * 访问统计，日志
 *
 * @author Mr.Deng
 * Created on 2018/3/28 14:41
 **/
@Aspect
@Controller
public class AccessAspect {

    @Autowired
    AccessLogService accessLogService;
    /**
     * 定义一个切入点.
     * 解释下：
     * ("execution(public * org.lvgang.controller..*.*(..))")
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.demo.controller.IndexController.index(..))")
    public void point() {
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换
     * 返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("point()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        AccessLog accessLog=new AccessLog();

        //客户端ip
        accessLog.setIp(LoggerUtils.getCliectIp(request));

        //请求的类及名称
        accessLog.setAccessclass( proceedingJoinPoint.getSignature().getDeclaringTypeName());
        accessLog.setAccessmethod(proceedingJoinPoint.getSignature().getName());

        accessLogService.save(accessLog);

        //obj之前可以写目标方法执行前的逻辑
        Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
        return obj;

    }
}

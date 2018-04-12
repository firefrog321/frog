package com.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * 统一处理程序的异常
 * @author Mr.Deng
 * Created on 2018/4/12 17:38
 **/
@ControllerAdvice
public class ExceptionController extends Throwable {
    /**
     * 捕捉异常，返回到错误页面
     * ExceptionHandler 表示捕捉到所有的异常，你也可以捕捉一个你自定义的异常
     * @param exception 捕获到的异常
     * @return 错误页面
     */
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exception(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("cause", exception.getClass());
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.addObject("stackTrace", Arrays.toString(exception.getStackTrace()));
        return modelAndView;
    }

}

package com.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LogController {


    private String name;
    public String pw;

    @RequestMapping("writelog")
    public Object writeLog()
    {

        return "OK";
    }
}
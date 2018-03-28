package com.demo.service.impl;

import com.demo.dao.AccessLogDao;
import com.demo.domain.AccessLog;
import com.demo.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 访问日志记录
 *
 * @author Mr.Deng
 * Created on 2018/3/28 15:17
 **/
@Service("accessLogService")
public class AccessLogServiceImpl implements AccessLogService {
    //线程
    ThreadLocal<AccessLog> threadLocal = new ThreadLocal<AccessLog>();

    @Autowired
    AccessLogDao accessLogDao;

    /**
     * 保存日志
     * Created on 2018/3/28 10:00
     **/
    @Override
    public AccessLog save(AccessLog accessLog) {
        //存入当前日期
        accessLog.setAccesstime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //根据IP查找记录
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List list=accessLogDao.queryCount(accessLog.getIp(),time);
        System.out.print(list.size());

        if(list.isEmpty()){
            accessLogDao.save(accessLog);
        }


        return null;
    }
}

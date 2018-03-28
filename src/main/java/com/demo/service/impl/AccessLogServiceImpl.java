package com.demo.service.impl;

import com.demo.dao.AccessLogDao;
import com.demo.domain.AccessLog;
import com.demo.service.AccessLogService;
import com.demo.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 访问日志记录
 *
 * @author Mr.Deng
 * Created on 2018/3/28 15:17
 **/
@Service("accessLogService")
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    AccessLogDao accessLogDao;

    /**
     * 保存日志
     * Created on 2018/3/28 10:00
     **/
    @Override
    public AccessLog save(AccessLog accessLog) {
        String ip = accessLog.getIp();
        //存入当前日期
        accessLog.setAccesstime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        accessLog.setAddress(LoggerUtils.getAddressByIP(ip));

        //根据IP查找记录
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int count = accessLogDao.queryCount(ip, time);
        //防止写入过多
        if (count <20) {
            accessLogDao.save(accessLog);
        }
        return null;
    }
}

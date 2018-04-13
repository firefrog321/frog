package com.demo.service;

import com.demo.domain.AccessLog;

/**
 * 访问日志记录
 *
 * @author Mr.Deng
 * Created on 2018/3/28 15:17
 **/
public interface AccessLogService {

    /**
     * 保存日志
     * 当天第一次访问，插入新纪录
     * 多次访问，更新访问次数
     * @param accessLog accessLog
     * @return null
     */
    AccessLog save(AccessLog accessLog);
}

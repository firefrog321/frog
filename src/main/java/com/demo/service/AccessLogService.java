package com.demo.service;

import com.demo.domain.AccessLog;

/**
 * 访问日志记录
 *
 * @author Mr.Deng
 * Created on 2018/3/28 15:17
 **/
public interface AccessLogService {
    AccessLog save(AccessLog accessLog);
}

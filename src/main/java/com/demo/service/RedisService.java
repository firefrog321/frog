package com.demo.service;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Deng
 * Created on 2018/3/30 16:48
 **/
public interface RedisService {
     void set(String key, Object value,int timeout,TimeUnit unit);

     Object get(String key);

     boolean haskey(String key);

}

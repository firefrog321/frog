package com.demo.service.impl;

import com.demo.service.RedisService;
import com.demo.utils.ConstantsUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Deng
 * Created on 2018/3/30 16:52
 **/
@Service
public class RedisServiceImpl  implements RedisService{
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 存入缓存
     * Created on 2018/3/30 20:27
     **/
    public void set(String key, Object value,int timeout,TimeUnit unit) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value, timeout, unit);
    }
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
    public boolean haskey(String key){
        return redisTemplate.hasKey(key);
    }
}

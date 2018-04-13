package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis共享session的配置
 * EnableRedisHttpSession设置Session失效时间，使用Redis Session之后，
 *      原Boot的server.session.timeout属性不再生效
 * @author Deng
 * Created on 2018/4/13 15:36
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1801)
public class SessionConfig {
}


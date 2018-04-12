package com.demo.service;

import com.demo.domain.AccessLog;
import com.demo.domain.User;

/**
 * 登陆
 * @author Mr.Deng
 * Created on 2018/4/12 17:20
 **/
public interface LoginService {

    /**
     * 验证用户是否存在
     * @param user 用户
     * @return boolean
     */
    boolean loginValidate(User user);
}

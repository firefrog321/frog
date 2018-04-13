package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import com.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * 登陆
 *
 * @author Mr.Deng
 * Created on 2018/4/12 17:20
 **/
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean loginValidate(User user) {
        return userDao.loginValidate(user) != 0;
    }
}

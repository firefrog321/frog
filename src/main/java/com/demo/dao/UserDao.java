package com.demo.dao;

import com.demo.domain.User;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Deng
 * Created on 2018/3/26 15:07
 **/
@Repository
public interface UserDao {

    /**
     * 登陆验证
     * @param user user
     * @return user在数据库的数量
     */
    int loginValidate(User user);
}

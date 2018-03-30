package com.demo.dao;

import com.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Mr.Deng
 * Created on 2018/3/26 15:07
 **/
public interface UserDao extends JpaRepository<User, Integer>{

    List<User> findAll();

    User findByUsername(String username);
}

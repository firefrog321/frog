package com.demo.dao;

import com.demo.domain.AccessLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogDao {


   /* @Select("select count(id) from accesslog where ip=#{ip} and substring(access_time,1,10)=#{accesstime} ")
    int queryCount(@Param("ip") String ip, @Param("accesstime")String accesstime);*/
    /**
     * 根据IP查找记录
     * @param accessLog accessLog
     * @return 存在的数量
     **/
    int queryCount(AccessLog accessLog);
    /**
     * insert
     * @param accessLog 实体
     **/
    void insert(AccessLog accessLog);

    /**
     *
     * @param accessLog 实体
     **/
    void updateAccessQuantity(AccessLog accessLog);
}

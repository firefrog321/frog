package com.demo.dao;

import com.demo.domain.AccessLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogDao {

    /**
     * 根据IP查找记录
     **/
    @Select("select count(id) from AccessLog where ip=#{ip} and substring(access_time,1,10)=#{accesstime} ")
    int queryCount(@Param("ip") String ip, @Param("accesstime")String accesstime);

    /**
     * insert
     * @param accessLog 实体
     **/
    void insert(AccessLog accessLog);
}

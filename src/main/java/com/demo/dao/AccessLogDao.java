package com.demo.dao;

import com.demo.domain.AccessLog;
import com.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 访问日志记录
 *
 * @author Mr.Deng
 * Created on 2018/3/28 15:20
 **/
public interface AccessLogDao extends JpaRepository<AccessLog, Integer>, JpaSpecificationExecutor<AccessLog> {
    /**
     * 根据IP查找记录
     * Created on 2018/3/28 15:37
     **/
   /* @Query("select count(id) from accesslog where ip=?1 and substring(access_time,1,10)=?2 ")
    Integer queryCount(@Param("ip") String ip, @Param("access_time") String access_time);*/
    @Query("select ip from accesslog where ip=?1 and substring(access_time,1,10)=?2 ")
    List<AccessLog> queryCount( String ip, String accesstime);
}

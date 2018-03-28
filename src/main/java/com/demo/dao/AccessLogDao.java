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
     * <p>
     * select 语句中表名要和实体类名字一样，区分大小写，大小写
     **/
    @Query("select count(id) from AccessLog where ip=?1 and substring(access_time,1,10)=?2 ")
    int queryCount(String ip,String accesstime);
}

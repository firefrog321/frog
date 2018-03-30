package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 访问日志实体类
 *
 * Created on 2018/3/28 15:00
 **/
@Entity
@Table(name = "accesslog", indexes = {@Index(name = "accesslog_ip", columnList = "ip", unique = false)})
public class AccessLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "int(100) COMMENT'ID'")
    private int id;

    private String ip;

    @Column(name = "access_time")
    private String accesstime;

    @Column(name = "access_class")
    private String accessclass;

    @Column(name = "access_method")
    private String accessmethod;

    @Column(name = "address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }

    public String getAccessclass() {
        return accessclass;
    }

    public void setAccessclass(String accessclass) {
        this.accessclass = accessclass;
    }

    public String getAccessmethod() {
        return accessmethod;
    }

    public void setAccessmethod(String accessmethod) {
        this.accessmethod = accessmethod;
    }
}

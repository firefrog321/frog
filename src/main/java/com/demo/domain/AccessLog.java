package com.demo.domain;

import java.io.Serializable;

public class AccessLog implements Serializable {
    private Integer id;

    private String accessClass;

    private String accessMethod;

    private String accessTime;

    private String address;

    private String ip;

    private int accessQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessClass() {
        return accessClass;
    }

    public void setAccessClass(String accessClass) {
        this.accessClass = accessClass;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAccessQuantity() {
        return accessQuantity;
    }

    public void setAccessQuantity(int accessQuantity) {
        this.accessQuantity = accessQuantity;
    }
}
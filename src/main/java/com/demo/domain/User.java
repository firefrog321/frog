package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Mr.Deng
 * Created on 2018/3/26 14:48
 **/

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String note;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

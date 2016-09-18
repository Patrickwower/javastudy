package com.dengyuecang.www.entity.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by acang on 16/9/6.
 */
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    private String id;

    private String username;

    private String pwd;

    private String nickname;

    private String token;

    @GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "id", unique = true, nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

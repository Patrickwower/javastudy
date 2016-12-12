package com.dengyuecang.www.entity.testapp;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by lxrent on 2016/11/24.
 */

@Entity
@Table(name="username")
public class WXLogin implements Serializable {

    private String id;

    private String name;

    private String sex;

    private String time;

    private String telephone;

    private String wxnum;

    public WXLogin(){

    }

    public WXLogin(String id,String name,String sex,String time,String telephone,String wxnum){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.time = time;
        this.telephone = telephone;
        this.wxnum = wxnum;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWxnum() {
        return wxnum;
    }

    public void setWxnum(String wxnum) {
        this.wxnum = wxnum;
    }

}

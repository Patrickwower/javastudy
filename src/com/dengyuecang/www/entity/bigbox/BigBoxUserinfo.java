package com.dengyuecang.www.entity.bigbox;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 2016/11/9.
 */
@Entity
@Table(name = "bigbox_userinfo")
public class BigBoxUserinfo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String location;

    private String mobile;

    private String imgurl;

    private String role;

    private String description;

    private String number_1;

    private String number_2;

    private String action;

    private Date create_date;

    public BigBoxUserinfo() {
    }

    public BigBoxUserinfo(String id, String name, String mobile, String role, String imgurl, String description) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.role = role;
        this.imgurl = imgurl;
        this.description = description;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber_1() {
        return number_1;
    }

    public void setNumber_1(String number_1) {
        this.number_1 = number_1;
    }

    public String getNumber_2() {
        return number_2;
    }

    public void setNumber_2(String number_2) {
        this.number_2 = number_2;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}

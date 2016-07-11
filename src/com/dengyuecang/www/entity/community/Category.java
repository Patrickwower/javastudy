package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_category")
public class Category {

    private String id;

    private String pid;

    private String name;

    private String level;

    private String creater;

    private Date createTime;

    private String sort;

    public Category() {
    }

    public Category(String id, String pid, String name, String level, String creater, Date createTime, String sort) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.level = level;
        this.creater = creater;
        this.createTime = createTime;
        this.sort = sort;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

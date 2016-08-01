package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_category")
public class Category implements Serializable {

    private String id;

    private String name;

    private String creater;

    private Date ctime;

    private String pid;

    public Category() {
    }

    @GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "id", unique = true, nullable = false, length = 100)
    @OrderBy("ctime desc")
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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public List<Article> articles;

    @ManyToMany(mappedBy="categories",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by acang on 16/7/7.
 */
@Entity
@Table(name="community_tag")
public class Tag implements Serializable {

    private String id;

    private String name;

//    private Tag parent;

    private String creater;

    private String status;

    private Date ctime;

    public Tag() {
    }

    public Tag(String id, String name, String creater, String status, Date ctime) {
        this.id = id;
        this.name = name;
        this.creater = creater;
        this.status = status;
        this.ctime = ctime;
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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "pid")
//    public Tag getParent() {
//        return parent;
//    }
//
//    public void setParent(Tag parent) {
//        this.parent = parent;
//    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

//    public List<Tag> children;

//    @OneToMany(targetEntity=Tag.class, mappedBy="parent", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
//    public List<Tag> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Tag> children) {
//        this.children = children;
//    }

    public Set<Article> articles;

    @ManyToMany(mappedBy="tags",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }


}

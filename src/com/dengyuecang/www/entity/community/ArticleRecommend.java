package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_article_recommend")
public class ArticleRecommend implements Serializable {

    private String id;

    private Article article;

    private String status;

    private Date ctime;

    private String sort;

    private String maxsort;

    private long timestamp;

    //banner中是什么类型,是文章,链接,还是专题
    private String type;

    private String url;

    private String subject_id;

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

    @OneToOne
    @JoinColumn(name="article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getMaxsort() {
        return maxsort;
    }

    public void setMaxsort(String maxsort) {
        this.maxsort = maxsort;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
}

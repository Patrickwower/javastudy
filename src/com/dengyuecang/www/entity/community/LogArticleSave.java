package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_log_article_save")
public class LogArticleSave {

    private String id;

    private Article article;

    private String content;

    private Date ctime;

    public LogArticleSave() {
    }

    public LogArticleSave(String id, Article article, String content, Date ctime) {
        this.id = id;
        this.article = article;
        this.content = content;
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

    @OneToOne
    @JoinColumn(name="article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}

package com.dengyuecang.www.entity.community;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_article_collection")
public class ArticleCollection implements Serializable {

    private String id;

    private Article article;

    private Member discussant;

    private Date ctime;

    private long timestamp;

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

    @OneToOne
    @JoinColumn(name="discussant")
    public Member getDiscussant() {
        return discussant;
    }

    public void setDiscussant(Member discussant) {
        this.discussant = discussant;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

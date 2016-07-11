package com.dengyuecang.www.entity.community;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by acang on 16/7/5.
 */
@Entity
@Table(name="community_article_evaluatef")
public class ArticleEvaluate {

    private String id;

    private String evaluate;

    private Article article;

    private Member member;

    private Date ctime;

    public ArticleEvaluate() {
    }

    public ArticleEvaluate(String id, String evaluate, Article article, Member member, Date ctime) {
        this.id = id;
        this.evaluate = evaluate;
        this.article = article;
        this.member = member;
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

    @OneToOne
    @JoinColumn(name="member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}

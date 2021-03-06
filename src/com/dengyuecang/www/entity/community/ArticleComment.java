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
@Table(name="community_article_comment")
public class ArticleComment implements Serializable {

    private String id;

    private Article article;

    private String comment;

    private Member discussant;

    private Date ctime;

    private long timestamp;

    private ArticleComment articleComment;

    private ArticleComment fatherComment;

    private String atMember;

    private String status;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @OneToOne
    @JoinColumn(name="comment_id")
    public ArticleComment getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(ArticleComment articleComment) {
        this.articleComment = articleComment;
    }

    @OneToOne
    @JoinColumn(name = "father_id")
    public ArticleComment getFatherComment() {
        return fatherComment;
    }

    public void setFatherComment(ArticleComment fatherComment) {
        this.fatherComment = fatherComment;
    }

    @Column(name = "at_member")
    public String getAtMember() {
        return atMember;
    }

    public void setAtMember(String atMember) {
        this.atMember = atMember;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

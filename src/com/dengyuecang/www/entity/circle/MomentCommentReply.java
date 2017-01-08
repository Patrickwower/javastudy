package com.dengyuecang.www.entity.circle;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 2017/1/9.
 */
@Entity
@Table(name = "dyc_circle_moment_comment_reply")
public class MomentCommentReply implements Serializable{

    private String id;

    private MomentComment comment;

    private String content;

    private Member discussant;

    private Date ctime;

    private long timestamp;

    private int sort;

    private Member at;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    public MomentComment getComment() {
        return comment;
    }

    public void setComment(MomentComment comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToOne
    @JoinColumn(name = "discussant")
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @OneToOne
    @JoinColumn(name = "at")
    public Member getAt() {
        return at;
    }

    public void setAt(Member at) {
        this.at = at;
    }
}

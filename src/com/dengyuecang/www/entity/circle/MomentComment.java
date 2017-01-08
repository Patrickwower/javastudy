package com.dengyuecang.www.entity.circle;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by acang on 2017/1/8.
 */
@Entity
@Table(name = "dyc_circle_moment_comment")
public class MomentComment implements Serializable{

    private String id;

    private Moment moment;

    private String content;

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
    @JoinColumn(name = "moment_id")
    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
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

    private List<MomentCommentReply> replys = new ArrayList<MomentCommentReply>();

    @OneToMany(mappedBy = "comment")
    @OrderBy("sort")
    public List<MomentCommentReply> getReplys() {
        return replys;
    }

    public void setReplys(List<MomentCommentReply> replys) {
        this.replys = replys;
    }
}

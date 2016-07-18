package com.dengyuecang.www.entity.community;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 16/7/12.
 */

@Entity
@Table(name="community_topic_comment")
public class TopicComment implements Serializable{

    private String id;

    private Topic topic;

    private String comment;

    private Member discussant;

    private Date ctime;

    private long timestamp;

    public TopicComment() {
    }

    public TopicComment(String id, Topic topic, String comment, Member discussant) {
        this.id = id;
        this.topic = topic;
        this.comment = comment;
        this.discussant = discussant;
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
    @JoinColumn(name="topic_id")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
}

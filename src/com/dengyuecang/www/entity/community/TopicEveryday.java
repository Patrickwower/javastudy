package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by acang on 16/7/6.
 */
@Entity
@Table(name="community_topic_everyday")
public class TopicEveryday implements Serializable {

    private String id;

    private Topic topic;

    private Date issueDate;

    public TopicEveryday() {
    }

    public TopicEveryday(String id, Topic topic, Date issueDate) {
        this.id = id;
        this.topic = topic;
        this.issueDate = issueDate;
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

    @Column(name="issue_date")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

}

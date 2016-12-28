package com.dengyuecang.www.entity.circle;

import com.dengyuecang.www.entity.Member;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by acang on 2016/12/12.
 */
@Entity
@Table(name="dyc_circle_moment")
public class Moment implements Serializable{

    private String id;

    private String content;

    private InterestBar interestBar;

    private List<MomentImage> imageList;

    //公开级别:既哪些人能看到 0是所有人可见, 1是自己可见
    private String public_level;

    private long timestamp;

    private Date ctime;

    private Member creater;

    //状态:100是刚发布都能看,200为删除
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToOne
    @JoinColumn(name = "interest_bar_id")
    public InterestBar getInterestBar() {
        return interestBar;
    }

    public void setInterestBar(InterestBar interestBar) {
        this.interestBar = interestBar;
    }

    @OneToMany(mappedBy = "moment")
    public List<MomentImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<MomentImage> imageList) {
        this.imageList = imageList;
    }

    public String getPublic_level() {
        return public_level;
    }

    public void setPublic_level(String public_level) {
        this.public_level = public_level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @OneToOne
    @JoinColumn(name = "member_id")
    public Member getCreater() {
        return creater;
    }

    public void setCreater(Member creater) {
        this.creater = creater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

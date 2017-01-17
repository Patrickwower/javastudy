package com.dengyuecang.www.entity.circle;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by acang on 2016/12/12.
 */
@Entity
@Table(name = "dyc_circle_interest_bar")
public class InterestBar implements Serializable{

    private String id;

    private String name;

    private String detail;

    private String creater;

    private Date ctime;

    private long timestamp;

    private Set<InterestType> types;

    private String img_url;

    private String cover;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
    @JoinTable(name="dyc_circle_interest_bar_type",joinColumns={@JoinColumn(name="bar_id")},inverseJoinColumns={@JoinColumn(name="type_id")})
    @OrderBy("id")
    public Set<InterestType> getTypes() {
        return types;
    }

    public void setTypes(Set<InterestType> types) {
        this.types = types;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.dengyuecang.www.entity.circle;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

/**
 * Created by acang on 2016/12/12.
 */
@Entity
@Table(name = "dyc_circle_moment_image")
public class MomentImage {

    private String id;

    private String source_url_path;

    private String thumbnail_url_path;

    private String sort;

    private Moment moment;

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

    public String getSource_url_path() {
        return source_url_path;
    }

    public void setSource_url_path(String source_url_path) {
        this.source_url_path = source_url_path;
    }

    public String getThumbnail_url_path() {
        return thumbnail_url_path;
    }

    public void setThumbnail_url_path(String thumbnail_url_path) {
        this.thumbnail_url_path = thumbnail_url_path;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "moment_id")
    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }
}

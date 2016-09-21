package com.dengyuecang.www.entity.community;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 首页的文章
 * Created by acang on 16/9/9.
 */
@Entity
@Table(name = "community_article_index")
public class ArticleIndex implements Serializable{

    private String id;

    private Article article;

    private Date index_time;

    private long timestamp;

    private int sort;

    private String max_sort;

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
    @JoinColumn(name = "article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getIndex_time() {
        return index_time;
    }

    public void setIndex_time(Date index_time) {
        this.index_time = index_time;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getMax_sort() {
        return max_sort;
    }

    public void setMax_sort(String max_sort) {
        this.max_sort = max_sort;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

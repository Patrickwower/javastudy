package com.dengyuecang.www.entity.circle;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by acang on 2016/12/12.
 */
@Entity
@Table(name = "dyc_circle_interest_type")
public class InterestType implements Serializable{

    private String id;

    private String name;

    private String creater;

    private String parent_id;

    private Set<InterestBar> interestBars;

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

//    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
//    @JoinTable(name="dyc_circle_interest_bar_type",joinColumns={@JoinColumn(name="type_id")},inverseJoinColumns={@JoinColumn(name="bar_id")})
//    @OrderBy("id")
//    public Set<InterestBar> getInterestBars() {
//        return interestBars;
//    }
//
//    public void setInterestBars(Set<InterestBar> interestBars) {
//        this.interestBars = interestBars;
//    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}

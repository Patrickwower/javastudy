package com.dengyuecang.www.entity.circle;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by acang on 2016/12/12.
 */
@Entity
@Table(name = "dyc_circle_interest_bar")
public class InterestBar implements Serializable{

    private String id;

    private String name;

    private String detail;

//    private List<InterestType> types;

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

//    public List<InterestType> getTypes() {
//        return types;
//    }
//
//    public void setTypes(List<InterestType> types) {
//        this.types = types;
//    }
}

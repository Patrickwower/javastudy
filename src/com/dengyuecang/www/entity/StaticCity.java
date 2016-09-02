package com.dengyuecang.www.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acang on 16/9/1.
 */
@Entity
@Table(name = "sys_city")
public class StaticCity implements Serializable{

    private String code;

    private String name;

    private String provincecode;

//    private List<StaticArea> areas = new ArrayList<StaticArea>();

    @GenericGenerator(name = "generator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "code", unique = true, nullable = false, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

//    @OneToMany(mappedBy = "citycode",fetch = FetchType.EAGER)
//    @Transient
//    public List<StaticArea> getAreas() {
//        return areas;
//    }
//
//    public void setAreas(List<StaticArea> areas) {
//        this.areas = areas;
//    }
}

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
@Table(name = "sys_province")
public class StaticProvince implements Serializable {

    private String code;

    private String name;

    private List<StaticCity>  citys = new ArrayList<StaticCity>();

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

    @OneToMany(mappedBy = "provincecode", targetEntity = StaticCity.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<StaticCity> getCitys() {
        return citys;
    }

    public void setCitys(List<StaticCity> citys) {
        this.citys = citys;
    }
}

package com.dengyuecang.www.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dyc_identity")
public class Identity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String createType;
	private String creater;
	private Date createTime;
	private String status;
	private String version;
	
	
	
	public Identity() {
		super();
	}



	public Identity(String id, String name, String createType, String creater,
			Date createTime, String status, String version) {
		super();
		this.id = id;
		this.name = name;
		this.createType = createType;
		this.creater = creater;
		this.createTime = createTime;
		this.status = status;
		this.version = version;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCreateType() {
		return createType;
	}



	public void setCreateType(String createType) {
		this.createType = createType;
	}



	public String getCreater() {
		return creater;
	}



	public void setCreater(String creater) {
		this.creater = creater;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}

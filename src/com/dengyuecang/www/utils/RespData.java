package com.dengyuecang.www.utils;

import java.io.Serializable;
import java.util.HashMap;


public class RespData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String respCode="1";
	private String respDesc="操作成功！";
	private Object data = new HashMap<Object,Object>();
	
	private Object rows;
	private Long total;
	
	
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
	
	
	
}

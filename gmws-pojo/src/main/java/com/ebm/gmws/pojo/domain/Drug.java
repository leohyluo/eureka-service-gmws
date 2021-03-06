package com.ebm.gmws.pojo.domain;

import java.util.Date;

public class Drug {

	private String drugId;
	private String drugName;
	private Date createTime;
	
	public Drug() {
		
	}
	
	public Drug(String drugId) {
		this.drugId = drugId;
	}
	
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

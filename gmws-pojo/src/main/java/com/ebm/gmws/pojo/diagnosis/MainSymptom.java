package com.ebm.gmws.pojo.diagnosis;

public class MainSymptom  {
	
	private Long id;
	
	private String name;
	
	private Integer requiredNum;
	
	private Integer totalQuestion;
	
	private Long order_;
	
	private Integer sex;
	
	private String symbol;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRequiredNum() {
		return requiredNum;
	}
	public void setRequiredNum(Integer requiredNum) {
		this.requiredNum = requiredNum;
	}
	public Integer getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public Long getOrder_() {
		return order_;
	}
	public void setOrder_(Long order_) {
		this.order_ = order_;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}

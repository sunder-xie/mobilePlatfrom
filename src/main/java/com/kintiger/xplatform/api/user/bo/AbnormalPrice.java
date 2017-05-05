package com.kintiger.xplatform.api.user.bo;

import java.util.Date;

public class AbnormalPrice {

	private Long prId;
	private String custId;
	private String operatorId;
    private String skuId;
    private String price;
    private String dayType;
    private String modifyDate;
    private String userId;
    private String unit;
    private String  status;
    private String  promotionalprice;
    private String  standardprice;
    private String cloudId;
	public Long getPrId() {
		return prId;
	}
	public void setPrId(Long prId) {
		this.prId = prId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPromotionalprice() {
		return promotionalprice;
	}
	public void setPromotionalprice(String promotionalprice) {
		this.promotionalprice = promotionalprice;
	}
	public String getStandardprice() {
		return standardprice;
	}
	public void setStandardprice(String standardprice) {
		this.standardprice = standardprice;
	}
	public String getCloudId() {
		return cloudId;
	}
	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}
	
}

package com.kintiger.xplatform.api.user.bo;

import java.util.Date;

public class OrderDetail {

    private String modifyDate;
    
    private String plannedDeliveryDate;
    private String realDeliveryDate;
    private String  lastModifyUser ;
    private String remark;
    private Long odId;
    private String orderDetailId;
    private Double quantity;
    private String unitCode;
    private String unitDesc;
    private Double totalPrice;
    private String priceUnitCode;
    private String priceUnitDesc;
    private Double price;  //单价
    private String orderDetailStatus;
    private String type;// a 增加 d 删除   u 修改
    private String orderType;
    private String modifyUserId;
    private String skuId;
    private String orderId;
    private String categoryDesc;
    private String dateType;
    private String id;
    private String mappingSKUId;//关联行项目   skuId&unitCode

    
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(String plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}
	public String getRealDeliveryDate() {
		return realDeliveryDate;
	}
	public void setRealDeliveryDate(String realDeliveryDate) {
		this.realDeliveryDate = realDeliveryDate;
	}
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public String getPriceUnitCode() {
		return priceUnitCode;
	}
	public void setPriceUnitCode(String priceUnitCode) {
		this.priceUnitCode = priceUnitCode;
	}
	public String getPriceUnitDesc() {
		return priceUnitDesc;
	}
	public void setPriceUnitDesc(String priceUnitDesc) {
		this.priceUnitDesc = priceUnitDesc;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getOrderDetailStatus() {
		return orderDetailStatus;
	}
	public void setOrderDetailStatus(String orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getOdId() {
		return odId;
	}
	public void setOdId(Long odId) {
		this.odId = odId;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMappingSKUId() {
		return mappingSKUId;
	}
	public void setMappingSKUId(String mappingSKUId) {
		this.mappingSKUId = mappingSKUId;
	}
	
}

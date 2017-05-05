package com.kintiger.xplatform.api.user.bo;

import java.io.Serializable;



public class BaseProduct implements Serializable{
	private static final long serialVersionUID = 1L;

	protected String categoryId;
	protected String categoryDesc;
	protected String categorySortId;
	protected String categorySortDesc;
	protected String brandsId;
	protected String brandsDesc;
	protected String empId;
	protected String custId;
	protected String time;
	protected String productType; 
	protected String skuOrder;
	protected String companyName;
	protected String skuUnit;
	protected String skuUnitId;
	protected String cloudId;
	protected String lastPrice;  //下单人-经销商-门店 -sku 最后一次提报单价
	protected String status; 
	protected String sapcode; 
	public BaseProduct() {
		super();
	}

	public BaseProduct(String skuId, String skuDesc, String categoryId,
			String categoryDesc, String brandsId, String brandsDesc,
			String empId) {
		super();
		this.categoryId = categoryId;
		this.categoryDesc = categoryDesc;
		this.brandsId = brandsId;
		this.brandsDesc = brandsDesc;
		this.empId = empId;
	}


	

	public String getCloudId() {
		return cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(String brandsId) {
		this.brandsId = brandsId;
	}

	public String getBrandsDesc() {
		return brandsDesc;
	}

	public void setBrandsDesc(String brandsDesc) {
		this.brandsDesc = brandsDesc;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}



	public String getCategorySortId() {
		return categorySortId;
	}

	public void setCategorySortId(String categorySortId) {
		this.categorySortId = categorySortId;
	}



	public String getCategorySortDesc() {
		return categorySortDesc;
	}

	public void setCategorySortDesc(String categorySortDesc) {
		this.categorySortDesc = categorySortDesc;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSkuOrder() {
		return skuOrder;
	}

	public void setSkuOrder(String skuOrder) {
		this.skuOrder = skuOrder;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSkuUnit() {
		return skuUnit;
	}

	public void setSkuUnit(String skuUnit) {
		this.skuUnit = skuUnit;
	}

	public String getSkuUnitId() {
		return skuUnitId;
	}

	public void setSkuUnitId(String skuUnitId) {
		this.skuUnitId = skuUnitId;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSapcode() {
		return sapcode;
	}

	public void setSapcode(String sapcode) {
		this.sapcode = sapcode;
	}

	@Override
	public String toString() {
		return "BaseProduct [categoryId=" + categoryId + ", categoryDesc="
				+ categoryDesc + ", categorySortId=" + categorySortId
				+ ", categorySortDesc=" + categorySortDesc + ", brandsId="
				+ brandsId + ", brandsDesc=" + brandsDesc + ", empId=" + empId
				+ ", custId=" + custId + ", time=" + time + ", productType="
				+ productType + ", skuOrder=" + skuOrder + ", companyName="
				+ companyName + ", skuUnit=" + skuUnit + ", skuUnitId="
				+ skuUnitId + ", cloudId=" + cloudId + ", lastPrice="
				+ lastPrice + ", status=" + status + ", sapcode=" + sapcode
				+ "]";
	}
	
	
	
	
}

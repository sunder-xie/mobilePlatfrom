package com.kintiger.xplatform.api.user.bo;

import java.io.Serializable;


/**
 * 手机端订单打印格式自定义
 * @author Administrator
 *
 */
public class OrderPrintFormat implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
	protected String formatId;
	/**
	 * 经销商编码
	 */
	protected String kunnr;
	/**
	 * 显示内容对应的标志
	 */
	protected String propertiesCode;
	/**
	 * 显示内容的描述
	 */
	protected String propertiesTxt;
	/**
	 * 类型标志
	 */
	protected String type;
	/**
	 * 类型描述
	 */
	protected String typeTxt;
	/**
	 * 排列顺序
	 */
	protected String orderDesc;
	
	public String getFormatId() {
		return formatId;
	}
	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getPropertiesCode() {
		return propertiesCode;
	}
	public void setPropertiesCode(String propertiesCode) {
		this.propertiesCode = propertiesCode;
	}
	public String getPropertiesTxt() {
		return propertiesTxt;
	}
	public void setPropertiesTxt(String propertiesTxt) {
		this.propertiesTxt = propertiesTxt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeTxt() {
		return typeTxt;
	}
	public void setTypeTxt(String typeTxt) {
		this.typeTxt = typeTxt;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
		
}

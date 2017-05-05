package com.kintiger.xplatform.api.news.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class NewsDetail extends SearchInfo {

	private static final long serialVersionUID = -6557795663452273731L;

	/**
	 * 明细表id.
	 */
	private Long detailId;

	/**
	 * 总表id.
	 */
	private Long totalId;

	/**
	 * 标题.
	 */
	private String detailTitle;

	/**
	 * 内容.
	 */
	private String detailContent;

	/**
	 * 发布人.
	 */
	private String detailOperator;

	/**
	 * 发布日期.
	 */
	private Date detailDate;

	/**
	 * 点击率.
	 */
	private Long clicksRatio;

	/**
	 * 发布组织.
	 */
	private String orgName;

	/**
	 * 删除状态（Y：正常，N：删除）.
	 */
	private String detailFlag;

	/**
	 * 修改日期.
	 */
	private Date modifyDate;

	/**
	 * Y为加了css，N为没加.
	 */
	private String cssFlag;

	private String totalName;

	private String newFlag;

	/**
	 * 是否为跑马灯状态（Y：是，N：否）.
	 */
	private String totalSign;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getTotalId() {
		return totalId;
	}

	public void setTotalId(Long totalId) {
		this.totalId = totalId;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public String getDetailOperator() {
		return detailOperator;
	}

	public void setDetailOperator(String detailOperator) {
		this.detailOperator = detailOperator;
	}

	public Date getDetailDate() {
		return detailDate != null ? (Date) detailDate.clone() : null;
	}

	public void setDetailDate(Date detailDate) {
		if (detailDate != null) {
			this.detailDate = (Date) detailDate.clone();
		}
	}

	public Long getClicksRatio() {
		return clicksRatio;
	}

	public void setClicksRatio(Long clicksRatio) {
		this.clicksRatio = clicksRatio;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(String detailFlag) {
		this.detailFlag = detailFlag;
	}

	public Date getModifyDate() {
		return modifyDate != null ? (Date) modifyDate.clone() : null;
	}

	public void setModifyDate(Date modifyDate) {
		if (modifyDate != null) {
			this.modifyDate = (Date) modifyDate.clone();
		}
	}

	public String getCssFlag() {
		return cssFlag;
	}

	public void setCssFlag(String cssFlag) {
		this.cssFlag = cssFlag;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public String getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	public String getTotalSign() {
		return totalSign;
	}

	public void setTotalSign(String totalSign) {
		this.totalSign = totalSign;
	}

}

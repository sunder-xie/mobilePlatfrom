package com.kintiger.xplatform.api.news.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class NewsFile extends SearchInfo {

	private static final long serialVersionUID = 803081488101441973L;

	/**
	 * ID
	 */
	private Long newsFileId;

	/**
	 * 文件名
	 */
	private String newsFileName;

	/**
	 * 明细表id
	 */
	private Long detailId;

	/**
	 * 文件路径
	 */
	private String newsFileUrl;

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 修改日期
	 */
	private Date modifyDate;

	/**
	 * Y:正常N删除
	 */
	private String newsDelFlag;

	public Long getNewsFileId() {
		return newsFileId;
	}

	public void setNewsFileId(Long newsFileId) {
		this.newsFileId = newsFileId;
	}

	public String getNewsFileName() {
		return newsFileName;
	}

	public void setNewsFileName(String newsFileName) {
		this.newsFileName = newsFileName;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getNewsFileUrl() {
		return newsFileUrl;
	}

	public void setNewsFileUrl(String newsFileUrl) {
		this.newsFileUrl = newsFileUrl;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getNewsDelFlag() {
		return newsDelFlag;
	}

	public void setNewsDelFlag(String newsDelFlag) {
		this.newsDelFlag = newsDelFlag;
	}

}

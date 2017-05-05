package com.kintiger.xplatform.api.sap.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * SAPAccount.
 * 
 * @author xujiakun
 * 
 */
public class SAPAccount extends SearchInfo {

	private static final long serialVersionUID = -2868592509059960263L;

	private String id;

	/**
	 * sap account.
	 */
	private String sapAccount;

	private String userId;

	/**
	 * 1: occupied 0: no.
	 */
	private String occupy;

	/**
	 * 1: available 0: no.
	 */
	private String status;

	private Date createDate;

	private Date modifyDate;

	private String userName;

	private String passport;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSapAccount() {
		return sapAccount;
	}

	public void setSapAccount(String sapAccount) {
		this.sapAccount = sapAccount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOccupy() {
		return occupy;
	}

	public void setOccupy(String occupy) {
		this.occupy = occupy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate != null ? (Date) createDate.clone() : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			this.createDate = (Date) createDate.clone();
		}
	}

	public Date getModifyDate() {
		return modifyDate != null ? (Date) modifyDate.clone() : null;
	}

	public void setModifyDate(Date modifyDate) {
		if (modifyDate != null) {
			this.modifyDate = (Date) modifyDate.clone();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

}

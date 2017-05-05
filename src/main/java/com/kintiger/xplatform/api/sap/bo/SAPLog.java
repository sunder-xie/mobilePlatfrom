package com.kintiger.xplatform.api.sap.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * SAPLog.
 * 
 * @author xujiakun
 * 
 */
public class SAPLog extends SearchInfo {

	private static final long serialVersionUID = -5054539343659524448L;

	private Long id;

	/**
	 * sap account.
	 */
	private String sapAccount;

	private String userId;

	private String tcode;

	private String ip;

	private Date createDate;

	private String passport;

	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return createDate != null ? (Date) createDate.clone() : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			this.createDate = (Date) createDate.clone();
		}
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

package com.kintiger.xplatform.api.user.bo;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * user.
 * 
 * @author xujiakun
 * 
 */
public class User extends SearchInfo {

	private static final long serialVersionUID = 1L;

	private String userId;

	/**
	 * 登录帐号.
	 */
	private String passport;

	/**
	 * 登录密码.
	 */
	private String password;

	/**
	 * 原密码.
	 */
	private String oldPassword;

	/**
	 * 用户名.
	 */
	private String userName;

	private String orgId;

	private String orgName;

	/**
	 * workfax.
	 */
	private String workFax;

	private String phone;

	/**
	 * mobile.
	 */
	private String mobile;

	/**
	 * address.
	 */
	private String address;

	/**
	 * email.
	 */
	private String email;

	/**
	 * sex.
	 */
	private String sex;

	/**
	 * remark.
	 */
	private String remark;
	
	private String  roleId;
	
	private String status;
	
	private String roleType;
	
	private String userCode;
	
	private String couldId;
	
	private String cloudId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCouldId() {
		return couldId;
	}

	public void setCouldId(String couldId) {
		this.couldId = couldId;
	}

	public String getCloudId() {
		return cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", passport=" + passport
				+ ", password=" + password + ", oldPassword=" + oldPassword
				+ ", userName=" + userName + ", orgId=" + orgId + ", orgName="
				+ orgName + ", workFax=" + workFax + ", phone=" + phone
				+ ", mobile=" + mobile + ", address=" + address + ", email="
				+ email + ", sex=" + sex + ", remark=" + remark + ", roleId="
				+ roleId + ", status=" + status + ", roleType=" + roleType
				+ ", userCode=" + userCode + ", couldId=" + couldId
				+ ", cloudId=" + cloudId + "]";
	}


}

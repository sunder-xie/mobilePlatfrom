package com.kintiger.xplatform.sap.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.sap.ISAPAccountService;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.sap.ISAPSyncService;
import com.kintiger.xplatform.api.sap.bo.SAPAccount;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * sap account.
 * 
 * @author xujiakun
 * 
 */
public class SAPAccountAction extends BaseAction {

	private static final long serialVersionUID = -398545940692345969L;

	private ISAPAccountService sapAccountService;

	private ISAPService sapService;

	private ISAPSyncService sapSyncService;

	private int total;

	private List<SAPAccount> sapAccountList;

	@Decode
	private String sapAccount;

	private String roleId;

	private String occupy;

	private String status;

	/**
	 * sap account页面.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "ERP帐号查询")
	public String searchSAPAccount() {
		return "searchSAPAccount";
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "sapAccountList", include = { "id", "sapAccount", "userName", "occupy", "status", "createDate",
		"modifyDate", "passport" }, total = "total")
	public String getSAPAccountJsonList() {
		SAPAccount s = new SAPAccount();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(sapAccount) && StringUtil.isNotEmpty(sapAccount.trim())) {
			s.setSapAccount(sapAccount.trim());
		}

		if (StringUtil.isNotEmpty(occupy) && StringUtil.isNotEmpty(occupy.trim())) {
			s.setOccupy(occupy.trim());
		}

		if (StringUtil.isNotEmpty(status) && StringUtil.isNotEmpty(status.trim())) {
			s.setStatus(status.trim());
		}

		total = sapAccountService.getSAPAccountCount(s);

		if (total != 0) {
			sapAccountList = sapAccountService.getSAPAccountList(s);
		}

		return JSON;
	}

	/**
	 * 释放帐号（任意帐号，可以是不属于当前用户占用的帐号）.
	 */
	@ActionLog(actionName = "ERP帐号释放")
	public String releaseSAPAccount() {
		boolean result = false;

		if (validateSAPAccount(sapAccount)) {
			result = sapService.logout(sapAccount, null);

			if (result) {
				this.setSuccessMessage("释放ERP帐号成功！");
			} else {
				this.setFailMessage("释放ERP帐号失败！");
			}
		} else {
			this.setFailMessage("ERP帐号正在使用中！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 退出并禁用帐号.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "ERP帐号禁用")
	public String disableSAPAccount() {
		boolean result = false;

		if (validateSAPAccount(sapAccount)) {
			result = sapService.disableSAPAccount(sapAccount);

			if (result) {
				this.setSuccessMessage("禁用ERP帐号成功！");
			} else {
				this.setFailMessage("禁用ERP帐号失败！");
			}
		} else {
			this.setFailMessage("ERP帐号正在使用中！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 判断在sap中是否正在使用.
	 * 
	 * @param sapAccount
	 * @return
	 */
	private boolean validateSAPAccount(String sapAccount) {
		if (StringUtil.isEmpty(sapAccount)) {
			return false;
		}

		SAPAccount account = new SAPAccount();
		account.setSapAccount(sapAccount);
		List<SAPAccount> sapAccounts = new ArrayList<SAPAccount>();
		sapAccounts.add(account);
		// 判断在sap中是否正在使用
		List<SAPAccount> list = sapSyncService.getUnusualSAPAccounts(sapAccounts);
		if (list != null && list.size() > 0) {
			SAPAccount tmp = list.get(0);
			if (tmp != null && sapAccount.equals(tmp.getSapAccount())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 启用帐号.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "ERP帐号启用")
	public String enableSAPAccount() {
		boolean result = false;
		if (StringUtil.isNotEmpty(sapAccount)) {
			result = sapService.enableSAPAccount(sapAccount);
		}

		if (result) {
			this.setSuccessMessage("启用ERP帐号成功！");
		} else {
			this.setFailMessage("启用ERP帐号失败！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 创建帐号.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "ERP帐号创建")
	public String createSAPAccount() {
		if (StringUtil.isEmpty(sapAccount)) {
			this.setFailMessage("ERP帐号不能为空！");
			return RESULT_MESSAGE;
		}

		String[] accounts = sapAccount.split(",");
		List<SAPAccount> sapAccounts = new ArrayList<SAPAccount>();
		for (String s : accounts) {
			if (StringUtil.isNotEmpty(s) && StringUtil.isNotEmpty(s.trim())) {
				SAPAccount account = new SAPAccount();
				account.setSapAccount(s.trim());

				sapAccounts.add(account);
			}
		}

		if (sapAccounts.size() == 0) {
			this.setFailMessage("ERP帐号不能为空！");
			return RESULT_MESSAGE;
		}

		this.setSuccessMessage("成功创建 " + sapAccountService.createSAPAccount(sapAccounts) + " 个ERP帐号");

		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "ERP角色同步")
	public String syncSAPRole() {
		this.setSuccessMessage(sapSyncService.syncSAPRole());
		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "ERP菜单同步")
	public String syncSAPTcode() {
		this.setSuccessMessage("成功同步 " + sapSyncService.syncSAPTcode(roleId) + " 个ERP菜单");
		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "ERP帐号状态更新")
	public String syncSAPAccount() {
		this.setSuccessMessage("成功更新 " + sapSyncService.syncSAPAccount() + " 个ERP帐号状态");
		return RESULT_MESSAGE;
	}

	public ISAPAccountService getSapAccountService() {
		return sapAccountService;
	}

	public void setSapAccountService(ISAPAccountService sapAccountService) {
		this.sapAccountService = sapAccountService;
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	public ISAPSyncService getSapSyncService() {
		return sapSyncService;
	}

	public void setSapSyncService(ISAPSyncService sapSyncService) {
		this.sapSyncService = sapSyncService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SAPAccount> getSapAccountList() {
		return sapAccountList;
	}

	public void setSapAccountList(List<SAPAccount> sapAccountList) {
		this.sapAccountList = sapAccountList;
	}

	public String getSapAccount() {
		return sapAccount;
	}

	public void setSapAccount(String sapAccount) {
		this.sapAccount = sapAccount;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

}

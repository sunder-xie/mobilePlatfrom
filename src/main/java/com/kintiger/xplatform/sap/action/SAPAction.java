package com.kintiger.xplatform.sap.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kintiger.xplatform.api.sap.ISAPLogService;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.sap.bo.SAPLog;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.util.ClientUtil;

/**
 * sap web 免登功能.
 * 
 * @author xujiakun
 * 
 */
public class SAPAction extends BaseAction {

	private static final long serialVersionUID = -9092859151846436697L;

	private ISAPService sapService;

	private ISAPLogService sapLogService;

	private String tcode;

	private String url;

	@ActionLog(actionName = "ERP系统免登")
	public String tcode() {
		HttpSession session = this.getSession();
		String passport = (String) session.getAttribute("SAP_ACCOUNT");
		String password = (String) session.getAttribute("SAP_ACCOUNT_PASSWORD");

		User user = this.getUser();
		String ip = ClientUtil.getIpAddr(this.getServletRequest());

		Map<String, String> map = sapService.login(passport, password, user.getUserId(), user.getPassport(), ip);

		if (map == null) {
			removeAttribute(session);
			return NONE;
		}

		// init sapLog
		SAPLog sapLog = new SAPLog();
		sapLog.setSapAccount(map.get("passport"));
		sapLog.setUserId(user.getUserId());
		sapLog.setTcode(tcode);
		sapLog.setIp(ip);

		// 记录sap操作日志
		if (!sapLogService.recordSAPLog(sapLog)) {
			removeAttribute(session);
			return NONE;
		}

		session.setAttribute("SAP_ACCOUNT", map.get("passport"));
		session.setAttribute("SAP_ACCOUNT_PASSWORD", map.get("password"));

		url = map.get("ssoUrl") + "?~TRANSACTION=" + tcode;

		return SUCCESS;
	}

	/**
	 * 清空session sap info.
	 * 
	 * @param session
	 */
	private void removeAttribute(HttpSession session) {
		if (session != null) {
			session.removeAttribute("SAP_ACCOUNT");
			session.removeAttribute("SAP_ACCOUNT_PASSWORD");
		}
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	public ISAPLogService getSapLogService() {
		return sapLogService;
	}

	public void setSapLogService(ISAPLogService sapLogService) {
		this.sapLogService = sapLogService;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

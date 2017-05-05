package com.kintiger.xplatform.sap.action;

import java.util.Date;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.sap.ISAPLogService;
import com.kintiger.xplatform.api.sap.bo.SAPLog;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.util.DateUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * sap log.
 * 
 * @author xujiakun
 * 
 */
public class SAPLogAction extends BaseAction {

	private static final long serialVersionUID = 4873455582917084694L;

	private ISAPLogService sapLogService;

	private int total;

	private List<SAPLog> sapLogList;

	@Decode
	private String sapAccount;

	@Decode
	private String tcode;

	/**
	 * sap log页面.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "ERP帐号操作日志查询")
	public String searchSAPLog() {
		gmtStart = DateUtil.getDateTime(DateUtil.addDays(new Date(), -1), DateUtil.DEFAULT_DATE_FORMAT);
		gmtEnd = DateUtil.getNowDateStr();

		return "searchSAPLog";
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "sapLogList", include = { "id", "sapAccount", "userName", "passport", "tcode", "createDate",
		"ip" }, total = "total")
	public String getSAPLogJsonList() {
		SAPLog s = new SAPLog();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(sapAccount) && StringUtil.isNotEmpty(sapAccount.trim())) {
			s.setSapAccount(sapAccount.trim());
		}

		if (StringUtil.isNotEmpty(tcode) && StringUtil.isNotEmpty(tcode.trim())) {
			s.setTcode(tcode.trim());
		}

		if (StringUtil.isNotEmpty(gmtStart) && StringUtil.isNotEmpty(gmtStart.trim())) {
			s.setGmtStart(gmtStart.trim());
		}

		if (StringUtil.isNotEmpty(gmtEnd) && StringUtil.isNotEmpty(gmtEnd.trim())) {
			s.setGmtEnd(gmtEnd.trim());
		}

		total = sapLogService.getSAPLogCount(s);

		if (total != 0) {
			sapLogList = sapLogService.getSAPLogList(s);
		}

		return JSON;
	}

	public ISAPLogService getSapLogService() {
		return sapLogService;
	}

	public void setSapLogService(ISAPLogService sapLogService) {
		this.sapLogService = sapLogService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SAPLog> getSapLogList() {
		return sapLogList;
	}

	public void setSapLogList(List<SAPLog> sapLogList) {
		this.sapLogList = sapLogList;
	}

	public String getSapAccount() {
		return sapAccount;
	}

	public void setSapAccount(String sapAccount) {
		this.sapAccount = sapAccount;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

}

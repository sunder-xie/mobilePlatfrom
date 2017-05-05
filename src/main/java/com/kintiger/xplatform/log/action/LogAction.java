package com.kintiger.xplatform.log.action;

import java.util.Date;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.log.IActionLogService;
import com.kintiger.xplatform.api.log.bo.Log;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.util.DateUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * log.
 * 
 * @author xujiakun
 * 
 */
public class LogAction extends BaseAction {

	private static final long serialVersionUID = 8136069885100794581L;

	private IActionLogService actionLogService;

	private int total;

	private List<Log> logs;

	private String passport;

	private String type;

	@ActionLog(actionName = "系统操作日志查询")
	public String searchActionLog() {
		initSearchDate();
		return "searchActionLog";
	}

	@JsonResult(field = "logs", include = { "id", "actionName", "passport", "userName", "ip", "createDate" }, total = "total")
	public String getActionLogJsonList() {
		Log c = new Log();
		c = getSearchInfo(c);

		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(passport.trim())) {
			c.setPassport(passport.trim());
		}

		if (StringUtil.isNotEmpty(gmtStart) && StringUtil.isNotEmpty(gmtStart.trim())) {
			c.setGmtStart(gmtStart.trim());
		}

		if (StringUtil.isNotEmpty(gmtEnd) && StringUtil.isNotEmpty(gmtEnd.trim())) {
			c.setGmtEnd(gmtEnd.trim());
		}

		total = actionLogService.getActionLogCount(c);

		if (total != 0) {
			logs = actionLogService.getActionLogList(c);
		}

		return JSON;
	}

	@ActionLog(actionName = "我的操作日志查询")
	public String searchMyActionLog() {
		type = "i";
		initSearchDate();
		return "searchActionLog";
	}

	@JsonResult(field = "logs", include = { "id", "actionName", "passport", "userName", "ip", "createDate" }, total = "total")
	public String getMyActionLogJsonList() {
		Log c = new Log();
		c = getSearchInfo(c);

		User user = getUser();
		c.setUserId(user.getUserId());

		if (StringUtil.isNotEmpty(gmtStart) && StringUtil.isNotEmpty(gmtStart.trim())) {
			c.setGmtStart(gmtStart.trim());
		}

		if (StringUtil.isNotEmpty(gmtEnd) && StringUtil.isNotEmpty(gmtEnd.trim())) {
			c.setGmtEnd(gmtEnd.trim());
		}

		total = actionLogService.getActionLogCount(c);

		if (total != 0) {
			logs = actionLogService.getActionLogList(c);
		}

		return JSON;
	}

	/**
	 * 初始化查询时间条件.
	 */
	private void initSearchDate() {
		gmtStart = DateUtil.getDateTime(DateUtil.addDays(new Date(), -1), DateUtil.DEFAULT_DATE_FORMAT);
		gmtEnd = DateUtil.getNowDateStr();
	}

	public IActionLogService getActionLogService() {
		return actionLogService;
	}

	public void setActionLogService(IActionLogService actionLogService) {
		this.actionLogService = actionLogService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

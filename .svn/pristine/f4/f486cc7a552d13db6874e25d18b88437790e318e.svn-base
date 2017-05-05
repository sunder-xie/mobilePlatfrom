package com.kintiger.xplatform.log.action;

import java.util.List;

import com.kintiger.xplatform.api.log.ILogMonitorService;
import com.kintiger.xplatform.api.log.bo.LogMonitor;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * log4j error log.
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorAction extends BaseAction {

	private static final long serialVersionUID = -898983625221087105L;

	private ILogMonitorService logMonitorService;

	private int total;

	private List<LogMonitor> logMonitorList;

	public String searchLogMonitor() {
		return "searchLogMonitor";
	}

	@JsonResult(field = "logMonitorList", include = { "logMonitorId", "className", "methodName", "lineNumber",
		"message", "e", "logDate", "createDate" }, total = "total")
	public String getLogMonitorJsonList() {
		LogMonitor c = new LogMonitor();
		c = getSearchInfo(c);

		total = logMonitorService.getLogMonitorCount(c);

		if (total != 0) {
			logMonitorList = logMonitorService.getLogMonitorList(c);
		}

		return JSON;
	}

	public ILogMonitorService getLogMonitorService() {
		return logMonitorService;
	}

	public void setLogMonitorService(ILogMonitorService logMonitorService) {
		this.logMonitorService = logMonitorService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<LogMonitor> getLogMonitorList() {
		return logMonitorList;
	}

	public void setLogMonitorList(List<LogMonitor> logMonitorList) {
		this.logMonitorList = logMonitorList;
	}

}

package com.kintiger.xplatform.monitor.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.monitor.IMonitorLogService;
import com.kintiger.xplatform.api.monitor.bo.MonitorLog;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 
 * @author xujiakun
 * 
 */
public class MonitorLogAction extends BaseAction {

	private static final long serialVersionUID = 5116348289768021026L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(MonitorLogAction.class);

	private IMonitorLogService monitorLogService;

	private int total;

	private List<MonitorLog> monitorLogList;

	@Decode
	private String monitorId;

	public String searchMonitorLog() {
		return "searchMonitorLog";
	}

	@JsonResult(field = "monitorLogList", include = { "monitorLogId", "monitorId", "createDate", "monitorResult",
		"threshold", "sqlMonitorTitle" }, total = "total")
	public String getMonitorLogJsonList() {
		MonitorLog s = new MonitorLog();
		s = getSearchInfo(s);

		try {
			if (StringUtil.isNotEmpty(monitorId) && StringUtil.isNotEmpty(monitorId.trim())) {
				s.setMonitorId(Long.valueOf(monitorId.trim()));
			} else {
				s.setMonitorId(0L);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		total = monitorLogService.getMonitorLogCount(s);
		if (total != 0) {
			monitorLogList = monitorLogService.getMonitorLogList(s);
		}

		return JSON;
	}

	public IMonitorLogService getMonitorLogService() {
		return monitorLogService;
	}

	public void setMonitorLogService(IMonitorLogService monitorLogService) {
		this.monitorLogService = monitorLogService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MonitorLog> getMonitorLogList() {
		return monitorLogList;
	}

	public void setMonitorLogList(List<MonitorLog> monitorLogList) {
		this.monitorLogList = monitorLogList;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

}

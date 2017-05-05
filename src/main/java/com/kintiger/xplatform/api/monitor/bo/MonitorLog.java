package com.kintiger.xplatform.api.monitor.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class MonitorLog extends SearchInfo {

	private static final long serialVersionUID = -8861157710341110910L;

	private Long monitorLogId;

	private Long monitorId;

	private Date createDate;

	private Date modifyDate;

	private String sqlMonitorTitle;

	private int monitorResult;

	private int threshold;

	public Long getMonitorLogId() {
		return monitorLogId;
	}

	public void setMonitorLogId(Long monitorLogId) {
		this.monitorLogId = monitorLogId;
	}

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public Date getModifyDate() {
		return modifyDate != null ? (Date) modifyDate.clone() : null;
	}

	public void setModifyDate(Date modifyDate) {
		if (modifyDate != null) {
			this.modifyDate = (Date) modifyDate.clone();
		}
	}

	public int getMonitorResult() {
		return monitorResult;
	}

	public void setMonitorResult(int monitorResult) {
		this.monitorResult = monitorResult;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Date getCreateDate() {
		return createDate != null ? (Date) createDate.clone() : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			this.createDate = (Date) createDate.clone();
		}
	}

	public String getSqlMonitorTitle() {
		return sqlMonitorTitle;
	}

	public void setSqlMonitorTitle(String sqlMonitorTitle) {
		this.sqlMonitorTitle = sqlMonitorTitle;
	}

}

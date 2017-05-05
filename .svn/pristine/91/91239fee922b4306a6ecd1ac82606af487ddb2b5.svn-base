package com.kintiger.xplatform.api.monitor.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 监控 method 性能.
 * 
 * @author xujiakun
 * 
 */
public class MethodMonitor extends SearchInfo {

	private static final long serialVersionUID = 492567288192413221L;

	private String methodMonitorId;

	private String className;

	private String methodName;

	/**
	 * 执行时间.
	 */
	private long cost;

	private Date createDate;

	public String getMethodMonitorId() {
		return methodMonitorId;
	}

	public void setMethodMonitorId(String methodMonitorId) {
		this.methodMonitorId = methodMonitorId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public Date getCreateDate() {
		return createDate != null ? (Date) createDate.clone() : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			this.createDate = (Date) createDate.clone();
		}
	}

}

package com.kintiger.xplatform.api.openapi.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class ResponseStats extends SearchInfo {

	private static final long serialVersionUID = -4832657794423889286L;

	private Long responseStatsId;

	private String methodName;

	private Long startTime;

	private Long endTime;

	private Date createDate;

	public Long getResponseStatsId() {
		return responseStatsId;
	}

	public void setResponseStatsId(Long responseStatsId) {
		this.responseStatsId = responseStatsId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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

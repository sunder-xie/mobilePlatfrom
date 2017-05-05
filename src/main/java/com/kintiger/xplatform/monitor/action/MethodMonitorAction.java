package com.kintiger.xplatform.monitor.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.monitor.IMethodMonitorService;
import com.kintiger.xplatform.api.monitor.bo.MethodMonitor;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * MethodMonitorAction.
 * 
 * @author xujiakun
 * 
 */
public class MethodMonitorAction extends BaseAction {

	private static final long serialVersionUID = 3419997830827128937L;

	private IMethodMonitorService methodMonitorService;

	private int total;

	private List<MethodMonitor> methodMonitorList;

	@Decode
	private String className;

	@Decode
	private String methodName;

	/**
	 * 
	 * @return
	 */
	public String searchMethodMonitor() {
		return "searchMethodMonitor";
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "methodMonitorList", include = { "methodMonitorId", "className", "methodName", "cost",
		"createDate" }, total = "total")
	public String getMethodMonitorJsonList() {
		MethodMonitor s = new MethodMonitor();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(className) && StringUtil.isNotEmpty(className.trim())) {
			s.setClassName(className.trim());
		}

		if (StringUtil.isNotEmpty(methodName) && StringUtil.isNotEmpty(methodName.trim())) {
			s.setMethodName(methodName.trim());
		}

		total = methodMonitorService.getMethodMonitorCount(s);
		if (total != 0) {
			methodMonitorList = methodMonitorService.getMethodMonitorList(s);
		}

		return JSON;
	}

	public IMethodMonitorService getMethodMonitorService() {
		return methodMonitorService;
	}

	public void setMethodMonitorService(IMethodMonitorService methodMonitorService) {
		this.methodMonitorService = methodMonitorService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MethodMonitor> getMethodMonitorList() {
		return methodMonitorList;
	}

	public void setMethodMonitorList(List<MethodMonitor> methodMonitorList) {
		this.methodMonitorList = methodMonitorList;
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

}

package com.kintiger.xplatform.openapi.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.openapi.IResponseService;
import com.kintiger.xplatform.api.openapi.bo.ResponseStats;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 
 * @author xujiakun
 * 
 */
public class ResponseAction extends BaseAction {

	private static final long serialVersionUID = -4088818836826761774L;

	private IResponseService responseService;

	private int total;

	private List<ResponseStats> responseStatsList;

	private String methodName;

	public String searchResponseStats() {
		return "searchResponseStats";
	}

	@JsonResult(field = "responseStatsList", include = { "responseStatsId", "methodName", "startTime", "endTime",
		"createDate" }, total = "total")
	public String getResponseStatsJsonList() {
		ResponseStats c = new ResponseStats();
		c = getSearchInfo(c);

		if (StringUtil.isNotEmpty(methodName) && StringUtil.isNotEmpty(methodName.trim())) {
			c.setMethodName(methodName.trim());
		}

		total = responseService.getResponseStatsCount(c);
		if (total != 0) {
			responseStatsList = responseService.getResponseStatsList(c);
		}

		return JSON;
	}

	public IResponseService getResponseService() {
		return responseService;
	}

	public void setResponseService(IResponseService responseService) {
		this.responseService = responseService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ResponseStats> getResponseStatsList() {
		return responseStatsList;
	}

	public void setResponseStatsList(List<ResponseStats> responseStatsList) {
		this.responseStatsList = responseStatsList;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}

package com.kintiger.xplatform.data.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.data.IDataLogService;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * DataLogAction.
 * 
 * @author xujiakun
 * 
 */
public class DataLogAction extends BaseAction {

	private static final long serialVersionUID = 4838230867135058512L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataLogAction.class);

	private IDataLogService dataLogService;

	private int total;

	private List<DataLogTotal> dataLogTotalList;

	private String dataConfigId;

	// private List<CmsTbDict> cmsTbDictList

	/**
	 * getDBTableJsonList.
	 * 
	 * @return
	 */
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue", "itemState", "remark" }, total = "total")
	public String getDBTableJsonList() {
		User users = this.getUser();

		DataLogTotal s = new DataLogTotal();
		s = getSearchInfo(s);
		s.setUserId(users.getUserId());

		total = dataLogService.getDBTableCount(s);
		/**
		 * if (total != 0) { cmsTbDictList = dataLogService.getDBTableList(s) }
		 */

		return JSON;
	}

	public String searchDataLog() {
		return "searchDataLog";
	}

	@JsonResult(field = "dataLogTotalList", include = { "dataLogTotalId", "dataConfigId", "tableName", "createDate",
		"flag", "modifyDate", "total" }, total = "total")
	public String getDataLogJsonList() {
		DataLogTotal s = new DataLogTotal();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(dataConfigId)) {
			try {
				s.setDataConfigId(Long.parseLong(dataConfigId));
			} catch (NumberFormatException e) {
				logger.error("dataConfigId:" + dataConfigId, e);
				return JSON;
			}
		}

		total = dataLogService.getDataLogTotalCount(s);
		if (total != 0) {
			dataLogTotalList = dataLogService.getDataLogTotalList(s);
		}

		return JSON;
	}

	public IDataLogService getDataLogService() {
		return dataLogService;
	}

	public void setDataLogService(IDataLogService dataLogService) {
		this.dataLogService = dataLogService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataLogTotal> getDataLogTotalList() {
		return dataLogTotalList;
	}

	public void setDataLogTotalList(List<DataLogTotal> dataLogTotalList) {
		this.dataLogTotalList = dataLogTotalList;
	}

	public String getDataConfigId() {
		return dataConfigId;
	}

	public void setDataConfigId(String dataConfigId) {
		this.dataConfigId = dataConfigId;
	}

}

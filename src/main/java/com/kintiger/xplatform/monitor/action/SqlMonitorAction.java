package com.kintiger.xplatform.monitor.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.monitor.ISqlMonitorService;
import com.kintiger.xplatform.api.monitor.bo.SqlMonitor;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * SqlMonitor.
 * 
 * @author xujiakun
 * 
 */
public class SqlMonitorAction extends BaseAction {

	private static final long serialVersionUID = 6178781065747143791L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(SqlMonitorAction.class);

	private ISqlMonitorService sqlMonitorService;

	private int total;

	private List<SqlMonitor> sqlMonitorList;

	private SqlMonitor sqlMonitor;

	private String sqlMonitorId;
	private String sqlMonitorId4del;
	private String sqlMonitorTitle;

	private String status;

	private String log;

	/**
	 * 
	 * @return
	 */
	public String searchSqlMonitor() {
		return "searchSqlMonitor";
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "sqlMonitorList", include = { "sqlMonitorId", "sqlMonitorTitle", "createDate", "freq",
		"threshold", "log", "status" }, total = "total")
	public String getSqlMonitorJsonList() {
		SqlMonitor s = new SqlMonitor();
		s = getSearchInfo(s);

		try {
			if (StringUtil.isNotEmpty(sqlMonitorId) && StringUtil.isNotEmpty(sqlMonitorId.trim())) {
				s.setSqlMonitorId(Long.parseLong(sqlMonitorId.trim()));
			}
			if (StringUtil.isNotEmpty(sqlMonitorTitle) && StringUtil.isNotEmpty(sqlMonitorTitle.trim())) {
				s.setSqlMonitorTitle(sqlMonitorTitle.trim());
			}
			if (StringUtil.isNotEmpty(status) && StringUtil.isNotEmpty(status.trim())) {
				s.setStatus(status.trim());
			}
			if (StringUtil.isNotEmpty(log) && StringUtil.isNotEmpty(log.trim())) {
				s.setLog(log.trim());
			}
		} catch (Exception e) {
			logger.error(e);
		}

		total = sqlMonitorService.getSqlMonitorCount(s);
		if (total != 0) {
			sqlMonitorList = sqlMonitorService.getSqlMonitorList(s);
		}

		return JSON;
	}

	/**
	 * validate.
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	private boolean validate(SqlMonitor sqlMonitor) {

		if (sqlMonitor == null) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @return
	 */
	public String createSqlMonitorPrepare() {
		return "createPrepare";
	}

	/**
	 * 
	 * @return
	 */
	public String createSqlMonitor() {

		if (!validate(sqlMonitor)) {
			this.setFailMessage(ISqlMonitorService.ERROR_INPUT_MESSAGE);
			return ERROR;
		}

		BooleanResult result = sqlMonitorService.createSqlMonitor(sqlMonitor);

		if (!result.getResult()) {
			this.setFailMessage(result.getCode());
			return RESULT_MESSAGE;
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 
	 * @return
	 */
	public String updateSqlMonitorPrepare() {

		if (StringUtil.isNotEmpty(sqlMonitorId4del) && StringUtil.isNotEmpty(sqlMonitorId4del.trim())) {
			try {
				sqlMonitor = sqlMonitorService.getSqlMonitorById(Long.parseLong(sqlMonitorId4del));
				return UPDATE_PREPARE;
			} catch (Exception e) {
				logger.error(sqlMonitorId4del, e);
			}
		}

		sqlMonitor = new SqlMonitor();
		return UPDATE_PREPARE;
	}

	public String updateSqlMonitor() {

		if (!validate(sqlMonitor)) {
			this.setFailMessage(ISqlMonitorService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		try {
			sqlMonitor.setSqlMonitorId(Long.valueOf(sqlMonitorId4del));
		} catch (Exception e) {
			this.setFailMessage(ISqlMonitorService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult result = sqlMonitorService.updateSqlMonitor(sqlMonitor);

		if (!result.getResult()) {
			this.setFailMessage(result.getCode());
		} else {
			this.setSuccessMessage("修改成功");
		}

		return RESULT_MESSAGE;
	}

	public ISqlMonitorService getSqlMonitorService() {
		return sqlMonitorService;
	}

	public void setSqlMonitorService(ISqlMonitorService sqlMonitorService) {
		this.sqlMonitorService = sqlMonitorService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SqlMonitor> getSqlMonitorList() {
		return sqlMonitorList;
	}

	public void setSqlMonitorList(List<SqlMonitor> sqlMonitorList) {
		this.sqlMonitorList = sqlMonitorList;
	}

	public SqlMonitor getSqlMonitor() {
		return sqlMonitor;
	}

	public void setSqlMonitor(SqlMonitor sqlMonitor) {
		this.sqlMonitor = sqlMonitor;
	}

	public String getSqlMonitorId() {
		return sqlMonitorId;
	}

	public void setSqlMonitorId(String sqlMonitorId) {
		this.sqlMonitorId = sqlMonitorId;
	}

	public String getSqlMonitorTitle() {
		return sqlMonitorTitle;
	}

	public void setSqlMonitorTitle(String sqlMonitorTitle) {
		this.sqlMonitorTitle = sqlMonitorTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getSqlMonitorId4del() {
		return sqlMonitorId4del;
	}

	public void setSqlMonitorId4del(String sqlMonitorId4del) {
		this.sqlMonitorId4del = sqlMonitorId4del;
	}

}

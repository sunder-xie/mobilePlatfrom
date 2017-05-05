package com.kintiger.xplatform.api.monitor;

import java.util.List;
import java.util.Map;

import com.kintiger.xplatform.api.monitor.bo.SqlMonitor;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * sqlMonitor.
 * 
 * @author xujiakun
 * 
 */
public interface ISqlMonitorService {

	int MONITOR_FREQ_15 = 15;

	int MONITOR_FREQ_30 = 30;

	int MONITOR_FREQ_60 = 60;

	String MONITOR_LOG_Y = "Y";

	String MONITOR_LOG_N = "N";

	String MONITOR_STATUS_N = "N";

	String MONITOR_STATUS_Y = "Y";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";

	/**
	 * execMonitorSql.
	 * 
	 * @param sql
	 * @return
	 */
	int execMonitorSql(String sql);

	/**
	 * 
	 * @param sqlDetail
	 * @return
	 */
	List<Map<String, Object>> execMonitorSqlDetail(String sqlDetail);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	int getSqlMonitorCount(SqlMonitor sqlMonitor);

	/**
	 * getSqlMonitorList.
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor);

	/**
	 * createSqlMonitor.
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	BooleanResult createSqlMonitor(SqlMonitor sqlMonitor);

	/**
	 * 
	 * @param sqlMonitorId
	 * @return
	 */
	SqlMonitor getSqlMonitorById(Long sqlMonitorId);

	/**
	 * 
	 * @param sqlMonitor
	 * @return
	 */
	BooleanResult updateSqlMonitor(SqlMonitor sqlMonitor);

}

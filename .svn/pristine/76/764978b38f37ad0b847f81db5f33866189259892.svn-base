package com.kintiger.xplatform.monitor.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.monitor.bo.MonitorLog;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.monitor.dao.IMonitorLogDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class MonitorLogDaoImpl extends BaseDaoImpl implements IMonitorLogDao {

	public int getMonitorLogCount(MonitorLog monitorLog) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getMonitorLogCount", monitorLog);
	}

	@SuppressWarnings("unchecked")
	public List<MonitorLog> getMonitorLogList(MonitorLog monitorLog) {
		return (List<MonitorLog>) getSqlMapClientTemplate().queryForList("monitor.getMonitorLogList", monitorLog);
	}

	public Long createMonitorLog(MonitorLog monitorLog) {
		return (Long) getSqlMapClientTemplate().insert("monitor.createMonitorLog", monitorLog);
	}

}

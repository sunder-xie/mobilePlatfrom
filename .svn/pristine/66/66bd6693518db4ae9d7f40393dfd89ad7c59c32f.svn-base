package com.kintiger.xplatform.monitor.dao.impl;

import java.util.List;
import java.util.Map;

import com.kintiger.xplatform.api.monitor.bo.SqlMonitor;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.monitor.dao.ISqlMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class SqlMonitorDaoImpl extends BaseDaoImpl implements ISqlMonitorDao {

	public int execMonitorSql(String sql) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.execMonitorSql", sql);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> execMonitorSqlDetail(String sqlDetail) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("monitor.execMonitorSqlDetail",
			sqlDetail);
	}

	public int getSqlMonitorCount(SqlMonitor sqlMonitor) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getSqlMonitorCount", sqlMonitor);
	}

	@SuppressWarnings("unchecked")
	public List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor) {
		return (List<SqlMonitor>) getSqlMapClientTemplate().queryForList("monitor.getSqlMonitorList", sqlMonitor);
	}

	public Long createSqlMonitor(SqlMonitor sqlMonitor) {
		return (Long) getSqlMapClientTemplate().insert("monitor.createSqlMonitor", sqlMonitor);
	}

	public SqlMonitor getSqlMonitorById(Long sqlMonitorId) {
		return (SqlMonitor) getSqlMapClientTemplate().queryForObject("monitor.getSqlMonitorById", sqlMonitorId);
	}

	public int updateSqlMonitor(SqlMonitor sqlMonitor) {
		return getSqlMapClientTemplate().update("monitor.updateSqlMonitor", sqlMonitor);
	}

}

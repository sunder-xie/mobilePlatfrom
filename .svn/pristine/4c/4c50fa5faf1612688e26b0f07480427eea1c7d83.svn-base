package com.kintiger.xplatform.log.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.log.bo.LogMonitor;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.log.dao.ILogMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorDaoImpl extends BaseDaoImpl implements ILogMonitorDao {

	public int getLogMonitorCount(LogMonitor logMonitor) {
		return (Integer) getSqlMapClientTemplate().queryForObject("log.getLogMonitorCount", logMonitor);
	}

	@SuppressWarnings("unchecked")
	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor) {
		return (List<LogMonitor>) getSqlMapClientTemplate().queryForList("log.getLogMonitorList", logMonitor);
	}

	public int createLogMonitor(final List<LogMonitor> logMonitorList) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (LogMonitor s : logMonitorList) {
					executor.insert("log.createLogMonitor", s);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<LogMonitor> getLogMonitorList4SendEmail() {
		return (List<LogMonitor>) getSqlMapClientTemplate().queryForList("log.getLogMonitorList4SendEmail");
	}

}

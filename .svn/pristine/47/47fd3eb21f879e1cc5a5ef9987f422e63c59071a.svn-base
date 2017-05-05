package com.kintiger.xplatform.log.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.log.bo.Log;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.log.dao.IActionLogDao;

/**
 * action log dao.
 * 
 * @author xujiakun
 * 
 */
public class ActionLogDaoImpl extends BaseDaoImpl implements IActionLogDao {

	public int createActionLog(final List<Log> logs) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Log l : logs) {
					executor.insert("log.createActionLog", l);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	public int getActionLogCount(Log log) {
		return (Integer) getSqlMapClientTemplate().queryForObject("log.getActionLogCount", log);
	}

	@SuppressWarnings("unchecked")
	public List<Log> getActionLogList(Log log) {
		return (List<Log>) getSqlMapClientTemplate().queryForList("log.getActionLogList", log);
	}

}

package com.kintiger.xplatform.monitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.cache.bo.CacheStats;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.monitor.dao.ICacheMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorDaoImpl extends BaseDaoImpl implements ICacheMonitorDao {

	public int getCacheMonitorCount(CacheStats cacheStats) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getCacheMonitorCount", cacheStats);
	}

	@SuppressWarnings("unchecked")
	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats) {
		return (List<CacheStats>) getSqlMapClientTemplate().queryForList("monitor.getCacheMonitorList", cacheStats);
	}

	public String createCacheMonitor(final List<CacheStats> cacheStatsList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (CacheStats s : cacheStatsList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("monitor.createCacheMonitor", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

}

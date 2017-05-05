package com.kintiger.xplatform.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.openapi.bo.ResponseStats;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.openapi.dao.IResponseDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class ResponseDaoImpl extends BaseDaoImpl implements IResponseDao {

	public int getResponseStatsCount(ResponseStats responseStats) {
		return (Integer) getSqlMapClientTemplate().queryForObject("response.getResponseStatsCount", responseStats);
	}

	@SuppressWarnings("unchecked")
	public List<ResponseStats> getResponseStatsList(ResponseStats responseStats) {
		return (List<ResponseStats>) getSqlMapClientTemplate().queryForList("response.getResponseStatsList",
			responseStats);
	}

	public String createResponseStats(final List<ResponseStats> responseStatsList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (ResponseStats s : responseStatsList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("response.createResponseStats", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

}

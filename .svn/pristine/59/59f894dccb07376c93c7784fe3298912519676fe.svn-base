package com.kintiger.xplatform.data.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.data.bo.DataLogDetail;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.data.dao.IDataLogDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * data log dao.
 * 
 * @author xujiakun
 * 
 */
public class DataLogDaoImpl extends BaseDaoImpl implements IDataLogDao {

	public Long createDataLogTotal(DataLogTotal dataLogTotal) {
		return (Long) getSqlMapClientTemplate().insert("data.createDataLogTotal", dataLogTotal);
	}

	public String createDataLogDetail(final List<DataLogDetail> dataLogDetailList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (DataLogDetail s : dataLogDetailList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("data.createDataLogDetail", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

	public int getDataLogTotalCount(DataLogTotal dataLogTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getDataLogTotalCount", dataLogTotal);
	}

	@SuppressWarnings("unchecked")
	public List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal) {
		return (List<DataLogTotal>) getSqlMapClientTemplate().queryForList("data.getDataLogTotalList", dataLogTotal);
	}

	public int getDBTableCount(DataLogTotal dataLogTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getDBTableCount", dataLogTotal);
	}

	public int getDataLogDetailCount(DataLogTotal dataLogTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getDataLogDetailCount", dataLogTotal);
	}

	public int updateDataLogTotal(Long dataLogTotalId, String userId, String flag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataLogTotalId", dataLogTotalId);
		map.put("userId", userId);
		map.put("flag", flag);

		return getSqlMapClientTemplate().update("data.updateDataLogTotal", map);
	}

}

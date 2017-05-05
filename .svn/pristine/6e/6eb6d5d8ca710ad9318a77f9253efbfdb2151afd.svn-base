package com.kintiger.xplatform.data.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.data.bo.DataConfig;
import com.kintiger.xplatform.data.dao.IDataConfigDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * data config dao.
 * 
 * @author xujiakun
 * 
 */
public class DataConfigDaoImpl extends BaseDaoImpl implements IDataConfigDao {

	public int getDataConfigCount(DataConfig dataConfig) {
		return (Integer) getSqlMapClientTemplate().queryForObject("data.getDataConfigCount", dataConfig);
	}

	@SuppressWarnings("unchecked")
	public List<DataConfig> getDataConfigList(DataConfig dataConfig) {
		return (List<DataConfig>) getSqlMapClientTemplate().queryForList("data.getDataConfigList", dataConfig);
	}

	public String createDataConfig(final List<DataConfig> dataConfigList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (DataConfig s : dataConfigList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("data.createDataConfig", s));
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

	public int updateDataConfig(DataConfig dataConfig) {
		return getSqlMapClientTemplate().update("data.updateDataConfig", dataConfig);
	}

}

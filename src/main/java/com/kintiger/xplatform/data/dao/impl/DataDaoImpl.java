package com.kintiger.xplatform.data.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.alibaba.common.lang.StringUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.data.bo.DataInfo;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.api.data.bo.TabColumn;
import com.kintiger.xplatform.data.dao.IDataDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * data dao.
 * 
 * @author xujiakun
 * 
 */
public class DataDaoImpl extends BaseDaoImpl implements IDataDao {

	@SuppressWarnings("unchecked")
	public List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataLogTotalId", dataLogTotalId);
		map.put("userId", userId);

		return (List<TabColumn>) getSqlMapClientTemplate().queryForList("data.getTabColumns1", map);
	}

	@SuppressWarnings("unchecked")
	public List<TabColumn> getTabColumnsByConfigId(Long dataConfigId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataConfigId", dataConfigId);
		map.put("userId", userId);

		return (List<TabColumn>) getSqlMapClientTemplate().queryForList("data.getTabColumns2", map);
	}

	public String createDataInfo(final List<DataInfo> dataInfoList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (DataInfo s : dataInfoList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					if (StringUtil.isNotEmpty(s.getSequenceValue())) {
						String v = (String) executor.queryForObject("data.getSequenceValue", s.getSequenceValue());
						s.setValue1(v);
					}
					executor.insert("data.createDataInfo", s);
					sb.append(s.getValue1());
				}
				executor.executeBatch();

				return sb.toString();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataPreviewList(DataLogTotal dataLogTotal) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("data.getDataPreviewList",
			dataLogTotal);
	}

	public int deleteDataInfo(Long dataLogTotalId, String userId, String tableName, String primaryKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataLogTotalId", dataLogTotalId);
		map.put("userId", userId);
		map.put("tableName", tableName);
		map.put("primaryKey", primaryKey);

		return getSqlMapClientTemplate().delete("data.deleteDataInfo", map);
	}

}

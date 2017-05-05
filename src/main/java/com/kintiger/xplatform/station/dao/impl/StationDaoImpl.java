package com.kintiger.xplatform.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.station.bo.Station;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.station.dao.IStationDao;

/**
 * station dao.
 * 
 * @author xujiakun
 * 
 */
public class StationDaoImpl extends BaseDaoImpl implements IStationDao {

	public int getStationJsonListCount(Station station) {
		return (Integer) getSqlMapClientTemplate().queryForObject("station.getStationJsonListCount", station);
	}

	@SuppressWarnings("unchecked")
	public List<Station> getStationJsonList(Station station) {
		return (List<Station>) getSqlMapClientTemplate().queryForList("station.getStationJsonList", station);
	}

	public Station getStation(String stationId) {
		return (Station) getSqlMapClientTemplate().queryForObject("station.getStation", stationId);
	}

	public int updateStation(Station station) {
		return getSqlMapClientTemplate().update("station.updateStation", station);
	}

	public int deleteStation(String stationId) {
		return getSqlMapClientTemplate().update("station.deleteStation", stationId);
	}

	public String createStation(Station station) {
		return (String) getSqlMapClientTemplate().insert("station.createStation", station);
	}

	public int getStationUserCount(Station station) {
		return (Integer) getSqlMapClientTemplate().queryForObject("station.getStationUserCount", station);
	}

	@SuppressWarnings("unchecked")
	public List<Station> getStationUserList(Station station) {
		return (List<Station>) getSqlMapClientTemplate().queryForList("station.getStationUserList", station);
	}

	public int deleteStationUser(Station station) {
		return getSqlMapClientTemplate().delete("station.deleteStationUser", station);
	}

	public int batchCreateStationUser(final List<Station> stations) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Station station : stations) {
					executor.insert("station.createStationUser", station);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

}

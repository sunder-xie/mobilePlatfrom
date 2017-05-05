package com.kintiger.xplatform.station.dao;

import java.util.List;

import com.kintiger.xplatform.api.station.bo.Station;

/**
 * station dao.
 * 
 * @author xujiakun
 * 
 */
public interface IStationDao {

	int getStationJsonListCount(Station station);

	List<Station> getStationJsonList(Station station);

	/**
	 * 
	 * @param stationId
	 * @return
	 */
	Station getStation(String stationId);

	/**
	 * 
	 * @param station
	 * @return
	 */
	int updateStation(Station station);

	/**
	 * 删除.
	 * 
	 * @param stationId
	 * @return
	 */
	int deleteStation(String stationId);

	/**
	 * 
	 * @param station
	 * @return
	 */
	String createStation(Station station);

	int getStationUserCount(Station station);

	List<Station> getStationUserList(Station station);

	int deleteStationUser(Station station);

	/**
	 * 
	 * @param station
	 * @return
	 */
	int batchCreateStationUser(List<Station> stations);

}

package com.kintiger.xplatform.api.station;

import java.util.List;

import com.kintiger.xplatform.api.station.bo.Station;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IStationService {

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	int getStationJsonListCount(Station station);

	List<Station> getStationJsonList(Station station);

	Station getStation(String stationId);

	int updateStation(Station station);

	/**
	 * 删除权限岗位 同时删除该岗位下的角色.
	 * 
	 * @param stationId
	 * @return
	 */
	BooleanResult deleteStation(String stationId);

	/**
	 * 创建权限岗位.
	 * 
	 * @param station
	 * @return
	 */
	String createStation(Station station);

	int getStationUserCount(Station station);

	/**
	 * 
	 * @param station
	 * @return
	 */
	List<Station> getStationUserList(Station station);

	int deleteStationUser(Station station);

	/**
	 * 批量创建station user.
	 * 
	 * @param station
	 * @return
	 */
	int batchCreateStationUser(List<Station> stations);

}

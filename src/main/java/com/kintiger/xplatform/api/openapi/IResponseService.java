package com.kintiger.xplatform.api.openapi;

import java.util.List;

import com.kintiger.xplatform.api.openapi.bo.ResponseStats;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IResponseService {

	String ERROR_MESSAGE = "操作失败！";

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	int getResponseStatsCount(ResponseStats responseStats);

	/**
	 * 
	 * @param responseStats
	 * @return
	 */
	List<ResponseStats> getResponseStatsList(ResponseStats responseStats);

	/**
	 * createResponseStats.
	 * 
	 * @param responseStatsList
	 * @return
	 */
	BooleanResult createResponseStats(List<ResponseStats> responseStatsList);

}

package com.kintiger.xplatform.log.dao;

import java.util.List;

import com.kintiger.xplatform.api.log.bo.Log;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IActionLogDao {

	/**
	 * 
	 * @param logs
	 * @return
	 */
	int createActionLog(List<Log> logs);

	/**
	 * 
	 * @param log
	 * @return
	 */
	int getActionLogCount(Log log);

	/**
	 * 
	 * @param log
	 * @return
	 */
	List<Log> getActionLogList(Log log);

}

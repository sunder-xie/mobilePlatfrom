package com.kintiger.xplatform.api.log;

import java.util.List;

import com.kintiger.xplatform.api.log.bo.Log;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IActionLogService {

	/**
	 * 
	 * @param logs
	 * @return
	 */
	boolean createActionLog(List<Log> logs);

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

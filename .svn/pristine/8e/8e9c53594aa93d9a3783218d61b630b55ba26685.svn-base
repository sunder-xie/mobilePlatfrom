package com.kintiger.xplatform.log.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.log.IActionLogService;
import com.kintiger.xplatform.api.log.bo.Log;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.log.dao.IActionLogDao;

/**
 * action log service.
 * 
 * @author xujiakun
 * 
 */
public class ActionLogServiceImpl implements IActionLogService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(ActionLogServiceImpl.class);

	private IActionLogDao actionLogDao;

	public boolean createActionLog(List<Log> logs) {
		try {
			actionLogDao.createActionLog(logs);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logs), e);
		}

		return false;
	}

	public int getActionLogCount(Log log) {
		try {
			return actionLogDao.getActionLogCount(log);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(log), e);
		}

		return 0;
	}

	public List<Log> getActionLogList(Log log) {
		try {
			return actionLogDao.getActionLogList(log);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(log), e);
		}

		return null;
	}

	public IActionLogDao getActionLogDao() {
		return actionLogDao;
	}

	public void setActionLogDao(IActionLogDao actionLogDao) {
		this.actionLogDao = actionLogDao;
	}

}

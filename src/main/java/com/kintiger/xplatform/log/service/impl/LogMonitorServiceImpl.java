package com.kintiger.xplatform.log.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.log.ILogMonitorService;
import com.kintiger.xplatform.api.log.bo.LogMonitor;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.log.dao.ILogMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class LogMonitorServiceImpl implements ILogMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(LogMonitorServiceImpl.class);

	private ILogMonitorDao logMonitorDao;

	public int getLogMonitorCount(LogMonitor logMonitor) {
		try {
			return logMonitorDao.getLogMonitorCount(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return 0;
	}

	public List<LogMonitor> getLogMonitorList(LogMonitor logMonitor) {
		try {
			return logMonitorDao.getLogMonitorList(logMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitor), e);
		}

		return null;
	}

	public boolean createLogMonitor(List<LogMonitor> logMonitorList) {
		try {
			logMonitorDao.createLogMonitor(logMonitorList);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(logMonitorList), e);
		}

		return false;
	}

	public List<LogMonitor> getLogMonitorList4SendEmail() {
		try {
			return logMonitorDao.getLogMonitorList4SendEmail();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ILogMonitorDao getLogMonitorDao() {
		return logMonitorDao;
	}

	public void setLogMonitorDao(ILogMonitorDao logMonitorDao) {
		this.logMonitorDao = logMonitorDao;
	}

}

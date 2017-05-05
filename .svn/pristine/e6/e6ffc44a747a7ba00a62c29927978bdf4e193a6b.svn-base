package com.kintiger.xplatform.monitor.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.monitor.IMethodMonitorService;
import com.kintiger.xplatform.api.monitor.bo.MethodMonitor;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.monitor.dao.IMethodMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class MethodMonitorServiceImpl implements IMethodMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MethodMonitorServiceImpl.class);

	private IMethodMonitorDao methodMonitorDao;

	public int getMethodMonitorCount(MethodMonitor methodMonitor) {
		try {
			return methodMonitorDao.getMethodMonitorCount(methodMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(methodMonitor), e);
		}

		return 0;
	}

	public List<MethodMonitor> getMethodMonitorList(MethodMonitor methodMonitor) {
		try {
			return methodMonitorDao.getMethodMonitorList(methodMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(methodMonitor), e);
		}

		return null;
	}

	public BooleanResult createMethodMonitor(MethodMonitor methodMonitor) {
		BooleanResult res = new BooleanResult();
		res.setResult(false);
		res.setCode(IMethodMonitorService.ERROR_MESSAGE);

		try {
			String result = methodMonitorDao.createMethodMonitor(methodMonitor);
			res.setResult(true);
			res.setCode(result);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(methodMonitor), e);
		}

		return res;
	}

	public IMethodMonitorDao getMethodMonitorDao() {
		return methodMonitorDao;
	}

	public void setMethodMonitorDao(IMethodMonitorDao methodMonitorDao) {
		this.methodMonitorDao = methodMonitorDao;
	}

}

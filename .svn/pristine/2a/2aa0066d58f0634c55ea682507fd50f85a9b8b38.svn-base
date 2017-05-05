package com.kintiger.xplatform.sap.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.sap.ISAPLogService;
import com.kintiger.xplatform.api.sap.bo.SAPLog;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.sap.dao.ISAPLogDao;

/**
 * sap log service.
 * 
 * @author xujiakun
 * 
 */
public class SAPLogServiceImpl implements ISAPLogService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SAPLogServiceImpl.class);

	private ISAPLogDao sapLogDao;

	public boolean recordSAPLog(SAPLog sapLog) {
		try {
			sapLogDao.createSAPLog(sapLog);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sapLog), e);
		}

		return false;
	}

	public int getSAPLogCount(SAPLog sapLog) {
		try {
			return sapLogDao.getSAPLogCount(sapLog);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sapLog), e);
		}

		return 0;
	}

	public List<SAPLog> getSAPLogList(SAPLog sapLog) {
		try {
			return sapLogDao.getSAPLogList(sapLog);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sapLog), e);
		}

		return null;
	}

	public ISAPLogDao getSapLogDao() {
		return sapLogDao;
	}

	public void setSapLogDao(ISAPLogDao sapLogDao) {
		this.sapLogDao = sapLogDao;
	}

}

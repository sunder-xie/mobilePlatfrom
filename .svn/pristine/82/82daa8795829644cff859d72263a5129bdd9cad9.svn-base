package com.kintiger.xplatform.data.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.data.IDataLogService;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.data.dao.IDataLogDao;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * data log service.
 * 
 * @author xujiakun
 * 
 */
public class DataLogServiceImpl implements IDataLogService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataLogServiceImpl.class);

	private IDataLogDao dataLogDao;

	public int getDataLogTotalCount(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDataLogTotalCount(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return 0;
	}

	public List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDataLogTotalList(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return null;
	}

	public int getDBTableCount(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDBTableCount(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return 0;
	}

	public IDataLogDao getDataLogDao() {
		return dataLogDao;
	}

	public void setDataLogDao(IDataLogDao dataLogDao) {
		this.dataLogDao = dataLogDao;
	}

}

package com.kintiger.xplatform.monitor.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.cache.bo.CacheStats;
import com.kintiger.xplatform.api.monitor.ICacheMonitorService;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.monitor.dao.ICacheMonitorDao;

/**
 * cache monitor service.
 * 
 * @author xujiakun
 * 
 */
public class CacheMonitorServiceImpl implements ICacheMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(CacheMonitorServiceImpl.class);

	private ICacheMonitorDao cacheMonitorDao;

	public int getCacheMonitorCount(CacheStats cacheStats) {
		try {
			return cacheMonitorDao.getCacheMonitorCount(cacheStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cacheStats), e);
		}

		return 0;
	}

	public List<CacheStats> getCacheMonitorList(CacheStats cacheStats) {
		try {
			return cacheMonitorDao.getCacheMonitorList(cacheStats);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(cacheStats), e);
		}

		return null;
	}

	public BooleanResult createCacheMonitor(List<CacheStats> cacheStatsList) {
		BooleanResult res = new BooleanResult();

		try {
			String result = cacheMonitorDao.createCacheMonitor(cacheStatsList);
			res.setResult(true);
			res.setCode(result);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setCode(ICacheMonitorService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(cacheStatsList), e);
		}

		return res;
	}

	public ICacheMonitorDao getCacheMonitorDao() {
		return cacheMonitorDao;
	}

	public void setCacheMonitorDao(ICacheMonitorDao cacheMonitorDao) {
		this.cacheMonitorDao = cacheMonitorDao;
	}

}

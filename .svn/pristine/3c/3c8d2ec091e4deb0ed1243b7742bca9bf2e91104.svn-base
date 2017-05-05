package com.kintiger.xplatform.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.kintiger.xplatform.api.monitor.ISqlMonitorService;
import com.kintiger.xplatform.api.monitor.bo.SqlMonitor;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.monitor.dao.ISqlMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class SqlMonitorServiceImpl implements ISqlMonitorService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SqlMonitorServiceImpl.class);

	private ISqlMonitorDao sqlMonitorDao;

	public int execMonitorSql(String sql) {
		try {
			return sqlMonitorDao.execMonitorSql(sql);
		} catch (Exception e) {
			logger.error(sql, e);
		}

		return 0;
	}

	public List<Map<String, Object>> execMonitorSqlDetail(String sqlDetail) {
		try {
			return sqlMonitorDao.execMonitorSqlDetail(sqlDetail);
		} catch (Exception e) {
			logger.error(sqlDetail, e);
		}

		return null;
	}

	public int getSqlMonitorCount(SqlMonitor sqlMonitor) {
		try {
			return sqlMonitorDao.getSqlMonitorCount(sqlMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sqlMonitor), e);
		}

		return 0;
	}

	public List<SqlMonitor> getSqlMonitorList(SqlMonitor sqlMonitor) {
		try {
			return sqlMonitorDao.getSqlMonitorList(sqlMonitor);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sqlMonitor), e);
		}

		return null;
	}

	public BooleanResult createSqlMonitor(SqlMonitor sqlMonitor) {
		BooleanResult result = new BooleanResult();

		try {
			sqlMonitorDao.createSqlMonitor(sqlMonitor);
			result.setCode(sqlMonitor.getSqlMonitorId().toString());
			result.setResult(true);
		} catch (Exception e) {
			result.setCode(ISqlMonitorService.ERROR_MESSAGE);
			result.setResult(false);
			logger.error(LogUtil.parserBean(sqlMonitor), e);
		}

		return result;
	}

	public SqlMonitor getSqlMonitorById(Long sqlMonitorId) {
		try {
			return sqlMonitorDao.getSqlMonitorById(sqlMonitorId);
		} catch (Exception e) {
			logger.error(sqlMonitorId, e);
		}

		return null;
	}

	public BooleanResult updateSqlMonitor(SqlMonitor sqlMonitor) {
		BooleanResult result = new BooleanResult();
		result.setCode(ISqlMonitorService.ERROR_MESSAGE);
		result.setResult(false);

		try {
			int c = sqlMonitorDao.updateSqlMonitor(sqlMonitor);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sqlMonitor), e);
		}

		return result;
	}

	public ISqlMonitorDao getSqlMonitorDao() {
		return sqlMonitorDao;
	}

	public void setSqlMonitorDao(ISqlMonitorDao sqlMonitorDao) {
		this.sqlMonitorDao = sqlMonitorDao;
	}

}

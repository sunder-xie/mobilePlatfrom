package com.kintiger.xplatform.boform.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.boform.IBoformService;
import com.kintiger.xplatform.api.boform.bo.QueryParameter;
import com.kintiger.xplatform.api.boform.bo.ReportParameter;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.boform.dao.IBoformDao;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

public class BoformServiceImpl implements IBoformService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(BoformServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IBoformDao boformDao;

	public int getReportParameterCount(ReportParameter reportParameter) {
		try {
			return boformDao.getReportParameterCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<ReportParameter> getReportParameterList(ReportParameter reportParameter) {
		try {
			return boformDao.getReportParameterList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public StringResult createBatchReportParameter(List<ReportParameter> reportParameterList) {
		StringResult result = new StringResult();

		try {
			String ids = boformDao.createBatchReportParameter(reportParameterList);
			result.setResult(ids);
			result.setCode(IBoformService.SUCCESS);

			String[] bids = new String[reportParameterList.size()];
			int i = 0;
			for (ReportParameter reportParameter : reportParameterList) {
				bids[i++] = reportParameter.getBid().toString();
			}

			removeCache(bids);

		} catch (Exception e) {
			result.setCode(IBoformService.ERROR);
			result.setResult(IBoformService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(reportParameterList), e);
		}

		return result;
	}

	public ReportParameter getReportParameterByPid(Long pid) {
		try {
			return boformDao.getReportParameterByPid(pid);
		} catch (Exception e) {
			logger.error(pid, e);
		}

		return null;
	}

	public StringResult updateReportParameter(ReportParameter reportParameter) {
		StringResult result = new StringResult();
		result.setCode(IBoformService.ERROR);
		result.setResult(IBoformService.ERROR_MESSAGE);

		try {
			int c = boformDao.updateReportParameter(reportParameter);
			if (c == 1) {
				result.setCode(IBoformService.SUCCESS);

				String[] bids = new String[1];
				bids[0] = reportParameter.getBid().toString();

				removeCache(bids);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return result;
	}

	public StringResult deleteReportParameter(ReportParameter reportParameter) {
		StringResult result = new StringResult();
		result.setCode(IBoformService.ERROR);
		result.setResult(IBoformService.ERROR_MESSAGE);

		try {
			int c = boformDao.deleteReportParameter(reportParameter);
			result.setResult(String.valueOf(c));
			result.setCode(IBoformService.SUCCESS);

			removeCache(reportParameter.getBids());
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ReportParameter> getReportParametersByBid(Long bid) {
		if (bid == null) {
			return null;
		}

		List<ReportParameter> list = null;

		try {
			list =
				(List<ReportParameter>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_BO_PARAMETER + bid);
		} catch (ServiceException e) {
			logger.error(bid, e);
		}

		if (list == null || list.size() == 0) {
			try {
				list = boformDao.getReportParametersByBid(bid);
				if (list != null && list.size() > 0) {
					// 永不超时
					memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_BO_PARAMETER + bid, list,
						IMemcachedCacheService.CACHE_KEY_BO_PARAMETER_DEFAULT_EXP);
				}
			} catch (Exception e) {
				logger.error(bid, e);
			}
		}

		return list;
	}

	public int getQueryOrgCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryOrgCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryOrgList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public int getQueryAllChildOrgCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryAllChildOrgCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryAllChildOrgList(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryAllChildOrgList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	public int getQueryParameterCount(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryParameterCount(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return 0;
	}

	public List<QueryParameter> getQueryParameterList(ReportParameter reportParameter) {
		try {
			return boformDao.getQueryParameterList(reportParameter);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameter), e);
		}

		return null;
	}

	/**
	 * when create update then removeCache
	 * 
	 * @param bids
	 */
	private void removeCache(String[] bids) {
		try {
			for (String id : bids) {
				memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_BO_PARAMETER + id);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IBoformDao getBoformDao() {
		return boformDao;
	}

	public void setBoformDao(IBoformDao boformDao) {
		this.boformDao = boformDao;
	}

}

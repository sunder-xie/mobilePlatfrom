package com.kintiger.xplatform.org.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.org.IOrgService;
import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.org.dao.IOrgDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class OrgServiceImpl implements IOrgService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(OrgServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private IOrgDao orgDao;

	public List<Borg> getOrgListByUserId(String userId) {
		try {
			return orgDao.getOrgListByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<Borg> getOrgTreeListByOrgId(String orgId) {
		try {
			return orgDao.getOrgTreeListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	public Borg getOrgByOrgId(String orgId) {
		try {
			return orgDao.getOrgByOrgId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		List<Borg> list = null;
		try {
			list = (List<Borg>) memcachedCacheService.get("org.getOrgTreeListByPorgId_" + pOrgId);
		} catch (ServiceException e) {
			logger.error("org.getOrgTreeListByPorgId_" + pOrgId, e);
		}

		if (list == null || list.size() == 0) {
			try {
				list = orgDao.getOrgTreeListByPorgId(pOrgId);
				// not null then set to cache
				if (list != null && list.size() > 0) {
					// 1h 超时
					memcachedCacheService.set("org.getOrgTreeListByPorgId_" + pOrgId, list,
						IMemcachedCacheService.CACHE_KEY_ORG_DEFAULT_EXP);
				}
			} catch (Exception e) {
				logger.error(pOrgId, e);
			}
		}

		return list;
	}

	public BooleanResult createOrg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			Long orgId = orgDao.createOrg(borg);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(orgId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error(LogUtil.parserBean(borg), e);
		}

		return booleanResult;
	}

	public BooleanResult updateBorgWithADInfo(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();

		Borg org = new Borg();
		org.setOrgId(borg.getOrgId());
		org.setsAMAccountName(borg.getsAMAccountName());
		org.setAdGroupName(borg.getAdGroupName());
		try {
			orgDao.updateBorg(org);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error("updateBorgWithADInfo", e);
		}

		return booleanResult;
	}

	public BooleanResult updateBorg(Borg borg) {
		BooleanResult booleanResult = new BooleanResult();

		try {
			orgDao.updateBorg(borg);
			booleanResult.setResult(true);
		} catch (Exception e) {
			booleanResult.setResult(false);
			logger.error(LogUtil.parserBean(borg), e);
		}

		return booleanResult;
	}

	public int getCompanyListCount(Borg borg) {
		try {
			return orgDao.getCompanyListCount(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return 0;
	}

	public List<Borg> getCompanyList(Borg borg) {
		try {
			return orgDao.getCompanyList(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return null;
	}

	public Borg getOrgByUserId(String userId) {
		try {
			return orgDao.getOrgByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public Borg getFnUserOrg(String userId) {
		try {
			String orgId = orgDao.getFnUserOrg(userId).split(",")[0];
			return orgDao.getOrgByOrgId(orgId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<Borg> getAllParentOrgs(String orgId) {
		try {
			return orgDao.getAllParentOrgs(orgId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(orgId), e);
		}

		return null;
	}

	public int getOrgCount(Borg borg) {
		try {
			return orgDao.getOrgCount(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return 0;
	}

	public List<Borg> getOrgList(Borg borg) {
		try {
			return orgDao.getOrgList(borg);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(borg), e);
		}

		return null;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IOrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(IOrgDao orgDao) {
		this.orgDao = orgDao;
	}

}

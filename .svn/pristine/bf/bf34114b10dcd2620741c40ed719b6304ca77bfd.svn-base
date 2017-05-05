package com.kintiger.xplatform.station.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.api.station.IStationService;
import com.kintiger.xplatform.api.station.bo.Station;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.role.dao.IRoleDao;
import com.kintiger.xplatform.station.dao.IStationDao;

/**
 * station service.
 * 
 * @author xujiakun
 * 
 */
public class StationServiceImpl implements IStationService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(StationServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IStationDao stationDao;

	private IRoleDao roleDao;

	public List<Station> getStationJsonList(Station station) {
		try {
			return stationDao.getStationJsonList(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return null;
		}
	}

	public int getStationJsonListCount(Station station) {
		try {
			return stationDao.getStationJsonListCount(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return 0;
		}
	}

	public Station getStation(String stationId) {
		try {
			return stationDao.getStation(stationId);
		} catch (Exception e) {
			logger.error("stationId:" + stationId, e);
		}

		return null;
	}

	public int updateStation(Station station) {
		try {
			return stationDao.updateStation(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
		}

		return 0;
	}

	public BooleanResult deleteStation(final String stationId) {
		BooleanResult res = new BooleanResult();
		res.setResult(false);

		if (StringUtil.isEmpty(stationId) && StringUtil.isEmpty(stationId.trim())) {
			res.setCode("被删除权限岗位不能为空！");
			return res;
		}

		int count = 1;
		try {
			Station s = new Station();
			s.setStationId(stationId);
			count = stationDao.getStationUserCount(s);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stationId), e);
		}

		if (count != 0) {
			res.setCode("该权限岗位下有人，请先删除人员！");
			return res;
		}

		boolean o = (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				// delete station
				try {
					int i = stationDao.deleteStation(stationId);
					if (i != 1) {
						ts.setRollbackOnly();
						return false;
					}
				} catch (Exception e) {
					logger.error("stationId:" + stationId, e);
					ts.setRollbackOnly();
					return false;
				}

				// delete station role
				try {
					Role role = new Role();
					role.setStationId(stationId);
					roleDao.deleteSelectedRole4Station(role);
				} catch (Exception e) {
					logger.error("stationId:" + stationId, e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});

		if (o) {
			res.setResult(true);
			res.setCode("权限岗位删除成功！");
		} else {
			res.setCode("权限岗位删除失败！");
		}

		return res;
	}

	public String createStation(Station station) {
		try {
			return stationDao.createStation(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
		}

		return null;
	}

	public int getStationUserCount(Station station) {
		try {
			return stationDao.getStationUserCount(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return 0;
		}
	}

	public List<Station> getStationUserList(Station station) {
		try {
			return stationDao.getStationUserList(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return null;
		}
	}

	public int deleteStationUser(Station station) {
		try {
			return stationDao.deleteStationUser(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return 0;
		}
	}

	public int batchCreateStationUser(List<Station> stations) {
		try {
			return stationDao.batchCreateStationUser(stations);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stations), e);
		}

		return 0;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IStationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}

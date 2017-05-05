package com.kintiger.xplatform.data.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.data.IDataConfigService;
import com.kintiger.xplatform.api.data.bo.DataConfig;
import com.kintiger.xplatform.data.dao.IDataConfigDao;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * data config service.
 * 
 * @author xujiakun
 * 
 */
public class DataConfigServiceImpl implements IDataConfigService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataConfigServiceImpl.class);

	private IDataConfigDao dataConfigDao;

	public int getDataConfigCount(DataConfig dataConfig) {
		try {
			return dataConfigDao.getDataConfigCount(dataConfig);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataConfig), e);
		}

		return 0;
	}

	public List<DataConfig> getDataConfigList(DataConfig dataConfig) {
		try {
			return dataConfigDao.getDataConfigList(dataConfig);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataConfig), e);
		}

		return null;
	}

	public BooleanResult createDataConfig(List<DataConfig> dataConfigList) {
		BooleanResult res = new BooleanResult();

		try {
			String result = dataConfigDao.createDataConfig(dataConfigList);
			res.setResult(true);
			res.setCode(result);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setCode(IDataConfigService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(dataConfigList), e);
		}

		return res;
	}

	public BooleanResult updateDataConfig(DataConfig dataConfig) {
		BooleanResult res = new BooleanResult();

		try {
			int result = dataConfigDao.updateDataConfig(dataConfig);
			res.setResult(true);
			res.setCode(String.valueOf(result));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setCode(IDataConfigService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(dataConfig), e);
		}

		return res;
	}

	public IDataConfigDao getDataConfigDao() {
		return dataConfigDao;
	}

	public void setDataConfigDao(IDataConfigDao dataConfigDao) {
		this.dataConfigDao = dataConfigDao;
	}

}

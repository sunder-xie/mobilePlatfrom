package com.kintiger.xplatform.data.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.data.IDataService;
import com.kintiger.xplatform.api.data.bo.DataInfo;
import com.kintiger.xplatform.api.data.bo.DataLogDetail;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.api.data.bo.TabColumn;
import com.kintiger.xplatform.data.dao.IDataDao;
import com.kintiger.xplatform.data.dao.IDataLogDao;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * data service.
 * 
 * @author xujiakun
 * 
 */
public class DataServiceImpl implements IDataService {

	private static final String TAG_USER_ID = "userId:";

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IDataDao dataDao;

	private IDataLogDao dataLogDao;

	public List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId, String userId) {
		if (dataLogTotalId == null || StringUtil.isEmpty(userId)) {
			return null;
		}

		try {
			return dataDao.getTabColumnsByLogId(dataLogTotalId, userId);
		} catch (Exception e) {
			logger.error("dataConfigId:" + dataLogTotalId, e);
		}

		return null;
	}

	public List<TabColumn> getTabColumnsByConfigId(Long dataConfigId, String userId) {
		if (dataConfigId == null || StringUtil.isEmpty(userId)) {
			return null;
		}

		try {
			return dataDao.getTabColumnsByConfigId(dataConfigId, userId);
		} catch (Exception e) {
			logger.error("dataConfigId:" + dataConfigId + TAG_USER_ID + userId, e);
		}

		return null;
	}

	public BooleanResult createDataInfo(final Long dataConfigId, final List<DataInfo> dataInfoList) {

		return (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				BooleanResult res = new BooleanResult();
				res.setResult(false);

				String result = null;

				// create dataInfo
				try {
					result = dataDao.createDataInfo(dataInfoList);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(dataInfoList), e);
					status.setRollbackOnly();
					return res;
				}

				String[] results = result.split(",");

				DataLogTotal dataLogTotal = new DataLogTotal();
				dataLogTotal.setDataConfigId(dataConfigId);

				// create log total
				try {
					dataLogDao.createDataLogTotal(dataLogTotal);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(dataLogTotal), e);
					status.setRollbackOnly();
					return res;
				}

				List<DataLogDetail> dataLogDetailList = new ArrayList<DataLogDetail>();

				for (String s : results) {
					DataLogDetail detail = new DataLogDetail();
					detail.setDataLogTotalId(dataLogTotal.getDataLogTotalId());
					detail.setDataId(s);
					dataLogDetailList.add(detail);
				}

				// create log detail
				try {
					dataLogDao.createDataLogDetail(dataLogDetailList);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(dataLogDetailList), e);
					status.setRollbackOnly();
					return res;
				}

				res.setResult(true);
				return res;
			}
		});
	}

	public int getDataPreviewCount(DataLogTotal dataLogTotal) {
		try {
			return dataLogDao.getDataLogDetailCount(dataLogTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return 0;
	}

	/**
	 * 
	 * @param dataLogTotal
	 * @param userId
	 * @return
	 */
	public List<DataInfo> getDataPreviewList(DataLogTotal dataLogTotal) {
		try {
			List<TabColumn> tabColumns =
				dataDao.getTabColumnsByLogId(dataLogTotal.getDataLogTotalId(), dataLogTotal.getUserId());
			if (tabColumns == null || tabColumns.size() == 0) {
				return null;
			}

			dataLogTotal.setTableName(tabColumns.get(0).getTableName());
			dataLogTotal.setPrimaryKey(tabColumns.get(0).getPrimaryKey());

			List<Map<String, Object>> lists = dataDao.getDataPreviewList(dataLogTotal);
			if (lists == null || lists.size() == 0) {
				return null;
			}

			List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
			@SuppressWarnings("rawtypes")
			Class[] c = { String.class };

			for (Map<String, Object> map : lists) {
				DataInfo dataInfo = new DataInfo();
				int i = 1;

				for (TabColumn tabColumn : tabColumns) {
					Object value = map.get(tabColumn.getColumnName());

					Method method2 = BeanUtils.findMethod(dataInfo.getClass(), "setValue" + (i++), c);
					method2.invoke(dataInfo, new Object[] { value != null ? value.toString() : "" });
				}

				dataInfoList.add(dataInfo);
			}

			return dataInfoList;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataLogTotal), e);
		}

		return null;
	}

	public BooleanResult deleteDataInfo(final Long dataLogTotalId, final String userId) {

		return (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				BooleanResult res = new BooleanResult();
				res.setResult(false);

				int result = 0;
				List<TabColumn> tabColumns = null;

				try {
					tabColumns = dataDao.getTabColumnsByLogId(dataLogTotalId, userId);
				} catch (Exception e) {
					logger.error("dataLogTotalId:" + dataLogTotalId + TAG_USER_ID + userId, e);
					status.setRollbackOnly();
					return res;
				}

				// delete dataInfo
				try {
					TabColumn tabColumn = tabColumns.get(0);
					result =
						dataDao.deleteDataInfo(dataLogTotalId, userId, tabColumn.getTableName(),
							tabColumn.getPrimaryKey());
				} catch (Exception e) {
					logger.error("dataLogTotalId:" + dataLogTotalId + TAG_USER_ID + userId, e);
					status.setRollbackOnly();
					return res;
				}

				// update log total
				try {
					dataLogDao.updateDataLogTotal(dataLogTotalId, userId, "D");
				} catch (Exception e) {
					logger.error("dataLogTotalId:" + dataLogTotalId + TAG_USER_ID + userId, e);
					status.setRollbackOnly();
					return res;
				}

				res.setResult(true);
				res.setCode(String.valueOf(result));
				return res;
			}
		});
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IDataDao getDataDao() {
		return dataDao;
	}

	public void setDataDao(IDataDao dataDao) {
		this.dataDao = dataDao;
	}

	public IDataLogDao getDataLogDao() {
		return dataLogDao;
	}

	public void setDataLogDao(IDataLogDao dataLogDao) {
		this.dataLogDao = dataLogDao;
	}

}

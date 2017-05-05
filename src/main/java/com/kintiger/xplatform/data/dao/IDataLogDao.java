package com.kintiger.xplatform.data.dao;

import java.util.List;

import com.kintiger.xplatform.api.data.bo.DataLogDetail;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IDataLogDao {

	/**
	 * 
	 * @param dataLogTotalList
	 * @return
	 */
	Long createDataLogTotal(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogDetailList
	 * @return
	 */
	String createDataLogDetail(List<DataLogDetail> dataLogDetailList);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataLogTotalCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	List<DataLogTotal> getDataLogTotalList(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDBTableCount(DataLogTotal dataLogTotal);

	// List<CmsTbDict> getDBTableList(DataLogTotal dataLogTotal)

	/**
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataLogDetailCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	int updateDataLogTotal(Long dataLogTotalId, String userId, String flag);

}

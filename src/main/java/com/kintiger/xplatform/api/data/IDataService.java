package com.kintiger.xplatform.api.data;

import java.util.List;

import com.kintiger.xplatform.api.data.bo.DataInfo;
import com.kintiger.xplatform.api.data.bo.DataLogTotal;
import com.kintiger.xplatform.api.data.bo.TabColumn;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IDataService {

	String ERROR_MESSAGE = "操作失败！";

	/**
	 * 根据日志id查询对应表字段信息.
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByLogId(Long dataLogTotalId, String userId);

	/**
	 * 获取表字段信息.
	 * 
	 * @param dataConfigId
	 * @param userId
	 * @return
	 */
	List<TabColumn> getTabColumnsByConfigId(Long dataConfigId, String userId);

	/**
	 * 批量创建datainfo.
	 * 
	 * @param dataInfoList
	 * @return
	 */
	BooleanResult createDataInfo(Long dataConfigId, List<DataInfo> dataInfoList);

	/**
	 * 根据dataLogTotalId 数据预览.
	 * 
	 * @param dataLogTotal
	 * @return
	 */
	int getDataPreviewCount(DataLogTotal dataLogTotal);

	/**
	 * 
	 * @param dataLogTotal
	 * @param userId
	 * @return
	 */
	List<DataInfo> getDataPreviewList(DataLogTotal dataLogTotal);

	/**
	 * 删除数据.
	 * 
	 * @param dataLogTotalId
	 * @param userId
	 * @return
	 */
	BooleanResult deleteDataInfo(Long dataLogTotalId, String userId);

}

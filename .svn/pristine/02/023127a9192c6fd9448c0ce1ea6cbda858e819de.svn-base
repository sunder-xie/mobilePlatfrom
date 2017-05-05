package com.kintiger.xplatform.api.data;

import java.util.List;

import com.kintiger.xplatform.api.data.bo.DataConfig;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * dataConfig.
 * 
 * @author xujiakun
 * 
 */
public interface IDataConfigService {

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	int getDataConfigCount(DataConfig dataConfig);

	/**
	 * 
	 * @param dataConfig
	 * @return
	 */
	List<DataConfig> getDataConfigList(DataConfig dataConfig);

	/**
	 * createDataConfig.
	 * 
	 * @param dataConfigList
	 * @return
	 */
	BooleanResult createDataConfig(List<DataConfig> dataConfigList);

	/**
	 * updateDataConfig.
	 * 
	 * @param dataConfig
	 * @return
	 */
	BooleanResult updateDataConfig(DataConfig dataConfig);

}

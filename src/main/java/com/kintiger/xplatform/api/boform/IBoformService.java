package com.kintiger.xplatform.api.boform;

import java.util.List;

import com.kintiger.xplatform.api.boform.bo.QueryParameter;
import com.kintiger.xplatform.api.boform.bo.ReportParameter;
import com.kintiger.xplatform.framework.bo.StringResult;

/**
 * bo報表中心接口<br>
 * 包括報表參數配置 報表中心免登.
 * 
 * @author xujiakun
 * 
 */
public interface IBoformService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getReportParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	List<ReportParameter> getReportParameterList(ReportParameter reportParameter);

	/**
	 * 批量创建.
	 * 
	 * @param reportParameterList
	 * @return
	 */
	StringResult createBatchReportParameter(List<ReportParameter> reportParameterList);

	/**
	 * 根据主键查询.
	 * 
	 * @param pid
	 * @return
	 */
	ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 修改.
	 * 
	 * @param reportParameter
	 * @return
	 */
	StringResult updateReportParameter(ReportParameter reportParameter);

	/**
	 * 删除.
	 * 
	 * @param reportParameter
	 * @return
	 */
	StringResult deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 根据报表id查询报表参数.
	 * 
	 * @param bid
	 * @return
	 */
	List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 查询smsuser.b_saporg_smsorg.
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg.
	 * 
	 * @param reportParameter
	 * @return
	 */
	List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织.
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织.
	 * 
	 * @param reportParameter
	 * @return
	 */
	List<QueryParameter> getQueryAllChildOrgList(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getQueryParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	List<QueryParameter> getQueryParameterList(ReportParameter reportParameter);

}

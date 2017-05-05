package com.kintiger.xplatform.boform.dao;

import java.util.List;

import com.kintiger.xplatform.api.boform.bo.QueryParameter;
import com.kintiger.xplatform.api.boform.bo.ReportParameter;

public interface IBoformDao {

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
	 * 
	 * @param reportParameterList
	 * @return
	 */
	String createBatchReportParameter(List<ReportParameter> reportParameterList);

	/**
	 * 
	 * @param pid
	 * @return
	 */
	ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 
	 * 
	 * @param reportParameter
	 * @return
	 */
	int updateReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	int deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param bid
	 * @return
	 */
	List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
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

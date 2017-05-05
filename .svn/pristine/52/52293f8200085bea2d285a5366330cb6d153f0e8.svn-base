package com.kintiger.xplatform.boform.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.boform.bo.QueryParameter;
import com.kintiger.xplatform.api.boform.bo.ReportParameter;
import com.kintiger.xplatform.boform.dao.IBoformDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

public class BoformDaoImpl extends BaseDaoImpl implements IBoformDao {

	public int getReportParameterCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject("boform.getReportParameterCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<ReportParameter> getReportParameterList(ReportParameter reportParameter) {
		return (List<ReportParameter>) getSqlMapClientTemplate().queryForList("boform.getReportParameterList",
			reportParameter);
	}

	public String createBatchReportParameter(final List<ReportParameter> reportParameterList) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				for (ReportParameter reportParameter : reportParameterList) {
					if (sb.length() != 0) {
						sb.append(",");
					}
					sb.append(executor.insert("boform.createReportParameter", reportParameter));
				}

				executor.executeBatch();
				return sb.toString();
			}
		});
	}

	public ReportParameter getReportParameterByPid(Long pid) {
		return (ReportParameter) getSqlMapClientTemplate().queryForObject("boform.getReportParameterByPid", pid);
	}

	public int updateReportParameter(ReportParameter reportParameter) {
		return getSqlMapClientTemplate().update("boform.updateReportParameter", reportParameter);
	}

	public int deleteReportParameter(ReportParameter reportParameter) {
		return getSqlMapClientTemplate().delete("boform.deleteReportParameter", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<ReportParameter> getReportParametersByBid(Long bid) {
		return (List<ReportParameter>) getSqlMapClientTemplate().queryForList("boform.getReportParametersByBid", bid);
	}

	public int getQueryOrgCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject("boform.getQueryOrgCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter) {
		return (List<QueryParameter>) getSqlMapClientTemplate().queryForList("boform.getQueryOrgList", reportParameter);
	}

	public int getQueryAllChildOrgCount(ReportParameter reportParameter) {
		return (Integer) getSqlMapClientTemplate().queryForObject("boform.getQueryAllChildOrgCount", reportParameter);
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryAllChildOrgList(ReportParameter reportParameter) {
		return (List<QueryParameter>) getSqlMapClientTemplate().queryForList("boform.getQueryAllChildOrgList",
			reportParameter);
	}

	public int getQueryParameterCount(ReportParameter reportParameter) {
		if (reportParameter.getTxt() == 0) {
			return (Integer) getSqlMapClientTemplate()
				.queryForObject("boform.getQueryParameterCount0", reportParameter);
		}

		if (reportParameter.getTxt() == 1) {
			return (Integer) getSqlMapClientTemplate()
				.queryForObject("boform.getQueryParameterCount1", reportParameter);
		}

		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<QueryParameter> getQueryParameterList(ReportParameter reportParameter) {
		if (reportParameter.getTxt() == 0) {
			return (List<QueryParameter>) getSqlMapClientTemplate().queryForList("boform.getQueryParameterList0",
				reportParameter);
		}

		if (reportParameter.getTxt() == 1) {
			return (List<QueryParameter>) getSqlMapClientTemplate().queryForList("boform.getQueryParameterList1",
				reportParameter);
		}

		return null;
	}

}

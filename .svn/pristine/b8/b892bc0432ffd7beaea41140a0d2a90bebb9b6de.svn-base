package com.kintiger.xplatform.boform.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.framework.ISessionMgr;
import com.kintiger.xplatform.api.boform.IBoformService;
import com.kintiger.xplatform.api.boform.bo.QueryParameter;
import com.kintiger.xplatform.api.boform.bo.ReportParameter;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.DateUtil;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * BO报表中心.
 * 
 * @author xujiakun
 * 
 */
public class BoformAction extends BaseAction {

	private static final long serialVersionUID = -8241058216682442587L;

	private static final String BO_TYPE_BO3 = "BO3";

	private Logger4jExtend logger = Logger4jCollection.getLogger(BoformAction.class);

	private IBoformService boformService;

	private int total;

	private List<ReportParameter> reportParameterList;

	private String bid;

	private String pid;

	private ReportParameter reportParameter;

	private String bo3url;

	private String bo3use;

	private String bo3pwd;

	private String bo3dev;

	private String bo3enterp;

	private String bo4url;

	private String bo4use;

	private String bo4pwd;

	private String bo4dev;

	private String bo4enterp;

	private int year;

	private List<Integer> yearList;

	private String custName;

	private String custId = "0";

	private List<QueryParameter> queryParameterList;

	/**
	 * 报表类型：水晶易表.
	 */
	private String reportType;

	/**
	 * 区别bo3/bo4.
	 */
	private String boType;

	/**
	 * bo报表跳转地址.
	 */
	private String url;

	@Decode
	private String tableName;

	@Decode
	private String zdid;

	@Decode
	private String txt;

	@Decode
	private String zdtxt;

	@Decode
	private String d;

	@ActionLog(actionName = "报表参数查询")
	public String searchReportParameter() {
		return "searchReportParameter";
	}

	@JsonResult(field = "reportParameterList", include = { "pid", "bid", "tableName", "zdid", "zdtxt", "memo",
		"amount", "txt", "che", "d", "nickname", "checkway" }, total = "total")
	public String getReportParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r = getSearchInfo(r);

		try {
			if (StringUtil.isNotEmpty(bid) && StringUtil.isNotEmpty(bid.trim())) {
				r.setBid(Long.parseLong(bid.trim()));
			}
		} catch (Exception e) {
			logger.error("bid:" + bid, e);
		}

		total = boformService.getReportParameterCount(r);
		if (total != 0) {
			reportParameterList = boformService.getReportParameterList(r);
		}

		return JSON;
	}

	/**
	 * validate.
	 * 
	 * @param reportParameter
	 * @return
	 */
	private boolean validate(ReportParameter reportParameter) {

		if (reportParameter == null) {
			return false;
		}

		return true;
	}

	/**
	 * validate.
	 * 
	 * @param reportParameterList
	 * @return
	 */
	private boolean validate(List<ReportParameter> reportParameterList) {

		if (reportParameterList == null) {
			return false;
		}

		return true;
	}

	public String createReportParameterPrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 创建ReportParameter.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "报表参数创建")
	public String createReportParameter() {

		if (!validate(reportParameterList)) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		StringResult result = boformService.createBatchReportParameter(reportParameterList);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	public String updateReportParameterPrepare() {

		if (StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(pid.trim())) {
			try {
				reportParameter = boformService.getReportParameterByPid(Long.parseLong(pid.trim()));
			} catch (Exception e) {
				logger.error(pid, e);
				reportParameter = new ReportParameter();
			}
		}

		return UPDATE_PREPARE;
	}

	/**
	 * 修改ReportParameter
	 * 
	 * @return
	 */
	@ActionLog(actionName = "报表参数修改")
	public String updateReportParameter() {

		if (!validate(reportParameter)) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		StringResult result = boformService.updateReportParameter(reportParameter);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除ReportParameter
	 * 
	 * @return
	 */
	@ActionLog(actionName = "报表参数删除")
	public String deleteReportParameter() {

		String[] l = new String[reportParameterList.size()];
		String[] bids = new String[reportParameterList.size()];

		int i = 0;
		ReportParameter rep = new ReportParameter();

		try {
			for (ReportParameter r : reportParameterList) {
				if (r.getPid() != null) {
					l[i] = r.getPid().toString();
					bids[i] = r.getBid().toString();
					i++;
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameterList), e);
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 无有效的BO报表参数id
		if (i == 0) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		rep.setCodes(l);
		rep.setBids(bids);

		StringResult result = boformService.deleteReportParameter(rep);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个BO报表参数！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 跳转bo报表查看页面
	 * 
	 * @return
	 */
	public String showBoRptPrepare() {
		if (StringUtil.isNotEmpty(bid) && StringUtil.isNotEmpty(bid.trim())) {

			reportParameterList = boformService.getReportParametersByBid(Long.parseLong(bid.trim()));

			if (reportParameterList != null && reportParameterList.size() != 0) {
				total = reportParameterList.size();
			}
		}

		// 参数选择年份
		year = DateUtil.getYear();
		yearList = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			yearList.add(year - 3 + i);
		}

		return "showBoRptPrepare";
	}

	/**
	 * 打开bo报表
	 * 
	 * @return
	 */
	@ActionLog(actionName = "报表查询")
	public String showBoRpt() {

		StringBuffer n = new StringBuffer(BO_TYPE_BO3.equals(boType) ? bo3url : bo4url);
		n.append("?sWindow=same&iDocID=").append(bid).append("&token=").append(sso(boType));

		boolean f1 = true;
		boolean f2 = true;
		String s = null;
		String nickname = null;
		int amount;

		for (ReportParameter r : reportParameterList) {
			s = r.getMemo();

			// 20 多值一页显示n条记录
			// 修改 amount = 2 多值
			amount = r.getAmount() == 20 ? 2 : r.getAmount();
			nickname = r.getNickname();

			if ("1".equals(reportType) && f1) {
				n.append("&lsS").append(nickname).append("=");
				f1 = false;
			}

			if (StringUtil.isNotEmpty(s)) {
				if ("1".equals(reportType)) {
					if (f2) {
						f2 = false;
					} else {
						n.append(",");
					}
					n.append(s);
				} else {
					if (amount == 7 || amount == 1 || amount == 3 || amount == 5 || amount == 6) {
						n.append("&lsS");
					} else if (amount == 2 || amount == 4 || amount == 0) {
						n.append("&lsM");
					}
					n.append(nickname).append("=").append(s);
				}
			}
		}

		url = n.toString();

		return "showBoRpt";
	}

	/**
	 * BO报表 特殊查询条件(物料、工厂...)
	 * 
	 * @return
	 */
	public String searchQueryParameter() {
		// 根据pid查询 tableName, zdid, txt, zdtxt, d
		if (StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(pid.trim())) {
			try {
				reportParameter = boformService.getReportParameterByPid(Long.parseLong(pid.trim()));

				// 20 多值一页显示n条记录
				// 修改 amount = 2 多值
				if (reportParameter != null && reportParameter.getAmount() == 20) {
					this.setLimit(100);
				}
			} catch (Exception e) {
				logger.error(pid, e);
			}
		}

		return "searchQueryParameter";
	}

	@JsonResult(field = "queryParameterList", include = { "id", "text" }, total = "total")
	public String getQueryParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r = getSearchInfo(r);

		if (StringUtil.isNotEmpty(tableName) && StringUtil.isNotEmpty(tableName.trim())) {
			r.setTableName(tableName.trim());
		}

		if (StringUtil.isNotEmpty(txt) && StringUtil.isNotEmpty(txt.trim())) {
			try {
				r.setTxt(Integer.parseInt(txt.trim()));
			} catch (Exception e) {
				logger.error(txt, e);
			}
		}

		if (StringUtil.isNotEmpty(zdid) && StringUtil.isNotEmpty(zdid.trim())) {
			r.setZdid(zdid.trim());
		}

		if (StringUtil.isNotEmpty(zdtxt) && StringUtil.isNotEmpty(zdtxt.trim())) {
			r.setZdtxt(zdtxt.trim());
		}

		if (StringUtil.isNotEmpty(d) && StringUtil.isNotEmpty(d.trim())) {
			d = d.trim();
			r.setD(d);
		}

		if ("\"smsuser\".b_saporg_smsorg".equals(tableName)) {

			// Borg borg = orgService.getFnUserOrg(getUser().getUserId());

			String id = "";
			// borg.getOrgId().toString();

			if ("Q".equals(d) || "B".equals(d) || "H".equals(d) || "Y".equals(d)) {
				// id = orgService.getFnAllChildStrOrg(id);
				// r.setCodes(id.split(","));
				r.setCode(id);

				total = boformService.getQueryAllChildOrgCount(r);
				if (total != 0) {
					queryParameterList = boformService.getQueryAllChildOrgList(r);
				}
			} else {
				r.setCodes(id.split(","));

				total = boformService.getQueryOrgCount(r);
				if (total != 0) {
					queryParameterList = boformService.getQueryOrgList(r);
				}
			}

			return JSON;
		}

		total = boformService.getQueryParameterCount(r);
		if (total != 0) {
			queryParameterList = boformService.getQueryParameterList(r);
		}

		return JSON;
	}

	/**
	 * 单点登录BO
	 * 
	 * @param boType
	 * @return
	 */
	private String sso(String boType) {
		String token =
			(String) this.getSession().getAttribute(BO_TYPE_BO3.equals(boType) ? "LogonToken" : "Bo4LogonToken");

		if (StringUtil.isEmpty(token)) {
			try {
				ISessionMgr sessionMgr = CrystalEnterprise.getSessionMgr();
				IEnterpriseSession enterpriseSession =
					sessionMgr.logon(BO_TYPE_BO3.equals(boType) ? bo3use : bo4use, BO_TYPE_BO3.equals(boType) ? bo3pwd
						: bo4pwd, BO_TYPE_BO3.equals(boType) ? bo3dev : bo4dev, BO_TYPE_BO3.equals(boType) ? bo3enterp
						: bo4enterp);
				// 单点登录
				this.getSession().setAttribute(
					BO_TYPE_BO3.equals(boType) ? "EnterpriseSession" : "Bo4EnterpriseSession", enterpriseSession);

				token = enterpriseSession.getLogonTokenMgr().getDefaultToken();

				this.getSession().setAttribute(BO_TYPE_BO3.equals(boType) ? "LogonToken" : "Bo4LogonToken", token);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return token;
	}

	public IBoformService getBoformService() {
		return boformService;
	}

	public void setBoformService(IBoformService boformService) {
		this.boformService = boformService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ReportParameter> getReportParameterList() {
		return reportParameterList;
	}

	public void setReportParameterList(List<ReportParameter> reportParameterList) {
		this.reportParameterList = reportParameterList;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public ReportParameter getReportParameter() {
		return reportParameter;
	}

	public void setReportParameter(ReportParameter reportParameter) {
		this.reportParameter = reportParameter;
	}

	public String getBo3url() {
		return bo3url;
	}

	public void setBo3url(String bo3url) {
		this.bo3url = bo3url;
	}

	public String getBo3use() {
		return bo3use;
	}

	public void setBo3use(String bo3use) {
		this.bo3use = bo3use;
	}

	public String getBo3pwd() {
		return bo3pwd;
	}

	public void setBo3pwd(String bo3pwd) {
		this.bo3pwd = bo3pwd;
	}

	public String getBo3dev() {
		return bo3dev;
	}

	public void setBo3dev(String bo3dev) {
		this.bo3dev = bo3dev;
	}

	public String getBo3enterp() {
		return bo3enterp;
	}

	public void setBo3enterp(String bo3enterp) {
		this.bo3enterp = bo3enterp;
	}

	public String getBo4url() {
		return bo4url;
	}

	public void setBo4url(String bo4url) {
		this.bo4url = bo4url;
	}

	public String getBo4use() {
		return bo4use;
	}

	public void setBo4use(String bo4use) {
		this.bo4use = bo4use;
	}

	public String getBo4pwd() {
		return bo4pwd;
	}

	public void setBo4pwd(String bo4pwd) {
		this.bo4pwd = bo4pwd;
	}

	public String getBo4dev() {
		return bo4dev;
	}

	public void setBo4dev(String bo4dev) {
		this.bo4dev = bo4dev;
	}

	public String getBo4enterp() {
		return bo4enterp;
	}

	public void setBo4enterp(String bo4enterp) {
		this.bo4enterp = bo4enterp;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Integer> getYearList() {
		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public List<QueryParameter> getQueryParameterList() {
		return queryParameterList;
	}

	public void setQueryParameterList(List<QueryParameter> queryParameterList) {
		this.queryParameterList = queryParameterList;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getZdtxt() {
		return zdtxt;
	}

	public void setZdtxt(String zdtxt) {
		this.zdtxt = zdtxt;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

}

package com.kintiger.xplatform.org.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.org.IOrgService;
import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * org.
 * 
 * @author xujiakun
 */
public class OrgAction extends BaseAction {

	private static final long serialVersionUID = -1042982762738113436L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(OrgAction.class);

	private IOrgService orgService;

	private String orgName;
	private String orgId;
	private Borg borg;
	private Long orgParentId;
	private int total;
	private String orgParentName;

	private Long orgLevel;
	private List<Borg> companyList;
	private String orgUnit;
	private String txtmd;

	/**
	 * 组织查询.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "组织查询")
	public String searchOrg() {
		return "searchOrg";
	}

	/**
	 * 查询具体某个组织信息.
	 * 
	 * @return
	 */
	public String searchOrgDetail() {
		if (orgId != null) {
			borg = orgService.getOrgByOrgId(orgId);
		}

		return "searchOrgDetail";
	}

	public String createOrgPrepare() {
		try {
			this.orgParentName = new String(orgParentName.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(orgParentName, e);
		}
		orgLevel = orgLevel + 1;

		return CREATE_PREPARE;
	}

	@ActionLog(actionName = "组织创建")
	public String createOrg() {
		if (!borg.getOrgCity().equals("H")) {
			borg.setOrganiseType("Z"); // 公司需要标记为Z
		} else {
			borg.setOrganiseType("X");
		}
		borg.setCreateTime(new Date());
		borg.setLastModify(new Date());
		borg.setState("Y");

		// start获得AdGroupName和sAMAccountName
		String orgType = borg.getOrganiseType();
		String groupSN = borg.getShortName();
		String groupJP = borg.getJianPing();
		if (!"X".equals(orgType)) {
			Long pId = borg.getOrgParentId();
			String tempSN = orgService.getOrgByOrgId(String.valueOf(pId)).getAdGroupName();
			groupSN = tempSN + "_" + groupSN;
			String tempJP = orgService.getOrgByOrgId(String.valueOf(pId)).getsAMAccountName();
			groupJP = tempJP + "_" + groupJP;
		}
		borg.setAdGroupName(groupSN);
		borg.setsAMAccountName(groupJP);
		// end获得AdGroupName和sAMAccountName

		BooleanResult booleanResult = orgService.createOrg(borg);

		String result = "T";
		if (!booleanResult.getResult()) {
			result = "F";
		}

		if ("F".equals(result)) {
			this.setFailMessage("ORACLE端组织创建失败.");
		} else if ("T".equals(result.toString())) {
			this.setSuccessMessage("创建成功.");
		}

		return RESULT_MESSAGE;
	}

	// EXP组织
	public String expOrgCheck() {
		List<Borg> synOrgList = orgService.getOrgTreeListByPorgId(String.valueOf(borg.getOrgId()));
		if (synOrgList != null) {
			for (Borg org : synOrgList) {

				// start获得AdGroupName和sAMAccountName
				String orgType = org.getOrganiseType();
				String groupSN = org.getShortName();
				String groupJP = org.getJianPing();
				if (!"X".equals(orgType)) {
					Long pId = org.getOrgParentId();
					String tempSN = orgService.getOrgByOrgId(String.valueOf(pId)).getAdGroupName();
					groupSN = tempSN + "_" + groupSN;
					String tempJP = orgService.getOrgByOrgId(String.valueOf(pId)).getsAMAccountName();
					groupJP = tempJP + "_" + groupJP;
				}
				org.setAdGroupName(groupSN);
				org.setsAMAccountName(groupJP);
				// end获得AdGroupName和sAMAccountName
				BooleanResult booleanResult = orgService.updateBorgWithADInfo(org);
				if (booleanResult.getResult()) {
					this.setSuccessMessage("检查完毕.");
				} else {
					this.setFailMessage("在EXP[" + borg.getOrgName() + "]下更新子组织sAMAccountName,ADGroupName属性失败！");
				}

			}
		} else {
			this.setFailMessage("在EXP[" + borg.getOrgName() + "]下寻找不到任何子组织！");
		}
		this.setSuccessMessage("检查完毕.");
		return RESULT_MESSAGE;

	}

	@ActionLog(actionName = "组织修改")
	public String updateOrg() {
		this.setSuccessMessage("修改成功.");

		BooleanResult EXPbooleanResult = orgService.updateBorg(borg);
		if (!EXPbooleanResult.getResult()) {
			this.setFailMessage("EXP修改失败.");
		}

		return RESULT_MESSAGE;
	}

	@JsonResult(field = "companyList", include = { "orgId", "orgName" }, total = "total")
	public String getCompanyJsonList() {
		Borg org = new Borg();
		org = getSearchInfo(org);

		if (StringUtil.isNotEmpty(orgId) && StringUtil.isNotEmpty(orgId.trim())) {
			org.setOrgId(Long.valueOf(orgId));
		}
		if (StringUtil.isNotEmpty(orgName) && StringUtil.isNotEmpty(orgName.trim())) {
			org.setOrgName(orgName);
		}

		total = orgService.getCompanyListCount(org);
		if (total != 0) {
			companyList = orgService.getCompanyList(org);
		}

		return JSON;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public Borg getBorg() {
		return borg;
	}

	public void setBorg(Borg borg) {
		this.borg = borg;
	}

	public Long getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(Long orgParentId) {
		this.orgParentId = orgParentId;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public List<Borg> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getTxtmd() {
		return txtmd;
	}

	public void setTxtmd(String txtmd) {
		this.txtmd = txtmd;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

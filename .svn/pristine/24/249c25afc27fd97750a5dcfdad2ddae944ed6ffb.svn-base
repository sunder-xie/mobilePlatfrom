package com.kintiger.xplatform.org.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.org.IOrgService;
import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.api.tree.bo.Tree4Ajax;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.bo.StringArrayResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * OrgTreeAjax.
 * 
 * @author xujiakun
 * 
 */
public class OrgTreeAjaxAction extends BaseAction {

	private static final long serialVersionUID = 9124918976690173831L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(OrgTreeAjaxAction.class);

	private String node;

	private String orgId;

	private String orgName;

	private String sapOrgId;

	private String actionName;

	private List<Tree4Ajax> treeList;

	private IOrgService orgService;

	private StringArrayResult companyInfo = new StringArrayResult();

	private String userId;

	public String getOrgTreeMain() {
		return SUCCESS;
	}

	/**
	 * 组织树 点击组织 查看该组织详细信息.
	 * 
	 * @return
	 */
	public String getOrgTreeInfo() {
		// AllUsers users = this.getUser();
		this.actionName = "orgAction!searchOrgDetail.htm";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	public String getOrgTreeInfo4User() {
		// AllUsers users = this.getUser();
		this.actionName = env.getProperty("appUrl") + "/user/userAction!showUserByOrg.htm";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	public String toGetOrgTreeInfo4Position() {
		return "orgTreePosition";
	}

	public String getOrgTreeInfo4Position() {
		this.actionName = env.getProperty("appUrl") + "/user/userAction!toGetPositionList.htm";
		this.node = "1";
		return "orgTreeAjaxInfo";
	}

	/**
	 * 组织树 根据总部标记 判断是否显示整个组织树.
	 * 
	 * @return
	 */
	public String getOrgTree() {
		this.node = "-1";
		return "orgTreeAjax";
	}

	/**
	 * 组织树 显示整个组织树.
	 * 
	 * @return
	 */
	public String getOrgAllTree() {
		return "orgTreeAjax";
	}

	public String getOrgTree4UserChange() {
		return "orgTreeAjax4UserChange";
	}

	@JsonResult(field = "treeList", include = { "id", "text" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		try {
			if (StringUtil.isNotEmpty(node)) {
				if ("-1".equals(node)) {
					orgTreeList = orgService.getOrgListByUserId(getUser().getUserId());
				} else {
					orgTreeList = orgService.getOrgTreeListByPorgId(node);
				}
			}
		} catch (Exception e) {
			logger.error(node, e);
		}

		if (orgTreeList == null || orgTreeList.size() == 0) {
			return JSON;
		}

		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			// tree.setCls("folder");
			treeList.add(tree);
		}

		return JSON;
	}

	@JsonResult(field = "companyInfo", include = { "result", "code" })
	public String getCompany() {
		companyInfo.setCode("T");
		String[] result = new String[2];
		if (!StringUtil.isNotEmpty(orgId)) {
			companyInfo.setCode("F");
			result[0] = "未传入参数orgId";
		}
		Borg b = orgService.getOrgByOrgId(orgId);
		if (b.getCompanyName() != null) {
			result[0] = String.valueOf(b.getCompanyId());
			result[1] = String.valueOf(b.getCompanyName());
		} else {
			result[0] = String.valueOf("未查组织ID为" + orgId + "的公司");
		}
		companyInfo.setResult(result);
		return JSON;
	}

	public String getOrgNameByOrgid() {
		return "orgInfo";
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSapOrgId() {
		return sapOrgId;
	}

	public void setSapOrgId(String sapOrgId) {
		this.sapOrgId = sapOrgId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public StringArrayResult getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(StringArrayResult companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

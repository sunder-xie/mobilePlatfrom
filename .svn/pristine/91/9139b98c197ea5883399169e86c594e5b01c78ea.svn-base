package com.kintiger.xplatform.data.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.data.IDataConfigService;
import com.kintiger.xplatform.api.data.bo.DataConfig;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.JsonUtil;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * dataConfig.
 * 
 * @author xujiakun
 * 
 */
public class DataConfigAction extends BaseAction {

	private static final long serialVersionUID = 1683301014926124737L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(DataConfigAction.class);

	private IDataConfigService dataConfigService;

	private IUserService userService;

	private List<DataConfig> dataConfigList;

	private int total;

	private String itemId;

	private String flag;

	private String orgId;

	private String listJson;

	private String userIds;

	/**
	 * 
	 * @return
	 */
	public String searchDataConfig() {
		return "searchDataConfig";
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "dataConfigList", include = { "dataConfigId", "flag", "createDate", "modifyDate", "userName",
		"tableName", "sequenceValue", "primaryKey" }, total = "total")
	public String getDataConfigJsonList() {
		DataConfig s = new DataConfig();
		s = getSearchInfo(s);

		if (StringUtil.isNotEmpty(itemId)) {
			try {
				s.setItemId(Long.parseLong(itemId));
			} catch (NumberFormatException e) {
				logger.error("itemId:" + itemId, e);
			}
		}

		if (StringUtil.isNotEmpty(flag)) {
			s.setFlag(flag.trim());
		}

		total = dataConfigService.getDataConfigCount(s);
		if (total != 0) {
			dataConfigList = dataConfigService.getDataConfigList(s);
		}

		return JSON;
	}

	/**
	 * 
	 * @return
	 */
	public String createAuthorizePrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 
	 * @return
	 */
	public String createAuthorize() {

		if (StringUtil.isEmpty(userIds) || StringUtil.isEmpty(itemId)) {
			this.setFailMessage(IDataConfigService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		Long item = null;

		try {
			item = Long.parseLong(itemId);
		} catch (NumberFormatException e) {
			logger.error("itemId:" + itemId, e);
			this.setFailMessage(IDataConfigService.ERROR_MESSAGE);
			return RESULT_MESSAGE;
		}

		List<DataConfig> list = new ArrayList<DataConfig>();
		String[] ids = userIds.split(",");

		for (String id : ids) {
			DataConfig dataConfig = new DataConfig();
			dataConfig.setUserId(id);
			dataConfig.setItemId(item);
			list.add(dataConfig);
		}

		BooleanResult result = dataConfigService.createDataConfig(list);

		if (!result.getResult()) {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 
	 * @return
	 */
	public String cancelAuthorize() {

		String[] l = new String[dataConfigList.size()];
		int i = 0;
		DataConfig dataConfig = new DataConfig();

		try {
			for (DataConfig p : dataConfigList) {
				if (p.getDataConfigId() != null) {
					l[i++] = String.valueOf(p.getDataConfigId());
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dataConfigList), e);
			this.setFailMessage(IDataConfigService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 无有效的授权信息id
		if (i == 0) {
			this.setFailMessage(IDataConfigService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		dataConfig.setCodes(l);
		BooleanResult result = dataConfigService.updateDataConfig(dataConfig);

		if (result.getResult()) {
			this.setSuccessMessage("成功禁用" + result.getCode() + "个操作人员");
		} else {
			this.setFailMessage(result.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String getOrgPeopleTree() {
		this.actionName = "dataConfigAction!sendOrgToGetPeople.htm?itemId=" + itemId;
		return "orgTreeAjax";
	}

	public String sendOrgToGetPeople() {
		if (StringUtil.isNotEmpty(orgId)) {
			List<User> users = userService.getUsersByOrgId(orgId);

			if (users != null && users.size() > 0) {
				listJson = JsonUtil.bean2Json(users.getClass(), users);
				StringBuilder temp = new StringBuilder();
				temp.append(listJson);
				temp.insert(0, "{values:");
				temp.append(",total:");
				temp.append(users.size());
				temp.append("}");
				listJson = temp.toString();
			}
		}

		return "sendOrgToGetPeople";
	}

	public IDataConfigService getDataConfigService() {
		return dataConfigService;
	}

	public void setDataConfigService(IDataConfigService dataConfigService) {
		this.dataConfigService = dataConfigService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<DataConfig> getDataConfigList() {
		return dataConfigList;
	}

	public void setDataConfigList(List<DataConfig> dataConfigList) {
		this.dataConfigList = dataConfigList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

}

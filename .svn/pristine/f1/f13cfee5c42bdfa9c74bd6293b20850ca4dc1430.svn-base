package com.kintiger.xplatform.user.action;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.org.IOrgService;
import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 人员管理.
 * 
 * @author xujiakun
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 2259150998870496246L;

	private IUserService userService;

	private IOrgService orgService;

	private List<User> userList;

	private User user;

	private String userId;

	private String orgStr;

	private int total;

	@Decode
	private String userName;

	@Decode
	private String search;

	public String searchUserInfo() {
		user = userService.getUser(userId);

		if (user != null) {
			initOrgStr(user.getOrgId());
		}

		return "searchUserInfo";
	}

	/**
	 * 查询个人信息.
	 * 
	 * @return
	 */
	public String searchMyInfo() {
		User u = super.getUser();
		user = userService.getUser(u.getUserId());

		if (user != null) {
			initOrgStr(user.getOrgId());
		}

		return "searchMyInfo";
	}

	/**
	 * 通讯录.
	 * 
	 * @return
	 */
	public String searchAddressBook() {
		return "searchAddressBook";
	}

	@JsonResult(field = "userList", include = { "userId", "passport", "userName", "phone", "mobile", "email", "address" }, total = "total")
	public String getUserJsonList() {
		User u = new User();
		u = getSearchInfo(u);

		if (StringUtil.isNotEmpty(userName) && StringUtil.isNotEmpty(userName.trim())) {
			u.setUserName(userName.trim());
		}

		if (StringUtil.isNotEmpty(search) && StringUtil.isNotEmpty(search.trim())) {
			u.setSearch(search);
		}

		total = userService.getUserCount(u);
		if (total != 0) {
			userList = userService.getUserList(u);
		}

		return JSON;
	}

	/**
	 * 查询向上组织结构.sdada
	 * 
	 * @param orgId
	 */
	private void initOrgStr(String orgId) {
		StringBuilder o = new StringBuilder();

		List<Borg> orgs = orgService.getAllParentOrgs(orgId);
		if (orgs != null && orgs.size() > 0) {
			for (Borg org : orgs) {
				if (o.length() != 0) {
					o.append("\\");
				}
				o.append(org.getOrgName());
			}
			orgStr = o.toString();
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}

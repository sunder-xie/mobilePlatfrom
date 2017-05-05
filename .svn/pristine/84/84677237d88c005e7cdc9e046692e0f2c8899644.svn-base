package com.kintiger.xplatform.role.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.api.role.IRoleService;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.JsonUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 角色.
 * 
 * @author xujiakun
 * 
 */
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 2107317464113267380L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(RoleAction.class);

	private IRoleService roleService;

	private IUserService userService;

	private List<Role> roleList = new ArrayList<Role>();

	private int total;

	@Decode
	private String roleId;

	@Decode
	private String roleName;

	private Role role;

	/**
	 * 权限岗位.
	 */
	private String stationId;

	private String roleIds;

	/**
	 * 权限点.
	 */
	private String conpointId;

	/**
	 * 职位岗位.
	 */
	private String positionTypeId;

	/**
	 * 菜单id.
	 */
	private String menuId;

	/**
	 * 角色类型.
	 */
	private String type;

	private String passport;

	@Decode
	private String userName;

	private String orgId;

	private String listJson;

	private String id;

	/**
	 * 单 or 复数.
	 */
	private String userIds;

	/**
	 * 查询角色.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "角色查询")
	public String searchRole() {
		return "searchRole";
	}

	@JsonResult(field = "roleList", include = { "roleId", "roleName", "remark", "type", "menuCount", "stationCount",
		"itemName" }, total = "total")
	public String getRoleJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtil.isNotEmpty(roleName) && StringUtil.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(type.trim())) {
			r.setType(type.trim());
		}

		total = roleService.getRoleCount(r);
		if (total != 0) {
			roleList = roleService.getRoleList(r);
		}
		return JSON;
	}

	/**
	 * 查询指定userId的角色.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "人员的角色查询")
	public String searchUserRole() {
		return "searchUserRole";
	}

	/**
	 * 查询指定userId的角色清单.
	 * 
	 * @return
	 */
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "remark", "type", "itemName" }, total = "total")
	public String getUserRoleJsonList() {
		Role r = new Role();

		if (StringUtil.isNotEmpty(userIds) && StringUtil.isNotEmpty(userIds.trim())) {
			r.setUserId(userIds.trim());
		} else {
			return JSON;
		}

		roleList = roleService.getRoleListByUser(r);

		if (roleList != null && roleList.size() > 0) {
			total = roleList.size();
		} else {
			total = 0;
		}

		return JSON;
	}

	/**
	 * validate.
	 * 
	 * @param role
	 * @return
	 */
	private boolean validate(Role role) {
		if (role == null) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return false;
		}

		if (StringUtil.isEmpty(role.getRoleId()) || StringUtil.isEmpty(role.getRoleId().trim())
			|| StringUtil.isEmpty(role.getRoleName()) || StringUtil.isEmpty(role.getRoleName().trim())) {
			this.setFailMessage("角色编号和角色名称不能为空！");
			return false;
		}

		if (role.getRoleId().trim().length() > 30) {
			this.setFailMessage("角色编号长度不能超过30！");
			return false;
		}

		if (role.getRoleName().trim().length() > 80) {
			this.setFailMessage("角色编号长度不能超过80！");
			return false;
		}

		return true;
	}

	public String createRolePrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 创建角色.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "角色创建")
	public String createRole() {
		if (!validate(role)) {
			return RESULT_MESSAGE;
		}

		String id = role.getRoleId().substring(0, 1);
		if ("Z".equals(id) || "z".equals(id) || "Y".equals(id) || "y".equals(id)) {
			this.setFailMessage("Z和Y开头的角色编号为ERP中保留编号，请重新命名！");
			return RESULT_MESSAGE;
		}

		User user = this.getUser();
		if (user != null) {
			role.setModifyUser(user.getUserId());
		}

		StringResult result = roleService.createRole(role);

		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("角色创建成功！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 修改/查询角色信息.
	 * 
	 * @return
	 */
	public String updateRolePrepare() {
		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
				role = roleService.getRoleByRoleId(roleId);
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		role = role == null ? new Role() : role;

		return UPDATE_PREPARE;
	}

	@ActionLog(actionName = "角色修改")
	public String updateRole() {
		if (!validate(role)) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		User user = this.getUser();
		if (user != null) {
			role.setModifyUser(user.getUserId());
		}

		StringResult result = roleService.updateRole(role);

		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("角色修改成功！");
		}

		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "角色删除")
	public String deleteRole() {
		if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(roleId.trim())) {
			this.setFailMessage("被删除角色不能为空！");
			return RESULT_MESSAGE;
		}

		try {
			roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(roleId, e);
			this.setFailMessage("系统正忙，请稍后再试！");
			return RESULT_MESSAGE;
		}

		User user = this.getUser();

		StringResult result = roleService.deleteRole(roleId, user.getUserId());

		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 供权限点使用/供菜单使用.
	 * 
	 * @return
	 */
	public String searchRole4Config() {
		return "searchRole4Config";
	}

	/**
	 * 供权限点使用/供菜单使用.
	 * 
	 * @return
	 */
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "remark" }, total = "total")
	public String getRole4ConfigJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtil.isNotEmpty(roleName) && StringUtil.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtil.isNotEmpty(conpointId) && StringUtil.isNotEmpty(conpointId.trim())) {
			try {
				r.setConpointId(Long.parseLong(conpointId.trim()));
			} catch (Exception e) {
				logger.error("conpointId:" + conpointId, e);
				return JSON;
			}

			total = roleService.getRole4ConpointCount(r);
			if (total != 0) {
				roleList = roleService.getRole4ConpointList(r);
			}

		} else if (StringUtil.isNotEmpty(menuId) && StringUtil.isNotEmpty(menuId.trim())) {
			try {
				r.setMenuId(Long.parseLong(menuId.trim()));
			} catch (Exception e) {
				logger.error("menuId:" + menuId, e);
				return JSON;
			}

			total = roleService.getRole4MenuCount(r);
			if (total != 0) {
				roleList = roleService.getRole4MenuList(r);
			}

		}

		return JSON;
	}

	/**
	 * 选择角色 用于权限/职位岗位.
	 * 
	 * @return
	 */
	public String searchSelectedRole() {
		return "searchSelectedRole";
	}

	@JsonResult(field = "roleList", include = { "id", "roleId", "roleName", "remark", "menuCount" }, total = "total")
	public String getSelectedRoleJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtil.isNotEmpty(roleName) && StringUtil.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtil.isNotEmpty(stationId) && StringUtil.isNotEmpty(stationId.trim())) {
			r.setStationId(stationId.trim());

			total = roleService.getSelectedRole4StationCount(r);
			if (total != 0) {
				roleList = roleService.getSelectedRole4StationList(r);
			}
		} else if (StringUtil.isNotEmpty(positionTypeId) && StringUtil.isNotEmpty(positionTypeId.trim())) {
			r.setPositionTypeId(positionTypeId.trim());

			total = roleService.getSelectedRole4PositionTypeCount(r);
			if (total != 0) {
				roleList = roleService.getSelectedRole4PositionTypeList(r);
			}
		}

		return JSON;
	}

	/**
	 * 选择角色 用于权限/职位岗位.
	 * 
	 * @return
	 */
	public String selectRole() {
		Role r = new Role();
		StringResult result = null;

		if (StringUtil.isNotEmpty(roleIds) && StringUtil.isNotEmpty(roleIds.trim())) {

			String[] temp = roleIds.split(",");
			String[] ids = new String[temp.length];
			int i = 0;

			for (String t : temp) {
				ids[i++] = t.trim();
			}
			r.setCodes(ids);
		}

		if (StringUtil.isNotEmpty(stationId) && StringUtil.isNotEmpty(stationId.trim())) {
			r.setStationId(stationId.trim());
			result = roleService.selectRole4Station(r);
		} else if (StringUtil.isNotEmpty(positionTypeId) && StringUtil.isNotEmpty(positionTypeId.trim())) {
			r.setPositionTypeId(positionTypeId.trim());
			result = roleService.selectRole4PositionType(r);
		}

		if (result == null) {
			this.setFailMessage(IRoleService.ERROR_MESSAGE);
		} else if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else if (IRoleService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("成功维护角色编号：" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 选择角色 用于权限/职位岗位.
	 * 
	 * @return
	 */
	public String deleteSelectedRole() {
		String[] l = new String[roleList.size()];
		int i = 0;
		Role ro = new Role();
		String type = null;

		for (Role r : roleList) {
			if (r.getStationRoleId() != null) {
				l[i++] = r.getStationRoleId().toString();
				type = "station";
			} else if (r.getPyRoleId() != null) {
				l[i++] = r.getPyRoleId().toString();
				type = "positionType";
			}
		}

		// 无有效的角色id
		if (i == 0) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		ro.setCodes(l);

		StringResult result = null;

		if ("station".equals(type)) {
			result = roleService.deleteSelectedRole4Station(ro);
		} else if ("positionType".equals(type)) {
			result = roleService.deleteSelectedRole4PositionType(ro);
		}

		if (result == null) {
			this.setFailMessage(IRoleService.ERROR_MESSAGE);
		} else if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个角色！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 配置user_role.
	 * 
	 * @return
	 */
	public String configRoleUserPrepare() {
		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		if (StringUtil.isNotEmpty(roleName) && StringUtil.isNotEmpty(roleName.trim())) {
			try {
				roleName = new String(roleName.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleName, e);
			}
		}

		return "configRoleUserPrepare";
	}

	@JsonResult(field = "roleList", include = { "id", "userId", "passport", "userName" }, total = "total")
	public String getRoleUserJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(passport.trim())) {
			r.setPassport(passport.trim());
		}

		if (StringUtil.isNotEmpty(userName) && StringUtil.isNotEmpty(userName.trim())) {
			r.setUserName(userName.trim());
		}

		total = roleService.getRoleUserCount(r);
		if (total != 0) {
			roleList = roleService.getRoleUserList(r);
		}

		return JSON;
	}

	public String chooseRoleUser() {
		try {
			roleId = new String(roleId.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("roleId:" + roleId, e);
		}

		return "chooseRoleUser";
	}

	/**
	 * decode.
	 */
	private void decodeRoleId() {
		if (StringUtil.isNotEmpty(roleId)) {
			try {
				roleId = new String(roleId.getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("roleId:" + roleId, e);
			}
		}
	}

	/**
	 * 展开人员组织树.
	 * 
	 * @return
	 */
	public String getOrgPeopleTree() {
		decodeRoleId();

		this.actionName = "roleAction!getPeopleSelector.htm?roleId=" + roleId;
		return "orgTreeAjaxInfo";
	}

	public String getPeopleSelector() {
		decodeRoleId();

		if (StringUtil.isNotEmpty(orgId)) {
			List<User> users = userService.getUsersByOrgId(orgId);

			if (users != null && users.size() > 0) {
				listJson = JsonUtil.bean2Json(users.getClass(), users);
				StringBuffer temp = new StringBuffer();
				temp.append(listJson);
				temp.insert(0, "{values:");
				temp.append(",total:");
				temp.append(users.size());
				temp.append("}");
				listJson = temp.toString();
			}
		}

		return "peopleSelector";
	}

	/**
	 * 保存角色人员.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "角色人员配置")
	public String configRoleUser() {
		if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(userIds) || StringUtil.isEmpty(roleId.trim())
			|| StringUtil.isEmpty(userIds.trim())) {
			this.setFailMessage("角色编号和人员编号不能为空！");
			return RESULT_MESSAGE;
		}

		String[] ids = userIds.split(",");

		Role s = new Role();
		s.setRoleId(roleId.trim());
		s.setCodes(ids);
		List<Role> l = roleService.getRoleUserList(s);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (l != null && l.size() > 0) {
			for (Role ss : l) {
				map.put(ss.getUserId(), true);
			}
		}

		User user = this.getUser();
		String modifyUser = null;
		if (user != null) {
			modifyUser = user.getUserId();
		}

		List<Role> roles = new ArrayList<Role>();
		for (String userId : ids) {
			Role st = new Role();
			st.setRoleId(roleId.trim());
			st.setUserId(userId.trim());
			st.setModifyUser(modifyUser);
			if (!map.containsKey(userId)) {
				roles.add(st);
			}
		}

		if (roles.size() == 0) {
			this.setFailMessage("人员都已维护角色！");
			return RESULT_MESSAGE;
		}

		StringResult c = roleService.batchCreateRoleUser(roles);

		if (IRoleService.SUCCESS.equals(c.getCode())) {
			this.setSuccessMessage("已成功维护" + c.getResult() + "个人员角色！");
		} else {
			this.setFailMessage(c.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除角色人员.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "角色人员删除")
	public String deleteRoleUser() {
		Role ro = new Role();

		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
			ro.setId(id.trim());
		} else {
			this.setFailMessage("删除编号不能为空!");
			return RESULT_MESSAGE;
		}

		StringResult r = roleService.deleteRoleUser(ro);

		if (IRoleService.SUCCESS.equals(r.getCode())) {
			this.setSuccessMessage("角色人员删除成功!");
		} else {
			this.setFailMessage("角色人员删除失败!");
		}

		return RESULT_MESSAGE;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getConpointId() {
		return conpointId;
	}

	public void setConpointId(String conpointId) {
		this.conpointId = conpointId;
	}

	public String getPositionTypeId() {
		return positionTypeId;
	}

	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

}

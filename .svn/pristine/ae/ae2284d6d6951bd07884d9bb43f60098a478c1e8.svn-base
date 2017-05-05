package com.kintiger.xplatform.api.role;

import java.util.List;

import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.framework.bo.StringResult;

/**
 * role.
 * 
 * @author xujiakun
 * 
 */
public interface IRoleService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";

	/**
	 * 
	 * @param role
	 * @return
	 */
	int getRoleCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRoleList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	StringResult createRole(Role role);

	/**
	 * 批量更新role.
	 * 
	 * @param roles
	 * @return
	 */
	StringResult batchCreateRole(List<Role> roles);

	/**
	 * 批量维护user_role.
	 * 
	 * @param roles
	 * @return
	 */
	StringResult batchCreateRoleUser(List<Role> roles);

	/**
	 * 
	 * @param role
	 * @return
	 */
	StringResult updateRole(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	StringResult updateSAPRole(Role role);

	/**
	 * 删除非sap role.
	 * 
	 * @param roleId
	 * @param modifyUser
	 * @return
	 */
	StringResult deleteRole(String roleId, String modifyUser);

	/**
	 * 逻辑删除sap role.
	 * 
	 * @return
	 */
	StringResult deleteSAPRole();

	/**
	 * 权限点选择角色页面.
	 * 
	 * @param role
	 * @return
	 */
	int getRole4ConpointCount(Role role);

	/**
	 * 权限点选择角色页面.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRole4ConpointList(Role role);

	/**
	 * 菜单选择角色页面.
	 * 
	 * @param role
	 * @return
	 */
	int getRole4MenuCount(Role role);

	/**
	 * 菜单选择角色页面.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRole4MenuList(Role role);

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	Role getRoleByRoleId(String roleId);

	/**
	 * 获取权限岗位已选择的角色.
	 * 
	 * @param role
	 * @return
	 */
	int getSelectedRole4StationCount(Role role);

	/**
	 * 获取权限岗位已选择的角色.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4StationList(Role role);

	/**
	 * 根据权限岗位选择角色.
	 * 
	 * @param role
	 * @return
	 */
	StringResult selectRole4Station(Role role);

	/**
	 * 删除权限岗位已选择角色.
	 * 
	 * @param role
	 * @return
	 */
	StringResult deleteSelectedRole4Station(Role role);

	/**
	 * 删除已经无效的station_role.
	 * 
	 * @return
	 */
	StringResult deleteInvalidSelectedRole4Station(String roleType);

	/**
	 * 获取职位岗位已选择的角色.
	 * 
	 * @param role
	 * @return
	 */
	int getSelectedRole4PositionTypeCount(Role role);

	/**
	 * 获取职位岗位已选择的角色.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4PositionTypeList(Role role);

	/**
	 * 根据职位岗位选择角色.
	 * 
	 * @param role
	 * @return
	 */
	StringResult selectRole4PositionType(Role role);

	/**
	 * 删除职位岗位已选择角色.
	 * 
	 * @param role
	 * @return
	 */
	StringResult deleteSelectedRole4PositionType(Role role);

	/**
	 * 根据user获取role.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRoleListByUser(Role role);

	/**
	 * user_role.
	 * 
	 * @param role
	 * @return
	 */
	int getRoleUserCount(Role role);

	/**
	 * user_role.
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRoleUserList(Role role);

	/**
	 * 删除 user_role.
	 * 
	 * @param role
	 * @return
	 */
	StringResult deleteRoleUser(Role role);

}

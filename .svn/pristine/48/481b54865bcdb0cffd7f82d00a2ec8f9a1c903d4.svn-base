package com.kintiger.xplatform.role.dao;

import java.util.List;

import com.kintiger.xplatform.api.role.bo.Role;

/**
 * role dao.
 * 
 * @author xujiakun
 * 
 */
public interface IRoleDao {

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
	String createRole(Role role);

	/**
	 * 批量更新.
	 * 
	 * @param roles
	 * @return
	 */
	int batchCreateRole(List<Role> roles);

	int batchCreateRoleUser(List<Role> roles);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int updateRole(Role role);

	/**
	 * 删除角色.
	 * 
	 * @param roleId
	 * @param modifyUser
	 * @return
	 */
	int deleteRole(String roleId, String modifyUser);

	/**
	 * 删除sap角色.
	 * 
	 * @return
	 */
	int deleteSAPRole();

	/**
	 * 
	 * @param role
	 * @return
	 */
	int getRole4ConpointCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRole4ConpointList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int getRole4MenuCount(Role role);

	/**
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
	 * 
	 * @param role
	 * @return
	 */
	int getSelectedRole4StationCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4StationList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4Station(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	String selectRole4Station(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int deleteSelectedRole4Station(Role role);

	/**
	 * 
	 * @param roleType
	 * @return
	 */
	int deleteInvalidSelectedRole4Station(String roleType);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int getSelectedRole4PositionTypeCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4PositionTypeList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getSelectedRole4PositionType(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	String selectRole4PositionType(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int deleteSelectedRole4PositionType(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRoleListByUser(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int getRoleUserCount(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	List<Role> getRoleUserList(Role role);

	/**
	 * 
	 * @param role
	 * @return
	 */
	int deleteRoleUser(Role role);

}

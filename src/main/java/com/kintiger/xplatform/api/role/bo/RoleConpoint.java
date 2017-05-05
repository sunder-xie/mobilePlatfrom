package com.kintiger.xplatform.api.role.bo;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 角色權限點對象
 * 
 * @author xujiakun
 * 
 */
public class RoleConpoint extends SearchInfo {

	private static final long serialVersionUID = -4115974155472979238L;

	private Long roleConpointId;

	private String roleId;

	private String roleName;

	private Long conpointId;

	private String conpointName;

	private Long menuId;

	private String menuName;

	private String closeFlag;

	public Long getRoleConpointId() {
		return roleConpointId;
	}

	public void setRoleConpointId(Long roleConpointId) {
		this.roleConpointId = roleConpointId;
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

	public Long getConpointId() {
		return conpointId;
	}

	public void setConpointId(Long conpointId) {
		this.conpointId = conpointId;
	}

	public String getConpointName() {
		return conpointName;
	}

	public void setConpointName(String conpointName) {
		this.conpointName = conpointName;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(String closeFlag) {
		this.closeFlag = closeFlag;
	}

}

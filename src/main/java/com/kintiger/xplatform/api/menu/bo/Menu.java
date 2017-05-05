package com.kintiger.xplatform.api.menu.bo;

import java.util.List;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 
 * @author xujiakun
 * 
 */
public class Menu extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	private Long id;

	private String name;

	private Long pid;

	private String pname;

	private String url;

	private String target;

	private int orderBy;

	private String userId;

	private String redirectUrl;

	private String roleId;

	private Long roleMenuId;

	private List<Long> ids;

	private int roleCount;

	private int menuCount;

	/**
	 * 菜单系统类别 E: exp S: sap.
	 */
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public int getRoleCount() {
		return roleCount;
	}

	public void setRoleCount(int roleCount) {
		this.roleCount = roleCount;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

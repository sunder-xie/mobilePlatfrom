package com.kintiger.xplatform.api.user.bo;

import java.io.Serializable;



public class BaseMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String menuCode;
	private String menuDesc;
	private String menuValue;
	private String menuType; //
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuValue() {
		return menuValue;
	}
	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}



	
}

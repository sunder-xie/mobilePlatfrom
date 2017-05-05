package com.kintiger.xplatform.menu.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.login.ICAService;
import com.kintiger.xplatform.api.menu.IMenuService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * Menu.
 * 
 * @author xujiakun
 * 
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 7380054609278309516L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuAction.class);

	private IMenuService menuService;

	private ICAService caService;

	private List<Menu> menuList = new ArrayList<Menu>();

	private int total;

	private String id;

	private String pid;

	private Menu menu;

	private String isRedirect;

	@Decode
	private String pname;

	@Decode
	private String name;

	@Decode
	private String roleId;

	private String menuIds;

	private String node;

	private String redirectUrl;

	private String type;

	private String target;

	@ActionLog(actionName = "菜单查询")
	public String searchMenu() {
		return "searchMenu";
	}

	@JsonResult(field = "menuList", include = { "id", "pid", "name", "pname", "url", "target", "redirectUrl",
		"roleCount", "menuCount" }, total = "total")
	public String getMenuJsonList() {
		Menu m = new Menu();
		m = getSearchInfo(m);

		try {
			if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(pid.trim())) {
				m.setPid(Long.parseLong(pid.trim()));
			}
			if (StringUtil.isNotEmpty(this.getName()) && StringUtil.isNotEmpty(this.getName().trim())) {
				m.setName(this.getName().trim());
			}
			if (StringUtil.isNotEmpty(pname) && StringUtil.isNotEmpty(pname.trim())) {
				m.setPname(pname.trim());
			}
			if (StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(target.trim())) {
				m.setTarget(target.trim());
			}
		} catch (Exception e) {
			logger.error("id:" + id + "pid:" + pid + "name:" + this.getName() + "pname:" + pname, e);
		}

		total = menuService.getMenuCount(m);
		if (total != 0) {
			menuList = menuService.getMenuList(m);
		}

		return JSON;
	}

	/**
	 * validate.
	 * 
	 * @param menu
	 * @return
	 */
	private boolean validate(Menu menu) {
		if (menu == null) {
			return false;
		}

		if (menu.getPid() == null || StringUtil.isEmpty(menu.getName()) || StringUtil.isEmpty(menu.getName().trim())
			|| StringUtil.isEmpty(menu.getTarget()) || StringUtil.isEmpty(menu.getTarget().trim())) {
			return false;
		}

		return true;
	}

	public String createMenuPrepare() {
		return CREATE_PREPARE;
	}

	@ActionLog(actionName = "菜单创建")
	public String createMenu() {
		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl("/" + env.getProperty("appName") + IMenuService.MENU_REDIRECT_URL);
		}

		StringResult result = menuService.createMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("菜单创建成功!");
		}

		return RESULT_MESSAGE;
	}

	public String updateMenuPrepare() {

		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
			try {
				menu = menuService.getMenuById(Long.parseLong(id));
				return UPDATE_PREPARE;
			} catch (Exception e) {
				logger.error(id, e);
			}
		}

		menu = new Menu();
		return UPDATE_PREPARE;
	}

	@ActionLog(actionName = "菜单修改")
	public String updateMenu() {

		if (!validate(menu)) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		if ("y".equals(isRedirect)) {
			menu.setRedirectUrl(menu.getUrl());
			menu.setUrl("/" + env.getProperty("appName") + IMenuService.MENU_REDIRECT_URL + menu.getId());
		}

		StringResult result = menuService.updateMenu(menu);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("菜单修改成功!");
		}

		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "菜单删除")
	public String deleteMenu() {
		if (StringUtil.isEmpty(id)) {
			this.setFailMessage("被删除菜单不能为空！");
			return RESULT_MESSAGE;
		}

		Long menuId = null;
		try {
			menuId = Long.valueOf(id);
		} catch (NumberFormatException e) {
			logger.error("id:" + id, e);
			this.setFailMessage(IMenuService.ERROR_MESSAGE);
			return RESULT_MESSAGE;
		}

		StringResult result = menuService.deleteMenu(menuId);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage(result.getResult());
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 角色菜单查询.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "角色菜单查询")
	public String searchSelectedMenu4Role() {
		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchSelectedMenu4Role";
	}

	@JsonResult(field = "menuList", include = { "roleMenuId", "id", "pid", "name", "url", "target", "redirectUrl" }, total = "total")
	public String getSelectedMenu4RoleJsonList() {
		Menu m = new Menu();
		m = getSearchInfo(m);

		try {
			if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(id.trim())) {
				m.setId(Long.parseLong(id.trim()));
			}
			if (StringUtil.isNotEmpty(this.getName()) && StringUtil.isNotEmpty(this.getName().trim())) {
				m.setName(this.getName().trim());
			}
			if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
				m.setRoleId(roleId.trim());
			}

		} catch (Exception e) {
			logger.error("id:" + id + "name:" + this.getName() + "roleId" + roleId, e);
		}

		total = menuService.getSelectedMenu4RoleCount(m);
		if (total != 0) {
			menuList = menuService.getSelectedMenu4RoleList(m);
		}

		return JSON;
	}

	@ActionLog(actionName = "角色菜单配置")
	public String selectMenu4Role() {
		Menu m = new Menu();

		if (StringUtil.isNotEmpty(menuIds) && StringUtil.isNotEmpty(menuIds.trim())) {
			m.setCodes(menuIds.split(","));
		}

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			m.setRoleId(roleId.trim());
		}

		StringResult result = menuService.selectMenu4Role(m);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		if (IMenuService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("成功维护角色菜单编号：" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "角色菜单删除")
	public String deleteSelectedMenu4Role() {

		Menu me = new Menu();
		List<Long> ids = new ArrayList<Long>();

		try {
			for (Menu m : menuList) {
				if (m.getId() != null) {
					ids.add(m.getId());
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menuList), e);
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 无有效的菜单id
		if (ids.size() == 0) {
			this.setFailMessage(IMenuService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		me.setIds(ids);
		me.setRoleId(roleId);
		StringResult result = menuService.deleteSelectedMenu4Role(me);

		if (IMenuService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个菜单！");
		}

		return RESULT_MESSAGE;
	}

	public String redirectMenu() {
		if (StringUtil.isNotEmpty(node) && StringUtil.isNotEmpty(node.trim())) {
			try {
				Menu me = menuService.getMenuById(Long.parseLong(node));
				redirectUrl = me.getRedirectUrl();

				sso();

			} catch (Exception e) {
				logger.error(node, e);
			}
		}

		if (StringUtil.isEmpty(redirectUrl)) {
			redirectUrl = "/";
		}

		if (StringUtil.isNotEmpty(redirectUrl) && StringUtil.contains(redirectUrl, "?")) {
			return "menuRedirect2";
		} else {
			return "menuRedirect1";
		}
	}

	/**
	 * generateToken.
	 * 
	 * @param redirectUrl
	 */
	private void sso() {
		User user = this.getUser();

		try {
			token = caService.generateToken(user);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getIsRedirect() {
		return isRedirect;
	}

	public void setIsRedirect(String isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}

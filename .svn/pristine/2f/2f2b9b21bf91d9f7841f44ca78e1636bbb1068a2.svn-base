package com.kintiger.xplatform.menu.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.menu.IMenuService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.tree.bo.Tree4Ajax;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * MenuTreeAjax.
 * 
 * @author xujiakun
 * 
 */
public class MenuTreeAjaxAction extends BaseAction {

	private static final long serialVersionUID = -785317530988895673L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuTreeAjaxAction.class);

	private IMenuService menuService;

	private IMemcachedCacheService memcachedCacheService;

	private String node;

	private List<Tree4Ajax> treeList = new ArrayList<Tree4Ajax>();

	private String appName;

	@SuppressWarnings("unchecked")
	@JsonResult(field = "treeList", include = { "id", "text", "href", "hrefTarget", "leaf" })
	public String getMenuTreeListByAjax() {

		List<Menu> menuList = null;

		if (StringUtil.isNotEmpty(node)) {
			Menu menu = new Menu();
			User user = this.getUser();

			try {
				menu.setUserId(user.getUserId());
				menu.setPid(Long.parseLong(node));
			} catch (NumberFormatException e) {
				logger.error(LogUtil.parserBean(user) + "node:" + node, e);
			}

			List<Menu> o = null;

			if ("1".equals(node)) {
				try {
					o =
						(List<Menu>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_MENU_TREE
							+ user.getUserId() + "_" + node);
				} catch (ServiceException e) {
					logger.error(e);
				}
			}

			menuList = (o == null || o.size() == 0) ? menuService.getMenuTreeList(menu) : o;

			menu = null;

			if ("1".equals(node) && (o == null || o.size() == 0)) {
				try {
					memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_MENU_TREE + user.getUserId() + "_"
						+ node, menuList, IMemcachedCacheService.CACHE_KEY_MENU_TREE_DEFAULT_EXP);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}

		if (menuList == null || menuList.size() == 0) {
			return JSON;
		}

		for (Menu menu : menuList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(menu.getId()));
			tree.setText(menu.getName());
			if (StringUtil.isNotEmpty(menu.getUrl())) {
				// 拼接href地址
				tree.setHref("/" + appName + menu.getUrl());
			}
			tree.setHrefTarget(menu.getTarget());
			if ("NA".equals(tree.getHrefTarget())) {
				tree.setHrefTarget("mainLeft");
				tree.setLeaf(Boolean.FALSE);
			} else {
				tree.setLeaf(Boolean.TRUE);
			}
			// tree.setCls("folder")
			treeList.add(tree);
		}

		return JSON;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}

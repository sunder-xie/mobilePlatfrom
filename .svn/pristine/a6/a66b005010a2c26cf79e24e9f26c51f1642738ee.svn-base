package com.kintiger.xplatform.api.menu;

import java.util.List;

import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.framework.bo.StringResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IMenuService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";

	String MENU_REDIRECT_URL = "/menu/menuAction!redirectMenu.htm?node=";

	/**
	 * getMenuTreeList.
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getMenuTreeList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int getMenuCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getMenuList(Menu menu);

	/**
	 * createMenu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult createMenu(Menu menu);

	/**
	 * 批量创建menu (暂不用seq).
	 * 
	 * @param menus
	 * @return
	 */
	StringResult batchCreateMenu(List<Menu> menus);

	/**
	 * updateMenu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult updateMenu(Menu menu);

	/**
	 * getMenuById.
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenuById(Long id);

	/**
	 * 删除非sap菜单 － 递归删除menu及role_menu.
	 * 
	 * @param id
	 * @return
	 */
	StringResult deleteMenu(Long id);

	/**
	 * 删除sap菜单.
	 * 
	 * @return
	 */
	StringResult deleteInvalidSAPMenu();

	/**
	 * getSelectedMenu4RoleCount.
	 * 
	 * @param menu
	 * @return
	 */
	int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * getSelectedMenu4RoleList.
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * selectMenu4Role.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult selectMenu4Role(Menu menu);

	/**
	 * 批量更新role_menu.
	 * 
	 * @param menus
	 * @return
	 */
	StringResult batchSelectMenu4Role(List<Menu> menus);

	/**
	 * deleteSelectedMenu4Role.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult deleteSelectedMenu4Role(Menu menu);

	/**
	 * 删除已经无效的role_menu.
	 * 
	 * @return
	 */
	StringResult deleteInvalidSelectedMenu4Role();

	/**
	 * 根据role 批量删除role_menu.
	 * 
	 * @param menu
	 * @return
	 */
	StringResult batchDeleteSelectedMenu4Role(Menu menu);

	/**
	 * 获取request menu id.
	 * 
	 * @param userId
	 * @param actionName
	 * @return
	 */
	Menu getMenuRequest(String userId, String actionName);

}

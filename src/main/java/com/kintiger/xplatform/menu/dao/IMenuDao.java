package com.kintiger.xplatform.menu.dao;

import java.util.List;

import com.kintiger.xplatform.api.menu.bo.Menu;

/**
 * menu dao.
 * 
 * @author xujiakun
 * 
 */
public interface IMenuDao {

	/**
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
	 * 
	 * @param menu
	 * @return
	 */
	Long createMenu(Menu menu);

	/**
	 * 批量更新menu.
	 * 
	 * @param menus
	 * @return
	 */
	int batchCreateMenu(List<Menu> menus);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenuById(Long id);

	/**
	 * 递归删除所有菜单及子菜单.
	 * 
	 * @param id
	 * @return
	 */
	int recursiveDeleteMenu(Long id);

	/**
	 * 
	 * @return
	 */
	int deleteInvalidSAPMenu();

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int getSelectedMenu4RoleCount(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> getSelectedMenu4RoleList(Menu menu);

	/**
	 * 
	 * @param menu
	 * @return
	 */
	int deleteSelectedMenu4Role(Menu menu);

	/**
	 * 
	 * @return
	 */
	int deleteInvalidSelectedMenu4Role();

	/**
	 * 批量删除role_menu.
	 * 
	 * @param menu
	 * @return
	 */
	int batchDeleteSelectedMenu4Role(Menu menu);

	/**
	 * 递归删除所有菜单及子菜单 对应的role_menu.
	 * 
	 * @param id
	 * @return
	 */
	int recursiveDeleteSelectedMenu4Role(Long id);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	boolean checkSelectedMenu4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	Long selectMenu4Role(String roleId, Long menuId);

	/**
	 * 批量更新role_menu.
	 * 
	 * @param menus
	 * @return
	 */
	int batchSelectMenu4Role(List<Menu> menus);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	Long getParentMenuId4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	List<Long> getChildMenuId4Role(String roleId, Long menuId);

	/**
	 * 
	 * @param userId
	 * @param actionName
	 * @return
	 */
	Menu getMenuRequest(String userId, String actionName);

}

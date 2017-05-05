package com.kintiger.xplatform.menu.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.menu.dao.IMenuDao;

/**
 * menu dao.
 * 
 * @author xujiakun
 * 
 */
public class MenuDaoImpl extends BaseDaoImpl implements IMenuDao {

	private static final String ROLE_ID = "roleId";

	private static final String MENU_ID = "menuId";

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuTreeList(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList("menu.getMenuTreeList", menu);
	}

	public int getMenuCount(Menu menu) {
		return (Integer) getSqlMapClientTemplate().queryForObject("menu.getMenuCount", menu);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList("menu.getMenuList", menu);
	}

	public Long createMenu(Menu menu) {
		return (Long) getSqlMapClientTemplate().insert("menu.createMenu", menu);
	}

	public int batchCreateMenu(final List<Menu> menus) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Menu menu : menus) {
					executor.insert("menu.batchCreateMenu", menu);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	public int updateMenu(Menu menu) {
		return getSqlMapClientTemplate().update("menu.updateMenu", menu);
	}

	public Menu getMenuById(Long id) {
		return (Menu) getSqlMapClientTemplate().queryForObject("menu.getMenuById", id);
	}

	public int recursiveDeleteMenu(Long id) {
		return getSqlMapClientTemplate().delete("menu.recursiveDeleteMenu", id);
	}

	public int deleteInvalidSAPMenu() {
		return getSqlMapClientTemplate().delete("menu.deleteInvalidSAPMenu");
	}

	public int getSelectedMenu4RoleCount(Menu menu) {
		return (Integer) getSqlMapClientTemplate().queryForObject("menu.getSelectedMenu4RoleCount", menu);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getSelectedMenu4RoleList(Menu menu) {
		return (List<Menu>) getSqlMapClientTemplate().queryForList("menu.getSelectedMenu4RoleList", menu);
	}

	public int deleteSelectedMenu4Role(Menu menu) {
		return getSqlMapClientTemplate().delete("menu.deleteSelectedMenu4Role", menu);
	}

	public int deleteInvalidSelectedMenu4Role() {
		return getSqlMapClientTemplate().delete("menu.deleteInvalidSelectedMenu4Role");
	}

	public int batchDeleteSelectedMenu4Role(Menu menu) {
		return getSqlMapClientTemplate().delete("menu.batchDeleteSelectedMenu4Role", menu);
	}

	public int recursiveDeleteSelectedMenu4Role(Long id) {
		return getSqlMapClientTemplate().delete("menu.recursiveDeleteSelectedMenu4Role", id);
	}

	public boolean checkSelectedMenu4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(ROLE_ID, roleId);
		m.put(MENU_ID, menuId);

		int c = (Integer) getSqlMapClientTemplate().queryForObject("menu.checkSelectedMenu4Role", m);

		return c == 0 ? false : true;
	}

	public Long selectMenu4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(ROLE_ID, roleId);
		m.put(MENU_ID, menuId);

		return (Long) getSqlMapClientTemplate().insert("menu.selectMenu4Role", m);
	}

	public int batchSelectMenu4Role(final List<Menu> menus) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				Map<String, Object> m = new HashMap<String, Object>();
				executor.startBatch();
				for (Menu menu : menus) {
					m.put(ROLE_ID, menu.getRoleId());
					m.put(MENU_ID, menu.getId());
					executor.insert("menu.selectMenu4Role", m);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	public Long getParentMenuId4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(ROLE_ID, roleId);
		m.put(MENU_ID, menuId);

		return (Long) getSqlMapClientTemplate().queryForObject("menu.getParentMenuId4Role", m);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getChildMenuId4Role(String roleId, Long menuId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(ROLE_ID, roleId);
		m.put(MENU_ID, menuId);

		return (List<Long>) getSqlMapClientTemplate().queryForList("menu.getChildMenuId4Role", m);
	}

	public Menu getMenuRequest(String userId, String actionName) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("userId", userId);
		m.put("actionName", actionName);

		return (Menu) getSqlMapClientTemplate().queryForObject("menu.getMenuRequest", m);
	}

}

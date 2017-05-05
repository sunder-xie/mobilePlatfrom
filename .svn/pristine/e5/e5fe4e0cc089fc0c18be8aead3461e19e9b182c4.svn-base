package com.kintiger.xplatform.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.menu.IMenuService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.role.IRoleService;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.menu.dao.IMenuDao;

/**
 * menu service.
 * 
 * @author xujiakun
 * 
 */
public class MenuServiceImpl implements IMenuService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MenuServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IRoleService roleService;

	private IMenuDao menuDao;

	public List<Menu> getMenuTreeList(Menu menu) {
		try {
			return menuDao.getMenuTreeList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public int getMenuCount(Menu menu) {
		try {
			return menuDao.getMenuCount(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return 0;
	}

	public List<Menu> getMenuList(Menu menu) {
		try {
			return menuDao.getMenuList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public StringResult createMenu(Menu menu) {
		StringResult result = new StringResult();

		try {
			Long id = menuDao.createMenu(menu);
			result.setResult(id.toString());
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IMenuService.ERROR);
			result.setResult(IMenuService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public StringResult batchCreateMenu(List<Menu> menus) {
		StringResult result = new StringResult();

		if (menus == null || menus.size() == 0) {
			result.setResult("0");
			result.setCode(IMenuService.SUCCESS);
			return result;
		}

		try {
			int id = menuDao.batchCreateMenu(menus);
			result.setResult(String.valueOf(id));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IMenuService.ERROR);
			result.setResult(IMenuService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(menus), e);
		}

		return result;
	}

	public StringResult updateMenu(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		if (menu == null || menu.getId() == null) {
			result.setResult(IMenuService.ERROR_INPUT_MESSAGE);
			return result;
		}

		Menu m = getMenuById(menu.getId());
		if (m == null) {
			result.setResult("被修改菜单已不存在！");
			return result;
		}

		if ("S".equals(m.getType())) {
			result.setResult("ERP的菜单只能通过同步ERP菜单方式修改！");
			return result;
		}

		try {
			int c = menuDao.updateMenu(menu);
			if (c == 1) {
				result.setCode(IMenuService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public Menu getMenuById(Long id) {
		try {
			return menuDao.getMenuById(id);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(id), e);
		}

		return null;
	}

	public StringResult deleteMenu(final Long id) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		if (id == null) {
			return result;
		}

		Menu menu = getMenuById(id);
		if (menu == null) {
			result.setResult("被删除菜单已不存在！");
			return result;
		}

		if ("S".equals(menu.getType())) {
			result.setResult("ERP的菜单只能通过同步ERP菜单方式删除！");
			return result;
		}

		boolean o = (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				// 1 递归 id 下所有 ids -> 删除所有 role_menu
				try {
					menuDao.recursiveDeleteSelectedMenu4Role(id);
				} catch (Exception e) {
					logger.error("id:" + id, e);
					ts.setRollbackOnly();
					return false;
				}

				// 2 删除 id 下所有 ids
				try {
					menuDao.recursiveDeleteMenu(id);
				} catch (Exception e) {
					logger.error("id:" + id, e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});

		if (o) {
			result.setCode(IMenuService.SUCCESS);
			result.setResult("菜单删除成功！");
		} else {
			result.setResult("菜单删除失败！");
		}

		return result;
	}

	public StringResult deleteInvalidSAPMenu() {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.deleteInvalidSAPMenu();
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public int getSelectedMenu4RoleCount(Menu menu) {
		try {
			return menuDao.getSelectedMenu4RoleCount(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return 0;
	}

	public List<Menu> getSelectedMenu4RoleList(Menu menu) {
		try {
			return menuDao.getSelectedMenu4RoleList(menu);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return null;
	}

	public StringResult selectMenu4Role(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			final String roleId = menu.getRoleId();
			// 验证roleId是否属于sap role
			if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(roleId.trim())) {
				return result;
			}

			Role r = roleService.getRoleByRoleId(roleId.trim());
			if (r == null) {
				result.setResult("被维护角色已不存在！");
				return result;
			}

			if ("S".equals(r.getType())) {
				result.setResult(roleId + "角色的菜单不允许手工修改！");
				return result;
			}

			StringBuilder ids = new StringBuilder();

			// 遍历选择的菜单 保证每一个菜单创建后是可用有效的（角色对应的菜单树是完整）
			for (String code : menu.getCodes()) {

				final Long menuId = Long.parseLong(code.trim());

				// 验证角色下该菜单是否存在
				boolean b = menuDao.checkSelectedMenu4Role(roleId, menuId);
				if (b) {
					continue;
				}

				Object o = transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						Long roleMenuId;
						// 1st 创建角色菜单
						try {
							roleMenuId = menuDao.selectMenu4Role(roleId, menuId);
						} catch (Exception e) {
							logger.error("roleId:" + roleId + "menuId:" + menuId, e);
							ts.setRollbackOnly();
							return null;
						}

						// 2nd 创建角色菜单的上一级菜单 pid == -1
						Long id = menuId;
						do {
							try {
								id = menuDao.getParentMenuId4Role(roleId, id);

								if (id == null) {
									break;
								}

								menuDao.selectMenu4Role(roleId, id);
							} catch (Exception e) {
								logger.error("roleId:" + roleId + "id:" + id, e);
								ts.setRollbackOnly();
								return null;
							}

						} while (true);

						// 返回角色菜单id
						return roleMenuId;
					}
				});

				if (o != null) {
					if (ids.length() != 0) {
						ids.append(",");
					}
					ids.append(o);
				}
			}

			result.setResult(ids.toString());
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public StringResult batchSelectMenu4Role(List<Menu> menus) {
		StringResult result = new StringResult();

		if (menus == null || menus.size() == 0) {
			result.setResult("0");
			result.setCode(IMenuService.SUCCESS);
			return result;
		}

		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.batchSelectMenu4Role(menus);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menus), e);
		}

		return result;
	}

	public StringResult deleteSelectedMenu4Role(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		String roleId = menu.getRoleId();
		// 验证roleId是否属于sap role
		if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(roleId.trim())) {
			return result;
		}

		Role r = roleService.getRoleByRoleId(roleId.trim());
		if (r == null) {
			result.setResult("被维护角色已不存在！");
			return result;
		}

		if ("S".equals(r.getType())) {
			result.setResult(roleId + "角色的菜单不允许手工删除！");
			return result;
		}

		try {
			List<Long> menuIds = conversion(roleId, menu.getIds());

			String[] l = new String[menuIds.size()];
			int i = 0;

			for (Long id : menuIds) {
				l[i++] = id.toString();
			}

			menu.setCodes(l);
			int c = menuDao.deleteSelectedMenu4Role(menu);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	/**
	 * 
	 * @param roleId
	 * @param ids
	 * @return
	 */
	private List<Long> conversion(String roleId, List<Long> ids) {
		List<Long> menuIds = new ArrayList<Long>();
		List<Long> childIds = null;

		for (Long id : ids) {
			menuIds.add(id);

			childIds = menuDao.getChildMenuId4Role(roleId, id);

			if (childIds != null && childIds.size() != 0) {
				menuIds.addAll(conversion(roleId, childIds));
			}
		}

		return menuIds;
	}

	public StringResult deleteInvalidSelectedMenu4Role() {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.deleteInvalidSelectedMenu4Role();
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public StringResult batchDeleteSelectedMenu4Role(Menu menu) {
		StringResult result = new StringResult();
		result.setCode(IMenuService.ERROR);
		result.setResult(IMenuService.ERROR_MESSAGE);

		try {
			int c = menuDao.batchDeleteSelectedMenu4Role(menu);
			result.setResult(String.valueOf(c));
			result.setCode(IMenuService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(menu), e);
		}

		return result;
	}

	public Menu getMenuRequest(String userId, String actionName) {
		try {
			return menuDao.getMenuRequest(userId, actionName);
		} catch (Exception e) {
			logger.error("userId:" + userId + "actionName:" + actionName, e);
		}

		return null;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

}

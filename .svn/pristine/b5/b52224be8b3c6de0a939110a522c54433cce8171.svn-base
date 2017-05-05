package com.kintiger.xplatform.sap.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.menu.IMenuService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.role.IRoleService;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.api.sap.ISAPAccountService;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.sap.ISAPSyncService;
import com.kintiger.xplatform.api.sap.bo.SAPAccount;
import com.kintiger.xplatform.api.sap.bo.SAPConnectionBean;
import com.kintiger.xplatform.framework.annotation.Profiler;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.sap.mw.jco.JCO;

/**
 * sap sync service.
 * 
 * @author xujiakun
 * 
 */
public class SAPSyncServiceImpl implements ISAPSyncService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SAPSyncServiceImpl.class);

	private SAPConnectionBean sapConnection;

	private IRoleService roleService;

	private IMenuService menuService;

	private TransactionTemplate transactionTemplate;

	private ISAPAccountService sapAccountService;

	private ISAPService sapService;

	private IDictService dictService;

	@Profiler
	public String syncSAPRole() {
		// 获取需要同步sap role
		List<Role> roles = getSAPRoles();

		if (roles == null || roles.size() == 0) {
			return "成功同步 0 个ERP角色";
		}

		int dCount = 0;
		int uCount = 0;
		int iCount = 0;

		// 1 将exp中所有sap相关角色都标记删除
		StringResult result = roleService.deleteSAPRole();
		if (IRoleService.SUCCESS.equals(result.getCode())) {
			dCount = Integer.parseInt(result.getResult());
		}

		// 2 遍历roles update exp role where role_id = sap role
		// 验证update结果 ＝＝ 0 则记录该sap role 需要insert
		List<Role> roles4insert = new ArrayList<Role>();
		for (Role role : roles) {
			role.setState("U");
			role.setModifyUser("system");
			result = roleService.updateSAPRole(role);
			if (!IRoleService.SUCCESS.equals(result.getCode())) {
				roles4insert.add(role);
			} else {
				dCount--;
				uCount++;
			}
		}

		// 3 批量更新
		if (roles4insert.size() > 0) {
			StringResult r = roleService.batchCreateRole(roles4insert);
			if (IRoleService.SUCCESS.equals(r.getCode())) {
				iCount = Integer.parseInt(r.getResult());
			}
		}

		// 4 处理 无效的station_role role_menu menu
		roleService.deleteInvalidSelectedRole4Station("S");

		menuService.deleteInvalidSelectedMenu4Role();

		syncSAPTcode();

		return "成功新增 " + iCount + " 个ERP角色" + "，更新 " + uCount + " 个ERP角色" + "，删除 " + dCount + " 个ERP角色";
	}

	/**
	 * 获取需要同步sap role.
	 * 
	 * @return
	 */
	private List<Role> getSAPRoles() {
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("ZRFC_WES_ROLE_MOVE");
			JCO.Function func = this.sapConnection.getFunction(client);
			// 执行
			client.execute(func);

			JCO.Table table = func.getTableParameterList().getTable("ROLES");
			if (!table.isEmpty()) {
				List<Role> roles = new ArrayList<Role>();
				do {
					Role role = new Role();
					role.setRoleId((String) table.getValue("AGR_NAME"));
					role.setRoleName((String) table.getValue("TEXT"));
					role.setState("U");
					// sap role type
					role.setType("S");
					roles.add(role);
				} while (table.nextRow());

				return roles;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}

		return null;
	}

	@Profiler
	public int syncSAPTcode() {
		return syncSAPTcode(null);
	}

	@Profiler
	public int syncSAPTcode(String roleId) {
		int count = 0;

		// 分页获取sap role
		Role role = new Role();
		role.setType("S");
		role.setSort("roleId");
		role.setDir("DESC");

		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(roleId.trim())) {
			role.setRoleId(roleId.trim());
		}

		int c = roleService.getRoleCount(role);

		// 不存在sap role
		if (c == 0) {
			return 0;
		}

		for (int i = 0; i < c; i = i + 10) {
			role.setStart(i);
			role.setLimit(10);
			final List<Role> roles = roleService.getRoleList(role);
			if (roles != null && roles.size() > 0) {
				int o = (Integer) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						int c = 0;

						// 1 同步rfc
						List<Menu> menus = getSAPTcodes(roles);

						// 2 物理删除所有role_menu数据
						Menu menu = new Menu();
						String[] l = new String[roles.size()];
						int i = 0;
						for (Role role : roles) {
							l[i++] = role.getRoleId();
						}

						menu.setCodes(l);
						StringResult r = null;
						r = menuService.batchDeleteSelectedMenu4Role(menu);
						if (IMenuService.ERROR.equals(r.getCode())) {
							return 0;
						}

						// 3 同步 tcode -> menu
						r = menuService.batchCreateMenu(menus);
						if (IMenuService.ERROR.equals(r.getCode())) {
							ts.setRollbackOnly();
							return 0;
						} else {
							c = Integer.parseInt(r.getResult());
						}

						// 4 同步 role_tcode -> role-menu
						menuService.batchSelectMenu4Role(menus);
						if (IMenuService.ERROR.equals(r.getCode())) {
							ts.setRollbackOnly();
							return 0;
						}

						return c;
					}
				});

				// 5 验证所有菜单 找出所有没有关联role的菜单 调用menuService删除menu
				menuService.deleteInvalidSAPMenu();

				count = count + o;
			}
		}

		return count;
	}

	private List<Menu> getSAPTcodes(List<Role> roles) {
		if (roles == null || roles.size() == 0) {
			return null;
		}

		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("ZRFC_WES_TCODE_MOVE");
			JCO.Function func = this.sapConnection.getFunction(client);
			// 输入参数
			JCO.Table table1 = func.getTableParameterList().getTable("ROLES");
			int tableNum = 0;
			for (Role role : roles) {
				table1.appendRow();
				table1.setRow(tableNum);
				table1.setValue(role.getRoleId(), "AGR_NAME");
				tableNum++;
			}
			// 执行
			client.execute(func);

			// 输出
			JCO.Table table2 = func.getTableParameterList().getTable("ROLE_TCODE");
			// 不存在tcode
			if (table2.isEmpty()) {
				return null;
			}

			List<Menu> menus = new ArrayList<Menu>();
			Map<String, String> map = new HashMap<String, String>();
			String folder;
			String tcode;
			String wesMenuId = getWESMenuId();
			if (StringUtil.isEmpty(wesMenuId)) {
				return null;
			}

			do {
				Menu menu = new Menu();
				menu.setRoleId((String) table2.getValue("AGR_NAME"));
				String t = map.get(menu.getRoleId());
				if (StringUtil.isEmpty(t)) {
					// 生成唯一标示id
					t = String.valueOf(System.nanoTime()).substring(5, 15);
					map.put(menu.getRoleId(), t);

					Menu me = new Menu();
					me.setRoleId(menu.getRoleId());
					me.setId(Long.parseLong(t + Long.parseLong((String) table2.getValue("PARENT_ID"))));
					// WES menu_id
					me.setPid(Long.valueOf(wesMenuId));
					me.setOrderBy(9);
					me.setName(menu.getRoleId());
					me.setTarget("NA");
					// sap 菜单
					me.setType("S");
					menus.add(me);
				}

				menu.setId(Long.parseLong(t + Long.parseLong((String) table2.getValue("OBJECT_ID"))));
				menu.setPid(Long.parseLong(t + Long.parseLong((String) table2.getValue("PARENT_ID"))));
				menu.setOrderBy(Integer.parseInt((String) table2.getValue("SORT_ORDER")));

				folder = (String) table2.getValue("FOLDER");
				// 文件夹
				if ("X".equals(folder)) {
					menu.setName((String) table2.getValue("TEXT"));
					menu.setTarget("NA");
				} else {
					tcode = (String) table2.getValue("REPORT");
					menu.setName((String) table2.getValue("TEXT") + " - " + tcode);
					menu.setUrl("/sap/sapAction!tcode.htm?tcode=" + tcode);
					menu.setTarget("mainRight");
				}
				// sap 菜单
				menu.setType("S");
				menus.add(menu);
			} while (table2.nextRow());

			return menus;
		} catch (Exception e) {
			logger.error("roles:" + LogUtil.parserBean(roles), e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}

		return null;
	}

	@Profiler
	public int syncSAPAccount() {
		int count = 0;

		// 1 查询某段时间内一直占用的account
		List<SAPAccount> sapAccounts = sapAccountService.getUnusualSAPAccountList();
		if (sapAccounts == null || sapAccounts.size() == 0) {
			return 0;
		}

		// 2 调用 rfc 返回存在问题account
		sapAccounts = getUnusualSAPAccounts(sapAccounts);
		if (sapAccounts == null || sapAccounts.size() == 0) {
			return 0;
		}

		// 3 调用 rfc 释放该account角色并修改密码
		for (SAPAccount account : sapAccounts) {
			if (sapService.logout(account.getSapAccount(), null)) {
				count++;
			}
		}

		return count;
	}

	public List<SAPAccount> getUnusualSAPAccounts(List<SAPAccount> sapAccounts) {
		if (sapAccounts == null || sapAccounts.size() == 0) {
			return null;
		}

		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("ZRFC_WES_CHECKUSER");
			JCO.Function func = this.sapConnection.getFunction(client);
			// 输入参数
			JCO.Table table1 = func.getTableParameterList().getTable("USERSIN");
			int tableNum = 0;
			for (SAPAccount account : sapAccounts) {
				table1.appendRow();
				table1.setRow(tableNum);
				table1.setValue(account.getSapAccount(), "USER");
				tableNum++;
			}
			// 执行
			client.execute(func);

			// 输出
			JCO.Table table2 = func.getTableParameterList().getTable("USERSOUT");
			if (!table2.isEmpty()) {
				List<SAPAccount> accounts = new ArrayList<SAPAccount>();
				do {
					SAPAccount ac = new SAPAccount();
					ac.setSapAccount((String) table2.getValue("USER"));
					accounts.add(ac);
				} while (table2.nextRow());

				return accounts;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}

		return null;
	}

	/**
	 * 获取wes第一级菜单.
	 * 
	 * @return
	 */
	private String getWESMenuId() {
		Dict dict = new Dict();
		dict.setDictTypeValue("wesMenuId");
		dict.setItemName("id");

		List<Dict> dicts = dictService.getDicts(dict);
		if (dicts == null || dicts.size() == 0 || dicts.get(0) == null) {
			return null;
		}

		return dicts.get(0).getItemValue();
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ISAPAccountService getSapAccountService() {
		return sapAccountService;
	}

	public void setSapAccountService(ISAPAccountService sapAccountService) {
		this.sapAccountService = sapAccountService;
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

}

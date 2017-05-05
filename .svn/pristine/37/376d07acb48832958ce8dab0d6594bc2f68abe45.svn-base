package com.kintiger.xplatform.role.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.role.IRoleService;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.framework.bo.StringResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.menu.dao.IMenuDao;
import com.kintiger.xplatform.role.dao.IRoleDao;

/**
 * role service.
 * 
 * @author xujiakun
 * 
 */
public class RoleServiceImpl implements IRoleService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(RoleServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IDictService dictService;

	private IRoleDao roleDao;

	private IMenuDao menuDao;

	public int getRoleCount(Role role) {
		try {
			return roleDao.getRoleCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRoleList(Role role) {
		try {
			return roleDao.getRoleList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult createRole(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		if ("S".equals(role.getType())) {
			result.setResult("ERP角色的类型不能被创建！");
			return result;
		}

		try {
			String id = roleDao.createRole(role);
			result.setResult(id);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult batchCreateRole(List<Role> roles) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int id = roleDao.batchCreateRole(roles);
			result.setResult(String.valueOf(id));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(roles), e);
		}

		return result;
	}

	public StringResult batchCreateRoleUser(List<Role> roles) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int id = roleDao.batchCreateRoleUser(roles);
			result.setResult(String.valueOf(id));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(roles), e);
		}

		return result;
	}

	public StringResult updateRole(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);

		if (role == null || StringUtil.isEmpty(role.getRoleId())) {
			result.setResult(IRoleService.ERROR_INPUT_MESSAGE);
			return result;
		}

		Role r = getRoleByRoleId(role.getRoleId());
		if (r == null) {
			result.setResult("被修改角色已不存在！");
			return result;
		}

		if ("S".equals(r.getType()) && !"S".equals(role.getType())) {
			result.setResult("ERP角色的类型不能被修改！");
			return result;
		}

		if (!"S".equals(r.getType()) && "S".equals(role.getType())) {
			result.setResult("其他角色不能修改为ERP角色！");
			return result;
		}

		// if sap role then 只能修改 descn
		if ("S".equals(r.getType())) {
			r.setRemark(role.getRemark());
			return updateSAPRole(r);
		} else {
			return updateSAPRole(role);
		}
	}

	public StringResult updateSAPRole(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		if (role == null || StringUtil.isEmpty(role.getRoleId())) {
			result.setResult(IRoleService.ERROR_INPUT_MESSAGE);
			return result;
		}

		try {
			int c = roleDao.updateRole(role);
			if (c == 1) {
				result.setCode(IRoleService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteRole(final String roleId, final String modifyUser) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);

		if (StringUtil.isEmpty(roleId) && StringUtil.isEmpty(roleId.trim())) {
			result.setResult("被删除角色不能为空！");
			return result;
		}

		Role role = getRoleByRoleId(roleId.trim());

		if (role == null) {
			result.setResult("被删除角色已不存在！");
			return result;
		}

		if ("S".equals(role.getType())) {
			result.setResult(role.getRoleId() + "角色不允许手工删除！");
			return result;
		}

		boolean o = (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				// 删除role
				try {
					int i = roleDao.deleteRole(roleId, modifyUser);
					if (i != 1) {
						ts.setRollbackOnly();
						return false;
					}
				} catch (Exception e) {
					logger.error("roleId:" + roleId, e);
					ts.setRollbackOnly();
					return false;
				}

				// 删除station_role
				try {
					Role r = new Role();
					r.setRoleId(roleId);
					roleDao.deleteSelectedRole4Station(r);
				} catch (Exception e) {
					logger.error("roleId:" + roleId, e);
					ts.setRollbackOnly();
					return false;
				}

				// 删除role_menu
				try {
					Menu menu = new Menu();
					String[] s = new String[1];
					s[0] = roleId;
					menu.setCodes(s);
					menuDao.batchDeleteSelectedMenu4Role(menu);
				} catch (Exception e) {
					logger.error("roleId:" + roleId, e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});

		if (o) {
			result.setCode(IRoleService.SUCCESS);
			result.setResult("角色删除成功！");
		} else {
			result.setResult("角色删除失败！");
		}

		return result;
	}

	public StringResult deleteSAPRole() {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteSAPRole();
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public int getRole4ConpointCount(Role role) {
		try {
			return roleDao.getRole4ConpointCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRole4ConpointList(Role role) {
		try {
			return roleDao.getRole4ConpointList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public int getRole4MenuCount(Role role) {
		try {
			return roleDao.getRole4MenuCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRole4MenuList(Role role) {
		try {
			return roleDao.getRole4MenuList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public Role getRoleByRoleId(String roleId) {
		try {
			return roleDao.getRoleByRoleId(roleId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(roleId), e);
		}

		return null;
	}

	public int getSelectedRole4StationCount(Role role) {
		try {
			return roleDao.getSelectedRole4StationCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getSelectedRole4StationList(Role role) {
		try {
			return roleDao.getSelectedRole4StationList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult selectRole4Station(Role role) {
		StringResult result = new StringResult();

		try {
			List<Role> list = roleDao.getSelectedRole4Station(role);

			Map<String, Boolean> map = new HashMap<String, Boolean>();

			for (Role r : list) {
				map.put(r.getRoleId(), true);
			}

			int i = 0;
			String[] temp = new String[role.getCodes().length];

			for (String code : role.getCodes()) {
				if (map.get(code) == null) {
					temp[i++] = code;
				}
			}

			role.setCodes(temp);

			String ids = roleDao.selectRole4Station(role);
			result.setResult(ids);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteSelectedRole4Station(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteSelectedRole4Station(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteInvalidSelectedRole4Station(String roleType) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteInvalidSelectedRole4Station(roleType);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public int getSelectedRole4PositionTypeCount(Role role) {
		try {
			return roleDao.getSelectedRole4PositionTypeCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getSelectedRole4PositionTypeList(Role role) {
		try {
			return roleDao.getSelectedRole4PositionTypeList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult selectRole4PositionType(Role role) {
		StringResult result = new StringResult();

		try {
			// ��ȡcodes���Ѵ��ڵ�role
			List<Role> list = roleDao.getSelectedRole4PositionType(role);

			Map<String, Boolean> map = new HashMap<String, Boolean>();

			for (Role r : list) {
				map.put(r.getRoleId(), true);
			}

			int i = 0;
			String[] temp = new String[role.getCodes().length];

			for (String code : role.getCodes()) {
				if (map.get(code) == null) {
					temp[i++] = code;
				}
			}

			role.setCodes(temp);

			String ids = roleDao.selectRole4PositionType(role);
			result.setResult(ids);
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			result.setCode(IRoleService.ERROR);
			result.setResult(IRoleService.ERROR_MESSAGE);
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public StringResult deleteSelectedRole4PositionType(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);
		result.setResult(IRoleService.ERROR_MESSAGE);

		try {
			int c = roleDao.deleteSelectedRole4PositionType(role);
			result.setResult(String.valueOf(c));
			result.setCode(IRoleService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public List<Role> getRoleListByUser(Role role) {
		try {
			return roleDao.getRoleListByUser(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public int getRoleUserCount(Role role) {
		try {
			return roleDao.getRoleUserCount(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return 0;
	}

	public List<Role> getRoleUserList(Role role) {
		try {
			return roleDao.getRoleUserList(role);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return null;
	}

	public StringResult deleteRoleUser(Role role) {
		StringResult result = new StringResult();
		result.setCode(IRoleService.ERROR);

		try {
			int c = roleDao.deleteRoleUser(role);
			if (c == 1) {
				result.setCode(IRoleService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(role), e);
		}

		return result;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

}

package com.kintiger.xplatform.role.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.alibaba.common.lang.StringUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.role.dao.IRoleDao;

/**
 * role dao.
 * 
 * @author xujiakun
 * 
 */
public class RoleDaoImpl extends BaseDaoImpl implements IRoleDao {

	public int getRoleCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getRoleCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRoleList", role);
	}

	public String createRole(Role role) {
		return (String) getSqlMapClientTemplate().insert("role.createRole", role);
	}

	public int batchCreateRole(final List<Role> roles) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Role role : roles) {
					executor.insert("role.createRole", role);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	public int batchCreateRoleUser(final List<Role> roles) {
		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				int count = 0;
				executor.startBatch();
				for (Role role : roles) {
					executor.insert("role.batchCreateRoleUser", role);
					count++;
				}
				executor.executeBatch();

				return count;
			}
		});
	}

	public int updateRole(Role role) {
		return getSqlMapClientTemplate().update("role.updateRole", role);
	}

	public int deleteRole(String roleId, String modifyUser) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("modifyUser", modifyUser);
		return getSqlMapClientTemplate().update("role.deleteRole", map);
	}

	public int deleteSAPRole() {
		return getSqlMapClientTemplate().update("role.deleteSAPRole");
	}

	public int getRole4ConpointCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getRole4ConpointCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRole4ConpointList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRole4ConpointList", role);
	}

	public int getRole4MenuCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getRole4MenuCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRole4MenuList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRole4MenuList", role);
	}

	public Role getRoleByRoleId(String roleId) {
		return (Role) getSqlMapClientTemplate().queryForObject("role.getRoleByRoleId", roleId);
	}

	public int getSelectedRole4StationCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getSelectedRole4StationCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4StationList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getSelectedRole4StationList", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4Station(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getSelectedRole4Station", role);
	}

	public String selectRole4Station(final Role role) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				Role r = new Role();
				for (String s : role.getCodes()) {
					if (StringUtil.isNotEmpty(s)) {
						r.setRoleId(s);
						r.setStationId(role.getStationId());
						if (sb.length() != 0) {
							sb.append(",");
						}
						sb.append(executor.insert("role.selectRole4Station", r));
					}
				}
				executor.executeBatch();
				return sb.toString();
			}
		});
	}

	public int deleteSelectedRole4Station(Role role) {
		return getSqlMapClientTemplate().delete("role.deleteSelectedRole4Station", role);
	}

	public int deleteInvalidSelectedRole4Station(String roleType) {
		return getSqlMapClientTemplate().delete("role.deleteInvalidSelectedRole4Station", roleType);
	}

	public int getSelectedRole4PositionTypeCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getSelectedRole4PositionTypeCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4PositionTypeList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getSelectedRole4PositionTypeList", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getSelectedRole4PositionType(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getSelectedRole4PositionType", role);
	}

	public String selectRole4PositionType(final Role role) {
		return (String) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			StringBuilder sb = new StringBuilder();

			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				Role r = new Role();
				for (String s : role.getCodes()) {
					if (StringUtil.isNotEmpty(s)) {
						r.setRoleId(s);
						r.setPositionTypeId(role.getPositionTypeId());
						if (sb.length() != 0) {
							sb.append(",");
						}
						sb.append(executor.insert("role.selectRole4PositionType", r));
					}
				}
				executor.executeBatch();
				return sb.toString();
			}
		});
	}

	public int deleteSelectedRole4PositionType(Role role) {
		return (Integer) getSqlMapClientTemplate().delete("role.deleteSelectedRole4PositionType", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleListByUser(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRoleListByUser", role);
	}

	public int getRoleUserCount(Role role) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getRoleUserCount", role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleUserList(Role role) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRoleUserList", role);
	}

	public int deleteRoleUser(Role role) {
		return getSqlMapClientTemplate().delete("role.deleteRoleUser", role);
	}

}

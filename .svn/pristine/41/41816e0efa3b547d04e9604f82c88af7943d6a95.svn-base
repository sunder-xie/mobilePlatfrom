package com.kintiger.xplatform.org.dao.impl;

import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.org.bo.Borg;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.org.dao.IOrgDao;

public class OrgDaoImpl extends BaseDaoImpl implements IOrgDao {

	/**
	 * 查组织
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Borg> getOrgListByUserId(String userId) {
		String orgIds = (String) getSqlMapClientTemplate().queryForObject("org.fnUserOrgList", userId);
		String[] orgIdarrs = orgIds.split(",");
		Borg borg = new Borg();
		borg.setOrgIdarrs(orgIdarrs);
		if (StringUtil.isNotEmpty(orgIds)) {
			return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getOrgListByOrgIds", borg);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		Borg org = new Borg();
		org.setOrgParentId(Long.valueOf(pOrgId));
		return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getOrgTreeListByOrgId", org);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByOrgId(String orgId) {
		Borg org = new Borg();
		org.setOrgParentId(Long.valueOf(orgId));
		return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getOrgTreeListByOrgId", org);
	}

	public Borg getOrgByOrgId(String orgId) {
		Borg org = new Borg();
		org.setOrgId(Long.valueOf(orgId));
		return (Borg) getSqlMapClientTemplate().queryForObject("org.getOrgByOrgId", org);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getCompanyList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getCompanyList", borg);
	}

	public Borg getCompanyName(Borg borg) {
		return (Borg) getSqlMapClientTemplate().queryForList("org.getCompanyList", borg);
	}

	public Long createOrg(Borg org) {
		return (Long) getSqlMapClientTemplate().insert("org.createOrg", org);
	}

	public int updateBorg(Borg borg) {
		return getSqlMapClientTemplate().update("org.updateBorg", borg);
	}

	public int getCompanyListCount(Borg borg) {
		return (Integer) getSqlMapClientTemplate().queryForObject("org.getCompanyListCount", borg);
	}

	public Borg getOrgByUserId(String userId) {
		return (Borg) getSqlMapClientTemplate().queryForObject("org.getOrgByUserId", Long.valueOf(userId));
	}

	public String getFnUserOrg(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject("org.getFnUserOrg", userId);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getAllParentOrgs(String orgId) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getAllParentOrgs", orgId);
	}

	public int getOrgCount(Borg borg) {
		return (Integer) getSqlMapClientTemplate().queryForObject("org.getOrgCount", borg);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList("org.getOrgListByOrgIds", borg);
	}

}

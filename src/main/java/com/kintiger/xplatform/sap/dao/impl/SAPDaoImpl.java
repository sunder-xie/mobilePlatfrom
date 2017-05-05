package com.kintiger.xplatform.sap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.xplatform.api.sap.bo.SAPAccount;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.sap.dao.ISAPDao;

/**
 * sap dao.
 * 
 * @author xujiakun
 * 
 */
public class SAPDaoImpl extends BaseDaoImpl implements ISAPDao {

	public int releaseSAPAccount(String passport, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passport", passport);
		map.put("userId", userId);
		return getSqlMapClientTemplate().update("sap.releaseSAPAccount", map);
	}

	public SAPAccount getIdleSAPAccount() {
		return (SAPAccount) getSqlMapClientTemplate().queryForObject("sap.getIdleSAPAccount");
	}

	public int occupySAPAccount(String passport, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passport", passport);
		map.put("userId", userId);
		return getSqlMapClientTemplate().update("sap.occupySAPAccount", map);
	}

	public int getSAPAccountCount(SAPAccount sapAccount) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sap.getSAPAccountCount", sapAccount);
	}

	@SuppressWarnings("unchecked")
	public List<SAPAccount> getSAPAccountList(SAPAccount sapAccount) {
		return (List<SAPAccount>) getSqlMapClientTemplate().queryForList("sap.getSAPAccountList", sapAccount);
	}

	public int disableSAPAccount(String passport) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passport", passport);
		return getSqlMapClientTemplate().update("sap.disableSAPAccount", map);
	}

	public int enableSAPAccount(String passport) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passport", passport);
		return getSqlMapClientTemplate().update("sap.enableSAPAccount", map);
	}

	@SuppressWarnings("unchecked")
	public List<SAPAccount> getUnusualSAPAccountList() {
		return (List<SAPAccount>) getSqlMapClientTemplate().queryForList("sap.getUnusualSAPAccountList");
	}

	public String createSAPAccount(SAPAccount sapAccount) {
		return (String) getSqlMapClientTemplate().insert("sap.createSAPAccount", sapAccount);
	}

}

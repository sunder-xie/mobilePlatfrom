package com.kintiger.xplatform.sap.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.sap.bo.SAPLog;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.sap.dao.ISAPLogDao;

/**
 * sap log dao.
 * 
 * @author xujiakun
 * 
 */
public class SAPLogDaoImpl extends BaseDaoImpl implements ISAPLogDao {

	public Long createSAPLog(SAPLog sapLog) {
		return (Long) getSqlMapClientTemplate().insert("sap.createSAPLog", sapLog);
	}

	public int getSAPLogCount(SAPLog sapLog) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sap.getSAPLogCount", sapLog);
	}

	@SuppressWarnings("unchecked")
	public List<SAPLog> getSAPLogList(SAPLog sapLog) {
		return (List<SAPLog>) getSqlMapClientTemplate().queryForList("sap.getSAPLogList", sapLog);
	}

}

package com.kintiger.xplatform.monitor.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.monitor.bo.MethodMonitor;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.monitor.dao.IMethodMonitorDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class MethodMonitorDaoImpl extends BaseDaoImpl implements IMethodMonitorDao {

	public int getMethodMonitorCount(MethodMonitor methodMonitor) {
		return (Integer) getSqlMapClientTemplate().queryForObject("monitor.getMethodMonitorCount", methodMonitor);
	}

	@SuppressWarnings("unchecked")
	public List<MethodMonitor> getMethodMonitorList(MethodMonitor methodMonitor) {
		return (List<MethodMonitor>) getSqlMapClientTemplate().queryForList("monitor.getMethodMonitorList",
			methodMonitor);
	}

	public String createMethodMonitor(MethodMonitor methodMonitor) {
		return (String) getSqlMapClientTemplate().insert("monitor.createMethodMonitor", methodMonitor);
	}

}

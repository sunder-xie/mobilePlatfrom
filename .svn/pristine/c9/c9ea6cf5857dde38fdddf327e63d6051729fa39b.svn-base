package com.kintiger.xplatform.api.sap;

import com.kintiger.xplatform.framework.exception.SystemException;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISSOService {

	String RESULT_ERROR = "Portal免登失败！";

	/**
	 * 根据帐号密码免登sap.
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	String getSSOUrl(String user, String password) throws SystemException;

	/**
	 * 根据帐号密码免登sap(MYSAPSSO2).
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	String getMySAPSSO2Ticket(String user, String password) throws SystemException;

}

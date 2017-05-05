package com.kintiger.xplatform.sap.dao;

import java.util.List;

import com.kintiger.xplatform.api.sap.bo.SAPAccount;

/**
 * sap dao.
 * 
 * @author xujiakun
 * 
 */
public interface ISAPDao {

	/**
	 * 释放loginId被占用的passport.
	 * 
	 * @param passport
	 * @param userId
	 * @return
	 */
	int releaseSAPAccount(String passport, String userId);

	/**
	 * 随机获取空闲帐号.
	 * 
	 * @return
	 */
	SAPAccount getIdleSAPAccount();

	/**
	 * 占用帐号.
	 * 
	 * @param passport
	 * @param userId
	 * @return
	 */
	int occupySAPAccount(String passport, String userId);

	/**
	 * 
	 * @param sapAccount
	 * @return
	 */
	int getSAPAccountCount(SAPAccount sapAccount);

	/**
	 * 
	 * @param sapAccount
	 * @return
	 */
	List<SAPAccount> getSAPAccountList(SAPAccount sapAccount);

	/**
	 * 禁用帐号.
	 * 
	 * @param passport
	 * @return
	 */
	int disableSAPAccount(String passport);

	/**
	 * 启用帐号.
	 * 
	 * @param passport
	 * @return
	 */
	int enableSAPAccount(String passport);

	/**
	 * 
	 * @return
	 */
	List<SAPAccount> getUnusualSAPAccountList();

	/**
	 * 
	 * @param sapAccount
	 * @return
	 */
	String createSAPAccount(SAPAccount sapAccount);

}

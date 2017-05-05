package com.kintiger.xplatform.api.sap;

import java.util.List;

import com.kintiger.xplatform.api.sap.bo.SAPAccount;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISAPAccountService {

	String RESULT_ERROR = "RFC操作失败！";

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
	 * 获取异常sap account.
	 * 
	 * @return
	 */
	List<SAPAccount> getUnusualSAPAccountList();

	/**
	 * 批量创建account.
	 * 
	 * @param sapAccounts
	 * @return
	 */
	int createSAPAccount(List<SAPAccount> sapAccounts);
}

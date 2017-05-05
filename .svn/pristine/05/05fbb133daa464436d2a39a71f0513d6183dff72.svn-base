package com.kintiger.xplatform.api.sap;

import java.util.List;

import com.kintiger.xplatform.api.sap.bo.SAPAccount;

/**
 * sap sync.
 * 
 * @author xujiakun
 * 
 */
public interface ISAPSyncService {

	/**
	 * 同步sap role.
	 */
	String syncSAPRole();

	/**
	 * 同步sap tcode.
	 */
	int syncSAPTcode();

	/**
	 * 同步sap tcode.
	 * 
	 * @param roleId
	 * @return
	 */
	int syncSAPTcode(String roleId);

	/**
	 * 同步sap account 验证帐号是否有效.
	 * 
	 * @return
	 */
	int syncSAPAccount();

	List<SAPAccount> getUnusualSAPAccounts(List<SAPAccount> sapAccounts);

}

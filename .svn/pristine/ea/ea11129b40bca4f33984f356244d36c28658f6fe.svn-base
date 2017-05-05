package com.kintiger.xplatform.api.sap;

import java.util.List;
import java.util.Map;

import com.kintiger.xplatform.api.user.bo.CuanhuoQuery;

/**
 * 
 * @author xujiakun
 * 
 */
public interface ISAPService {

	String RESULT_ERROR = "RFC操作失败！";

	/**
	 * 登录sap web.
	 * 
	 * @param passport
	 * @param password
	 * @param userId
	 * @param loginId
	 * @param ip
	 * @return
	 */
	Map<String, String> login(String passport, String password, String userId, String loginId, String ip);

	/**
	 * 退出sap web.
	 * 
	 * @param passport
	 * @param userId
	 * @return
	 */
	boolean logout(String passport, String userId);

	/**
	 * 退出并禁用帐号.
	 * 
	 * @param passport
	 * @return
	 */
	boolean disableSAPAccount(String passport);

	/**
	 * 启用帐号.
	 * 
	 * @param passport
	 * @return
	 */
	boolean enableSAPAccount(String passport);

	List<CuanhuoQuery> searchCuanhuo(CuanhuoQuery cuanhuoQuery);

}

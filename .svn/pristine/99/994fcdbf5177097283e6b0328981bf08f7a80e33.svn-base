package com.kintiger.xplatform.api.cache;

import java.net.InetSocketAddress;
import java.util.List;

import com.kintiger.xplatform.api.cache.bo.CacheStats;
import com.kintiger.xplatform.framework.exception.ServiceException;

/**
 * MemcachedCache.
 * 
 * @author xujiakun
 * 
 */
public interface IMemcachedCacheService extends ICacheService<String, Object> {

	/**
	 * default_exp_time.
	 */
	int DEFAULT_EXP = 24 * 60 * 60;

	/**
	 * key_news.
	 */
	String CACHE_KEY_NEWS = "key_news";

	int CACHE_KEY_NEWS_DEFAULT_EXP = 0;

	/**
	 * key_menu_tree_userId.
	 */
	String CACHE_KEY_MENU_TREE = "key_menu_tree_";

	int CACHE_KEY_MENU_TREE_DEFAULT_EXP = 12 * 60 * 60;

	/**
	 * open api.
	 */
	String CACHE_KEY_OPEN_API = "key_open_api";

	int CACHE_KEY_OPEN_API_DEFAULT_EXP = 0;

	/**
	 * log monitor.
	 */
	String CACHE_KEY_LOG_MONITOR = "key_log_monitor";

	int CACHE_KEY_LOG_MONITOR_DEFAULT_EXP = 0;

	/**
	 * bo parameter.
	 */
	String CACHE_KEY_BO_PARAMETER = "key_bo_parameter_";

	int CACHE_KEY_BO_PARAMETER_DEFAULT_EXP = 0;

	/**
	 * sso token.
	 */
	int CACHE_KEY_SSO_TOKEN_DEFAULT_EXP = 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_DEFAULT_EXP = 2 * 60 * 60;

	/**
	 * session.
	 */
	int CACHE_KEY_SESSION_EXP = 3 * 60;

	/**
	 * file id.
	 */
	String CACHE_KEY_FILE_ID = "key_file_id_";

	int CACHE_KEY_FILE_ID_DEFAULT_EXP = 1 * 60 * 60;

	/**
	 * sap account.
	 */
	int CACHE_KEY_SAP_ACCOUNT_DEFAULT_EXP = 1 * 60;

	/**
	 * action log.
	 */
	String CACHE_KEY_ACTION_LOG = "key_action_log";

	int CACHE_KEY_ACTION_LOG_DEFAULT_EXP = 0;

	/**
	 * check code.
	 */
	int CACHE_KEY_CHECK_CODE_DEFAULT_EXP = 10 * 60;

	/**
	 * org list.
	 */
	int CACHE_KEY_ORG_DEFAULT_EXP = 1 * 60 * 60;

	/**
	 * request.
	 */
	int CACHE_KEY_REQUEST_DEFAULT_EXP = 5 * 60;

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, int inc) throws ServiceException;

	/**
	 * incr.
	 * 
	 * @param key
	 * @param inc
	 * @return
	 * @throws ServiceException
	 */
	long incr(String key, long inc) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, int decr) throws ServiceException;

	/**
	 * decr.
	 * 
	 * @param key
	 * @param decr
	 * @return
	 * @throws ServiceException
	 */
	long decr(String key, long decr) throws ServiceException;

	/**
	 * 
	 * @param address
	 * @throws ServiceException
	 */
	void flushAll(InetSocketAddress address) throws ServiceException;

	/**
	 * cache stats.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<CacheStats> getStats() throws ServiceException;

}

package com.kintiger.xplatform.sap.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.sap.ISSOService;
import com.kintiger.xplatform.framework.exception.SystemException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.HttpUtil;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * sap web sso service.
 * 
 * @author xujiakun
 * 
 */
public class SSOServiceImpl implements ISSOService {

	private static final String PARAM_CLIENT = "sap-client";

	private static final String PARAM_USER = "sap-user";

	private static final String PARAM_PASSWORD = "sap-password";

	private static final String STRING_END = "/bc/gui/sap/its/webgui";

	private static final String STRING_ERROR = "Logon Error Message";

	private Logger4jExtend logger = Logger4jCollection.getLogger(SSOServiceImpl.class);

	private IDictService dictService;

	/**
	 * 获取sap web参数.
	 * 
	 * @return
	 */
	private Map<String, String> init() {
		Map<String, String> map = new HashMap<String, String>();

		Dict dict = new Dict();
		dict.setDictTypeValue("sapWeb");
		List<Dict> dicts = dictService.getDicts(dict);

		if (dicts != null && dicts.size() > 0) {
			for (Dict d : dicts) {
				map.put(d.getItemName(), d.getItemValue());
			}
		}

		return map;
	}

	public String getSSOUrl(String user, String password) throws SystemException {
		Map<String, String> map = init();

		Map<String, String> params = new HashMap<String, String>();
		params.put(PARAM_CLIENT, map.get("sapClient"));
		params.put(PARAM_USER, user);
		params.put(PARAM_PASSWORD, password);

		String responseStr = null;

		try {
			responseStr = HttpUtil.post(map.get("portalHost") + map.get("portalService"), params);
		} catch (SystemException e) {
			logger.error(LogUtil.parserBean(params), e);
			throw new SystemException(ISSOService.RESULT_ERROR, e);
		}

		// 系统免登失败
		if (StringUtil.isEmpty(responseStr) || responseStr.indexOf(STRING_ERROR) != -1) {
			throw new SystemException(ISSOService.RESULT_ERROR);
		}

		// 是否存在令牌
		int end = responseStr.indexOf(STRING_END);
		if (end == -1) {
			throw new SystemException(ISSOService.RESULT_ERROR);
		}

		return map.get("portalHost") + responseStr.substring(end - 106, end + STRING_END.length());
	}

	public String getMySAPSSO2Ticket(String user, String password) throws SystemException {
		Map<String, String> map = init();

		String ticket = null;
		StringBuilder str = new StringBuilder();
		try {
			str.append(map.get("portalHost")).append(map.get("portalService")).append("?").append(PARAM_CLIENT)
				.append("=").append(map.get("sapClient")).append("&").append(PARAM_USER).append("=").append(user)
				.append("&").append(PARAM_PASSWORD).append("=").append(password);

			URL url = new URL(str.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("content-type", "application/binary;charset=UTF-8");
			int responseCode = ((HttpURLConnection) connection).getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				String headerName = connection.getHeaderFieldKey(1);

				for (int i = 1; StringUtil.isNotEmpty(headerName); i++) {
					if ("set-cookie".equals(headerName)) {
						String cookie = connection.getHeaderField(i);
						if (cookie.indexOf("MYSAPSSO2") != -1) {
							ticket = cookie.substring(cookie.indexOf('=') + 1, cookie.indexOf(';'));
							break;
						}
					}
					headerName = connection.getHeaderFieldKey(i);
				}
			} else {
				throw new SystemException(ISSOService.RESULT_ERROR);
			}
		} catch (Exception e) {
			logger.error("str:" + str.toString(), e);
			throw new SystemException(ISSOService.RESULT_ERROR, e);
		}

		return ticket;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

}

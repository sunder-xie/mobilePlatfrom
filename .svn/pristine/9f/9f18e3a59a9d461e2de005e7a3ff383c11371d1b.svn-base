package com.kintiger.xplatform.framework.hessian;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.common.lang.StringEscapeUtil;
import com.alibaba.service.dsa.DSAException;
import com.alibaba.service.dsa.NoSuchKeyPairException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.kintiger.xplatform.api.system.IDSAService;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;

/**
 * DSA for Hessian.
 * 
 * @author xujiakun
 * 
 */
public class DSAHessianProxyFactory extends HessianProxyFactory {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DSAHessianProxyFactory.class);

	private IDSAService dsaService;

	private String keyPairName;

	/**
	 * 固定的加密字符串.
	 */
	private String secureKey;

	protected URLConnection openConnection(URL url) throws IOException {

		String signature = null;
		String baseUrl = url.toString();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(baseUrl);

		// 生成时间戳
		long timestamp = System.currentTimeMillis();
		// 生成方法签名
		try {
			signature = dsaService.sign(secureKey + "|" + Long.toString(timestamp), keyPairName);
		} catch (NoSuchKeyPairException ne) {
			logger.error("error in DSAHessianProxyFactory.openConnection,no such key" + keyPairName, ne);
		} catch (DSAException de) {
			logger.error("error in DSAHessianProxyFactory.openConnection,DSA sign error" + keyPairName, de);
		}
		if ('?' != (queryBuilder.charAt(queryBuilder.length() - 1))) {
			queryBuilder.append("?");
		}

		queryBuilder.append("sign=");
		if (signature != null) {
			queryBuilder.append(StringEscapeUtil.escapeURL(signature));
		}
		queryBuilder.append("&time=");
		queryBuilder.append(timestamp);
		URL secureUrl = new URL(queryBuilder.toString());

		URLConnection conn = secureUrl.openConnection();

		conn.setDoOutput(true);

		if (getReadTimeout() > 0) {
			try {
				conn.setReadTimeout((int) getReadTimeout());
			} catch (Exception e) {
				logger.error(e);
			}
		}

		// 设置自定义的content-type
		conn.setRequestProperty("Content-Type", "application/octet-stream");

		return conn;
	}

	public IDSAService getDsaService() {
		return dsaService;
	}

	public void setDsaService(IDSAService dsaService) {
		this.dsaService = dsaService;
	}

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

}

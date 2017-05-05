package com.kintiger.xplatform.framework.hessian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianServiceExporter;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.service.dsa.DSAException;
import com.alibaba.service.dsa.NoSuchKeyPairException;
import com.kintiger.xplatform.api.system.IDSAService;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.ClientUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class DSAHessianServiceExporter extends HessianServiceExporter {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DSAHessianServiceExporter.class);

	private IDSAService dsaService;

	/**
	 * 请求过期时间.
	 */
	private long timeout;

	/**
	 * 令牌原文.
	 */
	private String secureKey;

	/**
	 * 允许访问的客户端.
	 */
	private String allowedClients;

	/**
	 * 密钥名称.
	 */
	private String keyPairName;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
		IOException {

		// 1.如果设置了允许的IP地址，需要进行客户端IP地址验证
		if (StringUtil.isNotBlank(allowedClients)) {
			// String clientIP = getClientIPAddress(request)
			String clientIP = ClientUtil.getIpAddr(request);
			if (!isAllowedClient(clientIP)) {
				logger.error("hessian authentication error:" + clientIP + " not in allowedList " + allowedClients);
				return;
			}
		}

		// 2.如果设置了令牌或者过期时间，需要判断时间格式是否合法
		String strTimestamp = request.getParameter("time");
		long timestamp = 0;
		if (timeout > 0 || StringUtil.isNotBlank(secureKey)) {
			if (strTimestamp == null) {
				logger.error("hessian authentication error:timestamp not exist!");
				return;
			}
			try {
				timestamp = Long.parseLong(strTimestamp);
			} catch (NumberFormatException e) {
				logger.error("hessian authentication error:timestamp is not number!");
				return;
			}
		}

		// 3.如果设置了过期时间，需要判断请求是否过期
		if (timeout > 0 && isRequestExpired(timestamp)) {
			logger.error("hessian authentication error:request is expired!");
			return;
		}

		// 4. 如果设置了令牌，需要进行签名校验
		if (StringUtil.isNotBlank(secureKey)) {
			// 4.1 得到签名
			String signature = request.getParameter("sign");
			if (signature == null) {
				logger.error("hessian authentication error:signatures not exist!");
				return;
			}
			try {
				// 4.2 验证签名是否一致
				boolean isRight = dsaService.check(secureKey + "|" + timestamp, signature, keyPairName);
				if (!isRight) {
					logger.error("hessian authentication error:signatures not match!");
					return;
				}
			} catch (NoSuchKeyPairException ne) {
				logger.error("error in DSAHessianServiceExporter.handleRequest,no such key" + keyPairName, ne);
			} catch (DSAException de) {
				logger.error("error in DSAHessianServiceExporter.handleRequest,DSA sign error", de);
			}

		}

		// 5. 设置hessian content-type
		response.setContentType("application/octet-stream");

		super.handleRequest(request, response);
	}

	private boolean isAllowedClient(String clientIP) {
		return StringUtil.isNotEmpty(clientIP);
	}

	private boolean isRequestExpired(long timestamp) {
		return timestamp > 0;
	}

	public IDSAService getDsaService() {
		return dsaService;
	}

	public void setDsaService(IDSAService dsaService) {
		this.dsaService = dsaService;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getSecureKey() {
		return secureKey;
	}

	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}

	public String getAllowedClients() {
		return allowedClients;
	}

	public void setAllowedClients(String allowedClients) {
		this.allowedClients = allowedClients;
	}

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

}

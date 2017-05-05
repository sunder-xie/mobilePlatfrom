package com.kintiger.xplatform.api.system;

import com.alibaba.service.ServiceInitializationException;
import com.alibaba.service.dsa.DSAException;

/**
 * DSA加密簽名接口.
 * 
 * @author xujiakun
 * 
 */
public interface IDSAService {

	/**
	 * 对指定字符串进行签名.
	 * 
	 * @param content
	 *            要签名的字符串
	 * @param keyPairName
	 *            key pair
	 * 
	 * @return base64编码的签名
	 */
	String sign(String content, String keyPairName) throws DSAException;

	/**
	 * 对指定字符串进行签名.
	 * 
	 * @param content
	 *            要签名的字符串
	 * @param keyPairName
	 *            key pair
	 * @param charset
	 *            字符串的编码字符集
	 * 
	 * @return base64编码的签名
	 */
	String sign(String content, String keyPairName, String charset) throws DSAException;

	/**
	 * 检验content的签名.
	 * 
	 * @param content
	 *            要检验的内容
	 * @param signature
	 *            签名
	 * @param keyPairName
	 *            key pair
	 * 
	 * @return 如果签名正确，则返回<code>true</code>
	 */
	boolean check(String content, String signature, String keyPairName) throws DSAException;

	/**
	 * 检验content的签名.
	 * 
	 * @param content
	 *            要检验的内容
	 * @param signature
	 *            签名
	 * @param keyPairName
	 *            key pair
	 * @param 字符串的编码字符集
	 * 
	 * @return 如果签名正确，则返回<code>true</code>
	 */
	boolean check(String content, String signature, String keyPairName, String charset) throws DSAException;

	/**
	 * @throws ServiceInitializationException
	 */
	void init() throws ServiceInitializationException;

}

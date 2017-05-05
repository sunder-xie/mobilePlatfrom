package com.kintiger.xplatform.system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.service.ServiceInitializationException;
import com.alibaba.service.dsa.DSA;
import com.alibaba.service.dsa.DSAException;
import com.kintiger.xplatform.api.system.IDSAService;
import com.kintiger.xplatform.api.system.bo.KeyInfoElement;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * dsa service.
 * 
 * @author xujiakun
 * 
 */
public class DSAServiceImpl implements IDSAService {

	/**
	 * 配置文件名.
	 */
	private static final String KEY_CONFIG_FILE = "dsaConfig.xml";

	private static final String CONF_SUB_DIR = "/dsa/";

	/**
	 * 在配置文件中的节点标识.
	 */
	private static final String KEY_TOKEN_TEXT = "key";

	private static final String KEY_NAME_TEXT = "name";

	private static final String PUBKEY_FILE_TEXT = "pubKey";

	private static final String PRIKEY_FILE_TEXT = "priKey";

	private Logger4jExtend logger = Logger4jCollection.getLogger(DSAServiceImpl.class);

	private DSA dsa;

	/**
	 * 根据配置文件获取公私钥信息列表.
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<KeyInfoElement> getKeyInformation() {
		ArrayList<KeyInfoElement> keyInfoList = new ArrayList<KeyInfoElement>();
		InputStream inputStream = this.getClass().getResourceAsStream(CONF_SUB_DIR + KEY_CONFIG_FILE);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(inputStream);
			if (doc == null) {
				throw new Exception("Bad dsa configuration file: doc is null");
			}

			Element root = doc.getRootElement();
			if (root == null) {
				throw new Exception("Bad dsa configuration file: root is null");
			}

			String tempKeyName = null;
			String tempPubKey = null;
			String tempPriKey = null;
			for (Iterator i = root.elementIterator(KEY_TOKEN_TEXT); i.hasNext();) {
				Element element = (Element) i.next();
				if (element == null) {
					continue;
				}

				tempKeyName = element.elementText(KEY_NAME_TEXT);
				tempPubKey = element.elementText(PUBKEY_FILE_TEXT);
				if (tempPubKey != null) {
					tempPubKey = CONF_SUB_DIR + tempPubKey;
				}

				tempPriKey = element.elementText(PRIKEY_FILE_TEXT);
				if (tempPriKey != null) {
					tempPriKey = CONF_SUB_DIR + tempPriKey;
				}

				KeyInfoElement keyInfoElement =
					KeyInfoElement.createKeyInfoElement(tempKeyName, tempPubKey, tempPriKey);

				if (keyInfoElement != null) {
					keyInfoList.add(keyInfoElement);
				}
			}
		} catch (Exception ex) {
			logger.error("exception in getKeyInformation: " + LogUtil.parserBean(keyInfoList), ex);
		}

		return keyInfoList;
	}

	/**
	 * 初始化service.
	 * 
	 * @throws ServiceInitializationException
	 *             如果初始化失败
	 */
	@SuppressWarnings("rawtypes")
	public void init() throws ServiceInitializationException {
		dsa = new DSA();
		List<KeyInfoElement> keyInfoList = getKeyInformation();

		String pubKeyFileName = null;
		String priKeyFileName = null;
		String keyPairName = null;

		// 用来控制每个 keyPairName 对应的公私钥对只会读取一次
		Set<String> keys = new HashSet<String>();

		for (Iterator i = keyInfoList.iterator(); i.hasNext();) {
			KeyInfoElement keyInfoElement = (KeyInfoElement) i.next();

			keyPairName = keyInfoElement.getKeyPairName();
			pubKeyFileName = keyInfoElement.getPubKeyFileName();
			priKeyFileName = keyInfoElement.getPriKeyFileName();

			if (StringUtil.isNotEmpty(keyPairName) && !keys.contains(keyPairName)) {
				keys.add(keyPairName);
				logger.debug("Reading DSA key pair: name=" + keyPairName);

				if (pubKeyFileName != null) {
					logger.debug("public key file: " + pubKeyFileName);
				}

				if (priKeyFileName != null) {
					logger.debug("private key file: " + priKeyFileName);
				}

				if (pubKeyFileName != null) {
					try {
						dsa.setPublicKey(keyPairName, this.getClass().getResourceAsStream(pubKeyFileName));
					} catch (DSAException e) {
						throw new ServiceInitializationException("Failed to read public key file: " + pubKeyFileName, e);
					} catch (IOException e) {
						throw new ServiceInitializationException("Failed to read public key file: " + pubKeyFileName, e);
					}
				}

				if (priKeyFileName != null) {
					try {
						dsa.setPrivateKey(keyPairName, this.getClass().getResourceAsStream(priKeyFileName));
					} catch (DSAException e) {
						throw new ServiceInitializationException("Failed to read private key file: " + priKeyFileName,
							e);
					} catch (IOException e) {
						throw new ServiceInitializationException("Failed to read public key file: " + pubKeyFileName, e);
					}
				}
			}
		}
	}

	public String sign(String content, String keyPairName, String charset) throws DSAException {
		return dsa.sign(content, keyPairName, charset);
	}

	public String sign(String content, String keyPairName) throws DSAException {
		return dsa.sign(content, keyPairName);
	}

	public boolean check(String content, String signature, String keyPairName, String charset) throws DSAException {
		return dsa.check(content, signature, keyPairName, charset);
	}

	public boolean check(String content, String signature, String keyPairName) throws DSAException {
		return dsa.check(content, signature, keyPairName);
	}

}

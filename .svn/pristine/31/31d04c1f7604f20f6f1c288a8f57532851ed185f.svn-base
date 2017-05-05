package com.kintiger.xplatform.api.system.bo;

/**
 * dsa.
 * 
 * @author xujiakun
 * 
 */
public final class KeyInfoElement {

	/**
	 * keyPairName.
	 */
	private String keyPairName;

	/**
	 * pubKeyFileName.
	 */
	private String pubKeyFileName;

	/**
	 * priKeyFileName.
	 */
	private String priKeyFileName;

	private KeyInfoElement(String keyPairName, String pubKeyFileName, String priKeyFileName) {
		super();
		this.keyPairName = keyPairName;
		this.pubKeyFileName = pubKeyFileName;
		this.priKeyFileName = priKeyFileName;
	}

	public static KeyInfoElement createKeyInfoElement(String keyPairName, String pubKeyFileName, String priKeyFileName) {
		if (keyPairName == null) {
			return null;
		}
		return new KeyInfoElement(keyPairName, pubKeyFileName, priKeyFileName);
	}

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

	public String getPriKeyFileName() {
		return priKeyFileName;
	}

	public void setPriKeyFileName(String priKeyFileName) {
		this.priKeyFileName = priKeyFileName;
	}

	public String getPubKeyFileName() {
		return pubKeyFileName;
	}

	public void setPubKeyFileName(String pubKeyFileName) {
		this.pubKeyFileName = pubKeyFileName;
	}

}

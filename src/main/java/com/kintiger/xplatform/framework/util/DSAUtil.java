package com.kintiger.xplatform.framework.util;

import com.alibaba.service.dsa.util.DSAKeyPairGenerator;

/**
 * dsa util.
 * 
 * @author xujiakun
 * 
 */
public final class DSAUtil {

	private DSAUtil() {

	}

	public static void main(String[] args) {
		String[] arg0 = new String[2];
		arg0[0] = "pubkey.dat";
		arg0[1] = "prikey.dat";
		DSAKeyPairGenerator.main(arg0);
	}

}

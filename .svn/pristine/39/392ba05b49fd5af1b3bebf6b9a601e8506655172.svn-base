package com.kintiger.xplatform.framework.hessian;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * 
 * @author xujiakun
 * 
 */
public class DSAHessianProxyFactoryBean extends HessianProxyFactoryBean {

	private DSAHessianProxyFactory dsaHessianProxyFactory;

	public void afterPropertiesSet() {

		setProxyFactory(dsaHessianProxyFactory);

		super.afterPropertiesSet();
	}

	public DSAHessianProxyFactory getDsaHessianProxyFactory() {
		return dsaHessianProxyFactory;
	}

	public void setDsaHessianProxyFactory(DSAHessianProxyFactory dsaHessianProxyFactory) {
		this.dsaHessianProxyFactory = dsaHessianProxyFactory;
	}

}

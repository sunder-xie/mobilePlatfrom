package com.kintiger.xplatform.sap.service.impl;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.role.IRoleService;
import com.kintiger.xplatform.api.role.bo.Role;
import com.kintiger.xplatform.api.sap.ISAPAccountService;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.sap.ISSOService;
import com.kintiger.xplatform.api.sap.bo.SAPAccount;
import com.kintiger.xplatform.api.sap.bo.SAPConnectionBean;
import com.kintiger.xplatform.api.user.bo.CuanhuoQuery;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.exception.SystemException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.sap.dao.ISAPDao;
import com.sap.mw.jco.JCO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * sap service.
 * 
 * @author xujiakun
 * 
 */
public class SAPServiceImpl implements ISAPService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SAPServiceImpl.class);

	private SAPConnectionBean sapConnection;

	private ISSOService ssoService;

	private IMemcachedCacheService memcachedCacheService;

	private TransactionTemplate transactionTemplate;

	private IRoleService roleService;

	private ISAPDao sapDao;

	public Map<String, String> login(String passport, String password, final String userId, final String loginId,
		final String ip) {
		// if passport is not null then get the ssoUrl from cache
		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(password)) {
			// init
			Map<String, String> map = new HashMap<String, String>();

			map.put("passport", passport);
			map.put("password", password);

			String ssoUrl = null;
			try {
				ssoUrl = ssoService.getSSOUrl(passport, password);
			} catch (SystemException e) {
				logger.error("passport:" + passport + "password:" + password, e);
			}

			if (StringUtil.isNotEmpty(ssoUrl)) {
				map.put("ssoUrl", ssoUrl);
				return map;
			} else {
				// passport password 无效 then 释放已经被占用帐号（修改EXP临时帐号表）
				// 只释放被自己占用的帐号，可能存在该帐号已被他们正常占用
				releaseSAPAccount(passport, userId);
			}
		}

		// 账号密码空，或者sso失败，需要重新获取空闲帐号，重新sso
		// 获取空闲帐号
		SAPAccount account = null;
		try {
			account = sapDao.getIdleSAPAccount();
		} catch (Exception e) {
			logger.error(e);
		}

		// 获取失败 没有空闲帐号
		if (account == null) {
			return null;
		}

		String ps = account.getSapAccount();
		if (StringUtil.isEmpty(ps)) {
			// 获取失败 没有空闲帐号
			return null;
		}

		final String sapAccount = (ps.split("\\|"))[1];

		// 获取临时帐号 add cache 通过cache机制 保证一段时间内 该帐号被锁定
		try {
			memcachedCacheService.add(sapAccount, sapAccount, IMemcachedCacheService.CACHE_KEY_SAP_ACCOUNT_DEFAULT_EXP);
		} catch (ServiceException e) {
			logger.error("sapAccount:" + sapAccount, e);
			return null;
		}

		Object o = transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				// init
				Map<String, String> map = new HashMap<String, String>();
				// sap临时帐号密码
				String pw = null;

				// 占用空闲帐号
				try {
					int n = sapDao.occupySAPAccount(sapAccount, userId);
					// 占用失败 直接返回
					if (n != 1) {
						ts.setRollbackOnly();
						return null;
					}
				} catch (Exception e) {
					logger.error("sapAccount:" + sapAccount + "userId:" + userId, e);
					ts.setRollbackOnly();
					// 占用失败 直接返回
					return null;
				}

				// 获取当前用户SAP角色
				Role role = new Role();
				role.setType("S");
				role.setUserId(userId);

				List<Role> roles = roleService.getRoleListByUser(role);
				// 没有sap角色
				if (roles == null || roles.size() == 0) {
					ts.setRollbackOnly();
					return null;
				}

				// 修改account sap role 返回account密码
				pw = updatePermission(sapAccount, roles, loginId, ip);

				// 修改角色重新获取密码失败
				if (StringUtil.isEmpty(pw)) {
					// 只释放被自己占用的帐号，可能存在该帐号已被他们正常占用
					ts.setRollbackOnly();
					return null;
				}

				map.put("passport", sapAccount);
				map.put("password", pw);

				String ssoUrl = null;
				try {
					ssoUrl = ssoService.getSSOUrl(sapAccount, pw);
				} catch (SystemException e) {
					logger.error("sapAccount:" + sapAccount + "password:" + pw, e);
				}

				if (StringUtil.isNotEmpty(ssoUrl)) {
					map.put("ssoUrl", ssoUrl);
					return map;
				} else {
					// passport password 无效 then 释放已经被占用帐号（修改EXP临时帐号表）
					// 只释放被自己占用的帐号，可能存在该帐号已被他们正常占用
					ts.setRollbackOnly();
					return null;
				}

			}
		});

		return (Map<String, String>) o;
	}

	/**
	 * 释放已经被占用帐号（修改EXP临时帐号表 该帐号原先必须属于userId）.
	 * 
	 * @param passport
	 * @param userId
	 */
	private boolean releaseSAPAccount(String passport, String userId) {
		try {
			int c = sapDao.releaseSAPAccount(passport, userId);
			if (c == 1) {
				return true;
			}
		} catch (Exception e) {
			logger.error("passport:" + passport + "userId:" + userId, e);
			// 邮件通知管理员
		}

		return false;
	}

	/**
	 * 根据当前用户的sap角色，更新sap临时帐号权限.
	 * 
	 * @param passport
	 * @param roles
	 * @param loginId
	 * @return
	 */
	private String updatePermission(String passport, List<Role> roles, String loginId, String ip) {
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("ZRFC_WES_LOGIN");
			JCO.Function func = sapConnection.getFunction(client);

			JCO.Table table = func.getTableParameterList().getTable("ACTIVITYGROUPS");
			JCO.ParameterList output = func.getExportParameterList();
			JCO.ParameterList input = func.getImportParameterList();

			input.setValue(passport, "USERNAME");
			input.setValue(loginId, "WESNAME");
			input.setValue(ip, "USERIP");
			int tableNum = 0;
			if ((roles != null) && (roles.size() > 0)) {
				for (Role role : roles) {
					table.appendRow();
					table.setRow(tableNum);
					table.setValue(role.getRoleId(), "AGR_NAME");
					tableNum++;
				}
			}

			client.execute(func);

			JCO.Table r = func.getTableParameterList().getTable("RETURN");
			if (!r.isEmpty()) {
				do {
					if ("E".equals(r.getValue("TYPE"))) {
						return null;
					}
				} while (r.nextRow());
			}

			return output.getString("PW");
		} catch (Exception e) {
			logger.error("passport:" + passport + "roles:" + LogUtil.parserBean(roles) + "loginId:" + loginId + "ip:"
				+ ip, e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}

		return null;
	}

	public boolean logout(String passport, String userId) {
		// 释放该帐号角色
		updatePermission(passport, null, null, null);

		// 释放该帐号 不再占用
		if (releaseSAPAccount(passport, userId)) {
			return true;
		}

		return false;
	}

	public boolean disableSAPAccount(final String passport) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				try {
					// 禁用该帐号
					int c = sapDao.disableSAPAccount(passport);
					if (c != 1) {
						ts.setRollbackOnly();
						return false;
					}
				} catch (Exception e) {
					logger.error("passport:" + passport, e);
					ts.setRollbackOnly();
					return false;
				}

				try {
					// 释放该帐号角色并禁用帐号
					disableAccount(passport);
				} catch (Exception e) {
					logger.error("passport:" + passport, e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});
	}

	private void disableAccount(String passport) throws SystemException {
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("ZRFC_WES_DISABLE");
			JCO.Function func = sapConnection.getFunction(client);

			JCO.ParameterList input = func.getImportParameterList();
			input.setValue(passport, "USERNAME");

			client.execute(func);

			JCO.Table table = func.getTableParameterList().getTable("RETURN");

			if (!table.isEmpty()) {
				do {
					if ("E".equals(table.getValue("TYPE"))) {
						throw new SystemException(ISAPAccountService.RESULT_ERROR);
					}
				} while (table.nextRow());
			}
		} catch (Exception e) {
			logger.error("passport:" + passport, e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}
	}

	public boolean enableSAPAccount(final String passport) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				try {
					// 启用该帐号
					int c = sapDao.enableSAPAccount(passport);
					if (c != 1) {
						ts.setRollbackOnly();
						return false;
					}
				} catch (Exception e) {
					logger.error("passport:" + passport, e);
					ts.setRollbackOnly();
					return false;
				}

				try {
					// 释放该帐号角色并禁用帐号
					enableAccount(passport);
				} catch (Exception e) {
					logger.error("passport:" + passport, e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});
	}

	private void enableAccount(String passport) throws SystemException {
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("BAPI_USER_UNLOCK");
			JCO.Function func = sapConnection.getFunction(client);

			JCO.ParameterList input = func.getImportParameterList();
			input.setValue(passport, "USERNAME");

			client.execute(func);

			JCO.Table table = func.getTableParameterList().getTable("RETURN");

			if (!table.isEmpty()) {
				do {
					if ("E".equals(table.getValue("TYPE"))) {
						throw new SystemException(ISAPAccountService.RESULT_ERROR);
					}
				} while (table.nextRow());
			}
		} catch (Exception e) {
			logger.error("passport:" + passport, e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}
	}

	public SAPConnectionBean getSapConnection() {
		return this.sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public ISSOService getSsoService() {
		return ssoService;
	}

	public void setSsoService(ISSOService ssoService) {
		this.ssoService = ssoService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public ISAPDao getSapDao() {
		return sapDao;
	}

	public void setSapDao(ISAPDao sapDao) {
		this.sapDao = sapDao;
	}
	
	@Override
	public List<CuanhuoQuery> searchCuanhuo(CuanhuoQuery cuanhuoQuery) {
		List<CuanhuoQuery> cuanhuoQuerys = new ArrayList<CuanhuoQuery>();
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZGET_INFO_CUANHUO");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();
				input.getField("IV_WERKS").setValue(cuanhuoQuery.getIV_WERKS().toString());
				input.getField("IV_MATNR").setValue(cuanhuoQuery.getIV_MATNR().toString());
				input.getField("IV_LOCCO").setValue(cuanhuoQuery.getIV_LOCCO().toString());
				input.getField("IV_DATUM").setValue(cuanhuoQuery.getIV_DATUM().toString()); 
			client.execute(func);
			JCO.Table output = func.getTableParameterList().getTable(
					"ET_DATA");
			for (int i=0;i<output.getNumRows();i++,output.nextRow()) {
				CuanhuoQuery cq = new CuanhuoQuery();
				cq.setWERKS(output.getField("WERKS").getValue()
						.toString());
				cq.setWERKS_NAME(output.getField("WERKS_NAME").getValue()
						.toString());
				cq.setLOCCO(output.getField("LOCCO").getValue()
						.toString());
				cq.setDATUM(output.getField("DATUM").getValue()
						.toString());
				cq.setMATNR(output.getField("MATNR").getValue()
						.toString());
				cq.setMAKTX(output.getField("MAKTX").getValue()
						.toString());
				cq.setCHARG(output.getField("CHARG").getValue()
						.toString());
				cq.setVBELN_VL(output.getField("VBELN_VL").getValue()
						.toString());
				cq.setVBELN_VA(output.getField("VBELN_VA").getValue()
						.toString());
				cq.setKUNAG(output.getField("KUNAG").getValue()
						.toString());
				cq.setKUNAG_NAME(output.getField("KUNAG_NAME").getValue()
						.toString());
				cq.setKUNWE(output.getField("KUNWE").getValue()
						.toString());
				cq.setKUNWE_NAME(output.getField("KUNWE_NAME").getValue()
						.toString());
				if(output.getField("PODAT").getValue() !=null){
				    cq.setPODAT(output.getField("PODAT").getValue().toString());
				}else{
					cq.setPODAT("");
				}
				cuanhuoQuerys.add(cq);
			}
		
		} catch (ServiceException e) {
			logger.error(cuanhuoQuery, e);
		}
		return cuanhuoQuerys;
	}

}

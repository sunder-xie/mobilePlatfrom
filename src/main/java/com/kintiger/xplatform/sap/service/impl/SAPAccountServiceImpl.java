package com.kintiger.xplatform.sap.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.xplatform.api.sap.ISAPAccountService;
import com.kintiger.xplatform.api.sap.bo.SAPAccount;
import com.kintiger.xplatform.api.sap.bo.SAPConnectionBean;
import com.kintiger.xplatform.framework.exception.SystemException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.sap.dao.ISAPDao;
import com.sap.mw.jco.JCO;

/**
 * sap account service.
 * 
 * @author xujiakun
 * 
 */
public class SAPAccountServiceImpl implements ISAPAccountService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(SAPAccountServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private SAPConnectionBean sapConnection;

	private ISAPDao sapDao;

	public int getSAPAccountCount(SAPAccount sapAccount) {
		try {
			return sapDao.getSAPAccountCount(sapAccount);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sapAccount), e);
		}

		return 0;
	}

	public List<SAPAccount> getSAPAccountList(SAPAccount sapAccount) {
		try {
			return sapDao.getSAPAccountList(sapAccount);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sapAccount), e);
		}

		return null;
	}

	public List<SAPAccount> getUnusualSAPAccountList() {
		try {
			return sapDao.getUnusualSAPAccountList();
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public int createSAPAccount(List<SAPAccount> sapAccounts) {
		if (sapAccounts == null || sapAccounts.size() == 0) {
			return 0;
		}

		int count = 0;

		for (final SAPAccount sapAccount : sapAccounts) {
			boolean o = (Boolean) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus ts) {
					try {
						sapDao.createSAPAccount(sapAccount);
					} catch (Exception e) {
						logger.error("sapAccount:" + sapAccount, e);
						ts.setRollbackOnly();
						return false;
					}

					try {
						createAccount(sapAccount.getSapAccount());
					} catch (Exception e) {
						logger.error("sapAccount:" + sapAccount, e);
						ts.setRollbackOnly();
						return false;
					}

					return true;
				}
			});

			if (o) {
				count++;
			}
		}

		return count;
	}

	private void createAccount(String sapAccount) throws SystemException {
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();

			sapConnection.setFuncName("BAPI_USER_CREATE");
			JCO.Function func = sapConnection.getFunction(client);

			JCO.ParameterList input = func.getImportParameterList();
			input.setValue(sapAccount, "USERNAME");

			JCO.Structure s1 = func.getImportParameterList().getStructure("PASSWORD");
			s1.setValue("123456", "BAPIPWD");

			JCO.Structure s2 = func.getImportParameterList().getStructure("ADDRESS");
			s2.setValue(sapAccount, "LASTNAME");

			JCO.Structure s3 = func.getImportParameterList().getStructure("LOGONDATA");
			s3.setValue("S", "USTYP");

			JCO.Structure s4 = func.getImportParameterList().getStructure("DEFAULTS");
			s4.setValue("LP01", "SPLD");
			s4.setValue("G", "SPDB");
			s4.setValue("D", "SPDA");

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
			logger.error("sapAccount:" + sapAccount, e);
		} finally {
			if (client != null) {
				JCO.releaseClient(client);
			}
			client = null;
		}
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public ISAPDao getSapDao() {
		return sapDao;
	}

	public void setSapDao(ISAPDao sapDao) {
		this.sapDao = sapDao;
	}

}

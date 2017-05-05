package com.kintiger.xplatform.dict.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.dict.bo.DictType;
import com.kintiger.xplatform.dict.dao.IDictDao;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;

/**
 * 
 * @author xujiakun
 * 
 */
public class DictServiceImpl implements IDictService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(DictServiceImpl.class);

	private TransactionTemplate transactionTemplate;

	private IDictDao dictDao;

	public int getDictTypeCount(DictType dictType) {
		try {
			return dictDao.getDictTypeCount(dictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dictType), e);
		}

		return 0;
	}

	public List<DictType> getDictTypeList(DictType dictType) {
		try {
			return dictDao.getDictTypeList(dictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dictType), e);
		}

		return null;
	}

	public int getDictCount(Dict dict) {
		try {
			return dictDao.getDictCount(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return 0;
	}

	public List<Dict> getDictList(Dict dict) {
		try {
			return dictDao.getDictList(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return null;
	}

	public List<Dict> getDicts(Dict dict) {
		try {
			return dictDao.getDicts(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return null;
	}

	public BooleanResult createDict(Dict dict) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IDictService.ERROR_MESSAGE);

		try {
			Long itemId = dictDao.createDict(dict);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(itemId));
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return booleanResult;
	}

	public BooleanResult createDictType(DictType dictType) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IDictService.ERROR_MESSAGE);

		try {
			Long dictTypeId = dictDao.createDictType(dictType);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(dictTypeId));
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dictType), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDict(Dict dict) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IDictService.ERROR_MESSAGE);

		try {
			int n = dictDao.updateDict(dict);
			if (n == 1) {
				booleanResult.setResult(true);
			} else {
				booleanResult.setCode(IDictService.ERROR_NULL_MESSAGE);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return booleanResult;
	}

	public BooleanResult updateDictType(DictType dictType) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IDictService.ERROR_MESSAGE);

		try {
			int n = dictDao.updateDictType(dictType);
			if (n == 1) {
				booleanResult.setResult(true);
			} else {
				booleanResult.setCode(IDictService.ERROR_NULL_MESSAGE);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dictType), e);
		}

		return booleanResult;
	}

	public BooleanResult deleteDictType(final DictType dictType) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode(IDictService.ERROR_MESSAGE);

		boolean o = (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				try {
					dictType.setDictTypeState("D");
					int n = dictDao.updateDictType(dictType);
					if (n != 1) {
						ts.setRollbackOnly();
						return false;
					}
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(dictType), e);
					ts.setRollbackOnly();
					return false;
				}

				Dict dict = new Dict();
				try {
					dict.setDictTypeId(dictType.getDictTypeId());
					dict.setItemState("D");
					dictDao.updateDict(dict);
				} catch (Exception e) {
					logger.error(LogUtil.parserBean(dict), e);
					ts.setRollbackOnly();
					return false;
				}

				return true;
			}
		});

		if (o) {
			booleanResult.setResult(true);
		}

		return booleanResult;
	}

	public BooleanResult deleteDict(Dict dict) {
		if (dict == null || dict.getItemId() == null) {
			BooleanResult booleanResult = new BooleanResult();
			booleanResult.setResult(false);
			booleanResult.setCode(IDictService.ERROR_MESSAGE);
			return booleanResult;
		}

		dict.setItemState("D");

		return updateDict(dict);
	}

	public DictType getDictType(DictType dictType) {
		try {
			return dictDao.getDictType(dictType);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dictType), e);
		}

		return null;
	}

	public Dict getDict(Dict dict) {
		try {
			return dictDao.getDict(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}

		return null;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IDictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(IDictDao dictDao) {
		this.dictDao = dictDao;
	}

}

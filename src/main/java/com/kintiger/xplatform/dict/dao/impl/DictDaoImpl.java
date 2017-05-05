package com.kintiger.xplatform.dict.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.dict.bo.DictType;
import com.kintiger.xplatform.dict.dao.IDictDao;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author xujiakun
 * 
 */
public class DictDaoImpl extends BaseDaoImpl implements IDictDao {

	public int getDictTypeCount(DictType dictType) {
		return (Integer) getSqlMapClientTemplate().queryForObject("dict.getDictTypeCount", dictType);
	}

	@SuppressWarnings("unchecked")
	public List<DictType> getDictTypeList(DictType dictType) {
		return (List<DictType>) getSqlMapClientTemplate().queryForList("dict.getDictTypeList", dictType);
	}

	public int getDictCount(Dict dict) {
		return (Integer) getSqlMapClientTemplate().queryForObject("dict.getDictCount", dict);
	}

	@SuppressWarnings("unchecked")
	public List<Dict> getDictList(Dict dict) {
		return (List<Dict>) getSqlMapClientTemplate().queryForList("dict.getDictList", dict);
	}

	@SuppressWarnings("unchecked")
	public List<Dict> getDicts(Dict dict) {
		return (List<Dict>) getSqlMapClientTemplate().queryForList("dict.getDicts", dict);
	}

	public Long createDict(Dict dict) {
		return (Long) getSqlMapClientTemplate().insert("dict.createDict", dict);
	}

	public Long createDictType(DictType dictType) {
		return (Long) getSqlMapClientTemplate().insert("dict.createDictType", dictType);
	}

	public int updateDict(Dict dict) {
		return getSqlMapClientTemplate().update("dict.updateDict", dict);
	}

	public int updateDictType(DictType dictType) {
		return getSqlMapClientTemplate().update("dict.updateDictType", dictType);
	}

	public DictType getDictType(DictType dictType) {
		return (DictType) getSqlMapClientTemplate().queryForObject("dict.getDictType", dictType);
	}

	public Dict getDict(Dict dict) {
		return (Dict) getSqlMapClientTemplate().queryForObject("dict.getDict", dict);
	}

}

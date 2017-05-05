package com.kintiger.xplatform.dict.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.dict.bo.DictType;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * 字典.
 * 
 * @author xujiakun
 * 
 */
public class DictAction extends BaseAction {

	private static final long serialVersionUID = 5042752280539471298L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(DictAction.class);

	private List<Dict> dictList = new ArrayList<Dict>();

	private List<DictType> dictTypeList = new ArrayList<DictType>();

	private IDictService dictService;

	private int total;

	@Decode
	private String dictTypeName;
	@Decode
	private String remark;
	@Decode
	private String dictTypeValue;

	private String dictTypeId;

	private String itemId;

	private String itemValue;

	private Dict dict;

	private DictType dictType;

	/**
	 * 查询DictType.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "字典查询")
	public String searchDictType() {
		return "searchDictType";
	}

	/**
	 * 查询DictType.
	 * 
	 * @return
	 */
	@JsonResult(field = "dictTypeList", include = { "dictTypeId", "dictTypeName", "dictTypeValue", "remark",
		"modifyDate" }, total = "total")
	public String getDictTypeJsonList() {
		DictType m = new DictType();
		m = getSearchInfo(m);

		if (StringUtil.isNotEmpty(dictTypeName) && StringUtil.isNotEmpty(dictTypeName.trim())) {
			m.setDictTypeName(dictTypeName.trim());
		}

		if (StringUtil.isNotEmpty(dictTypeValue) && StringUtil.isNotEmpty(dictTypeValue.trim())) {
			m.setDictTypeValue(dictTypeValue.trim());
		}

		if (StringUtil.isNotEmpty(remark) && StringUtil.isNotEmpty(remark.trim())) {
			m.setRemark(remark.trim());
		}

		total = dictService.getDictTypeCount(m);

		if (total != 0) {
			dictTypeList = dictService.getDictTypeList(m);
		}

		return JSON;
	}

	/**
	 * 
	 * @return
	 */
	@JsonResult(field = "dictList", include = { "itemId", "itemName", "itemValue", "itemDescription", "remark",
		"modifyDate" }, total = "total")
	public String getDictJsonList() {
		Dict m = new Dict();
		m = getSearchInfo(m);

		if (StringUtil.isNotEmpty(dictTypeId) && StringUtil.isNotEmpty(dictTypeId.trim())) {
			try {
				m.setDictTypeId(Long.valueOf(dictTypeId));
			} catch (NumberFormatException e) {
				logger.error("dictTypeId:" + dictTypeId, e);

				return JSON;
			}
		} else {
			return JSON;
		}

		total = dictService.getDictCount(m);

		if (total != 0) {
			dictList = dictService.getDictList(m);
		}

		return JSON;
	}

	@JsonResult(field = "dictList", include = { "itemId", "itemName", "itemValue" })
	public String getDictList4ComboBox() {
		Dict m = new Dict();

		if (StringUtil.isNotEmpty(dictTypeValue) && StringUtil.isNotEmpty(dictTypeValue.trim())) {
			m.setDictTypeValue(dictTypeValue.trim());
		} else {
			return JSON;
		}

		if (StringUtil.isNotEmpty(itemValue) && StringUtil.isNotEmpty(itemValue.trim())) {
			m.setItemValue(itemValue.trim());
		}

		dictList = dictService.getDicts(m);

		return JSON;
	}

	private boolean validateDictType(DictType dictType) {
		if (dictType == null) {
			return false;
		}

		if (StringUtil.isEmpty(dictType.getDictTypeName()) || StringUtil.isEmpty(dictType.getDictTypeName().trim())
			|| StringUtil.isEmpty(dictType.getDictTypeValue())
			|| StringUtil.isEmpty(dictType.getDictTypeValue().trim())) {
			return false;
		}

		return true;
	}

	public String createDictTypePrepare() {
		return "createDictTypePrepare";
	}

	@ActionLog(actionName = "字典类型创建")
	public String createDictType() {
		if (!validateDictType(dictType)) {
			this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult booleanResult = dictService.createDictType(dictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		} else {
			this.setSuccessMessage("字典类型创建成功！");
		}

		return RESULT_MESSAGE;
	}

	private boolean validateDict(Dict dict) {
		if (dict == null) {
			return false;
		}

		if (dict.getDictTypeId() == null || StringUtil.isEmpty(dict.getItemName())
			|| StringUtil.isEmpty(dict.getItemName().trim()) || StringUtil.isEmpty(dict.getItemValue())
			|| StringUtil.isEmpty(dict.getItemValue().trim())) {
			return false;
		}

		return true;
	}

	public String createDictPrepare() {
		if (StringUtil.isNotEmpty(dictTypeName) && StringUtil.isNotEmpty(dictTypeName.trim())) {
			try {
				dictTypeName = new String(dictTypeName.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(dictTypeName, e);
			}
		}

		return "createDictPrepare";
	}

	@ActionLog(actionName = "字典明细创建")
	public String createDict() {
		if (!validateDict(dict)) {
			this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult booleanResult = dictService.createDict(dict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		} else {
			this.setSuccessMessage("字典明细创建成功！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 字典类型查看修改.
	 * 
	 * @return
	 */
	public String updateDictTypePrepare() {
		if (StringUtil.isNotEmpty(dictTypeId) && StringUtil.isNotEmpty(dictTypeId.trim())) {
			try {
				DictType type = new DictType();
				type.setDictTypeId(Long.valueOf(dictTypeId));
				dictType = dictService.getDictType(type);
			} catch (NumberFormatException e) {
				logger.error(dictTypeId, e);
			}
		}

		return "updateDictTypePrepare";
	}

	@ActionLog(actionName = "字典类型修改")
	public String updateDictType() {
		if (!validateDictType(dictType)) {
			this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult booleanResult = dictService.updateDictType(dictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		} else {
			this.setSuccessMessage("字典类型修改成功！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 字典明细查看修改.
	 * 
	 * @return
	 */
	public String updateDictPrepare() {
		if (StringUtil.isNotEmpty(itemId) && StringUtil.isNotEmpty(itemId.trim())) {
			try {
				Dict d = new Dict();
				d.setItemId(Long.valueOf(itemId));
				dict = dictService.getDict(d);
			} catch (NumberFormatException e) {
				logger.error(dictTypeId, e);
			}
		}

		return "updateDictPrepare";
	}

	@ActionLog(actionName = "字典明细修改")
	public String updateDict() {
		if (!validateDict(dict)) {
			this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult booleanResult = dictService.updateDict(dict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		} else {
			this.setSuccessMessage("字典明细修改成功！");
		}

		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "字典类型删除")
	public String deleteDictType() {
		if (StringUtil.isEmpty(dictTypeId) || StringUtil.isEmpty(dictTypeId.trim())) {
			this.setFailMessage("被删除字典类型不能为空！");
			return RESULT_MESSAGE;
		}

		try {
			DictType d = new DictType();
			d.setDictTypeId(Long.valueOf(dictTypeId));

			BooleanResult booleanResult = dictService.deleteDictType(d);
			if (!booleanResult.getResult()) {
				this.setFailMessage(booleanResult.getCode());
			} else {
				this.setSuccessMessage("字典类型删除成功！");
			}

			return RESULT_MESSAGE;
		} catch (NumberFormatException e) {
			logger.error(dictTypeId, e);
		}

		this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
		return RESULT_MESSAGE;
	}

	@ActionLog(actionName = "字典明细删除")
	public String deleteDict() {
		if (StringUtil.isEmpty(itemId) || StringUtil.isEmpty(itemId.trim())) {
			this.setFailMessage("被删除字典明细不能为空！");
			return RESULT_MESSAGE;
		}

		try {
			Dict d = new Dict();
			d.setItemId(Long.valueOf(itemId));

			BooleanResult booleanResult = dictService.deleteDict(d);

			if (!booleanResult.getResult()) {
				this.setFailMessage(booleanResult.getCode());
			} else {
				this.setSuccessMessage("字典明细删除成功！");
			}

			return RESULT_MESSAGE;
		} catch (NumberFormatException e) {
			logger.error(itemId, e);
		}

		this.setFailMessage(IDictService.ERROR_INPUT_MESSAGE);
		return RESULT_MESSAGE;
	}

	public List<Dict> getDictList() {
		return dictList;
	}

	public void setDictList(List<Dict> dictList) {
		this.dictList = dictList;
	}

	public List<DictType> getDictTypeList() {
		return dictTypeList;
	}

	public void setDictTypeList(List<DictType> dictTypeList) {
		this.dictTypeList = dictTypeList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public String getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

}

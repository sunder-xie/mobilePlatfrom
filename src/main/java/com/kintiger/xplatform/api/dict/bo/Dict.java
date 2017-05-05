package com.kintiger.xplatform.api.dict.bo;

import java.util.Date;

import com.kintiger.xplatform.framework.bo.SearchInfo;

/**
 * 字典對象.
 * 
 * @author xujiakun
 * 
 */
public class Dict extends SearchInfo {

	private static final long serialVersionUID = -31231206571094321L;

	/**
	 * 字典项ID.
	 */
	private Long itemId;

	/**
	 * 字典类型ID.
	 */
	private Long dictTypeId;

	/**
	 * 字典项父项ID.
	 */
	private Long parentItemId;

	/**
	 * 字典项名称.
	 */
	private String itemName;

	/**
	 * 字典项描述.
	 */
	private String itemDescription;

	/**
	 * 值.
	 */
	private String itemValue;

	/**
	 * 备注.
	 */
	private String remark;

	/**
	 * 状态.
	 */
	private String itemState;

	private Date modifyDate;

	private DictType dictType;

	/**
	 * 字典主表名称.
	 */
	private String dictTypeName;

	private String dictTypeValue;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(Long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public Long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public Date getModifyDate() {
		return modifyDate != null ? (Date) modifyDate.clone() : null;
	}

	public void setModifyDate(Date modifyDate) {
		if (modifyDate != null) {
			this.modifyDate = (Date) modifyDate.clone();
		}
	}

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

}

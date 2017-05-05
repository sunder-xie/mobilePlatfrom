package com.kintiger.xplatform.api.sms.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Message.
 * 
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -3931114432698585860L;

	private Long id;

	private String mobile;

	private String smsContent;

	private String smsType;

	private Date sendTime;

	private Long sendFlag;

	private String smsId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public Date getSendTime() {
		return sendTime != null ? (Date) sendTime.clone() : null;
	}

	public void setSendTime(Date sendTime) {
		if (sendTime != null) {
			this.sendTime = (Date) sendTime.clone();
		}
	}

	public Long getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Long sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

}

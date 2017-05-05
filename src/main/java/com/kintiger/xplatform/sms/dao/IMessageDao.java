package com.kintiger.xplatform.sms.dao;

import com.kintiger.xplatform.api.sms.bo.Message;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IMessageDao {

	/**
	 * 
	 * @param message
	 * @return
	 */
	int createMessage(Message message);

}

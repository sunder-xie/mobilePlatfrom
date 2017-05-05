package com.kintiger.xplatform.api.sms;

import com.kintiger.xplatform.api.sms.bo.Message;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 
 * @author xujiakun
 * 
 */
public interface IMessageService {

	String SMS_TYPE_DEFAULT = "WBZ";

	Long SEND_FLAG_DEFAULT = 0L;

	String SMS_ID_PREFIX_DEFAULT = "NF";

	/**
	 * sendMessage.
	 * 
	 * @param message
	 * @return
	 */
	BooleanResult sendMessage(Message message);

}

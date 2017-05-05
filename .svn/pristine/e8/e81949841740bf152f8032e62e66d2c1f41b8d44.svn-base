package com.kintiger.xplatform.sms.service.impl;

import com.kintiger.xplatform.api.sms.IMessageService;
import com.kintiger.xplatform.api.sms.bo.Message;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.sms.dao.IMessageDao;

/**
 * 
 * @author jiakunxu
 * 
 */
public class MessageServiceImpl implements IMessageService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(MessageServiceImpl.class);

	private IMessageDao messageDao;

	public BooleanResult sendMessage(Message message) {
		BooleanResult result = new BooleanResult();

		try {
			messageDao.createMessage(message);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
			logger.error(LogUtil.parserBean(message), e);
		}

		return result;
	}

	public IMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

}

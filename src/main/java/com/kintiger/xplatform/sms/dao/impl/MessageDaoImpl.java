package com.kintiger.xplatform.sms.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.alibaba.common.lang.StringUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.xplatform.api.sms.bo.Message;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.sms.dao.IMessageDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {

	public int createMessage(final Message message) {

		return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();

				String mobile = message.getMobile();

				if (StringUtil.isNotEmpty(mobile)) {
					String[] mobiles = mobile.split(";");
					for (String m : mobiles) {
						message.setMobile(m.trim());
						executor.insert("sms.createMessage", message);
					}
				}

				return executor.executeBatch();
			}
		});
	}

}

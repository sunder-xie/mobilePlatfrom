package com.kintiger.xplatform.framework.ibatis.type;

import java.sql.SQLException;

import com.alibaba.common.lang.StringUtil;
import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * 
 * @author
 * 
 */
public class PostfixTypeHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter arg0) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object obj) throws SQLException {
		String parameter = (String) obj;

		if (StringUtil.isBlank(parameter)) {
			setter.setString("%");
		} else {
			parameter = parameter.replace("%", "\\%");
			parameter = parameter.replace("_", "\\_");

			setter.setString("%" + parameter);
		}
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}

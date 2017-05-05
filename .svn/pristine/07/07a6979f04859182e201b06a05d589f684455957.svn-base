package com.kintiger.xplatform.framework.webwork.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.log.bo.Log;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.ClientUtil;
import com.kintiger.xplatform.framework.util.DateUtil;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * ActionLogInterceptor.
 * 
 * @author xujiakun
 * 
 */
public class ActionLogInterceptor implements Interceptor {

	private static final long serialVersionUID = -57833731348869514L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(ActionLogInterceptor.class);

	private IMemcachedCacheService memcachedCacheService;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		Object action = invocation.getAction();
		Method method = null;
		try {
			method = action.getClass().getDeclaredMethod(invocation.getProxy().getMethod(), new Class[0]);
		} catch (Exception e) {
			method =
				action.getClass().getDeclaredMethod(
					"do" + invocation.getProxy().getMethod().substring(0, 1).toUpperCase()
						+ invocation.getProxy().getMethod().substring(1), new Class[0]);
		}

		ActionLog actionLog = method.getAnnotation(ActionLog.class);
		if (actionLog != null) {
			String actionName = actionLog.actionName();
			// actionName 不为空时，记录操作日志
			if (StringUtil.isEmpty(actionName)) {
				return invocation.invoke();
			}

			@SuppressWarnings("rawtypes")
			Map session = invocation.getInvocationContext().getSession();
			User user = (User) session.get("ACEGI_SECURITY_LAST_LOGINUSER");
			if (user == null) {
				return invocation.invoke();
			}

			Log log = new Log();
			log.setUserId(user.getUserId());
			log.setActionName(actionName);

			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			log.setIp(ClientUtil.getIpAddr(request));
			log.setCreateDate(DateUtil.getNowDatetimeStr());

			try {
				@SuppressWarnings("unchecked")
				List<Log> logs = (List<Log>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_ACTION_LOG);

				if (logs == null || logs.size() == 0) {
					logs = new ArrayList<Log>();
				}

				logs.add(log);

				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_ACTION_LOG, logs,
					IMemcachedCacheService.CACHE_KEY_ACTION_LOG_DEFAULT_EXP);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return invocation.invoke();
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

}

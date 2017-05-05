package com.kintiger.xplatform.framework.webwork.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.login.ICAService;
import com.kintiger.xplatform.api.user.bo.User;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * AuthencationInterceptor.
 * 
 * @author xujiakun
 * 
 */
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -7498838714747075663L;

	private static final String LOGIN_TIMEOUT = "440";

	private static final String UNAUTHORIZED = "401";

	private ICAService caService;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get("ACEGI_SECURITY_LAST_LOGINUSER");

		if (user == null) {
			return LOGIN_TIMEOUT;
		}

		if (validateRequest(user.getUserId())) {
			return invocation.invoke();
		} else {
			return UNAUTHORIZED;
		}
	}

	/**
	 * 验证当前Request是否有权限.
	 * 
	 * @return
	 */
	private boolean validateRequest(String userId) {
		String actionName = getActionName();
		if (StringUtil.isNotEmpty(actionName)) {
			// 1 判断actionName是否属于menu
			// 2 判断当前用户是否拥有该menu权限
			return caService.validateRequest(userId, actionName);
		}

		return false;
	}

	/**
	 * actionName.
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		// Map map = ctx.getSession()
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath()) + request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	private String getRequetSessionId() {
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		return request.getRequestedSessionId();
	}

	@SuppressWarnings("rawtypes")
	private Map getRequestParam() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		return request.getParameterMap();
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

}

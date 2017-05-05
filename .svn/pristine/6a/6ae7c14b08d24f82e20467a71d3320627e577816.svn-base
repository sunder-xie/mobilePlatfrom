package com.kintiger.xplatform.login.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.common.lang.StringUtil;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.login.ICAService;
import com.kintiger.xplatform.api.login.bo.ValidateResult;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.exception.SystemException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.mail.MailService;
import com.kintiger.xplatform.framework.util.EncryptUtil;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.util.OidUtil;

/**
 * 登录模块.
 * 
 * @author xujiakun
 * 
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 7498561926934442624L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(LoginAction.class);

	private ICAService caService;

	private String passport;

	private String password;

	private boolean validate;

	private IMemcachedCacheService memcachedCacheService;

	private ISAPService sapService;

	private IUserService userService;

	private String smtpServer;

	private String mailFrom;

	private String mailUsername;

	private String mailPassword;

	/**
	 * 验证码.
	 */
	private String checkCode;

	/**
	 * 原密码.
	 */
	private String oldPassword;

	private String userName;

	/**
	 * 登录首页.
	 * 
	 * @return
	 */
	public String index() {
		User user = getUser();
		if (user != null) {
			userName = user.getUserName();
			passport = user.getPassport();
		}

		return SUCCESS;
	}

	/**
	 * 登录验证.
	 * 
	 * @return
	 */
//	@ActionLog(actionName = "系统登录")
//	public String login() {
//
//		ValidateResult result = null;
//
//		// true:需要域验证 false:不需要域验证
//		if (validate) {
//			result = caService.validateUserByLDAP(passport, password);
//		} else {
//			result = caService.validateUser(passport, password);
//		}
//
//		// 验证失败
//		if (ICAService.RESULT_FAILED.equals(result.getResultCode())
//			|| ICAService.RESULT_ERROR.equals(result.getResultCode())) {
//			this.setFailMessage(result.getMessage());
//			return "incorrect";
//		}
//
//		// 验证通过
//		User loginUser = result.getUser();
//
//		HttpSession session = this.getSession();
//		session.setAttribute("ACEGI_SECURITY_LAST_PASSPORT", loginUser.getPassport());
//		session.setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
//
//		return SUCCESS;
//	}

	/**
	 * 退出系统.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "系统退出")
	public String logout() {
		HttpSession session = this.getSession();

		// 清空cache中session信息
		try {
			memcachedCacheService.remove(session.getId());
		} catch (Exception e) {
			logger.error(e);
		}

		// 退出sap web免登
		String ps = (String) session.getAttribute("SAP_ACCOUNT");
		if (StringUtil.isNotEmpty(ps)) {
			User user = this.getUser();
			if (user != null) {
				sapService.logout(ps, user.getUserId());
			}
		}

		// 退出bo免登
		try {
			IEnterpriseSession enterpriseSession = (IEnterpriseSession) session.getAttribute("EnterpriseSession");
			if (enterpriseSession != null) {
				enterpriseSession.logoff();
			}

			enterpriseSession = (IEnterpriseSession) session.getAttribute("Bo4EnterpriseSession");
			if (enterpriseSession != null) {
				enterpriseSession.logoff();
			}
		} catch (Exception e) {
			logger.error(e);
		}

		try {
			// login
			session.removeAttribute("ACEGI_SECURITY_LAST_PASSPORT");
			session.removeAttribute("ACEGI_SECURITY_LAST_LOGINUSER");
			// bo
			session.removeAttribute("EnterpriseSession");
			session.removeAttribute("Bo4EnterpriseSession");
			session.removeAttribute("LogonToken");
			session.removeAttribute("Bo4LogonToken");
			// sap web
			session.removeAttribute("SAP_ACCOUNT");
			session.removeAttribute("SAP_ACCOUNT_PASSWORD");

			session.invalidate();
		} catch (Exception e) {
			logger.error(e);
		}

		return LOGOUT;
	}

	public String homePage() {
		return "homePage";
	}

	public String headMenu() {

		User user = getUser();

		// 用户信息显示
		passport = user.getPassport();
		this.setName(user.getUserName());

		return "headMenu";
	}

	public String forgetPassword() {
		if (StringUtil.isNotEmpty(passport) && StringUtil.isNotEmpty(passport.trim())) {
			try {
				passport = new String(passport.trim().getBytes("ISO8859-1"), "UTF-8");
			} catch (Exception e) {
				logger.error(passport, e);
			}
		}

		return SUCCESS;
	}

	/**
	 * 找回登录密码 发送验证码.
	 * 
	 * @return
	 */
//	public String sendCheckCode() {
//		if (StringUtil.isEmpty(passport) || StringUtil.isEmpty(passport.trim())) {
//			this.setSuccessMessage("用户名不能为空！");
//			return ERROR;
//		}
//
//		User user = userService.getUserByPassport(passport);
//
//		if (user == null) {
//			this.setSuccessMessage("系统内无该用户,请确认账号是否正确！");
//			return ERROR;
//		}
//
//		if (StringUtil.isEmpty(user.getEmail())) {
//			this.setSuccessMessage("邮件发送失败，没有找到该账号的邮箱地址，请确认账号是否正确！如账号是准确的，请联系管理员，确认邮箱信息是否维护！");
//			return ERROR;
//		}
//
//		String key = generateCheckCode(user);
//
//		if (StringUtil.isEmpty(key)) {
//			this.setSuccessMessage("系统正忙，请稍后再试！");
//			return ERROR;
//		}
//
//		StringBuilder content = new StringBuilder();
//		content.append("尊敬的用户<br>&nbsp;&nbsp;您的姓名为：").append(user.getUserName())
//			.append("&nbsp;&nbsp;<br>&nbsp;&nbsp;您的登陆账号为：").append(passport)
//			.append("&nbsp;&nbsp;<br>&nbsp;&nbsp;请点击修改:<a href='").append(env.getProperty("appUrl"))
//			.append("/setPassword.htm?checkCode=").append(key)
//			.append("'>点击此链接修改</a><br>请在5分钟内完成验证，5分钟后链接失效，您需要重新进行验证。如果您错误的收到了本电子邮件，请您忽略上述内容<br>");
//
//		MailService mail =
//			new MailService(smtpServer, mailFrom, "xplatform", mailUsername, mailPassword, user.getEmail(), "密码修改确认函",
//				content.toString());
//		Map<String, String> map = mail.send();
//
//		if ("success".equals(map.get("state"))) {
//			String[] s = user.getEmail().split("@");
//			StringBuilder email = new StringBuilder();
//			email.append(s[0].substring(0, 1));
//			for (int i = 0; i < s[0].length() - 2; i++) {
//				email.append("*");
//			}
//			email.append(s[0].substring(s[0].length() - 1, s[0].length()));
//			email.append("@").append(s[1]);
//
//			this.setSuccessMessage("尊敬的用户 " + user.getUserName() + "，您好：邮件已发送至您邮箱" + email.toString() + "，注意查收！");
//			return SUCCESS;
//		}
//
//		this.setSuccessMessage("邮件发送失败,请联系系统管理员！");
//		return ERROR;
//	}

	/**
	 * 修改密码.
	 * 
	 * @return
	 */
	public String setPassword() {
		return SUCCESS;
	}

	public String updatePassword() {
		if (StringUtil.isEmpty(checkCode)) {
			this.setFailMessage("链接失效，请重新点击忘记登录密码！");
			return RESULT_MESSAGE;
		}

		User user = validateCheckCode(checkCode);
		if (user == null) {
			this.setFailMessage("链接失效，请重新点击忘记登录密码！");
			return RESULT_MESSAGE;
		}

		if (StringUtil.isEmpty(password)) {
			this.setFailMessage("密码不能为空！");
			return RESULT_MESSAGE;
		}

		try {
			user.setPassword(EncryptUtil.md5Encry(password));
		} catch (SystemException e) {
			logger.error("password:" + password, e);
			this.setFailMessage("系统正忙，请稍后再试！");
			return RESULT_MESSAGE;
		}

		BooleanResult result = userService.updateUser(user);
		if (result.getResult()) {
			invalidCheckCode(checkCode);
			this.setSuccessMessage("成功修改密码！");
			return RESULT_MESSAGE;
		}

		this.setFailMessage("系统正忙，请稍后再试！");
		return RESULT_MESSAGE;
	}

	private String generateCheckCode(Object object) {
		try {
			String token = OidUtil.newId();
			memcachedCacheService.add(token, object, IMemcachedCacheService.CACHE_KEY_CHECK_CODE_DEFAULT_EXP);

			return token;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(object), e);
		}

		return null;
	}

	private User validateCheckCode(String checkCode) {
		try {
			return (User) memcachedCacheService.get(checkCode);
		} catch (Exception e) {
			logger.error("checkCode:" + checkCode, e);
		}

		return null;
	}

	private void invalidCheckCode(String checkCode) {
		try {
			memcachedCacheService.remove(checkCode);
		} catch (Exception e) {
			logger.error("checkCode:" + checkCode, e);
		}
	}

	/**
	 * 重新修改密码.
	 * 
	 * @return
	 */
	public String resetPassword() {
		return "resetPassword";
	}

	@ActionLog(actionName = "密码修改")
	public String renewPassword() {
		if (StringUtil.isEmpty(oldPassword)) {
			this.setFailMessage("请输入原密码！");
			return RESULT_MESSAGE;
		}

		if (StringUtil.isEmpty(password)) {
			this.setFailMessage("请输入新密码！");
			return RESULT_MESSAGE;
		}

		User user = getUser();
		try {
			user.setPassword(EncryptUtil.md5Encry(password));
			user.setOldPassword(EncryptUtil.md5Encry(oldPassword));
		} catch (SystemException e) {
			logger.error("password:" + password + "oldPassword:" + oldPassword, e);
			this.setFailMessage("系统正忙，请稍后再试！");
			return RESULT_MESSAGE;
		}

		BooleanResult result = userService.updateUser(user);
		if (result.getResult()) {
			this.setSuccessMessage("成功修改密码，请重新登录！");
			// 清空session
			logout();

			return RESULT_MESSAGE;
		}

		this.setFailMessage("原密码不正确，请重新修改密码！");
		return RESULT_MESSAGE;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

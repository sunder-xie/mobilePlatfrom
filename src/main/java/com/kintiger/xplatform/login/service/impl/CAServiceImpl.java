package com.kintiger.xplatform.login.service.impl;

import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.support.TransactionTemplate;



















import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.login.ICAService;
import com.kintiger.xplatform.api.login.ILDAPService;
import com.kintiger.xplatform.api.login.bo.ValidateResult;
import com.kintiger.xplatform.api.menu.IMenuService;
import com.kintiger.xplatform.api.menu.bo.Menu;
import com.kintiger.xplatform.api.openapi.IOpenapiService;
import com.kintiger.xplatform.api.sap.ISAPService;
import com.kintiger.xplatform.api.sap.bo.SAPConnectionBean;
import com.kintiger.xplatform.api.user.IUserService;
import com.kintiger.xplatform.api.user.bo.AbnormalPrice;
import com.kintiger.xplatform.api.user.bo.BaseCustomer;
import com.kintiger.xplatform.api.user.bo.BaseMarketCheck;
import com.kintiger.xplatform.api.user.bo.BaseMenu;
import com.kintiger.xplatform.api.user.bo.BaseProduct;
import com.kintiger.xplatform.api.user.bo.CuanhuoQuery;
import com.kintiger.xplatform.api.user.bo.CustSku;
import com.kintiger.xplatform.api.user.bo.Dicts;
import com.kintiger.xplatform.api.user.bo.DisPlay;
import com.kintiger.xplatform.api.user.bo.Distribution;
import com.kintiger.xplatform.api.user.bo.Kpi;
import com.kintiger.xplatform.api.user.bo.Kunnr;
import com.kintiger.xplatform.api.user.bo.KunnrStockDate;
import com.kintiger.xplatform.api.user.bo.LoginLog;
import com.kintiger.xplatform.api.user.bo.Lstm;
import com.kintiger.xplatform.api.user.bo.Order;
import com.kintiger.xplatform.api.user.bo.OrderDetail;
import com.kintiger.xplatform.api.user.bo.OrderPrintFormat;
import com.kintiger.xplatform.api.user.bo.Picture;
import com.kintiger.xplatform.api.user.bo.Sign;
import com.kintiger.xplatform.api.user.bo.SkuUnit;
import com.kintiger.xplatform.api.user.bo.Stock;
import com.kintiger.xplatform.api.user.bo.StockAge;
import com.kintiger.xplatform.api.user.bo.StockReport;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.api.user.bo.VistCust;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.exception.SystemException;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.EncryptUtil;
import com.kintiger.xplatform.framework.util.JsonUtil;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.framework.util.OidUtil;
import com.sap.mw.jco.JCO;

/**
 * Title: 手机app登陆服务端接口后加载数据
 * Description: mobilePlatform
 *  @author: xujiakun
 * @date:2016年12月6日 上午8:46:02
 */
public class CAServiceImpl implements ICAService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(CAServiceImpl.class);

	private IUserService userService;
	private ISAPService sapService;
	private ILDAPService ldapService;
	private IMemcachedCacheService memcachedCacheService;
	private IMenuService menuService;
	private TransactionTemplate transactionTemplate;
	public ValidateResult validateToken(String token) {
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_TOKEN);

		try {
			User user = (User) memcachedCacheService.get(token);
			if (user != null) {
				// 令牌验证一次后 失效
				memcachedCacheService.remove(token);
				return setSuccessResult(result, user);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public String generateToken(Object object) {
		try {
			String token = OidUtil.newId();
			memcachedCacheService.add(token, object,
					IMemcachedCacheService.CACHE_KEY_SSO_TOKEN_DEFAULT_EXP);

			return token;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(object), e);
		}

		return null;
	}

	public boolean validateRequest(String userId, String actionName) {
		Boolean result = null;

		try {
			result = (Boolean) memcachedCacheService.get(userId + actionName);
		} catch (ServiceException e) {
			logger.error(userId + actionName, e);
		}

		if (result != null) {
			return result;
		}

		Menu menu = menuService.getMenuRequest(userId, actionName);

		// 当前请求action不属于menu or 查询出现异常
		if (menu == null) {
			result = true;
		} else if ("1".equals(menu.getSort())) {
			// 当前用户拥有菜单
			result = true;
		} else {
			result = false;
		}

		try {
			result = (Boolean) memcachedCacheService.set(userId + actionName,
					result,
					IMemcachedCacheService.CACHE_KEY_REQUEST_DEFAULT_EXP);
		} catch (ServiceException e) {
			logger.error(userId + actionName, e);
		}

		return result;
	}

	private ValidateResult setSuccessResult(ValidateResult result, User user) {
		result.setResultCode(ICAService.RESULT_SUCCESS);
		user.setPassword(null);
		result.setUser(user);
		result.setMessage(null);
		return result;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ILDAPService getLdapService() {
		return ldapService;
	}

	public void setLdapService(ILDAPService ldapService) {
		this.ldapService = ldapService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(
			IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public User validateUser(String mobile, String password,String phoneSelfNum) {
		return getUser(mobile, password,phoneSelfNum);
	}
	public User validateAdmUser(String mobile, String password,String phoneSelfNum) {
		return getAdmUser(mobile, password,phoneSelfNum);
	}
	public List<BaseProduct> getSku() {
		return getSkuById();
	}

	public List<BaseProduct> getSkuByCloud(String cloudId) {
		return getSkuByCloudId(cloudId);
	}

	public List<BaseProduct> getOrderSkuByKunner(String cloudId) {
		return getOrderSku(cloudId);
	}

	public List<BaseCustomer> getChannel() {
		return getChannelById();
	}

	public List<BaseCustomer> getDivision() {
		return getDivisionById();
	}

	public List<BaseCustomer> getDivisionSearch(String zwl04t) {
		return getDivisionSearchById(zwl04t);
	}

	public List<BaseCustomer> getDivisionforSearch(String zwl04) {
		return getDivisionforSearchById(zwl04);
	}

	public List<BaseProduct> getTime() {
		return getTimeId();
	}

	public List<Dicts> getDict() {
		return getDictById();
	}
	public List<Dicts> getAdmDict() {
		return getAdmDictById();
	}
	public List<Dicts> getDictforAct() {
		return getDictforActById();
	}

	public List<BaseMenu> getMenu() {
		return getMenuById();
	}

	public List<Dicts> getVersion(String dictTypeValue) {
		return getVersionById(dictTypeValue);
	}

	public List<BaseCustomer> getCus(String userId) {
		return getCusById(userId);
	}

	public List<BaseCustomer> getCusforSearch(BaseCustomer customer,
			String roleId) {
		return getCusforSearchBy(customer, roleId);
	}
	public List<BaseCustomer> getCusforSearch1(BaseCustomer customer,
			String roleId) {
		return getCusforSearchBy1(customer, roleId);
	}
	public List<BaseCustomer> ggetCusforSearchSet(BaseCustomer customer,
			String roleId) {
		return getCusforSearchSet(customer, roleId);
	}
	public List<BaseCustomer> getCusforSearchXpp(BaseCustomer customer,
			String roleId) {
		return getCusforSearchByXpp(customer, roleId);
	}

	public List<BaseCustomer> getKunnrforSearch(String info) {
		return getKunnrforSearchBy(info);
	}

	public List<BaseMarketCheck> getAct(String userId) {
		return getActById(userId);
	}

	public List<BaseMarketCheck> getActforSearch(BaseMarketCheck bmk1) {
		return getActforSearchBy(bmk1);
	}

	public List<BaseCustomer> getRouteforSearch(BaseCustomer customer) {
		return getRouteforSearchBy(customer);
	}

	public List<BaseCustomer> getRouteforJXS(BaseCustomer customer) {
		return getRouteforJXSBy(customer);
	}

	public List<BaseMarketCheck> getActByDid(String marketDetailId) {
		return getActByDidP(marketDetailId);
	}

	public List<BaseCustomer> getModifier(String userId) {
		return getModifierP(userId);
	}

	public List<BaseCustomer> getKunnr(String kunnr) {
		return getKunnrP(kunnr);
	}

	public int getBlCount(BaseCustomer costomer) {
		try {
			return getBlCountP(costomer);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return 0;

	}

	public int getActCount(BaseMarketCheck bmk) {

		try {
			return getActCountP(bmk);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return 0;
	}

	public Long insertImage(Picture p) {
		try {
			return insertImageP(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertPrice(AbnormalPrice ap) {
		try {
			return insertPriceP(ap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertDistr(Distribution distr) {
		try {
			return insertDistrP(distr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertDisply(DisPlay disp) {
		try {
			return insertDisplyP(disp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertCus(BaseCustomer customer) {
		try {
			return insertCusP(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	
	public Long insertStage(StockAge stage) {
		try {
			return insertStageP(stage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertSupervise(BaseMarketCheck bmk) {
		try {
			insertSuperviseP(bmk);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public BooleanResult insertOrder(Order order) {
		BooleanResult result = new BooleanResult();
		BooleanResult result1 = new BooleanResult();
		result.setResult(false);
		try {
			result1 = insertOrderP(order);
			if (result1.getResult()) {
				result.setResult(true);
				result.setCode(result1.getCode());
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}
		return result;
	}

	public BooleanResult updateOrder(Order order) {
		BooleanResult result = new BooleanResult();
		BooleanResult result1 = new BooleanResult();
		result.setResult(false);
		try {
			result1 = updateOrderP(order);
			if (result1.getResult()) {
				result.setResult(true);
				result.setCode(result1.getCode());
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}
		return result;
	}

	public boolean insertCheck(BaseMarketCheck bmk) {
		try {
			insertCheckP(bmk);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public BooleanResult updateCustomer(BaseCustomer updateCustomer) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			result = updateCustomerP(updateCustomer);
			if (result.getResult()) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(updateCustomer), e);
		}

		return result;
	}

	public BooleanResult updateSupervise(BaseMarketCheck bmk) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			result = updateSuperviseP(bmk);
			if (result.getResult()) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bmk), e);
		}

		return result;
	}

	public Long insertImageInfo(Picture p) {
		try {
			return insertImagePinfo(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private User getUser(String mobile, String password,String phoneSelfNum) {
		User loginUser = new User();
		String flag = "N";
		User loginUserinfo = new User();
		List<User> loginUsers = new ArrayList<User>();
		if (mobile.matches("^[0-9]*$") && mobile.length() == 11) {
			loginUsers = userService.getUserByMobile(mobile);

		} else {
			loginUsers = userService.getUserByCode(mobile);
		}
		if (0 != loginUsers.size()) {
			try {
				if (!EncryptUtil.md5Encry(password).equals(
						loginUsers.get(0).getPassword())) {
					loginUserinfo.setStatus("2");// 密码不对
					loginUser = loginUserinfo;
					return loginUser;
				}
				if (loginUsers.get(0).getMobile() == null) {
					loginUserinfo.setStatus("12");// 未维护公司手机号
					loginUser = loginUserinfo;
					return loginUser;
				}else{
				  if(null!=phoneSelfNum&&!"".equals(phoneSelfNum)){
					List<User> countUsers = new ArrayList<User>();
					countUsers=	userService.getUserByMobile(phoneSelfNum);
					if(countUsers.size()==0){
						loginUserinfo.setStatus("13");// 卡号不为xpp人员公务号，不能登录
						loginUser = loginUserinfo;
						return loginUser;
					}
				  }
				}
			} catch (SystemException e1) {
				e1.printStackTrace();
				return null;
			}
			for (User loginUser1 : loginUsers) {
				if (("M").equals(loginUser1.getRoleType())) {
					loginUser = loginUser1;
					loginUser.setStatus("1");// 密码正确
					flag = "Y";
					userService.saveLogin(loginUser);
					break;
				}
			}
			if (("N").equals(flag)) {
				loginUserinfo.setStatus("3");// 角色不存在
				loginUser = loginUserinfo;
			}
		} else {
			loginUserinfo.setStatus("4");// 用户不存在
			loginUser = loginUserinfo;
		}
		return loginUser;
	}
	private User getAdmUser(String mobile, String password,String phoneSelfNum) {
		User loginUser = new User();
		String flag = "N";
		User loginUserinfo = new User();
		List<User> loginUsers = new ArrayList<User>();
		if (mobile.matches("^[0-9]*$") && mobile.length() == 11) {
			loginUsers = userService.getUserByMobile(mobile);

		} else {
			loginUsers = userService.getUserByCode(mobile);
		}
		if (0 != loginUsers.size()) {
			try {
				if (!EncryptUtil.md5Encry(password).equals(
						loginUsers.get(0).getPassword())) {
					loginUserinfo.setStatus("2");// 密码不对
					loginUser = loginUserinfo;
					return loginUser;
				}
				
				 				
			} catch (SystemException e1) {
				e1.printStackTrace();
				return null;
			}
			for (User loginUser1 : loginUsers) {
				if (("admmo").equals(loginUser1.getRoleType())) {
					loginUser = loginUser1;
					loginUser.setStatus("1");// 密码正确
					flag = "Y";
					break;
				}
			}
			if (("N").equals(flag)) {
				loginUserinfo.setStatus("3");// 角色不存在
				loginUser = loginUserinfo;
			}
//			List<Dicts> dicts=userService.getAdmDict();
//			if (null != dicts) {
//				for (Dicts dicts2 : dicts) {
//					if ("login".equals(dicts2.getRemark())) {
//						if (loginUsers.get(0).getOrgId().equals(dicts2.getItemValue())) {
//							loginUser=loginUsers.get(0);
//							loginUser.setStatus("1");
//							flag="Y";
//							break;
//						}
//					}
//				}
//			}
//			if (("N").equals(flag)) {
//			loginUserinfo.setStatus("3");// 角色不存在
//			loginUser = loginUserinfo;
//			}
		} else {
			loginUserinfo.setStatus("4");// 用户不存在
			loginUser = loginUserinfo;
		}
		return loginUser;
	}
	private List<BaseProduct> getSkuById() {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = userService.getSku();
		if (null == bps) {
			return null;
		}
		return bps;
	}

	private List<BaseCustomer> getChannelById() {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getChannel();
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseCustomer> getDivisionById() {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getDivision();
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseCustomer> getDivisionSearchById(String zwl04t) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getDivisionSearch(zwl04t);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseProduct> getSkuByCloudId(String cloudId) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = userService.SkuByCloud(cloudId);
		if (null == bps) {
			return null;
		}
		return bps;
	}

	private List<BaseProduct> getOrderSku(String cloudId) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = userService.getOrderSkuByKunner(cloudId);
		if (null == bps) {
			return null;
		}
		return bps;
	}

	private List<BaseCustomer> getDivisionforSearchById(String zwl04) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getDivisionforSearch(zwl04);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseProduct> getTimeId() {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = userService.getTime();
		if (null == bps) {
			return null;
		}
		return bps;
	}

	private List<Dicts> getDictById() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = userService.getDict();
		if (null == dicts) {
			return null;
		}
		return dicts;
	}
	private List<Dicts> getAdmDictById() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = userService.getAdmDict();
		if (null == dicts) {
			return null;
		}
		return dicts;
	}
	private List<Dicts> getDictforActById() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = userService.getDictforAct();
		if (null == dicts) {
			return null;
		}
		return dicts;
	}

	private List<BaseMenu> getMenuById() {
		List<BaseMenu> menus = new ArrayList<BaseMenu>();
		menus = userService.getMenu();
		if (null == menus) {
			return null;
		}
		return menus;
	}

	private List<Dicts> getVersionById(String dictTypeValue) {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = userService.getVersion(dictTypeValue);
		if (null == dicts) {
			return null;
		}
		return dicts;
	}

	private List<BaseCustomer> getCusById(String userId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getCus(userId);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseCustomer> getCusforSearchBy(BaseCustomer customer,
			String roleId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getCusforSearch(customer, roleId);
		if (null == bcs) {
			return null;
		}
		return bcs;

	}
	/**
	 * MethodsTitle: 拜访管理之查找客户
	 * @author: xg.chen
	 * @date:2016年11月7日 
	 * @param customer
	 * @param roleId
	 * @return
	 */
	private List<BaseCustomer> getCusforSearchBy1(BaseCustomer customer,
			String roleId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getCusforSearch1(customer, roleId);
		if (null == bcs) {
			return null;
		}
		return bcs;

	}

	private List<BaseCustomer> getCusforSearchByXpp(BaseCustomer customer,
			String roleId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getCusforSearchXpp(customer, roleId);
		if (null == bcs) {
			return null;
		}
		return bcs;

	}

	private List<BaseCustomer> getKunnrforSearchBy(String info) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getKunnrforSearch(info);
		if (null == bcs) {
			return null;
		}
		return bcs;

	}

	private List<BaseMarketCheck> getActById(String userId) {
		List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		bmks = userService.getAct(userId);
		if (null == bmks) {
			return null;
		}
		return bmks;
	}

	private List<BaseMarketCheck> getActforSearchBy(BaseMarketCheck bmk1) {
		List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		bmks = userService.getActforSearch(bmk1);
		if (null == bmks) {
			return null;
		}
		return bmks;
	}

	private List<BaseCustomer> getRouteforSearchBy(BaseCustomer customer) {
		List<BaseCustomer> customers = new ArrayList<BaseCustomer>();
		customers = userService.getRouteforSearch(customer);
		if (null == customers) {
			return null;
		}
		return customers;
	}

	private List<BaseCustomer> getRouteforJXSBy(BaseCustomer customer) {
		List<BaseCustomer> customers = new ArrayList<BaseCustomer>();
		customers = userService.getRouteforJXS(customer);
		if (null == customers) {
			return null;
		}
		return customers;
	}

	private List<BaseMarketCheck> getActByDidP(String marketDetailId) {
		List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		bmks = userService.getActByDid(marketDetailId);
		if (null == bmks) {
			return null;
		}
		return bmks;
	}

	private List<BaseCustomer> getModifierP(String userId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getModifier(userId);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private List<BaseCustomer> getKunnrP(String kunnr) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getKunnr(kunnr);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	private int getBlCountP(BaseCustomer customer) {

		try {
			return userService.getBlCount(customer);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	private int getActCountP(BaseMarketCheck bmk) {

		try {
			return userService.getActCount(bmk);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	private Long insertImageP(Picture p) {
		try {
			return userService.insertImage(p);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	private Long insertPriceP(AbnormalPrice ap) {
		try {
			return userService.insertPrice(ap);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	private Long insertDistrP(Distribution distr) {
		try {
			return userService.insertDistr(distr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	private Long insertDisplyP(DisPlay disp) {
		try {
			return userService.insertDisply(disp);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	private Long insertCusP(BaseCustomer customer) {
		try {
			return userService.insertCus(customer);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}


	private BooleanResult updateCustomerP(BaseCustomer customer) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			result = userService.updateCustomer(customer);
			if (result.getResult()) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customer), e);
		}

		return result;
	}

	private BooleanResult updateSuperviseP(BaseMarketCheck bmk) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			result = userService.updateSupervise(bmk);
			if (result.getResult()) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bmk), e);
		}

		return result;

	}

	private Long insertStageP(StockAge stage) {
		try {
			return userService.insertStage(stage);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	private boolean insertSuperviseP(BaseMarketCheck bmk) {
		try {
			userService.insertSupervise(bmk);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	private BooleanResult insertOrderP(Order order) {
		BooleanResult result = new BooleanResult();
		BooleanResult result1 = new BooleanResult();
		result.setResult(false);

		try {
			result1 = userService.insertOrder(order);
			if (result1.getResult()) {
				result.setResult(true);
				result.setCode(result1.getCode());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	private BooleanResult updateOrderP(Order order) {
		BooleanResult result = new BooleanResult();
		BooleanResult result1 = new BooleanResult();
		result.setResult(false);

		try {
			result1 = userService.updateOrder(order);
			if (result1.getResult()) {
				result.setResult(true);
				result.setCode(result1.getCode());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	// boolean o = (Boolean) transactionTemplate.execute(new
	// TransactionCallback() {
	// public Object doInTransaction(TransactionStatus ts) {
	// // 插入订单
	// try {
	//
	//
	// Order order = new Order();
	// BooleanResult result1 = new BooleanResult();
	// result1= userService.insertOrder(order);
	// if (result1.getResult()) {
	// ts.setRollbackOnly();
	// return false;
	// }
	// } catch (Exception e) {
	// logger.error( e);
	// ts.setRollbackOnly();
	// return false;
	// }
	// return true;
	// }
	// });
	//
	// if (result.getResult()) {
	// result.setResult(true);
	// }

	private boolean insertCheckP(BaseMarketCheck bmk) {
		try {
			userService.insertCheck(bmk);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	private Long insertImagePinfo(Picture p) {
		try {
			return userService.insertImagePinfo(p);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	/*********************/
	public List<Order> getOrderTotal(Order order) {
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = userService.getOrderTotal(order);
			if (null == orderList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(order.getOrderId(), e);
		}
		return orderList;
	}

	/********* detail ***********/
	public List<OrderDetail> getOrderDetail(String orderId) {
		List<OrderDetail> orderList = new ArrayList<OrderDetail>();
		try {
			orderList = userService.getOrderDetail(orderId);
			if (null == orderList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(orderId, e);
		}
		return orderList;
	}

	public List<Dicts> getIndexDetail(String cloudId) {
		List<Dicts> dictList = new ArrayList<Dicts>();
		try {
			dictList = userService.getIndexDetail(cloudId);
			if (null == dictList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(cloudId, e);
		}
		return dictList;
	}

	@Override
	public Long insertLoginLog(LoginLog loginLog) {
		try {
			return userService.insertLoginLog(loginLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public BooleanResult updateLoginLog(LoginLog loginLog) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			result = userService.updateLoginLog(loginLog);
			if (result.getResult()) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(loginLog), e);
		}

		return result;
	}

	@Override
	public List<BaseCustomer> getKunnrByJL(BaseCustomer customer, String roleId) {
		try {
			return userService.getKunnrByJL(customer, roleId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customer), e);
		}
		return null;
	}

	public boolean insertKunnrStock(List<Stock> list) {
		try {
			return userService.insertKunnrStock(list);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(list), e);
		}
		return false;
	}

	public List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate) {
		try {
			return userService.getKunnrStockDate(kunnrStockDate);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnrStockDate), e);
		}
		return null;
	}
	/**
	 * 提交保存分销量
	 * version 1.1  更新提报库存逻辑
	 * xg.chen modify date 20161208
	 */
	@Override
	public BooleanResult uploadKunnrStock(Stock stock, String type, String roleId) {
		BooleanResult result=new BooleanResult();
		//获取Top200经销商
		Map<String, String> kunnrMap=new HashMap<String, String>();
		kunnrMap=getKunnrForTop200();
		//提报
		if("K".equals(stock.getUserType())){//Top200经销商提报
			//根据userId获取经销商标号
			Kunnr kunnr=userService.getKunnrIdForUserId(stock.getUserId());
			if(kunnr!=null){
				if(kunnrMap.get(kunnr.getKunnr())!=null){//Top200经销商提报
					result=KunnrStockRepot(stock,type,roleId);
				} else {
					result.setResult(false);
					result.setCode(":非Top200经销商提报，不能提报数据！");
					return result;
				}
			}
		} else if("A".equals(stock.getUserType())){//业务员提报
			if(kunnrMap.get(stock.getCustId())==null){
				result=KunnrStockRepot(stock,type,roleId);
			} else {//业务员不能提报Top200经销商的相关数据
				result.setResult(false);
				result.setCode(":业务员不能提报Top200经销商的数据！");
				return result;
			}
		} else {//督导提报
			result=KunnrStockRepot(stock,type,roleId);
		}
		return result;
	}
	/**
	 * MethodsTitle: 提交保存分销量
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:44:01
	 * @version 1.0
	 * @param stock 提报品相
	 * @param type 提报标识
	 * @param roleId 角色
	 * @return
	 */
	public BooleanResult KunnrStockRepot(Stock stock, String type, String roleId) {
		BooleanResult result=new BooleanResult();
		Long i=0L;
		//库存提报
		if(!"sales_day".equals(stock.getFlag())){//非日分销提报
			if ("D".equals(type) && stock.getId() != 0) {// 删除
				result = userService.deleteKunnrStockNew(stock);
			} else if (stock.getId() != 0) {// 更新
				result = userService.updateKunnrStockNew(stock);
				result.setCode(stock.getId() + "");
			} else if (stock.getId() == 0) {
				List<Stock> list=new ArrayList<Stock>();
				if("kunnr_week".equals(stock.getFlag())){//周库存提报
					list=getKunnrStockNew4Week(stock);
				} else {
					list=getKunnrStockNew1(stock);
				}
				if(list!=null&&list.size()>0&&!stock.getFlag().equals("sales_day")){
					for (int j = 0; j < list.size(); j++) {
						if (stock.getCategoryId().equals(list.get(j).getCategoryId()) 
								&& stock.getCustId().equals(list.get(j).getCustId())
								&& stock.getUserType().equals(list.get(j).getUserType())
								&& stock.getCheckTime().equals(list.get(j).getCheckTime())) {
							result.setResult(false);
							result.setCode(":已存在提报数据，不能重复提交！");
							return result;
						}
					}
				}
				result = userService.insertKunnrStockNew(stock);
			}
		} else {//日分销库存提报
			if ("D".equals(type) && stock.getId() != 0) {// 删除
				i = userService.deleteKunnrStock(stock);
				result.setCode(stock.getId() + "");
			} else if (stock.getId() != 0) {// 更新
				i = userService.updateKunnrStock(stock);
				result.setCode(stock.getId() + "");
			} else if (stock.getId() == 0) {// 插入
				List<Stock> list=getKunnrStockNew1(stock);
				if(list!=null&&list.size()>0&&stock.getFlag().equals("sales_day")){
					for (int j = 0; j < list.size(); j++) {
						if (stock.getCategoryId().equals(list.get(j).getCategoryId()) 
								&& stock.getCustId().equals(list.get(j).getCustId())
								&& stock.getUserType().equals(list.get(j).getUserType())
								&& stock.getCheckTime().equals(list.get(j).getCheckTime())) {
							result.setResult(false);
							result.setCode(":已存在提报数据，不能重复提交！");
							return result;
						}
					}
				}
				i = userService.insertKunnrStock(stock);
				result.setCode(i + "");
			}
			if(i!=null){
				result.setResult(true);
			}else{
				result.setResult(false);
				result.setCode("上传失败");
			}
		}
		return result;
	}
	
	/**
	 * MethodsTitle: 获取Top200经销商
	 * @author: xg.chen
	 * @date:2016年12月8日 下午2:43:02
	 * @version 1.0
	 * @return
	 */
	public Map<String, String> getKunnrForTop200(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Kunnr kunnr=new Kunnr();
			List<Kunnr> kunnrs=userService.getKunnrForTop200(kunnr);
			if(kunnr!=null){
				for (int i = 0; i < kunnrs.size(); i++) {
					String kunnrId=kunnrs.get(i).getKunnr();
					String name=kunnrs.get(i).getName1();
					if(null==map.get(kunnrId)){
						map.put(kunnrId, name);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return map;
	}
	/**
	 * MethodsTitle: 获取周库存提报的数据
	 * 描述：和月库存的区别就是不需要判断货龄是否一致
	 * @author: xg.chen
	 * @date:2016年12月13日 下午2:26:53
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	private List<Stock> getKunnrStockNew4Week(Stock stock) {
		try {
			return userService.getKunnrStockNew4Week(stock);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stock), e);
		}
		return null;
	}
	/**
	 * MethodsTitle: 获取月库存提报的数据和日分销量库存数据
	 * @author: xg.chen
	 * @date:2016年12月13日 下午2:29:14
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	private List<Stock> getKunnrStockNew1(Stock stock) {
		try {
			return userService.getKunnrStockNew1(stock);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stock), e);
		}
		return null;
	}

	@Override
	public List<Stock> getKunnrStock(Stock stock) {
		try {
			return userService.getKunnrStock(stock);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(stock), e);
		}
		return null;
	}

	public List<Kpi> getKpiNeed(String userId) {
		try {
			List<Kpi> kpi = userService.getKpiNeed(userId);
			return kpi;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(userId), e);
		}
		return null;
	}

	@Override
	public Kpi getKpi(Kpi orgId) {
		try {
			Kpi kpi = userService.getKpi(orgId);
			return kpi;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(orgId), e);
		}
		return null;
	}

	@Override
	public List<Kpi> getKpiByKunnrId(String kunnrId) {
		try {
			List<Kpi> kpiList = userService.getKpiByKunnrId(kunnrId);
			return kpiList;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnrId), e);
		}
		return null;
	}

	@Override
	public List<Kpi> getParentsOrg(Kpi need) {
		try {
			List<Kpi> kpiList = userService.getParentsOrg(need);
			return kpiList;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(need), e);
		}
		return null;
	}

	@Override
	public String getVisitActual(String userId) {
		try {
			String VisitActual = userService.getVisitActual(userId);
			return VisitActual;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(userId), e);
		}
		return null;
	}

	@Override
	public String getSuperKunnrInfo(Stock stock) {
		return userService.getSuperKunnrInfo(stock);
	}

	@Override
	public List<BaseProduct> getSkuLastPrice(BaseProduct product) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = userService.getSkuLastPrice(product);
		if (null == bps) {
			return null;
		}
		return bps;
	
	}

	@Override
	public List<OrderPrintFormat> getKunnrOrderFormat(OrderPrintFormat format) {
		List<OrderPrintFormat> opfs = new ArrayList<OrderPrintFormat>();
		opfs = userService.getKunnrOrderFormat(format);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public List<Lstm> getLstm(Lstm lstm) {
		List<Lstm> opfs = new ArrayList<Lstm>();
		opfs = userService.getLstm(lstm);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public List<Lstm> findLstm(Lstm lstm) {
		List<Lstm> opfs = new ArrayList<Lstm>();
		opfs = userService.findLstm(lstm);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public boolean insertLstm(Lstm lstm) {
		
	Long opfs = userService.insertLstm(lstm);
		if (null == opfs) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateLstm(Lstm lstm) {
		Long opfs = userService.updateLstm(lstm);
		if (opfs>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteLstm(Lstm lstm) {
		Long opfs = userService.deleteLstm(lstm);
		if (opfs>0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteallLstm(Lstm lstm) {
		Long opfs = userService.deleteallLstm(lstm);
		if (opfs>0) {
			return true;
		}
		return false;
	}

	@Override
	public BooleanResult uploadlstm(Lstm lstm, String status) {
		BooleanResult result=new BooleanResult();
		Long i=0L;
		i= userService.deleteLstm(lstm);
		if(i!=null){
			result.setResult(true);
		}else{
			result.setResult(false);
			result.setCode("上传失败");
		}
			


	  return result;
		
	}

	@Override
	public List<Lstm> findallLstm(Lstm lstm) {
		List<Lstm> opfs = new ArrayList<Lstm>();
		opfs = userService.findallLstm(lstm);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public List<User> getUsers(String info) {
		List<User> opfs = new ArrayList<User>();
		opfs = userService.getUsers(info);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public boolean resetPwd(User rstUser) {
		Long opfs = userService.resetPwd(rstUser);
		if (opfs>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CustSku> getCustSku(CustSku custsku) {
		List<CustSku> opfs = new ArrayList<CustSku>();
		opfs = userService.findallCustSku(custsku);
		if (null == opfs) {
			return null;
		}
		return opfs;
	}

	@Override
	public List<Order> getWeekOrderTotal(Order order) {
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = userService.getWeekOrderTotal(order);
			if (null == orderList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(order.getOrderId(), e);
		}
		return orderList;
	}

	@Override
	public String getMantnr(String chuanhaosku) {
		List<BaseProduct> skuList = new ArrayList<BaseProduct>();
		try {
			skuList = userService.getMantnr(chuanhaosku);
			if (null == skuList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(chuanhaosku, e);
		}
		return skuList.get(0).getSapcode();
	}

	@Override
	public List<CuanhuoQuery> searchCuanhuo(CuanhuoQuery cuanhuoQuery) {
		List<CuanhuoQuery> cuanhuoList = new ArrayList<CuanhuoQuery>();
		try {
			cuanhuoList = sapService.searchCuanhuo(cuanhuoQuery);
			if (null == cuanhuoList) {
				return null;
			}
		} catch (ServiceException e) {
			logger.error(cuanhuoList, e);
		}
		return cuanhuoList;
	}

	public ISAPService getSapService() {
		return sapService;
	}

	public void setSapService(ISAPService sapService) {
		this.sapService = sapService;
	}

	@Override
	public Long insertSign(Sign sign) {
		try {
			return userService.insertSign(sign);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Sign> getSignList(Sign sign) {
		try {
			return userService.getSignList(sign);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}


	@Override
	public boolean updateSign(Sign sign) {
		Long opfs = userService.updateSign(sign);
		if (opfs>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<BaseCustomer> getCusforSearchSet(BaseCustomer customer,
			String roleId) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = userService.getCusforSearchSet(customer, roleId);
		if (null == bcs) {
			return null;
		}
		return bcs;
	}

	@Override
	public List<VistCust> getVistCust(VistCust vistCust) {
		try {
			return userService. getVistCust(vistCust);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public String getTime1() {
		try {
			return userService. getTime1();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<SkuUnit> getSkuUnit() {
		try {
			return userService. getSkuUnit();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Long uploadStockReport(StockReport stock) {
		try {
			return userService. uploadStockReport(stock);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<StockReport> getCustomerStockByCustid(String custId) {
		try {
			return userService. getCustomerStockByCustid(custId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void checkImplementStatusDetail(String miaDetailId) {
		try {
		  userService.checkImplementStatusDetail(miaDetailId);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
}

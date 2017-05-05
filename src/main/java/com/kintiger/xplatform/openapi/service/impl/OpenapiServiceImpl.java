package com.kintiger.xplatform.openapi.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.impl.conn.SingleClientConnManager;
import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.aspectj.weaver.ast.And;

import com.alibaba.common.lang.StringUtil;
import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.crystaldecisions.enterprise.ocaframework.idl.OCA.OCAs.interface_num;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.dict.IDictService;
import com.kintiger.xplatform.api.dict.bo.Dict;
import com.kintiger.xplatform.api.login.ICAService;
import com.kintiger.xplatform.api.openapi.IOpenapiService;
import com.kintiger.xplatform.api.openapi.bo.ResponseStats;
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
import com.kintiger.xplatform.api.user.bo.KpiVisit;
import com.kintiger.xplatform.api.user.bo.KunnrStockDate;
import com.kintiger.xplatform.api.user.bo.LoginLog;
import com.kintiger.xplatform.api.user.bo.Lstm;
import com.kintiger.xplatform.api.user.bo.Order;
import com.kintiger.xplatform.api.user.bo.OrderDetail;
import com.kintiger.xplatform.api.user.bo.OrderPrintFormat;
import com.kintiger.xplatform.api.user.bo.Picture;
import com.kintiger.xplatform.api.user.bo.ResultMessage;
import com.kintiger.xplatform.api.user.bo.Sign;
import com.kintiger.xplatform.api.user.bo.SkuUnit;
import com.kintiger.xplatform.api.user.bo.Stock;
import com.kintiger.xplatform.api.user.bo.StockAge;
import com.kintiger.xplatform.api.user.bo.StockReport;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.api.user.bo.VistCust;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.JsonUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Title: app客户端服务接口实现 Description: mobilePlatform
 * 
 * @author: xg.chen
 * @date:2016年12月2日 上午11:04:28
 */
public class OpenapiServiceImpl implements IOpenapiService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(OpenapiServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private ICAService caService;

	private Properties env;

	private IDictService idictService;

	// public String rest(MultivaluedMap<String, String> params) {
	// Long startTime = System.currentTimeMillis();
	// // 没有参数
	// if (params == null || params.isEmpty()) {
	// Response response = new Response();
	// response.setCode(IOpenapiService.ERROR);
	// response.setMsg(IOpenapiService.ERROR_MSG_PARAMS);
	// return response(memcachedCacheService, IOpenapiService.ERROR_MSG_PARAMS,
	// startTime, response);
	// }
	//
	// String method = null;
	// List<String> methods = params.get("method");
	//
	// if (methods != null && methods.size() > 0) {
	// method = methods.get(0);
	// }
	// // 没有方法
	// if (StringUtil.isEmpty(method)) {
	// Response response = new Response();
	// response.setCode(IOpenapiService.ERROR);
	// response.setMsg(IOpenapiService.ERROR_MSG_METHOD);
	//
	// return response(memcachedCacheService, IOpenapiService.ERROR_MSG_METHOD,
	// startTime, response);
	// }
	// // 根据 method判断接口
	// if (IOpenapiService.XPLATFORM_USER_LOGIN.equals(method)) {
	// String passport = null;
	// String password = null;
	// List<String> passports = params.get("passport");
	// List<String> passwords = params.get("password");
	// if (passports != null && passports.size() > 0) {
	// passport = passports.get(0);
	// }
	// if (passwords != null && passwords.size() > 0) {
	// password = passwords.get(0);
	// }
	// ValidateResult result = caService.validateUser(passport, password);
	// UserResponse response = new UserResponse();
	// response.setCode(result.getResultCode());
	// response.setMsg(result.getMessage());
	// User user = result.getUser();
	// if (user != null) {
	// user.setOrgId(null);
	// }
	// response.setUser(user);
	//
	// return response(memcachedCacheService, method, startTime, response);
	//
	// }
	//
	// return null;
	// }

	public String login(MultivaluedMap<String, String> params) {
		String mobile = null;
		String password = null;
		String phoneSelfNum = null;// 手机卡号码
		User loginUser = new User();
		List<String> mobiles = params.get("mobile");
		List<String> passwords = params.get("password");
		List<String> phoneSelfNums = params.get("phoneSelfNum");
		if (mobiles != null && mobiles.size() > 0) {
			mobile = mobiles.get(0);
		}
		if (passwords != null && passwords.size() > 0) {
			password = passwords.get(0);
		}
		if (phoneSelfNums != null && phoneSelfNums.size() > 0) {
			phoneSelfNum = phoneSelfNums.get(0);
		}
		loginUser = caService.validateUser(mobile, password, phoneSelfNum);
		return JsonUtil.bean2Json(loginUser.getClass(), loginUser);
	}

	public String admlogin(MultivaluedMap<String, String> params) {
		String mobile = null;
		String password = null;
		String phoneSelfNum = null;// 手机卡号码
		User loginUser = new User();
		List<String> mobiles = params.get("mobile");
		List<String> passwords = params.get("password");
		List<String> phoneSelfNums = params.get("phoneSelfNum");
		if (mobiles != null && mobiles.size() > 0) {
			mobile = mobiles.get(0);
		}
		if (passwords != null && passwords.size() > 0) {
			password = passwords.get(0);
		}
		if (phoneSelfNums != null && phoneSelfNums.size() > 0) {
			phoneSelfNum = phoneSelfNums.get(0);
		}
		loginUser = caService.validateAdmUser(mobile, password, phoneSelfNum);
		return JsonUtil.bean2Json(loginUser.getClass(), loginUser);
	}

	@Override
	public String resetPwd(MultivaluedMap<String, String> params) {
		String admuser = null;
		String password = null;
		String phoneSelfNum = null;// 手机卡号码
		String rstusercode = null;
		User loginUser = new User();
		User rstUser = new User();
		List<String> admusers = params.get("admuser");
		List<String> passwords = params.get("password");
		List<String> phoneSelfNums = params.get("phoneSelfNum");
		List<String> rstusercodes = params.get("rstusercode");
		if (admusers != null && admusers.size() > 0) {
			admuser = admusers.get(0);
		}
		if (passwords != null && passwords.size() > 0) {
			password = passwords.get(0);
		}
		if (phoneSelfNums != null && phoneSelfNums.size() > 0) {
			phoneSelfNum = phoneSelfNums.get(0);
		}
		if (rstusercodes != null && rstusercodes.size() > 0) {
			rstusercode = rstusercodes.get(0);
		}
		loginUser = caService.validateAdmUser(admuser, password, phoneSelfNum);
		if (loginUser == null) {
			return "非法用户，请用手机客户端登录";
		}
		// 设置密码为123456
		rstUser.setPassword("e10adc3949ba59abbe56e057f20f883e");
		rstUser.setUserCode(rstusercode);
		if (caService.resetPwd(rstUser)) {
			return "密码已成功设置为123456!";
		} else {
			return "密码重设失败，请稍后再试!";
		}

	}

	public String sku() {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		bps = caService.getSku();
		return JsonUtil.bean2Json(bps.getClass(), bps);
	}

	public String skuByCloud(MultivaluedMap<String, String> params) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		String cloudId = null;
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (null == cloudId) {
			bps = caService.getSku();
		} else {
			bps = caService.getSkuByCloud(cloudId);
		}
		return JsonUtil.bean2Json(bps.getClass(), bps);
	}

	// 获取订单产品列表
	@Override
	public String getOrderSkuByKunner(MultivaluedMap<String, String> params) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		String cloudId = null;
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (cloudId != null) {
			bps = caService.getOrderSkuByKunner(cloudId);
		}

		return JsonUtil.bean2Json(bps.getClass(), bps);
	}

	public String channel() {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = caService.getChannel();

		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	public String division() {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = caService.getDivision();
		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	public String searchDivision(MultivaluedMap<String, String> params) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		String zwl04t = null;
		List<String> zwl04ts = params.get("zwl04t");
		if (zwl04ts != null && zwl04ts.size() > 0) {
			zwl04t = zwl04ts.get(0);
			zwl04t = ReplaceSpace(zwl04t);
		}
		bcs = caService.getDivisionSearch(zwl04t);
		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	public String version() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		String dictTypeValue = null;
		// List<String> dictTypeValues = params.get("dictTypeValue");
		// if (dictTypeValues!= null && dictTypeValues.size() > 0){
		// dictTypeValue = dictTypeValues.get(0);
		// }
		//dicts = caService.getVersion("mobileVersion");

		return JsonUtil.bean2Json(dicts.getClass(), dicts);
	}

	/**
	 * version new
	 */
	public String version(MultivaluedMap<String, String> params) {
		String cloudId = null;
		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<Dicts> dicts = new ArrayList<Dicts>();
		// String dictTypeValue = null;
		/*if (IOpenapiService.COMPANYCODE.equals(cloudId)) {
			dicts = caService.getVersion("mobileVersion");
		} else {// kunnr code
			dicts = caService.getVersion("mobileKunnrVersion");
		}
*/
		return JsonUtil.bean2Json(dicts.getClass(), dicts);
	}

	public String time() {
		String time=caService.getTime1();//获取的是当前数据库的时间，而且没用？？why?
		System.out.println("获取到的当前数据库的时间:"+time);
		  try {
		   long l = new Date().getTime();
		   String str = String.valueOf(l);
		   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   Date d=new Date(l); 
		   System.out.println("获取当前服务器的格式化后时间:"+sdf.format(d));
		   System.out.println("获取当前服务器的毫秒时间:"+str);
		   return str;
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return "";
	}

	public String dict() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = caService.getDict();
		return JsonUtil.bean2Json(dicts.getClass(), dicts);
	}

	public String admdict() {
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = caService.getAdmDict();
		return JsonUtil.bean2Json(dicts.getClass(), dicts);
	}

	public String menu() {
		List<BaseMenu> menus = new ArrayList<BaseMenu>();
		menus = caService.getMenu();
		return JsonUtil.bean2Json(menus.getClass(), menus);
	}

	public String customer(MultivaluedMap<String, String> params) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		String userId = null;
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		bcs = caService.getCus(userId);
		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	/**
	 * 查询客户
	 */
	public String searchCustomer(MultivaluedMap<String, String> params) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		BaseCustomer bc = new BaseCustomer();
		String info = null;
		String kunnr = null;// 经销商客户端使用
		String roleId = null;// 客户经理，城市经理
		String userId = null;
		String longitude = null;// 经度
		String latitude = null;// 纬度
		String locDw = null;// 定位传的参数 DW
		String setflag = null;
		List<String> infos = params.get("info");
		if (infos != null && infos.size() > 0) {
			info = infos.get(0).trim();
			info = ReplaceSpace(info);
			// if (!"".equals(info)) {
			// String regEx = "[' ']+"; // 一个或多个空格
			// Pattern p = Pattern.compile(regEx);
			// Matcher m = p.matcher(info);
			// info = m.replaceAll("%");
			// info="%"+info+"%";
			// }
			// System.out.println("模糊查询："+info);
		}
		List<String> kunnrs = params.get("kunnr");
		if (kunnrs != null && kunnrs.size() > 0) {
			kunnr = kunnrs.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> longitudes = params.get("longitude");
		if (longitudes != null && longitudes.size() > 0) {
			longitude = longitudes.get(0);

		}
		List<String> latitudes = params.get("latitude");
		if (latitudes != null && latitudes.size() > 0) {
			latitude = latitudes.get(0);
		}
		List<String> locDws = params.get("locDw");
		if (locDws != null && locDws.size() > 0) {
			locDw = locDws.get(0);
		}
		List<String> setflags = params.get("setflag");
		if (setflags != null && setflags.size() > 0) {
			setflag = setflags.get(0);
		}
		bc.setLongitude(longitude);
		bc.setLatitude(latitude);
		bc.setDistance("0.02");// 地图查询距离

		// 客户经理查询
		// if(IOpenapiService.MOBILE_KHJL.equals(roleId)||IOpenapiService.MOBILE_CSJL.equals(roleId)
		// ||IOpenapiService.MOBILE_YWY.equals(roleId)||IOpenapiService.MOBILE_ZG.equals(roleId)){
		bc.setUserId(userId);
		// }
		bc.setInfo(info);
		bc.setKunnr(kunnr);
		bc.setLocDw(locDw);
		// bcs = caService.getCusforSearch(bc, roleId);
		if ("Y".equals(setflag)) {
			bcs = caService.getCusforSearchSet(bc, roleId);
		} else {
			bcs = caService.getCusforSearch1(bc, roleId);
		}

		if (bcs != null) {
			return JsonUtil.bean2Json(bcs.getClass(), bcs);
		}
		return null;
	}

	/**
	 * 查询客户
	 */
	public String searchCustomerXpp(MultivaluedMap<String, String> params) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		BaseCustomer bc = new BaseCustomer();
		String info = null;
		String kunnr = null;// 经销商客户端使用
		String roleId = null;// 客户经理，城市经理
		String userId = null;
		String longitude = null;// 经度
		String latitude = null;// 纬度
		String locDw = null;// 定位传的参数 DW
		String zwl04 = null;
		String setflag = null;
		String customerImportance = null;// 门店重要性
		String customerAnnualSale = null;// 门店销售金额
		List<String> infos = params.get("info");
		if (infos != null && infos.size() > 0) {
			info = infos.get(0).trim();

			if (!"".equals(info)) {
				String regEx = "[' ']+"; // 一个或多个空格
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(info);
				info = m.replaceAll("%");
				info = "%" + info + "%";
			}
			// System.out.println("模糊查询："+info);
		}
		List<String> kunnrs = params.get("kunnr");
		if (kunnrs != null && kunnrs.size() > 0) {
			kunnr = kunnrs.get(0);
		}
		List<String> zwl04s = params.get("zwl04");
		if (zwl04s != null && zwl04s.size() > 0) {
			zwl04 = zwl04s.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> longitudes = params.get("longitude");
		if (longitudes != null && longitudes.size() > 0) {
			longitude = longitudes.get(0);

		}
		List<String> latitudes = params.get("latitude");
		if (latitudes != null && latitudes.size() > 0) {
			latitude = latitudes.get(0);
		}
		List<String> locDws = params.get("locDw");
		if (locDws != null && locDws.size() > 0) {
			locDw = locDws.get(0);
		}
		List<String> setflags = params.get("setflag");
		if (setflags != null && setflags.size() > 0) {
			setflag = setflags.get(0);
		}
		bc.setLongitude(longitude);
		bc.setLatitude(latitude);
		bc.setDistance("0.02");// 地图查询距离

		// 客户经理查询
		// if(IOpenapiService.MOBILE_KHJL.equals(roleId)||IOpenapiService.MOBILE_CSJL.equals(roleId)
		// ||IOpenapiService.MOBILE_YWY.equals(roleId)||IOpenapiService.MOBILE_ZG.equals(roleId)){
		bc.setUserId(userId);
		// }
		bc.setInfo(info);
		bc.setKunnr(kunnr);
		bc.setZwl04(zwl04);
		bc.setLocDw(locDw);
		bc.setCustomerImportance(customerImportance);
		bc.setCustomerAnnualSale(customerAnnualSale);
		// bcs = caService.getCusforSearch(bc, roleId);
		if ("Y".equals(setflag)) {
			bcs = caService.getCusforSearchSet(bc, roleId);
		} else {
			bcs = caService.getCusforSearch1(bc, roleId);
		}
		if (bcs != null) {
			return JsonUtil.bean2Json(bcs.getClass(), bcs);
		}
		return null;
	}

	public String searchuserrstpsw(MultivaluedMap<String, String> params) {
		List<User> bcs = new ArrayList<User>();
		BaseCustomer bc = new BaseCustomer();
		String info = null;
		List<String> infos = params.get("info");
		if (infos != null && infos.size() > 0) {
			info = infos.get(0);
		}

		bcs = caService.getUsers(info);
		// System.out.println(bcs);
		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	/**
	 * 查询经销商
	 */
	public String searchKunnr(MultivaluedMap<String, String> params) {
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		String info = null;
		List<String> infos = params.get("info");
		if (infos != null && infos.size() > 0) {
			info = infos.get(0);
			info = ReplaceSpace(info);
		}
		bcs = caService.getKunnrforSearch(info);
		return JsonUtil.bean2Json(bcs.getClass(), bcs);
	}

	public String activity(MultivaluedMap<String, String> params) {
		List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		String userId = null;
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		bmks = caService.getAct(userId);
		return JsonUtil.bean2Json(bmks.getClass(), bmks);
	}

	/**
	 * 获取门店活动信息
	 */
	public String searchActivity(MultivaluedMap<String, String> params) {
		List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		BaseMarketCheck bmk1 = new BaseMarketCheck();

		String custId = null;
		String userId = null;
		// String dict = null;
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
			String[] l = custId.split(",");
			bmk1.setCodes(l);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		bmk1.setUserId(userId);
		bmk1.setCustId(custId);
		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = caService.getDictforAct();

		if (dicts != null && dicts.size() > 0) {
			if ("Y".equals(dicts.get(0).getItemValue())) {
				bmk1.setFlag("Y");
			} else {
				bmk1.setFlag("N");
			}
		}
		bmks = caService.getActforSearch(bmk1);
		/***/

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String date1 = "";
		String startDatedate1 = "";
		String date2 = "";
		String endDatedate1 = "";
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyy年MM月dd日");

		Date startDate = null;
		Date endDate = null;

		for (int i = 0; i < bmks.size(); i++) {
			try {
				if (bmks.get(i).getStartDate() != null) {
					if (bmks.get(i).getStartDate().indexOf("-") > -1) {
						date1 = sf.format(date);
						startDate = sf.parse(bmks.get(i).getStartDate());
						startDatedate1 = sf.format(startDate);
					} else if (bmks.get(i).getStartDate().indexOf("/") > -1) {
						date1 = sf1.format(date);
						startDate = sf1.parse(bmks.get(i).getStartDate());
						startDatedate1 = sf.format(startDate);
					} else if (bmks.get(i).getStartDate().indexOf(".") > -1) {
						date1 = sf2.format(date);
						startDate = sf2.parse(bmks.get(i).getStartDate());
						startDatedate1 = sf.format(startDate);
					} else if (bmks.get(i).getStartDate().indexOf("年") > -1) {
						date1 = sf3.format(date);
						startDate = sf3.parse(bmks.get(i).getStartDate());
						startDatedate1 = sf.format(startDate);
					}
				}
				if (bmks.get(i).getEndDate() != null) {
					if (bmks.get(i).getEndDate().indexOf("-") > -1) {
						date2 = sf.format(date);
						endDate = sf.parse(bmks.get(i).getEndDate());
						endDatedate1 = sf.format(endDate);
					} else if (bmks.get(i).getEndDate().indexOf("/") > -1) {
						date2 = sf1.format(date);
						endDate = sf1.parse(bmks.get(i).getEndDate());
						endDatedate1 = sf.format(endDate);
					} else if (bmks.get(i).getEndDate().indexOf(".") > -1) {
						date2 = sf2.format(date);
						endDate = sf2.parse(bmks.get(i).getEndDate());
						endDatedate1 = sf.format(endDate);
					} else if (bmks.get(i).getEndDate().indexOf("年") > -1) {
						date2 = sf3.format(date);
						endDate = sf3.parse(bmks.get(i).getEndDate());
						endDatedate1 = sf.format(endDate);
					}
				}
				if (bmks.get(i).getStartDate() != null
						&& bmks.get(i).getEndDate() != null
						&& !"".equals(date1) && !"".equals(date2)) {
					if ((date1.equals(startDatedate1) || date.after(startDate))
							&& (date2.equals(endDatedate1) || date
									.before(endDate))) {
						bmks.get(i).setIsModify("Y");
					} else {

						bmks.get(i).setIsModify("N");
					}
				} else {
					bmks.get(i).setIsModify("Y");
				}
				if (!"0".equals(bmks.get(i).getCheck_flag())) {
					bmks.get(i).setIsModify("N");
				}

			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		// System.out.println(bmks);
		return JsonUtil.bean2Json(bmks.getClass(), bmks);
	}

	public String searchRoute(MultivaluedMap<String, String> params) {
		List<BaseCustomer> customers = new ArrayList<BaseCustomer>();
		BaseCustomer customer = new BaseCustomer();
		String userId = null;
		String roleId = null;
		String cloudId = null;
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		customer.setUserId(userId);
		// if("mobile_dd".equals(roleId)){//督导
		// customers = caService.getRouteforSearch(customer);
		// }else{
		if (!IOpenapiService.COMPANYCODE.equals(cloudId)) {// 经销商业代线路
			customers = caService.getRouteforJXS(customer);
		} else {// xpp员工线路
			customers = caService.getRouteforSearch(customer);
		}

		// }
		return JsonUtil.bean2Json(customers.getClass(), customers);
	}

	/**
	 * 根据角色获取关联经销商
	 */
	public String getKunnrByJL(MultivaluedMap<String, String> params) {
		String userId = null;
		String roleId = null;
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}

		List<BaseCustomer> kunners = new ArrayList<BaseCustomer>();
		BaseCustomer customer = new BaseCustomer();
		customer.setUserId(userId);
		kunners = caService.getKunnrByJL(customer, roleId);

		return JsonUtil.bean2Json(kunners.getClass(), kunners);

	}

	public String PhotoUpload(byte[] data, String photoType, String custId,
			String activityId, String skuId, String equipmentId,
			String longitude, String latitude, String userId) {
		BufferedOutputStream o = null;
		String str = String.valueOf(System.currentTimeMillis());
		// String photoType = null;
		// String custId = null;
		// String activityId = null;
		// String skuId = null;
		// String equipmentId = null;
		// String longitude = null;
		// String latitude = null;
		// String userId = null;
		// byte[] data = null;
		// byte[] date = params.get("data").get(0);
		// List<String> photoTypes = params.get("photoType");
		// if (photoTypes!= null && photoTypes.size() > 0){
		// photoType = photoTypes.get(0);
		// }
		// List<String> custIds = params.get("custId");
		// if (custIds!= null && custIds.size() > 0){
		// custId = custIds.get(0);
		// }
		// List<String> activityIds = params.get("activityId");
		// if (activityIds!= null && activityIds.size() > 0){
		// activityId = activityIds.get(0);
		// }
		// List<String> skuIds = params.get("skuId");
		// if (skuIds!= null && skuIds.size() > 0){
		// userId = skuIds.get(0);
		// }
		// List<String> userIds = params.get("userId");
		// if (userIds!= null && userIds.size() > 0){
		// userId = userIds.get(0);
		// }
		//
		// List<String> equipmentIds = params.get("equipmentId");
		// if (equipmentIds!= null && equipmentIds.size() > 0){
		// equipmentId = equipmentIds.get(0);
		// }
		// List<String> longitudes = params.get("longitude");
		// if (longitudes!= null && longitudes.size() > 0){
		// longitude = longitudes.get(0);
		// }
		// List<String> latitudes = params.get("latitude");
		// if (latitudes!= null && latitudes.size() > 0){
		// latitude = latitudes.get(0);
		// }
		try {
			String subFolders = new SimpleDateFormat("yyyyMM").format(
					new Date()).toString();
			String path = env.getProperty("imagePath") + "/" + subFolders;
			File savedir = new File(path);
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			File f = new File(path, str + ".png");
			o = new BufferedOutputStream(new FileOutputStream(f));
			o.write(data);
			// o.flush();
			// o.close();
			Picture p = new Picture();
			p.setFileName(str + ".png");
			// p.setActivityId(activityId);
			Long id = caService.insertImage(p);
			if (null != id) {
				return id.toString();
			} else {
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	/**
	 * 
	 */
	public String photoUpload(MultivaluedMap<String, String> params) {
		BufferedOutputStream o = null;
		String str = String.valueOf(System.currentTimeMillis());
		String By = null;
		List<String> Bys = params.get("photo");
		if (Bys != null && Bys.size() > 0) {
			By = Bys.get(0);

		}
		byte[] byteArray = Base64.decode(By);

		String photoType = null;
		String custId = null;
		String activityId = null;
		String skuId = null;
		String equipmentId = null;
		String createDate = null;
		String dayType = null;
		String userId = null;
		String uploadDate = null;
		String itemId = null;
		String pzTime = null;
		// byte[] data = null;
		// byte[] date = params.get("data").get(0);
		String cloudId = null;
		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<String> photoTypes = params.get("photoType");
		if (photoTypes != null && photoTypes.size() > 0) {
			photoType = photoTypes.get(0);
		}
		List<String> pzTimes = params.get("pzTime");
		if (pzTimes != null && pzTimes.size() > 0) {
			pzTime = pzTimes.get(0);
		}
		List<String> dayTypes = params.get("dayType");
		if (dayTypes != null && dayTypes.size() > 0) {
			dayType = dayTypes.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> activityIds = params.get("activityId");
		if (activityIds != null && activityIds.size() > 0) {
			activityId = activityIds.get(0);
		}
		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			userId = skuIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}

		List<String> equipmentIds = params.get("equipmentId");
		if (equipmentIds != null && equipmentIds.size() > 0) {
			equipmentId = equipmentIds.get(0);
		}

		List<String> itemIds = params.get("itemId");
		if (itemIds != null && itemIds.size() > 0) {
			itemId = itemIds.get(0);
		}
		// List<String> longitudes = params.get("longitude");
		// if (longitudes!= null && longitudes.size() > 0){
		// longitude = longitudes.get(0);
		// }
		// List<String> latitudes = params.get("latitude");
		// if (latitudes!= null && latitudes.size() > 0){
		// latitude = latitudes.get(0);
		// }
		try {
			SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String subFolders = new SimpleDateFormat("yyyyMMdd").format(
					new Date()).toString();
			String path = env.getProperty("imagePath") + "/" + subFolders;
			// String path = subFolders;
			// System.out.println("-----------"+path);
			File savedir = new File(path);
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			File f = new File(path, str + ".png");
			o = new BufferedOutputStream(new FileOutputStream(f));
			o.write(byteArray);
			/*
			 * o.flush(); o.close();
			 */
			Picture p = new Picture();
			p.setFileName(str + ".png");
			p.setActivityId(activityId);
			p.setCloudId(cloudId);
			p.setPhotoType(photoType);
			p.setCustId(custId);
			p.setSkuId(skuId);
			p.setEquipmentId(equipmentId);
			p.setUploadDate(uploadDate);
			p.setUserId(userId);
			p.setDayType(dayType);
			p.setActivityId(activityId);
			p.setItemId(itemId);
			p.settFile(subFolders);
			Long timeL = Long.parseLong(pzTime);
			p.setPzTime(new Date(timeL));
			Long id = caService.insertImage(p);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	public String priceUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String skuId = null;
		String price = null;
		String unit = null;
		String userId = null;
		String createDate = null;
		String standardprice = null;
		String promotionalprice = null;
		// byte[] date = params.get("data").get(0);
		String cloudId = null;
		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<String> standardprices = params.get("standardprice");
		if (standardprices != null && standardprices.size() > 0) {
			standardprice = standardprices.get(0);
		}

		List<String> promotionalprices = params.get("promotionalprice");
		if (promotionalprices != null && promotionalprices.size() > 0) {
			promotionalprice = promotionalprices.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		// List<String> activityIds = params.get("activityId");
		// if (activityIds!= null && activityIds.size() > 0){
		// activityId = activityIds.get(0);
		// }
		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			skuId = skuIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}

		List<String> prices = params.get("price");
		if (prices != null && prices.size() > 0) {

			price = prices.get(0);
		}
		List<String> units = params.get("unit");
		if (units != null && units.size() > 0) {
			unit = units.get(0);
		}

		try {
			AbnormalPrice ap = new AbnormalPrice();
			ap.setCustId(custId);
			ap.setUnit(unit);
			ap.setPrice(price);
			ap.setSkuId(skuId);
			ap.setUserId(userId);
			ap.setOperatorId(userId);
			ap.setCloudId(cloudId);
			ap.setStatus("Y");
			ap.setDayType(createDate);
			ap.setStandardprice(standardprice);
			ap.setPromotionalprice(promotionalprice);
			Long id = caService.insertPrice(ap);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String distrUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String skuId = null;
		String userId = null;
		String quantity = null;
		String createDate = null;
		String cloudId = null;
		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			skuId = skuIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}

		List<String> quantitys = params.get("quantity");
		if (quantitys != null && quantitys.size() > 0) {
			quantity = quantitys.get(0);
		}

		try {
			Distribution distr = new Distribution();
			distr.setCustId(custId);
			distr.setSkuId(skuId);
			distr.setUserId(userId);
			distr.setOperatorId(userId);
			distr.setQuantity(quantity);
			distr.setDayType(createDate);
			distr.setCloudId(cloudId);
			Long id = caService.insertDistr(distr);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String displayUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String userId = null;
		String dictTypeValue = null;
		String itemValue = null;
		String counts = null;
		String createDate = null;
		String itemDesc = null;
		String cloudId = null;
		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> dictTypeValues = params.get("dictTypeValue");
		if (dictTypeValues != null && dictTypeValues.size() > 0) {
			dictTypeValue = dictTypeValues.get(0);
		}

		List<String> itemValues = params.get("itemValue");
		if (itemValues != null && itemValues.size() > 0) {
			itemValue = itemValues.get(0);
		}

		List<String> countss = params.get("counts");
		if (countss != null && countss.size() > 0) {
			counts = countss.get(0);
		}

		List<String> itemDescs = params.get("itemDesc");
		if (itemDescs != null && itemDescs.size() > 0) {
			itemDesc = itemDescs.get(0);
		}
		try {
			DisPlay disp = new DisPlay();
			disp.setCounts(counts);
			disp.setCustId(custId);
			disp.setUserId(userId);
			disp.setDayType(createDate);
			disp.setCloudId(cloudId);
			disp.setDictTypeValue(dictTypeValue);
			disp.setItemDesc(itemDesc);
			disp.setItemValue(itemValue);
			Long id = caService.insertDisply(disp);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	/**
	 * 创建门店 xg.chen version1.1 for 20161104
	 */
	public String custUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String channelId = null;
		String address = null;
		String userId = null;// 业代Id
		String contractName = null;// 联系人
		String contractPhone = null;// 联系人电话
		String contractMobile = null;
		String orgId = null;
		String custLevel = null;
		String custType = null;
		String businessLicense = null;
		String zwl04 = null;
		String zwl03 = null;
		String zwl02 = null;
		String zwl01 = null;
		String custState = null;
		String custName = null;
		String kunnr = null;
		String longitude = null;// 经度
		String latitude = null;// 纬度
		String customerImportance = null;// 门店重要性
		String customerAnnualSale = null;// 门店销售金额

		List<String> kunnrs = params.get("kunnr");
		if (kunnrs != null && kunnrs.size() > 0) {
			kunnr = kunnrs.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> contractMobiles = params.get("contractMobile");
		if (contractMobiles != null && contractMobiles.size() > 0) {
			contractMobile = contractMobiles.get(0);
		}
		List<String> channelIds = params.get("channelId");
		if (channelIds != null && channelIds.size() > 0) {
			channelId = channelIds.get(0);
		}
		List<String> addresss = params.get("address");
		if (addresss != null && addresss.size() > 0) {
			address = addresss.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> contractNames = params.get("contractName");
		if (contractNames != null && contractNames.size() > 0) {
			contractName = contractNames.get(0);
		}

		List<String> contractPhones = params.get("contractPhone");
		if (contractPhones != null && contractPhones.size() > 0) {
			contractPhone = contractPhones.get(0);
		}

		List<String> orgIds = params.get("orgId");
		if (orgIds != null && orgIds.size() > 0) {
			orgId = orgIds.get(0);
		}
		List<String> custLevels = params.get("custLevel");
		if (custLevels != null && custLevels.size() > 0) {
			custLevel = custLevels.get(0);
		}
		List<String> custTypes = params.get("custType");
		if (custTypes != null && custTypes.size() > 0) {
			custType = custTypes.get(0);
		}
		List<String> businessLicenses = params.get("businessLicense");
		if (businessLicenses != null && businessLicenses.size() > 0) {
			businessLicense = businessLicenses.get(0);
		}
		List<String> zwl04s = params.get("zwl04");
		if (zwl04s != null && zwl04s.size() > 0) {
			zwl04 = zwl04s.get(0);
		}
		List<String> custStates = params.get("custState");
		if (custStates != null && custStates.size() > 0) {
			custState = custStates.get(0);
		}
		List<String> custNames = params.get("custName");
		if (custNames != null && custNames.size() > 0) {
			custName = custNames.get(0);
		}
		List<String> lonitudes = params.get("longitude");
		if (lonitudes != null && lonitudes.size() > 0) {
			longitude = lonitudes.get(0);
		}
		List<String> latitudes = params.get("latitude");
		if (latitudes != null && latitudes.size() > 0) {
			latitude = latitudes.get(0);
		}
		List<String> customerImportances = params.get("customerImportance");
		if (customerImportances != null && customerImportances.size() > 0) {
			customerImportance = customerImportances.get(0);
		}
		List<String> customerAnnualSales = params.get("customerAnnualSale");
		if (customerAnnualSales != null && customerAnnualSales.size() > 0) {
			customerAnnualSale = customerAnnualSales.get(0);
		}
		List<BaseCustomer> bcs = new ArrayList<BaseCustomer>();
		bcs = caService.getDivisionforSearch(zwl04);
		if (bcs != null && bcs.size() > 0) {
			BaseCustomer customer2 = bcs.get(0);
			zwl03 = customer2.getZwl03();
			zwl02 = customer2.getZwl02();
			zwl01 = customer2.getZwl01();
		}
		if (custId == null || ("").equals(custId)) {
			try {
				BaseCustomer customer = new BaseCustomer();
				BaseCustomer customer2 = new BaseCustomer();
				BaseCustomer customer4 = new BaseCustomer();
				customer.setChannelId(channelId);
				customer.setAddress(address);
				customer.setUserId(userId);
				customer.setContractName(contractName);
				customer.setCustName(custName);
				customer.setContractPhone(contractPhone);
				customer.setContractMobile(contractMobile);
				customer.setOrgId(orgId);
				customer.setCustLevel(custLevel);
				customer.setCustType(custType);
				customer.setBusinessLicense(businessLicense);
				customer.setCustState(custState);
				customer.setZwl04(zwl04);
				customer.setZwl03(zwl03);
				customer.setZwl02(zwl02);
				customer.setZwl01(zwl01);
				customer.setKunnr(kunnr);
				customer.setLongitude(longitude);
				customer.setLatitude(latitude);
				customer.setCustomerImportance(customerImportance);
				customer.setCustomerAnnualSale(customerAnnualSale);
				if (null != kunnr) {
					customer4 = caService.getKunnr(kunnr).get(0);
				}
				customer.setOrgId(customer4.getOrgId());
				int j = caService.getBlCount(customer);
				if (null != userId) {
					customer2 = caService.getModifier(userId).get(0);
				}
				if (null != customer2.getModifier()) {
					customer.setModifier(customer2.getModifier());
				}
				if (1 != j) {
					Long id = caService.insertCus(customer);
					if (null != id) {
						return id.toString();
					} else {
						return "fail";
					}
				} else {
					return "same";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		} else {
			try {
				BaseCustomer customer1 = new BaseCustomer();
				BaseCustomer customer3 = new BaseCustomer();
				BaseCustomer customer5 = new BaseCustomer();
				customer1.setChannelId(channelId);
				customer1.setCustId(custId);
				customer1.setAddress(address);
				customer1.setUserId(userId);
				customer1.setContractName(contractName);
				customer1.setContractPhone(contractPhone);
				customer1.setContractMobile(contractMobile);
				customer1.setOrgId(orgId);
				customer1.setCustLevel(custLevel);
				customer1.setCustType(custType);
				customer1.setCustState(custState);
				customer1.setBusinessLicense(businessLicense);
				customer1.setZwl04(zwl04);
				customer1.setZwl03(zwl03);
				customer1.setZwl02(zwl02);
				customer1.setZwl01(zwl01);
				customer1.setKunnr(kunnr);
				customer1.setCustName(custName);
				customer1.setLongitude(longitude);
				customer1.setLatitude(latitude);
				customer1.setCustomerImportance(customerImportance);
				customer1.setCustomerAnnualSale(customerAnnualSale);
				if (null != kunnr) {
					customer5 = caService.getKunnr(kunnr).get(0);
				}
				customer1.setOrgId(customer5.getOrgId());
				if (null != userId) {
					customer3 = caService.getModifier(userId).get(0);
				}
				if (null != customer3.getModifier()) {
					customer1.setModifier(customer3.getModifier());
				}
				BooleanResult result = caService.updateCustomer(customer1);
				if (result.getResult()) {
					return "success";
				} else {
					return "fail";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}

		}
	}

	public String orderUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String userId = null;
		String orderId = null;
		String totalPrice = null;
		String totalPricaeUniteCode = null;
		String totalPricaeUniteDesc = null;
		String orderDesc = null;
		String orderQuntity = null;
		String orgId = null;
		String status = null;
		String createDate = null;
		String modifyDate = null;
		String orderStatus = null;
		String orderFundStatus = null;
		String orderCreateDate = null;
		String type = null;
		String couldId = null;
		List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
		String orderDetail1 = null;
		String cloudId = null;
		ResultMessage message = new ResultMessage();

		List<String> cloudIdList = params.get("cloudId");
		if (cloudIdList != null && cloudIdList.size() > 0) {
			cloudId = cloudIdList.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> totalPrices = params.get("totalPrice");
		if (totalPrices != null && totalPrices.size() > 0) {
			totalPrice = totalPrices.get(0);
		}
		List<String> totalPricaeUniteCodes = params.get("totalPricaeUniteCode");
		if (totalPricaeUniteCodes != null && totalPricaeUniteCodes.size() > 0) {
			totalPricaeUniteCode = totalPricaeUniteCodes.get(0);
		}
		List<String> totalPricaeUniteDescs = params.get("totalPricaeUniteDesc");
		if (totalPricaeUniteDescs != null && totalPricaeUniteDescs.size() > 0) {
			totalPricaeUniteDesc = totalPricaeUniteDescs.get(0);
		}
		List<String> orderDescs = params.get("orderDesc");
		if (orderDescs != null && orderDescs.size() > 0) {
			orderDesc = orderDescs.get(0);
		}
		List<String> orderQuntitys = params.get("orderQuntity");
		if (orderQuntitys != null && orderQuntitys.size() > 0) {
			orderQuntity = orderQuntitys.get(0);
		}
		List<String> orgIds = params.get("orgId");
		if (orgIds != null && orgIds.size() > 0) {
			orgId = orgIds.get(0);
		}
		List<String> orderIds = params.get("orderId");
		if (orderIds != null && orderIds.size() > 0) {
			orderId = orderIds.get(0);
		}
		List<String> couldIds = params.get("couldId");
		if (couldIds != null && couldIds.size() > 0) {
			couldId = couldIds.get(0);
		}
		List<String> statuss = params.get("status");
		if (statuss != null && statuss.size() > 0) {
			status = statuss.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		List<String> modifyDates = params.get("modifyDate");
		if (modifyDates != null && modifyDates.size() > 0) {
			modifyDate = modifyDates.get(0);
		}
		List<String> orderCreateDates = params.get("orderCreateDate");
		if (orderCreateDates != null && orderCreateDates.size() > 0) {
			orderCreateDate = orderCreateDates.get(0);
		}

		List<String> orderDetails = params.get("orderDetail");
		if (orderDetails != null && orderDetails.size() > 0) {
			orderDetail1 = orderDetails.get(0);
		}
		List<String> orderStatuss = params.get("orderStatus");
		if (orderStatuss != null && orderStatuss.size() > 0) {
			orderStatus = orderStatuss.get(0);
		}
		List<String> orderFundStatuss = params.get("orderFundStatus");
		if (orderFundStatuss != null && orderFundStatuss.size() > 0) {
			orderFundStatus = orderFundStatuss.get(0);
		}

		List<OrderDetail> odlist = JSON.parseObject(orderDetail1,
				new TypeReference<List<OrderDetail>>() {
				});
		if (("").equals(orderId) || orderId == null) {// 创建订单
			try {
				Order order = new Order();
				order.setTotalPrice(totalPrice);
				order.setCustId(custId);
				order.setLastModifyUser(userId);
				order.setUserId(userId);
				order.setTotalPricaeUniteCode(totalPricaeUniteCode);
				order.setTotalPricaeUniteDesc(totalPricaeUniteDesc);
				order.setCloudId(cloudId);
				order.setOrderDesc(orderDesc);
				// order.setOrderDesc(orderDesc);
				order.setOrgId(orgId);
				order.setOrderStatus(orderStatus);
				order.setOrderFundStatus(orderFundStatus);
				Long timeL = Long.parseLong(orderCreateDate);
				order.setOrderCreateDate(new Date(timeL));
				order.setOrderQuntity(orderQuntity);
				order.setCouldId(couldId);
				if (odlist != null && odlist.size() != 0) {
					order.setOrderDetail(odlist);
				}
				BooleanResult result = caService.insertOrder(order);
				if (result.getResult()) {
					message.setResultCode("success");
					message.setResultDesc(result.getCode());
					return JsonUtil.bean2Json(message.getClass(), message);
				} else {
					message.setResultCode("fail");
					return JsonUtil.bean2Json(message.getClass(), message);

				}
			} catch (Exception e) {
				e.printStackTrace();
				message.setResultCode("fail");
				message.setResultDesc("异常");
				return JsonUtil.bean2Json(message.getClass(), message);
			}
		} else {// 修改订单
			try {
				Order order = new Order();
				order.setOrderId(orderId);
				order.setTotalPrice(totalPrice);
				order.setCustId(custId);
				order.setLastModifyUser(userId);
				order.setUserId(userId);
				order.setTotalPricaeUniteCode(totalPricaeUniteCode);
				order.setTotalPricaeUniteDesc(totalPricaeUniteDesc);
				order.setCloudId(cloudId);
				order.setOrderDesc(orderDesc);
				order.setOrderQuntity(orderQuntity);
				order.setOrgId(orgId);
				order.setOrderStatus(orderStatus);
				order.setOrderFundStatus(orderFundStatus);
				order.setCouldId(couldId);
				Long timeL = Long.parseLong(orderCreateDate);
				order.setOrderCreateDate(new Date(timeL));
				if (odlist != null && odlist.size() != 0) {
					order.setOrderDetail(odlist);
				}
				BooleanResult result = caService.updateOrder(order);
				if (result.getResult()) {
					message.setResultCode("success");
					message.setResultDesc(result.getCode());
					return JsonUtil.bean2Json(message.getClass(), message);
				} else {
					message.setResultCode("fail");
					return JsonUtil.bean2Json(message.getClass(), message);

				}
			} catch (Exception e) {
				e.printStackTrace();
				message.setResultCode("fail");
				message.setResultDesc("异常");
				return JsonUtil.bean2Json(message.getClass(), message);
			}
		}
	}

	@Override
	public String getWeekOrderTotal(MultivaluedMap<String, String> params) {
		String userId = null;
		String cloudId = null;
		String custId = null;
		String firstday = null;
		String lastday = null;

		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> firstdays = params.get("firstday");
		if (firstdays != null && firstdays.size() > 0) {
			firstday = firstdays.get(0).substring(0, 10);
		}
		List<String> lastdays = params.get("lastday");
		if (lastdays != null && lastdays.size() > 0) {
			lastday = lastdays.get(0).substring(0, 10);
		}
		// 获取总单
		List<Order> orderList;
		try {
			Order orderParameter = new Order(custId, userId, cloudId);
			orderParameter.setFirstday(firstday);
			orderParameter.setLastday(lastday);
			orderList = caService.getWeekOrderTotal(orderParameter);
			if (orderList == null || orderList.size() == 0) {
				return IOpenapiService.SUCCESS;
			}
			String str = "";
			for (Order order : orderList) {
				if (!"".equals(str)) {
					str = str + "," + order.getOrderId();
				} else {
					str = order.getOrderId();
				}
			}
			// 获取明细
			List<OrderDetail> orderDetailList = caService.getOrderDetail(str);
			List<OrderDetail> odList = new ArrayList<OrderDetail>();
			for (int i = 0; i < orderList.size(); i++) {
				odList = new ArrayList<OrderDetail>();
				for (OrderDetail orderDetail : orderDetailList) {
					if (orderDetail.getOrderId().equals(
							orderList.get(i).getOrderId())) {
						odList.add(orderDetail);
					}
				}
				orderList.get(i).setOrderDetail(odList);
			}

			for (int i = 0; i < orderList.size(); i++) {
				orderList.get(i)
						.setOrderDetailList(
								JsonUtil.bean2Json(orderList.get(i)
										.getOrderDetail().getClass(), orderList
										.get(i).getOrderDetail()));
				orderList.get(i).getOrderDetail().clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return IOpenapiService.FAIL;
		}

		return JsonUtil.bean2Json(orderList.getClass(), orderList);
	}

	@Override
	public String orderDownload(MultivaluedMap<String, String> params) {
		String userId = null;
		String cloudId = null;
		String custId = null;
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		// 获取总单
		List<Order> orderList;
		try {
			Order orderParameter = new Order(custId, userId, cloudId);
			orderList = caService.getOrderTotal(orderParameter);
			if (orderList == null || orderList.size() == 0) {
				return IOpenapiService.SUCCESS;
			}
			String str = "";
			for (Order order : orderList) {
				if (!"".equals(str)) {
					str = str + "," + order.getOrderId();
				} else {
					str = order.getOrderId();
				}
			}
			// 获取明细
			List<OrderDetail> orderDetailList = caService.getOrderDetail(str);
			List<OrderDetail> odList = new ArrayList<OrderDetail>();
			for (int i = 0; i < orderList.size(); i++) {
				odList = new ArrayList<OrderDetail>();
				for (OrderDetail orderDetail : orderDetailList) {
					if (orderDetail.getOrderId().equals(
							orderList.get(i).getOrderId())) {
						odList.add(orderDetail);
					}
				}
				orderList.get(i).setOrderDetail(odList);
			}

			for (int i = 0; i < orderList.size(); i++) {
				orderList.get(i)
						.setOrderDetailList(
								JsonUtil.bean2Json(orderList.get(i)
										.getOrderDetail().getClass(), orderList
										.get(i).getOrderDetail()));
				orderList.get(i).getOrderDetail().clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return IOpenapiService.FAIL;
		}

		return JsonUtil.bean2Json(orderList.getClass(), orderList);
	}

	public String stockageUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String userId = null;
		String skuId = null;
		String startDay = null;
		String endDay = null;
		String createDate = null;
		String year = null;
		String month = null;
		String quantity = null;
		String cloudId = null;
		String unitCode = null;
		String unitDesc = null;

		List<String> years = params.get("year");
		if (years != null && years.size() > 0) {
			year = years.get(0);
		}
		List<String> months = params.get("month");
		if (months != null && months.size() > 0) {
			month = months.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> startDays = params.get("startDay");
		if (startDays != null && startDays.size() > 0) {
			startDay = startDays.get(0);
		}

		List<String> endDays = params.get("endDay");
		if (endDays != null && endDays.size() > 0) {
			endDay = endDays.get(0);
		}
		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			skuId = skuIds.get(0);
		}
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		List<String> quantitys = params.get("quantity");
		if (quantitys != null && quantitys.size() > 0) {
			quantity = quantitys.get(0);
		}
		List<String> unitCodes = params.get("unitCode");
		if (unitCodes != null && unitCodes.size() > 0) {
			unitCode = unitCodes.get(0);
		}
		List<String> unitDescs = params.get("unitDesc");
		if (unitDescs != null && unitDescs.size() > 0) {
			unitDesc = unitDescs.get(0);
		}

		try {
			StockAge stage = new StockAge();
			stage.setCustId(custId);
			stage.setUserId(userId);
			stage.setDayType(createDate);
			stage.setSkuId(skuId);
			stage.setStartDay(startDay);
			stage.setEndDay(endDay);
			stage.setYear(year);
			stage.setMonth(month);
			stage.setCloudId(cloudId);
			stage.setQuantity(quantity);
			stage.setUnitCode(unitCode);
			stage.setUnitDesc(unitDesc);
			Long id = caService.insertStage(stage);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String superviseUpload(MultivaluedMap<String, String> params) {
		String marketDetailId = null;
		String hxProportion = null;
		String Execution = null;
		// String eventId = null;
		String itemId = null;
		String dxExplanation = null;
		String custId = null;
		String userId = null;
		String roleId = null;
		String checkPercentBus = null;
		String reason = null;
		String dayType = null;

		List<String> dayTypes = params.get("dayType");
		if (dayTypes != null && dayTypes.size() > 0) {
			dayType = dayTypes.get(0);
		}

		List<String> marketDetailIds = params.get("marketDetailId");
		if (marketDetailIds != null && marketDetailIds.size() > 0) {
			marketDetailId = marketDetailIds.get(0);
		}

		List<String> checkPercentBuss = params.get("checkPercentBus");
		if (checkPercentBuss != null && checkPercentBuss.size() > 0) {
			checkPercentBus = checkPercentBuss.get(0);
		}

		List<String> reasons = params.get("reason");
		if (reasons != null && reasons.size() > 0) {
			reason = reasons.get(0);
		}

		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		List<String> hxProportions = params.get("hxProportion");
		if (hxProportions != null && hxProportions.size() > 0) {
			hxProportion = hxProportions.get(0);
		}
		List<String> Executions = params.get("Execution");
		if (Executions != null && Executions.size() > 0) {
			Execution = Executions.get(0);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> itemIds = params.get("itemId");
		if (itemIds != null && itemIds.size() > 0) {
			itemId = itemIds.get(0);
		}

		List<String> dxExplanations = params.get("dxExplanation");
		if (dxExplanations != null && dxExplanations.size() > 0) {
			dxExplanation = dxExplanations.get(0);
			dxExplanation = dxExplanation.replaceAll("\n", "")
					.replaceAll(" ", "").replaceAll("\t", "")
					.replaceAll("\r", "");
		}
		// List<BaseMarketCheck> bmks = new ArrayList<BaseMarketCheck>();
		// BaseMarketCheck bmk1 = new BaseMarketCheck();
		// bmks=caService.getActByDid(marketDetailId);
		// if(0!=bmks.size()){
		// bmk1= bmks.get(0);
		// }
		try {
			BaseMarketCheck bmk = new BaseMarketCheck();
			BaseCustomer customer = new BaseCustomer();
			customer = caService.getModifier(userId).get(0);
			if ("mobile_dd".equals(roleId)) {
				customer = (BaseCustomer) this.caService.getModifier(userId)
						.get(0);
				bmk.setDxExplanation(dxExplanation);
				bmk.setExecution(Execution);
				bmk.setHxProportion(hxProportion);
				bmk.setMarketDetailId(marketDetailId);
				bmk.setItemId(itemId);
				bmk.setCustId(custId);
				bmk.setImplementPerson(customer.getModifier());
				bmk.setUserId(userId);
				bmk.setDayType(dayType);
				bmk.setStatus("S");
			} else if ("mobile_csjl".equals(roleId)
					|| "mobile_ywy".equals(roleId)) {
				// } else if ("mobile_ywy".equals(roleId)) {
				customer = (BaseCustomer) this.caService.getModifier(userId)
						.get(0);
				bmk.setCheckPercentBus(checkPercentBus);
				bmk.setReason(reason);
				bmk.setMarketDetailId(marketDetailId);
				bmk.setItemId(itemId);
				bmk.setCustId(custId);
				bmk.setUserId(userId);
				bmk.setDayType(dayType);
				bmk.setStatus_bus("S");
			} else {
				return "success";
			}
			int j = caService.getActCount(bmk);
			if (j != 0) {
				if (caService.insertCheck(bmk)) {
					BooleanResult result = caService.updateSupervise(bmk);
					if (result.getResult()) {
						caService.checkImplementStatusDetail(marketDetailId);
						return "success";
					} else {
						return "fail";
					}
				} else {
					return "fail";
				}
			} else {
				if (caService.insertCheck(bmk)) {
					if (caService.insertSupervise(bmk)) {
						caService.checkImplementStatusDetail(marketDetailId);
						return "success";
					} else {
						return "fail";
					}
				} else {
					return "fail";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String updateCustomer(MultivaluedMap<String, String> params) {
		String custId = null;
		String userId = null;
		String skuId = null;
		String startDay = null;
		String endDay = null;
		String createDate = null;
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		List<String> createDates = params.get("createDate");
		if (createDates != null && createDates.size() > 0) {
			createDate = createDates.get(0);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		List<String> startDays = params.get("startDay");
		if (startDays != null && startDays.size() > 0) {
			startDay = startDays.get(0);
		}

		List<String> endDays = params.get("endDay");
		if (endDays != null && endDays.size() > 0) {
			endDay = endDays.get(0);
		}
		try {
			BaseCustomer customr = new BaseCustomer();
			StockAge stage = new StockAge();
			stage.setCustId(custId);
			stage.setUserId(userId);
			stage.setDayType(createDate);
			stage.setSkuId(skuId);
			stage.setStartDay(startDay);
			stage.setEndDay(endDay);
			// Long id=caService.insertStage(stage);

			BooleanResult result = caService.updateCustomer(customr);
			if (result.getResult()) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String getIndexDetail(MultivaluedMap<String, String> params) {

		String cloudId = null;
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}

		try {
			// 获取总单
			List<Dicts> dictList = caService.getIndexDetail(cloudId);
			return JsonUtil.bean2Json(dictList.getClass(), dictList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 登陆信息
	 */
	@Override
	public String uploadLoginLog(MultivaluedMap<String, String> params) {
		String loginLogId = null;
		String userId = null;
		String imei = null;
		String imsi = null;
		String loginMobile = null;
		String status = null;
		String version = null;
		String packageName = null;
		String handSetInfo = null;
		ResultMessage rm = null;
		try {
			List<String> loginLogIds = params.get("loginLogId");
			if (loginLogIds != null && loginLogIds.size() > 0) {
				loginLogId = loginLogIds.get(0);
			}

			List<String> userIds = params.get("userId");
			if (userIds != null && userIds.size() > 0) {
				userId = userIds.get(0);
			}
			List<String> imeis = params.get("imei");
			if (imeis != null && imeis.size() > 0) {
				imei = imeis.get(0);
			}

			List<String> imsis = params.get("imsi");
			if (imsis != null && imsis.size() > 0) {
				imsi = imsis.get(0);
			}

			List<String> loginMobiles = params.get("loginMobile");
			if (loginMobiles != null && loginMobiles.size() > 0) {
				loginMobile = loginMobiles.get(0);
			}

			List<String> statuses = params.get("status");
			if (statuses != null && statuses.size() > 0) {
				status = statuses.get(0);
			}
			List<String> versions = params.get("version");
			if (versions != null && versions.size() > 0) {
				version = versions.get(0);
			}

			List<String> packageNames = params.get("packageName");
			if (packageNames != null && packageNames.size() > 0) {
				packageName = packageNames.get(0);
			}
			List<String> handSetInfos = params.get("handSetInfo");
			if (handSetInfos != null && handSetInfos.size() > 0) {
				handSetInfo = handSetInfos.get(0);
			}
			rm = new ResultMessage();
			if ("online".equals(status)) {
				LoginLog loginLog = new LoginLog(userId, imei, loginMobile,
						imsi, status, version, packageName, handSetInfo);
				long r = caService.insertLoginLog(loginLog);// 登陆成功，返回登陆loginLogId
				if (r != 0L) {
					rm.setResultCode(IOpenapiService.SUCCESS);
					rm.setResultDesc(String.valueOf(r));
				} else {
					rm.setResultCode(IOpenapiService.FAIL);
				}
				return JsonUtil.bean2Json(rm.getClass(), rm);
			} else if ("offline".equals(status)) {
				LoginLog loginLog = new LoginLog(Long.valueOf(loginLogId),
						status);
				BooleanResult s = caService.updateLoginLog(loginLog);// 退出成功
				rm.setResultCode(s.getResult() ? IOpenapiService.SUCCESS
						: IOpenapiService.FAIL);
				rm.setResultDesc("0L");
				return JsonUtil.bean2Json(rm.getClass(), rm);
			}

		} catch (Exception e) {
			logger.error("退出登录时的loginLogId:" + loginLogId);
			logger.error(e);
		}
		rm.setResultCode(IOpenapiService.FAIL);
		return JsonUtil.bean2Json(rm.getClass(), rm);

	}

	public String uploadkunnrStock(MultivaluedMap<String, String> params) {
		String kunnrStock = null;
		ResultMessage rm = new ResultMessage();
		List<String> kunnrStocks = params.get("kunnrStock");
		if (kunnrStocks != null && kunnrStocks.size() > 0) {
			kunnrStock = kunnrStocks.get(0);
		}

		List<Stock> list = JSON.parseObject(kunnrStock,
				new TypeReference<List<Stock>>() {
				});
		if (caService.insertKunnrStock(list)) {
			rm.setResultCode(IOpenapiService.SUCCESS);
		} else {
			rm.setResultCode(IOpenapiService.FAIL);
		}

		return JsonUtil.bean2Json(rm.getClass(), rm);
	}

	/**
	 * 提交保存分销量 version 1.2 xg.chen modify date 20161129
	 */
	public String uploadkunnrStockNew(MultivaluedMap<String, String> params) {
		String kunnrStock = null;
		String type = null;
		String roleId = null;
		ResultMessage rm = new ResultMessage();
		List<String> kunnrStocks = params.get("kunnrStock");
		if (kunnrStocks != null && kunnrStocks.size() > 0) {
			kunnrStock = kunnrStocks.get(0);
		}
		List<String> types = params.get("type");
		if (types != null && types.size() > 0) {
			type = types.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		Stock stock = JSON.parseObject(kunnrStock, new TypeReference<Stock>() {

		});
		// 判断当前提报时间是否在有效时间内
		KunnrStockDate kunnerDate = new KunnrStockDate();
		kunnerDate.setCheck_time(stock.getCheckTime());
		if (!"sales_day".equals(stock.getFlag())) {
			if ("kunnr_month".equals(stock.getFlag())) {
				kunnerDate.setMonth("2");
				kunnerDate.setWeek(null);
			} else {
				kunnerDate.setMonth(null);
				kunnerDate.setWeek("1");
			}
			List<KunnrStockDate> list = caService.getKunnrStockDate(kunnerDate);
			if (list == null || list.size() == 0) {
				rm.setResultCode(IOpenapiService.TIMEOUT);
				rm.setResultDesc("非提报期间，上传失败");
				return JsonUtil.bean2Json(rm.getClass(), rm);
			}
		} else {
			// 修复日分销量时间和当前时间不一致的bug
			// modify date ： xg.chen 20161008
			// 获取当前时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(date);
			String checkDate = stock.getCheckTime();
			if (!checkDate.equals(time)) {
				rm.setResultCode(IOpenapiService.TIMEOUT);
				rm.setResultDesc("非提报期间，上传失败");
				return JsonUtil.bean2Json(rm.getClass(), rm);
			}
		}
		try {
			if ("mobile_dd".equals(roleId)) {// 督导
				stock.setUserType("D");
			} else if ("jxs_role".equals(roleId)) {// 经销商(经销商提报添加数据标识K)
				stock.setUserType("K");
			} else {// 业务员
				stock.setUserType("A");
			}
			BooleanResult result = caService.uploadKunnrStock(stock, type,
					roleId);
			if (result.getResult()) {
				rm.setResultCode(IOpenapiService.SUCCESS);
				rm.setResultDesc(result.getCode());
			} else {
				rm.setResultCode(IOpenapiService.FAIL);
				rm.setResultDesc(result.getCode());
			}

			return JsonUtil.bean2Json(rm.getClass(), rm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rm.setResultCode(IOpenapiService.FAIL);
		rm.setResultDesc("上传失败");
		return JsonUtil.bean2Json(rm.getClass(), rm);
	}

	public String getKunnrStock(MultivaluedMap<String, String> params) {
		String kunnrStock = null;

		List<String> kunnrStocks = params.get("kunnrStock");
		if (kunnrStocks != null && kunnrStocks.size() > 0) {
			kunnrStock = kunnrStocks.get(0);
		}
		Stock stock = JSON.parseObject(kunnrStock, new TypeReference<Stock>() {
		});
		List<Stock> stockList = new ArrayList<Stock>();
		try {
			stockList = caService.getKunnrStock(stock);
			return JsonUtil.bean2Json(stockList.getClass(), stockList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getKunnrStockDate(MultivaluedMap<String, String> params) {
		String type = null;
		String state = null;
		List<String> types = params.get("type");
		if (types != null && types.size() > 0) {
			type = types.get(0);
		}
		List<String> states = params.get("state");
		if (states != null && states.size() > 0) {
			state = states.get(0);
		}
		KunnrStockDate kunnrStockDate = new KunnrStockDate();
		kunnrStockDate.setMonth(null);
		kunnrStockDate.setWeek(null);
		List<KunnrStockDate> list = caService.getKunnrStockDate(kunnrStockDate);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setState("1");
			String[] date = list.get(i).getCheck_time().split("-");
			list.get(i).setYear(date[0]);
			list.get(i).setMonth(date[1]);
			list.get(i).setWeek(date[2]);
			list.get(i).setStartDate(list.get(i).getStart_date().getTime());
			list.get(i).setEndDate(list.get(i).getEnd_date().getTime());
			if ("6".equals(list.get(i).getId())) {
				list.get(i).setType("2");
			} else {
				list.get(i).setType("1");
			}
		}

		return JsonUtil.bean2Json(list.getClass(), list);
	}

	/**
	 * response.
	 * 
	 * @param methodName
	 * @param startTime
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String response(IMemcachedCacheService memcachedCacheService,
			String methodName, Long startTime, Object obj) {
		try {
			ResponseStats responseStats = new ResponseStats();
			responseStats.setMethodName(methodName);
			responseStats.setStartTime(startTime);
			responseStats.setEndTime(System.currentTimeMillis());

			List<ResponseStats> list = (List<ResponseStats>) memcachedCacheService
					.get(IMemcachedCacheService.CACHE_KEY_OPEN_API);

			// 初始化cache
			if (list == null || list.size() == 0) {
				list = new ArrayList<ResponseStats>();
			}

			list.add(responseStats);

			memcachedCacheService.set(
					IMemcachedCacheService.CACHE_KEY_OPEN_API, list,
					IMemcachedCacheService.CACHE_KEY_OPEN_API_DEFAULT_EXP);

		} catch (Exception e) {
			logger.error(e);
		}

		return JsonUtil.bean2Json(obj.getClass(), obj);
	}

	public String getKpiBo(MultivaluedMap<String, String> params) {
		String userId = null;
		String kunnrId = null;
		Kpi kpi = new Kpi();
		Dict dict = new Dict();
		List<Kpi> KpiList = new ArrayList<Kpi>();
		List<Kpi> orgList = new ArrayList<Kpi>();

		// 获得传进来的userId
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
			// userId = "92862";

			// 获得登陆人的岗位列表及岗位对应组织
			List<Kpi> list = caService.getKpiNeed(userId);

			try {
				if (list != null && list.size() > 0) {
					for (Kpi need : list) {
						// 通过登陆人的岗位组织id查出这个组织的所有父组织(到大区为止)
						orgList = caService.getParentsOrg(need);
						if (orgList != null && orgList.size() > 0) {
							for (Kpi orgId : orgList) {
								// 通过组织id，查出Kpi数据
								kpi = caService.getKpi(orgId);
								kpi.setOrgId(orgId.getN_orgId());
								KpiList.add(kpi);
							}
						} else {
							kpi = caService.getKpi(need);
							kpi.setOrgId(need.getN_orgId());
							KpiList.add(kpi);
						}

					}
					// 去重复
					if (KpiList != null && KpiList.size() > 1) {
						for (int i = 0; i < KpiList.size(); i++) {
							for (int j = KpiList.size() - 1; j > i; j--) {
								if (KpiList.get(i).getOrgId()
										.equals(KpiList.get(j).getOrgId())) {
									KpiList.remove(j);
								}
							}
						}
					}
					return JsonUtil.bean2Json(KpiList.getClass(), KpiList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 如果传过来的是经销商Id
			List<String> kunnrIds = params.get("kunnrId");
			if (kunnrIds != null && kunnrIds.size() > 0) {
				kunnrId = kunnrIds.get(0);
				try {
					KpiList = caService.getKpiByKunnrId("00" + kunnrId);
					if (KpiList != null && KpiList.size() > 0) {
						return JsonUtil.bean2Json(KpiList.getClass(), KpiList);
					} else {
						kpi.setCk_actual(0.0);
						kpi.setCk_target(0.0);
						kpi.setCk_rate(0.0);
						kpi.setFx_actual(0.0);
						kpi.setFx_target(0.0);
						kpi.setFx_rate(0.0);
						KpiList.add(kpi);
						return JsonUtil.bean2Json(KpiList.getClass(), KpiList);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public String checkKpiMenu(MultivaluedMap<String, String> params) {
		String userId = null;
		String menuCheckFlag = null;
		String kunnrCheckFlag = null;
		String normalCheckFlag = null;
		Dict dict = new Dict();
		Dict dict2 = new Dict();

		// 获得传进来的userId
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
			// userId = "92862";

			// 获得登陆人的岗位列表及岗位对应组织
			List<Kpi> list = caService.getKpiNeed(userId);

			dict.setDictTypeValue("checkKpiMenu");
			// 获取字典中配置中，不需要菜单的岗位列表
			List<Dict> dictList = idictService.getDictList(dict);

			dict2.setDictTypeValue("KpiBo");
			// 获取字典中配置中，需要查询经销商的岗位列表
			List<Dict> dictList2 = idictService.getDictList(dict2);

			try {
				if (list != null && list.size() > 0) {
					for (Kpi need : list) {
						if (dictList != null && dictList.size() > 0) {
							for (int k = 0; k < dictList.size(); k++) {
								if (dictList.get(k).getItemValue()
										.equals(need.getN_stationId())) {
									// 如果登录人的岗位符合字典中的不需要KPI菜单的岗位，menu隐藏
									menuCheckFlag = "TYPE_DD";
									return JsonUtil.bean2Json(
											menuCheckFlag.getClass(),
											menuCheckFlag);
								}
							}
						}

						if (dictList2 != null && dictList2.size() > 0) {
							for (int k = 0; k < dictList2.size(); k++) {
								if (dictList2.get(k).getItemValue()
										.equals(need.getN_stationId())) {
									// 如果登录人的岗位符合字典中的需要查询经销商的岗位，不隐藏经销商
									kunnrCheckFlag = "TYPE_YD";
								}
							}
						}
					}
					if (kunnrCheckFlag != null) {
						return JsonUtil.bean2Json(kunnrCheckFlag.getClass(),
								kunnrCheckFlag);
					}
					// JSX数据隐藏
					normalCheckFlag = "TYPE_JL";
					return JsonUtil.bean2Json(normalCheckFlag.getClass(),
							normalCheckFlag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getKpiVisit(MultivaluedMap<String, String> params) {
		String userId = null;
		String v_actual = null;
		KpiVisit kpiVisit = new KpiVisit();
		List<KpiVisit> kpiVisitList = new ArrayList<KpiVisit>();
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
			//
			//
			// userId = "92862";
		}
		try {
			v_actual = caService.getVisitActual(userId);
			kpiVisit.setV_actual(v_actual);
			kpiVisit.setV_target("0");
			kpiVisit.setV_rate("0");
			kpiVisitList.add(kpiVisit);
		} catch (Exception e) {
			e.printStackTrace();
			kpiVisit.setV_actual("-999");
			kpiVisit.setV_target("-999");
			kpiVisit.setV_rate("-999");
			kpiVisitList.add(kpiVisit);
		}
		return JsonUtil.bean2Json(kpiVisitList.getClass(), kpiVisitList);
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(
			IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public ICAService getCaService() {
		return caService;
	}

	public void setCaService(ICAService caService) {
		this.caService = caService;
	}

	public Properties getEnv() {
		return env;
	}

	public void setEnv(Properties env) {
		this.env = env;
	}

	public IDictService getIdictService() {
		return idictService;
	}

	public void setIdictService(IDictService idictService) {
		this.idictService = idictService;
	}

	/**
	 * 获取提报后数据汇总
	 */
	public String getSuperKunnrInfo(MultivaluedMap<String, String> params) {
		String kunnrStock = null;
		String roleId = null;
		List<String> kunnrStocks = params.get("kunnrStock");
		if (kunnrStocks != null && kunnrStocks.size() > 0) {
			kunnrStock = kunnrStocks.get(0);
		}
		List<String> roleIds = params.get("roleId");
		if (roleIds != null && roleIds.size() > 0) {
			roleId = roleIds.get(0);
		}
		Stock stock = JSON.parseObject(kunnrStock, new TypeReference<Stock>() {
		});
		if ("mobile_dd".equals(roleId)) {
			stock.setUserType("D");
		} else if ("jxs_role".equals(roleId)) {
			stock.setUserType("K");
		} else if (roleId == null) {
			stock.setUserType(null);
		} else {
			stock.setUserType("A");
		}

		String kunnrInfo = caService.getSuperKunnrInfo(stock);

		return kunnrInfo;
	}

	public String getSkuLastPrice(MultivaluedMap<String, String> params) {
		List<BaseProduct> bps = new ArrayList<BaseProduct>();
		BaseProduct bp = new BaseProduct();
		String cloudId = null;// 经销商
		String userId = null;// 菜单人
		String custId = null;// 门店ID
		String skuId = null;// skuID
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (cloudId != null) {
			bp.setCloudId(cloudId);
		}
		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}
		if (userId != null) {
			bp.setEmpId(userId);
		}
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		if (custId != null) {
			bp.setCustId(custId);
		}
		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			skuId = skuIds.get(0);
		}
		if (skuId != null) {
			bp.setCategoryId(skuId);
		}
		bps = caService.getSkuLastPrice(bp);
		// System.out.println(bps);
		return JsonUtil.bean2Json(bps.getClass(), bps);
	}

	public String getKunnrOrderFormat(MultivaluedMap<String, String> params) {
		List<OrderPrintFormat> opfs = new ArrayList<OrderPrintFormat>();
		OrderPrintFormat opf = new OrderPrintFormat();
		String cloudId = null;// 经销商
		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (cloudId != null) {
			opf.setKunnr(cloudId);
		}

		opfs = caService.getKunnrOrderFormat(opf);
		return JsonUtil.bean2Json(opfs.getClass(), opfs);
	}

	@Override
	public String getLstm(MultivaluedMap<String, String> params) {
		List<Lstm> opfs = new ArrayList<Lstm>();
		Lstm bp = new Lstm();
		Lstm bp1 = new Lstm();
		String cloudId = null;// 经销商
		String custId = null;// 门店ID

		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (cloudId != null) {
			bp.setCloudid(cloudId);
		}

		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		if (custId != null) {
			bp.setCust_id(custId);
			bp1.setCust_id(custId);
		}

		opfs = caService.getLstm(bp);
		// caService.deleteallLstm(bp1);
		for (Lstm lstm : opfs) {
			List<Lstm> lstm2 = caService.findallLstm(lstm);
			if (lstm2.size() > 0) {
				if (lstm2.get(0).getModify_date().before(lstm.getModify_date()))
					caService.updateLstm(lstm);
			} else
				caService.insertLstm(lstm);

		}

		List<Lstm> opfs1 = new ArrayList<Lstm>();

		opfs1 = caService.findLstm(bp1);
		for (Lstm lstm1 : opfs1) {
			int i = 0, j = 0;
			for (Lstm lstm2 : opfs) {
				if (lstm1.getSku_id().equals(lstm2.getSku_id())
						&& lstm1.getModify_date()
								.equals(lstm2.getModify_date())) {
					i = 1;
					opfs.remove(j);
					break;
				}
				j++;
			}
			if (i == 0) {
				// System.out.println("print------"+lstm1);
				caService.deleteLstm(lstm1);
			}
		}
		opfs1 = caService.findLstm(bp1);
		// System.out.println("print--"+opfs);
		return JsonUtil.bean2Json(opfs1.getClass(), opfs1);
	}

	@Override
	public String uploadlstm(MultivaluedMap<String, String> params) {

		String lstm = null;
		String status = null;

		ResultMessage rm = new ResultMessage();
		List<String> lstms = params.get("lstm");
		if (lstms != null && lstms.size() > 0) {
			lstm = lstms.get(0);
		}
		List<String> statuss = params.get("status");
		if (statuss != null && statuss.size() > 0) {
			status = statuss.get(0);
		}

		Lstm lstm1 = JSON.parseObject(lstm, new TypeReference<Lstm>() {
		});

		// System.out.println("lstm1"+lstms);
		try {

			BooleanResult result = caService.uploadlstm(lstm1, status);
			if (result.getResult()) {
				rm.setResultCode(IOpenapiService.SUCCESS);
				rm.setResultDesc(result.getCode());
			} else {
				rm.setResultCode(IOpenapiService.FAIL);
				rm.setResultDesc(result.getCode());
			}

			return JsonUtil.bean2Json(rm.getClass(), rm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rm.setResultCode(IOpenapiService.FAIL);
		rm.setResultDesc("上传失败");
		return JsonUtil.bean2Json(rm.getClass(), rm);

	}

	@Override
	public String admmobileVersion(MultivaluedMap<String, String> params) {

		List<Dicts> dicts = new ArrayList<Dicts>();
		dicts = caService.getVersion("admmobileVersion");
		return JsonUtil.bean2Json(dicts.getClass(), dicts);

	}

	@Override
	public String getCustSku(MultivaluedMap<String, String> params) {
		List<CustSku> opfs = new ArrayList<CustSku>();
		CustSku bp = new CustSku();

		String cloudId = null;// 经销商
		String custId = null;// 门店ID

		List<String> cloudIds = params.get("cloudId");
		if (cloudIds != null && cloudIds.size() > 0) {
			cloudId = cloudIds.get(0);
		}
		if (cloudId != null) {
			bp.setCloudid(cloudId);
		}

		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		if (custId != null) {
			bp.setCust_id(custId);
		}

		opfs = caService.getCustSku(bp);

		return JsonUtil.bean2Json(opfs.getClass(), opfs);
	}

	@Override
	public String searchCuanhuo(MultivaluedMap<String, String> params) {
		List<CuanhuoQuery> opfs = new ArrayList<CuanhuoQuery>();
		CuanhuoQuery bp = new CuanhuoQuery();
		String IV_WERKS;// 工厂
		String IV_MATNR = "";// 物料编码
		String IV_LOCCO;// 打码号
		String IV_DATUM;// 生产日期
		String chuanhaofactory = null, chuanhaodate = null, chuanhaosku = null, chuanhaodamacode = null;

		List<String> chuanhaofactorys = params.get("chuanhaofactory");
		if (chuanhaofactorys != null && chuanhaofactorys.size() > 0) {
			chuanhaofactory = chuanhaofactorys.get(0);
		}
		if (chuanhaofactory != null) {
			bp.setIV_WERKS(chuanhaofactory);
			// bp.setIV_WERKS("1000");
		}
		List<String> chuanhaodates = params.get("chuanhaodate");
		if (chuanhaodates != null && chuanhaodates.size() > 0) {
			chuanhaodate = chuanhaodates.get(0);
		}
		if (chuanhaodate != null) {
			chuanhaodate = chuanhaodate.replace("-", "");
			// bp.setIV_DATUM("20150724");
			bp.setIV_DATUM(chuanhaodate);
		}
		List<String> chuanhaoskus = params.get("chuanhaosku");
		if (chuanhaoskus != null && chuanhaoskus.size() > 0) {
			chuanhaosku = chuanhaoskus.get(0);
		}
		if (chuanhaosku != null) {
			IV_MATNR = caService.getMantnr(chuanhaosku);
			// bp.setIV_MATNR("10100003");
			bp.setIV_MATNR(IV_MATNR);

		}

		List<String> chuanhaodamacodes = params.get("chuanhaodamacode");
		if (chuanhaodamacodes != null && chuanhaodamacodes.size() > 0) {
			chuanhaodamacode = chuanhaodamacodes.get(0);
		}
		if (chuanhaodamacode != null) {
			// bp.setIV_LOCCO("0000");
			bp.setIV_LOCCO(chuanhaodamacode);
		}
		// System.out.println(chuanhaofactory+"--"+chuanhaodate+"--"+IV_MATNR+"--"+chuanhaodamacode);
		// bp.setKUNAG_NAME("湖州凤凰街道永森副食品商行");
		// bp.setKUNWE_NAME("湖州凤凰街道永森副食品商行");
		// bp.setPODAT("2015-9-10");
		// bp.setVBELN_VA("1000092337");
		// for (int i = 0; i < 10; i++) {
		// opfs.add(bp);
		// }

		opfs = caService.searchCuanhuo(bp);

		return JsonUtil.bean2Json(opfs.getClass(), opfs);
	}
	
	public String signUpload(MultivaluedMap<String, String> params) {
		String operator_id = null;
		String address = null;
		String sign_type = null;
		String longitude = null;// 经度
		String latitude = null;// 纬度

		List<String> operator_ids = params.get("operator_id");
		if (operator_ids != null && operator_ids.size() > 0) {
			operator_id = operator_ids.get(0);
		}

		List<String> sign_types = params.get("sign_type");
		if (sign_types != null && sign_types.size() > 0) {
			sign_type = sign_types.get(0);
		}

		List<String> lonitudes = params.get("longitude");
		if (lonitudes != null && lonitudes.size() > 0) {
			longitude = lonitudes.get(0);
		}
		List<String> latitudes = params.get("latitude");
		if (latitudes != null && latitudes.size() > 0) {
			latitude = latitudes.get(0);
		}
		List<String> addresss = params.get("address");
		if (addresss != null && addresss.size() > 0) {
			address = addresss.get(0);
		}
		Sign sign = new Sign();
		sign.setAddress(address);
		sign.setLatitude(latitude);
		sign.setOperator_id(operator_id);
		sign.setLongitude(longitude);
		sign.setSign_type(sign_type);
		List<Sign> signs = caService.getSignList(sign);
		Long id = null;
		if (signs != null && signs.size() > 0) {
			sign.setSign_id(signs.get(0).getSign_id());
			caService.updateSign(sign);
			id = signs.get(0).getSign_id();
		} else {
			id = caService.insertSign(sign);
		}

		if (id != null) {
			Sign sign2 = new Sign();
			sign2.setSign_id(id);
			List<Sign> signs2 = caService.getSignList(sign2);
			if (signs2 != null && signs2.size() > 0) {
				return JsonUtil.bean2Json(signs2.getClass(), signs2);
			}
		}

		return "";

	}

	@Override
	public String getSignList(MultivaluedMap<String, String> params) {
		String operator_id = null;
		String address = null;
		String sign_type = null;
		String longitude = null;// 经度
		String latitude = null;// 纬度

		List<String> operator_ids = params.get("operator_id");
		if (operator_ids != null && operator_ids.size() > 0) {
			operator_id = operator_ids.get(0);
		}

		List<String> sign_types = params.get("sign_type");
		if (sign_types != null && sign_types.size() > 0) {
			sign_type = sign_types.get(0);
		}

		List<String> lonitudes = params.get("longitude");
		if (lonitudes != null && lonitudes.size() > 0) {
			longitude = lonitudes.get(0);
		}
		List<String> latitudes = params.get("latitude");
		if (latitudes != null && latitudes.size() > 0) {
			latitude = latitudes.get(0);
		}
		List<String> addresss = params.get("address");
		if (addresss != null && addresss.size() > 0) {
			address = addresss.get(0);
		}
		Sign sign = new Sign();
		sign.setAddress(address);
		sign.setLatitude(latitude);
		sign.setOperator_id(operator_id);
		sign.setLongitude(longitude);
		sign.setSign_type(sign_type);
		List<Sign> signs = caService.getSignList(sign);
		if (signs != null && signs.size() > 0) {
			return JsonUtil.bean2Json(signs.getClass(), signs);
		}
		return "";
	}

	@Override
	public String getVistCust(MultivaluedMap<String, String> params) {
		String operator_id = null;
		List<String> operator_ids = params.get("operator_id");
		if (operator_ids != null && operator_ids.size() > 0) {
			operator_id = operator_ids.get(0);
		}
		VistCust vistCust = new VistCust();
		vistCust.setUser_id(operator_id);
		List<VistCust> vistCusts = caService.getVistCust(vistCust);
		if (vistCusts != null && vistCusts.size() > 0) {
			return JsonUtil.bean2Json(vistCusts.getClass(), vistCusts);
		}
		return null;
	}

	public String getSkuUnit() {
		List<SkuUnit> bps = new ArrayList<SkuUnit>();
		bps = caService.getSkuUnit();
		return JsonUtil.bean2Json(bps.getClass(), bps);
	}

	@Override
	public String createCustomerStock(MultivaluedMap<String, String> params) {
		String custId = null;
		String skuId = null;
		String categoryId = null;
		String productionDate = null;
		String checkTime = null;
		String unitDesc = null;
		String quantity = null;
		String flag = null;
		String serId = null;
		String userType = null;
		String size = null;
		List<String> custIdList = params.get("custId");
		if (custIdList != null && custIdList.size() > 0) {
			custId = custIdList.get(0);
		}
		List<String> sizeList = params.get("size");
		if (sizeList != null && sizeList.size() > 0) {
			size = sizeList.get(0);
		}
		for (int i = 1; i <= Integer.parseInt(size); i++) {
			System.out.println(params.get("checkTime" + i));
			System.out.println(params.get("quantity" + i));
		}
		// List<String> checkTimeList = params.get("checkTime");
		// System.out.println("checkTimeList :"+checkTimeList.size());
		// if (checkTimeList != null && checkTimeList.size() > 0) {
		// // checkTime = checkTimeList.get(0);
		// for (String string : checkTimeList) {
		// System.out.println(string);
		// }
		// }
		//
		// List<String> quantityList = params.get("quantity");
		// System.out.println("quantityList :"+quantityList.size());
		// if (quantityList != null && quantityList.size() > 0) {
		// // quantity = quantityList.get(0);
		// for (String string : quantityList) {
		// System.out.println(string);
		// }
		// }
		return null;
	}

	// 空格替换为%
	String ReplaceSpace(String info) {
		if (!"".equals(info)) {
			String regEx = "[' ']+"; // 一个或多个空格
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(info);
			info = m.replaceAll("%");
			info = "%" + info + "%";
			return info;
		}
		return null;
	}

	@Override
	public String customerStockUpload(MultivaluedMap<String, String> params) {
		String custId = null;
		String userId = null;
		String skuId = null;
		String quantity = null;
		String categoryId = null;
		String productionDate = null;
		String checkTime = null;
		String unitDesc = null;
		String flag = null;
		String userType = null;

		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}

		List<String> userIds = params.get("userId");
		if (userIds != null && userIds.size() > 0) {
			userId = userIds.get(0);
		}

		List<String> skuIds = params.get("skuId");
		if (skuIds != null && skuIds.size() > 0) {
			skuId = skuIds.get(0);
		}

		List<String> quantitys = params.get("quantity");
		if (quantitys != null && quantitys.size() > 0) {
			quantity = quantitys.get(0);
		}

		List<String> unitDescs = params.get("unitDesc");
		if (unitDescs != null && unitDescs.size() > 0) {
			unitDesc = unitDescs.get(0);
		}
		List<String> categoryIds = params.get("categoryId");
		if (categoryIds != null && categoryIds.size() > 0) {
			categoryId = categoryIds.get(0);
		}
		List<String> productionDates = params.get("productionDate");
		if (productionDates != null && productionDates.size() > 0) {
			productionDate = productionDates.get(0);
		}
		List<String> checkTimes = params.get("checkTime");
		if (checkTimes != null && checkTimes.size() > 0) {
			checkTime = checkTimes.get(0);
		}
		List<String> flags = params.get("flag");
		if (flags != null && flags.size() > 0) {
			flag = flags.get(0);
		}
		List<String> userTypes = params.get("userType");
		if (userTypes != null && userTypes.size() > 0) {
			userType = userTypes.get(0);
		}

		try {
			StockReport stock = new StockReport();

			stock.setCustId(Long.parseLong(custId));
			stock.setUserId(Long.parseLong(userId));
			stock.setUserType(userType);
			stock.setSkuId(Long.parseLong(skuId));
			stock.setQuantity(Double.parseDouble(quantity));
			stock.setCategoryId(Long.parseLong(categoryId));
			stock.setProductionDate(productionDate);
			stock.setCheckTime(checkTime);
			stock.setUnitDesc(unitDesc);
			stock.setFlag(flag);
			System.out.println("StockReport:" + stock);
			Long id = caService.uploadStockReport(stock);
			if (null != id) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String getCustomerStockByCustid(MultivaluedMap<String, String> params) {
		String custId = null;
		List<String> custIds = params.get("custId");
		if (custIds != null && custIds.size() > 0) {
			custId = custIds.get(0);
		}
		try {
			System.out.println("custId:" + custId);
			List<StockReport> bps = new ArrayList<StockReport>();
			bps = caService.getCustomerStockByCustid(custId);
			return JsonUtil.bean2Json(bps.getClass(), bps);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

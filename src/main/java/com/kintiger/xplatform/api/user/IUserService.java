package com.kintiger.xplatform.api.user;

import java.util.List;

import com.kintiger.xplatform.api.user.bo.AbnormalPrice;
import com.kintiger.xplatform.api.user.bo.BaseCustomer;
import com.kintiger.xplatform.api.user.bo.BaseMarketCheck;
import com.kintiger.xplatform.api.user.bo.BaseMenu;
import com.kintiger.xplatform.api.user.bo.BaseProduct;
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

/**
 * 人員信息接口<br>
 * 包含人員基本信息 企業架構.
 * 
 * @author xujiakun
 * 
 */
public interface IUserService {

	String SUCCESS = "success";

	String ERROR = "error";

	String ERROR_MESSAGE = "操作失败！";

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	String ERROR_NULL_MESSAGE = "操作失败，不存在！";

	String ERROR_EXIST_MESSAGE = "操作失败，已存在！";

	String ERROR_CONNET_MESSAGE = "操作失败，WebService连接异常！";

	String CHECK_TYPE_HR = "hr";

	String CHECK_TYPE_IT = "it";

	String CHECK_TYPE_NONE = "none";

	/**
	 * 
	 * @param passport
	 * @return
	 */
	List<User> getUserByMobile(String mobile);

	List<User> getUserByCode(String mobile);

	List<BaseProduct> getSku();

	List<BaseCustomer> getChannel();

	List<BaseCustomer> getDivision();

	List<BaseCustomer> getDivisionSearch(String zwl04t);

	List<BaseCustomer> getDivisionforSearch(String zwl04);

	List<BaseProduct> SkuByCloud(String cloudId);

	List<BaseProduct> getOrderSkuByKunner(String cloudId);

	List<BaseProduct> getTime();

	List<Dicts> getDict();

	List<Dicts> getAdmDict();

	List<Dicts> getDictforAct();

	List<BaseMenu> getMenu();

	List<BaseCustomer> getCus(String userId);

	List<BaseCustomer> getCusforSearch(BaseCustomer customer, String roleId);

	/**
	 * MethodsTitle: 拜访管理之查找客户
	 * 
	 * @author: xg.chen
	 * @date:2016年11月7日
	 * @param customer
	 * @param roleId
	 * @return
	 */
	List<BaseCustomer> getCusforSearch1(BaseCustomer customer, String roleId);

	/**
	 * MethodsTitle: 拜访管理之查找客户 当条件setflag==Y时满足
	 * 
	 * @author: xg.chen
	 * @date:2016年11月7日
	 * @param customer
	 * @param roleId
	 * @return
	 */
	List<BaseCustomer> getCusforSearchSet(BaseCustomer customer, String roleId);

	List<BaseCustomer> getCusforSearchXpp(BaseCustomer customer, String roleId);

	List<BaseCustomer> getKunnrforSearch(String info);

	List<Dicts> getVersion(String dictTypeValue);

	List<BaseMarketCheck> getAct(String userId);

	List<BaseMarketCheck> getActforSearch(BaseMarketCheck bmk1);

	List<BaseCustomer> getRouteforSearch(BaseCustomer customer);

	List<BaseCustomer> getRouteforJXS(BaseCustomer customer);

	List<BaseMarketCheck> getActByDid(String marketDetailId);

	List<BaseCustomer> getModifier(String userId);

	List<BaseCustomer> getKunnr(String kunnr);

	int getBlCount(BaseCustomer customer);

	int getActCount(BaseMarketCheck bmk);

	Long insertImage(Picture p);

	Long insertImagePinfo(Picture p);

	Long insertPrice(AbnormalPrice ap);

	Long insertDistr(Distribution distr);

	BooleanResult updateCustomer(BaseCustomer customer);

	BooleanResult updateSupervise(BaseMarketCheck bmk);

	BooleanResult insertOrder(Order order);

	BooleanResult updateOrder(Order order);

	Long insertDisply(DisPlay disp);

	Long insertCus(BaseCustomer customer);

	Long insertStage(StockAge stage);

	boolean insertSupervise(BaseMarketCheck bmk);

	boolean insertCheck(BaseMarketCheck bmk);

	List<Order> getOrderTotal(Order order);

	List<OrderDetail> getOrderDetail(String orderId);

	List<Dicts> getIndexDetail(String cloudId);

	boolean insertKunnrStock(List<Stock> list);

	List<Stock> getKunnrStock(Stock stock);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	List<User> getUsersByOrgId(String orgId);

	/**
	 * 
	 * @param userIds
	 * @return
	 */
	List<User> getUsersByIds(List<String> userIds);

	/**
	 * 
	 * @param user
	 * @return
	 */
	BooleanResult updateUser(User user);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	User getUser(String userId);

	/**
	 * 
	 * @param User
	 * @return
	 */
	List<User> getUserList(User user);

	/**
	 * 
	 * @param User
	 * @return
	 */
	int getUserCount(User user);

	/**
	 * 
	 * @param loginLog
	 * @return Long
	 */
	Long insertLoginLog(LoginLog loginLog);

	/**
	 * 
	 * @param loginLog
	 * @return boolean
	 */
	BooleanResult updateLoginLog(LoginLog loginLog);

	/**
	 * MethodsTitle: 根据角色获取经销商
	 * 
	 * @author: xg.chen
	 * @date:2016年11月22日 上午9:25:26
	 * @version 1.0
	 * @param customer
	 * @param roleId
	 * @return
	 */
	List<BaseCustomer> getKunnrByJL(BaseCustomer customer, String roleId);

	List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate);

	List<Stock> getKunnrStockNew(Stock stock);

	List<Stock> getKunnrStockNew1(Stock stock);

	Long deleteKunnrStock(Stock stock);

	Long updateKunnrStock(Stock stock);

	Long insertKunnrStock(Stock stock);

	/**
	 * MethodsTitle: 删除提报库存
	 * 
	 * @author: xg.chen
	 * @date:2016年11月29日 下午2:09:53
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	BooleanResult deleteKunnrStockNew(Stock stock);

	/**
	 * MethodsTitle: 更新提报库存
	 * 
	 * @author: xg.chen
	 * @date:2016年11月29日 下午2:09:53
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	BooleanResult updateKunnrStockNew(Stock stock);

	/**
	 * MethodsTitle: 插入提报库存
	 * 
	 * @author: xg.chen
	 * @date:2016年11月29日 下午2:09:53
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	BooleanResult insertKunnrStockNew(Stock stock);

	List<Kpi> getKpiNeed(String userId);

	Kpi getKpi(Kpi orgId);

	List<Kpi> getKpiByKunnrId(String kunnrId);

	List<Kpi> getParentsOrg(Kpi need);

	String getVisitActual(String userId);

	/**
	 * MethodsTitle: 返回提交后提报数据
	 * 
	 * @author: xg.chen
	 * @date:2016年12月9日 上午9:53:02
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	String getSuperKunnrInfo(Stock stock);

	/**
	 * 获取经销商sku列表信息new 2015-4-17 提报人-经销商-门店-sku-最后一次提报价格
	 * 
	 * @param product
	 * @return
	 */
	List<BaseProduct> getSkuLastPrice(BaseProduct product);

	/**
	 * 获取经销商打印格式 2015-4-17
	 * 
	 * @param product
	 * @return
	 */
	List<OrderPrintFormat> getKunnrOrderFormat(OrderPrintFormat format);

	/**
	 * 获取历史条码
	 * 
	 * @param product
	 * @return
	 */
	List<Lstm> getLstm(Lstm lstm);

	List<Lstm> findLstm(Lstm lstm);

	Long insertLstm(Lstm lstm);

	Long updateLstm(Lstm lstm);

	Long deleteLstm(Lstm lstm);

	List<Lstm> findallLstm(Lstm lstm);

	Long deleteallLstm(Lstm lstm);

	List<User> getUsers(String info);

	Long resetPwd(User rstUser);

	List<CustSku> findallCustSku(CustSku custsku);

	List<Order> getWeekOrderTotal(Order order);

	List<BaseProduct> getMantnr(String chuanhaosku);

	Long insertSign(Sign sign);

	List<Sign> getSignList(Sign sign);

	Long updateSign(Sign sign);

	List<VistCust> getVistCust(VistCust vistCust);

	String getTime1();

	List<SkuUnit> getSkuUnit();

	Long uploadStockReport(StockReport stock);

	List<StockReport> getCustomerStockByCustid(String custid);

	void checkImplementStatusDetail(String miaDetailId);

	public void saveLogin(User user);

	/**
	 * MethodsTitle: 获取提报周库存
	 * 
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:14:41
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	List<Stock> getKunnrStockNew4Week(Stock stock);

	/**
	 * 
	 * MethodsTitle: 获取Top200经销商
	 * 
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:15:09
	 * @version 1.0
	 * @param kunnr
	 * @return
	 */
	List<Kunnr> getKunnrForTop200(Kunnr kunnr);

	/**
	 * MethodsTitle: 根据userId获取经销商标号
	 * 
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:15:31
	 * @version 1.0
	 * @param kunnr
	 * @return
	 */
	Kunnr getKunnrIdForUserId(String userId);
}

package com.kintiger.xplatform.api.login;

import java.util.List;

import com.kintiger.xplatform.api.login.bo.ValidateResult;
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
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	String RESULT_SUCCESS = "0";

	String RESULT_FAILED = "1";

	String RESULT_ERROR = "2";

	String INCORRECT_NULL = "手机号或密码不能为空！";

	String INCORRECT_LOGINID = "该用户在系统中不存在！";

	String INCORRECT_LOGIN = "手机号或密码输入不正确！";

	String INCORRECT_TOKEN = "token验证失败！";

	/**
	 * validateUser.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	User validateUser(String mobile, String password,String phoneSelfNum);
	
	User validateAdmUser(String mobile, String password,String phoneSelfNum);
	List<BaseProduct> getSku();
	
	List<BaseCustomer> getChannel();
	
	List<BaseCustomer>getDivision();
	
	List<BaseCustomer>getDivisionSearch(String zwl04t);
	
	List<BaseProduct>getSkuByCloud(String cloudId);
	List<BaseProduct>getOrderSkuByKunner(String custId);
	List<BaseCustomer>getDivisionforSearch(String zwl04);
	
	
	List<BaseProduct> getTime();
	
	List<Dicts> getDict();
	
	List<Dicts> getAdmDict();
	
	List<Dicts> getDictforAct();
	
	List<BaseMenu> getMenu();
	
	List<BaseCustomer> getCus(String userId);
	
	List<BaseCustomer> getCusforSearch(BaseCustomer customer,String roleId);
	/**
	 * MethodsTitle: 拜访管理之查找客户
	 * 当条件setflag==Y时满足
	 * @author: xg.chen
	 * @date:2016年11月7日 
	 * @param customer
	 * @param roleId
	 * @return
	 */
	List<BaseCustomer> getCusforSearchSet(BaseCustomer customer,String roleId);
	/**
	 * MethodsTitle: 拜访管理之查找客户
	 * @author: xg.chen
	 * @date:2016年11月7日 
	 * @param customer
	 * @param roleId
	 * @return
	 */
	List<BaseCustomer> getCusforSearch1(BaseCustomer customer,String roleId);
	List<BaseCustomer> getCusforSearchXpp(BaseCustomer customer,String roleId);
	List<BaseCustomer> getKunnrforSearch(String info);
	
	
	
	List<Dicts> getVersion(String dictTypeValue);
	
	List<BaseMarketCheck>getAct(String userId);
	
	List<BaseMarketCheck>getActforSearch(BaseMarketCheck bmk1);
	
	List<BaseCustomer>getRouteforSearch(BaseCustomer customer);
	
	
	List<BaseCustomer>getRouteforJXS(BaseCustomer customer);
	
	
	
	List<BaseMarketCheck> getActByDid(String marketDetailId);
	
	int getBlCount(BaseCustomer customer);
	
	int getActCount(BaseMarketCheck bmk);
	
	List<BaseCustomer> getModifier(String userId);
	
	List<BaseCustomer> getKunnr(String kunnr);
	
	
	Long insertImage(Picture p);
	
	Long insertImageInfo(Picture p);
	
	Long insertPrice(AbnormalPrice ap);
	
	Long insertDistr(Distribution distr);
	
	Long insertDisply(DisPlay disp);
	
	Long insertCus(BaseCustomer customer);
    Long insertSign(Sign sign);
    List<Sign> getSignList(Sign sign);
    List<VistCust> getVistCust(VistCust vistCust);
    
	Long insertStage(StockAge stage);
	
	boolean insertSupervise(BaseMarketCheck bmk);
	
	
	BooleanResult insertOrder(Order order);
	
	BooleanResult updateOrder(Order order);
	boolean updateSign(Sign sign);
		
	
	
	
	boolean insertCheck(BaseMarketCheck bmk);
	
	

	
	
	BooleanResult updateCustomer(BaseCustomer customr);
	
	BooleanResult updateSupervise(BaseMarketCheck bmk);
	
	List<Order> getOrderTotal(Order order);
	 List<OrderDetail> getOrderDetail(String  orderId) ;
	List<Dicts> getIndexDetail(String cloudId);
	
	Long  insertLoginLog(LoginLog loginLog);
	BooleanResult updateLoginLog(LoginLog loginLog);
	
	List<BaseCustomer> getKunnrByJL(BaseCustomer customer,String roleId);
	
	boolean insertKunnrStock(List<Stock> list);
	/**
	 * MethodsTitle: 提交保存分销量
	 * @author: xg.chen
	 * @date:2016年11月22日 上午9:16:54
	 * @version 1.0
	 * @param stock 提报数据
	 * @param type 提报标识
	 * @param roleId 提报角色
	 * @return
	 */
	BooleanResult uploadKunnrStock(Stock stock,String type,String roleId);

	
	List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate);
	List<Stock> getKunnrStock(Stock stock);
	
	/**
	 * validateUserByLDAP.
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
//	ValidateResult validateUserByLDAP(String passport, String password);

	/**
	 * validateToken.
	 * 
	 * @param token
	 * @return
	 */
	ValidateResult validateToken(String token);

	/**
	 * generateToken.
	 * 
	 * @param object
	 * @return
	 */
	String generateToken(Object object);

	/**
	 * 验证Request有效性.
	 * 
	 * @param userId
	 * @param actionName
	 * @return
	 */
	boolean validateRequest(String userId, String actionName);
	
	
	List<Kpi> getKpiNeed(String userId);

	Kpi getKpi(Kpi orgId);

	List<Kpi> getKpiByKunnrId(String kunnrId);

	List<Kpi> getParentsOrg(Kpi need);

	String getVisitActual(String userId);
	/**
	 * MethodsTitle: 返回提报后数据展示
	 * @author: xg.chen
	 * @date:2016年12月9日 上午10:01:25
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	String getSuperKunnrInfo(Stock stock);
	/**
	 * 获取经销商sku列表信息new  2015-4-17 提报人-经销商-门店-sku-最后一次提报价格
	 * @param product
	 * @return
	 */
	List<BaseProduct> getSkuLastPrice(BaseProduct product);
	/**
	 * 获取经销商打印格式 2015-4-17
	 * @param product
	 * @return
	 */
	List<OrderPrintFormat> getKunnrOrderFormat(OrderPrintFormat format);
	/**
	 * 获取历史条码
	 * @param product
	 * @return
	 */
	List<Lstm> getLstm(Lstm lstm);
	List<Lstm> findLstm(Lstm lstm);
	boolean insertLstm(Lstm lstm);
	boolean updateLstm(Lstm lstm);
	boolean deleteLstm(Lstm lstm);
	boolean deleteallLstm(Lstm lstm);
	BooleanResult uploadlstm(Lstm lstm, String status);

	List<Lstm> findallLstm(Lstm lstm);

	List<User> getUsers(String info);

	boolean resetPwd(User rstUser);

	List<CustSku> getCustSku(CustSku custsku);

	List<Order> getWeekOrderTotal(Order orderParameter);

	String getMantnr(String chuanhaosku);

	List<CuanhuoQuery> searchCuanhuo(CuanhuoQuery cuanhuoQuery);
	String getTime1();
	List<SkuUnit> getSkuUnit();
	Long uploadStockReport(StockReport stock);
	List <StockReport > getCustomerStockByCustid(String custid);
	void checkImplementStatusDetail(String miaDetailId);
}

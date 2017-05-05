package com.kintiger.xplatform.user.dao;

import java.util.List;

import com.kintiger.xplatform.api.dict.bo.Dict;
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
 * user dao.
 * 
 * @author xujiakun
 * 
 */
public interface IUserDao {

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

	List<BaseProduct> getOrderSkuByKunner(String custId);

	List<BaseProduct> getTime();

	List<Dicts> getDict();
	
	List<Dicts> getAdmDict();

	List<Dicts> getDictforAct();

	List<BaseMenu> getMenu();

	List<Dicts> getVersion(String dictTypeValue);

	List<BaseCustomer> getCus(String userId);

	List<BaseCustomer> getCusforSearch(BaseCustomer customer);
	/**
	 * MethodsTitle: 拜访管理之查找客户
	 * @author: xg.chen
	 * @date:2016年11月7日 
	 * @param customer
	 * @return
	 */
	List<BaseCustomer> getCusforSearch1(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchBycsjl(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchBykhjl(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchByywy(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchByzg(BaseCustomer customer);
	
	List<BaseCustomer> getCusforSearchBycsjl1(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchBykhjl1(BaseCustomer customer);
	List<BaseCustomer> getCusforSearchByorg(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchByywy1(BaseCustomer customer);

	List<BaseCustomer> getCusforSearchByzg1(BaseCustomer customer);

	List<BaseCustomer> getKunnrforSearch(String info);

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

	int updateCustomer(BaseCustomer customer);

	int updateSupervise(BaseMarketCheck bmk);

	int updateOrder(Order order);

	int updateOrderDetail(Order order);

	Long insertDisply(DisPlay disp);

	Long insertOrder(Order order);

	Long insertOrderDetail(OrderDetail orderDetail);

	Long insertCus(BaseCustomer customer);

	Long insertStage(StockAge stage);

	boolean insertSupervise(BaseMarketCheck bmk);

	boolean insertCheck(BaseMarketCheck bmk);

	/**
	 * 
	 * @param orgId
	 * @return
	 */
	List<User> getUsersByOrgId(Long orgId);

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
	int updateUser(User user);

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

	List<Order> getOrderTotal(Order order);

	List<OrderDetail> getOrderDetail(String orderId);

	List<Dicts> getIndexDetail(String cloudId);

	/** 登陆退出信息保存 **/
	Long insertLoginLog(LoginLog loginLog);

	int updateLoginLog(LoginLog loginLog);

	/** 客户经理获取经销商数据 */
	List<BaseCustomer> getKunnrByKHJL(BaseCustomer customer);

	/** 城市经理获取经销商数据 */
	List<BaseCustomer> getKunnrByCSJL(BaseCustomer customer);

	/** 业代获取经销商数据 */
	List<BaseCustomer> getKunnrByYD(BaseCustomer customer);

	/** 主管获取经销商数据 */
	List<BaseCustomer> getKunnrByZG(BaseCustomer customer);
	
	/** 经销商雇员获取经销商数据 */
	List<BaseCustomer> getKunnrByGY(BaseCustomer customer);

	/** 插入库存盘点 **/
	void insertKunnrStock(final List<Stock> list);

	Long updateKunnrStock(Stock stock);

	Long deleteKunnrStock(Stock stock);

	Long insertKunnrStock(Stock stock);
	Long insertKunnrStock1(Stock stock);
	Long updateKunnrStockNew(Stock stock);

	Long deleteKunnrStockNew(Stock stock);

	Long insertKunnrStockNew(Stock stock);
	
	List<Stock> getKunnrStock(Stock stock);

	List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate);

	List<Stock> getKunnrStockNew(Stock stock);
	List<Stock> getSuperKunnrStock(Stock stock);
	List<Stock> getKunnrStockNew1(Stock stock);
	List<Stock> getKunnrStockById(Stock stock);
	Long rollbackKunnerStockNew(Stock stock);
	Long rollbackSuperKunnerStock(Stock stock);
	Long coverKunnrStockNew(Stock stock);

	List<Kpi> getKpiNeed(String userId);

	Kpi getKpi(Kpi need);

	List<Kpi> getKpiByKunnrId(String kunnrId);
	
	void insertKunnrStockNew1(Stock stock);

	List<Kpi> getParentsOrg(Kpi need);

	String getVisitActual(String userId);
	
	String getSuperKunnrName(Stock stock);
	/**
	 * MethodsTitle: 获取提报会中箱数
	 * @author: xg.chen
	 * @date:2016年12月9日 上午9:50:32
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	String getSuperKunnrQuantity(Stock stock);
	
	Long coverSuperKunnrStock(Stock stock);
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

	long updateLstm(Lstm lstm);

	Long insertLstm(Lstm lstm);

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
	 Long updateCustomerStock(StockReport  stock);
	 Long createCustomerStock(StockReport stock) ;
	 int getCustomerStockCount(StockReport stock);
	 List <StockReport > getCustomerStockByCustid(String custId);
	 void checkImplementStatusDetail(String miaDetailId);
	
	 void saveLogin(User user);
	/**
	 * MethodsTitle: 获取提报的周库存
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:00:52
	 * @version 1.0
	 * @param stock
	 * @return
	 */
	List<Stock> getKunnrStockNew4Week(Stock stock);
	/**
	 * MethodsTitle: 获取top200经销商
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:00:27
	 * @version 1.0
	 * @param kunnr
	 * @return
	 */
	List<Kunnr> getKunnrForTop200(Kunnr kunnr);
	/**
	 * MethodsTitle: 根据userId获取经销商的KunnrId
	 * @author: xg.chen
	 * @date:2016年12月8日 下午3:19:42
	 * @version 1.0
	 * @param userId
	 * @return
	 */
	Kunnr getKunnrIdForUserId(String userId);

}

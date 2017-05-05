package com.kintiger.xplatform.user.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
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
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.user.dao.IUserDao;

/**
 * user dao.
 * 
 * @author allen.yue
 * 
 */
public class UserDaoImpl extends BaseDaoImpl implements IUserDao {
	@SuppressWarnings("unchecked")
	public List<User> getUserByMobile(String mobile) {
		return getSqlMapClientTemplate().queryForList("user.getUserByMobile", mobile);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByCode(String mobile) {
		return getSqlMapClientTemplate().queryForList("user.getUserByCode", mobile);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseProduct> getOrderSkuByKunner(String cloudId) {
		return getSqlMapClientTemplate().queryForList("user.getOrderSkuByKunner", cloudId);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<BaseProduct> getSku() {
		return getSqlMapClientTemplate().queryForList("user.getSku");
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getChannel() {
		return getSqlMapClientTemplate().queryForList("user.getChannel");
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getDivision() {
		return getSqlMapClientTemplate().queryForList("user.getDivision");
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getDivisionSearch(String zwl04t) {
		return getSqlMapClientTemplate().queryForList("user.getDivisionSearch",zwl04t);
	}

	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getDivisionforSearch(String zwl04) {
		return getSqlMapClientTemplate().queryForList("user.getDivisionforSearch",zwl04);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseProduct> SkuByCloud(String cloudId) {
		return getSqlMapClientTemplate().queryForList("user.getSku",cloudId);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<BaseProduct> getTime() {
		return getSqlMapClientTemplate().queryForList("user.getTime");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Dicts> getDict() {
		return getSqlMapClientTemplate().queryForList("user.getDict");
	}
	@SuppressWarnings("unchecked")
	public List<Dicts> getAdmDict() {
		return getSqlMapClientTemplate().queryForList("user.getAdmDict");
	}
	@SuppressWarnings("unchecked")
	public List<Dicts> getDictforAct() {
		return getSqlMapClientTemplate().queryForList("user.getDictforAct");
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<BaseMenu> getMenu() {
		return getSqlMapClientTemplate().queryForList("user.getMenu");
	}
	@SuppressWarnings("unchecked")
	public List<Dicts> getVersion(String dictTypeValue) {
		return getSqlMapClientTemplate().queryForList("user.getVersion",dictTypeValue);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCus(String userId) {
		return getSqlMapClientTemplate().queryForList("user.getCus",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearch( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearch",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearch1( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearch1",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchBycsjl( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchBycsjl",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchBycsjl1( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchBycsjl1",customer);
	}

	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchBykhjl(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchBykhjl",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchBykhjl1(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchBykhjl1",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchByorg(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchByorg",customer);
	}

	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchByywy(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchByywy",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchByywy1(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchByywy1",customer);
	}

	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchByzg(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchByzg",customer);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getCusforSearchByzg1(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getCusforSearchByzg1",customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnrforSearch(String info) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrforSearch",info);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BaseMarketCheck> getAct(String userId) {
		return getSqlMapClientTemplate().queryForList("user.getAct",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseMarketCheck> getActforSearch(BaseMarketCheck bmk1) {
		return getSqlMapClientTemplate().queryForList("user.getActforSearch",bmk1);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getRouteforSearch(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getRouteforSearch",customer);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getRouteforJXS(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getRouteforJXS",customer);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<BaseMarketCheck> getActByDid(String marketDetailId) {
		return getSqlMapClientTemplate().queryForList("user.getActByDid",marketDetailId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getModifier(String userId) {
		return getSqlMapClientTemplate().queryForList("user.getModifier",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnr(String kunnr) {
		return getSqlMapClientTemplate().queryForList("user.getKunnr",kunnr);
	}
	
	
	
	
	public int getBlCount(BaseCustomer customer) {
		return (Integer) getSqlMapClientTemplate().queryForObject("user.getBlCount", customer);
	}
	
	public int getActCount(BaseMarketCheck bmk) {
		return (Integer) getSqlMapClientTemplate().queryForObject("user.getActCount", bmk);
	}
	
	
	
	public Long insertImage(Picture p) {
	  try {return  (Long) getSqlMapClientTemplate().insert(
					"user.insertPhoto", p);	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Long insertPrice(AbnormalPrice ap) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertPrice", ap);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	public Long insertDistr(Distribution distr) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertDistr", distr);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	
	public int updateCustomer(BaseCustomer customer) {
		return getSqlMapClientTemplate().update("user.updateCustomer", customer);
	}
	
	public int updateSupervise(BaseMarketCheck bmk) {
		return getSqlMapClientTemplate().update("user.updateSupervise", bmk);
	}
	
	public int updateOrder(Order order) {
		return getSqlMapClientTemplate().update("user.updateOrder", order);
	}
	public int updateOrderDetail(Order order) {
		return getSqlMapClientTemplate().update("user.updateOrderDetail", order);
	}
	
	public Long insertDisply(DisPlay disp) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertDisply", disp);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	public Long insertOrder(Order order) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertOrder", order);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	public Long insertOrderDetail(OrderDetail orderDetail) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertOrderDetail", orderDetail);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	public Long insertCus(BaseCustomer customer) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertCus", customer);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	
	
	public Long insertStage(StockAge stage) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertStage", stage);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	public boolean insertSupervise(BaseMarketCheck bmk){
		  try {   getSqlMapClientTemplate().insert(
					"user.insertSupervise", bmk);	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;	
	}
	
	public boolean insertCheck(BaseMarketCheck bmk){
		  try {   getSqlMapClientTemplate().insert(
					"user.insertCheck", bmk);	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;	
	}
	
	
	

	public Long insertImagePinfo(Picture p) {
		  try {return  (Long) getSqlMapClientTemplate().insert(
						"user.insertImagePinfo", p);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	
	

	@SuppressWarnings("unchecked")
	public List<User> getUsersByOrgId(Long orgId) {
		return getSqlMapClientTemplate().queryForList("user.getUsersByOrgId", orgId);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByIds(List<String> userIds) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("userIds", userIds);

		return getSqlMapClientTemplate().queryForList("user.getUsersByIds", map);
	}

	public int updateUser(User user) {
		return getSqlMapClientTemplate().update("user.updateUser", user);
	}

	public User getUser(String userId) {
		return (User) getSqlMapClientTemplate().queryForObject("user.getUser", userId);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList(User user) {
		return getSqlMapClientTemplate().queryForList("user.getUserList", user);
	}

	public int getUserCount(User user) {
		return (Integer) getSqlMapClientTemplate().queryForObject("user.getUserCount", user);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrderTotal(Order order) {
		return getSqlMapClientTemplate().queryForList("user.getOrderTotal", order);
	}

	@SuppressWarnings("unchecked")
	public List<OrderDetail> getOrderDetail(String  orderId) {
		return getSqlMapClientTemplate().queryForList("user.getOrderDetail", orderId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Dicts> getIndexDetail(String cloudId) {
		return getSqlMapClientTemplate().queryForList("user.getIndexDetail", cloudId);
	}


	public Long insertLoginLog(LoginLog loginLog) {
		try {
			return (Long) getSqlMapClientTemplate().insert("user.insertLoginLog", loginLog);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateLoginLog(LoginLog  loginLog) {
		return getSqlMapClientTemplate().update("user.updateLoginLog", loginLog);
	}
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnrByKHJL(BaseCustomer customer) {
			return getSqlMapClientTemplate().queryForList("user.getKunnrByKHJL", customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnrByCSJL( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrByCSJL", customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnrByYD( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrByYD", customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<BaseCustomer> getKunnrByZG( BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrByZG", customer);
	}
	
	public void insertKunnrStock(final List<Stock> list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				for (Stock stock : list) {
					executor.insert("user.insertKunnrStock", stock);
				}
				executor.executeBatch();
				return null;
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	public List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrStockDaye", kunnrStockDate);
	}

	@Override
	public Long updateKunnrStock(Stock stock) {
		return (long) getSqlMapClientTemplate().update("user.updateKunnrStock", stock);
	}

	public Long deleteKunnrStock(Stock stock) {
		return (long) getSqlMapClientTemplate().update("user.deleteKunnrStock", stock);
	}

	@Override
	public Long insertKunnrStock(Stock stock) {
		return  (Long) getSqlMapClientTemplate().insert("user.insertKunnrStock", stock);	
	}

	@Override
	public Long insertKunnrStock1(Stock stock) {
		return  (Long) getSqlMapClientTemplate().insert("user.insertKunnrStock1", stock);	
	}
	
	@Override
	public Long updateKunnrStockNew(Stock stock) {
		return (long) getSqlMapClientTemplate().update("user.updateKunnrStockNew", stock);
	}

	public Long deleteKunnrStockNew(Stock stock) {
		return (long) getSqlMapClientTemplate().update("user.deleteKunnrStockNew", stock);
	}

	@Override
	public Long insertKunnrStockNew(Stock stock) {
		return  (Long) getSqlMapClientTemplate().insert("user.insertKunnrStockNew", stock);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Stock> getKunnrStock(Stock stock) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrStock", stock);
	}

	@SuppressWarnings("unchecked")
	public List<Stock> getKunnrStockById(Stock stock) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrStockById", stock);
	}
	/**
	 * 查询库存督导是否已提报
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getKunnrStockNew(Stock stock) {
		
		return getSqlMapClientTemplate().queryForList("user.getKunnrStockNew", stock);
	}

	/**
	 * 查询主分库存中督导是否已提报
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getSuperKunnrStock(Stock stock) {
		
		return getSqlMapClientTemplate().queryForList("user.getSuperKunnrStock", stock);
	}
	
	/**
	 * 查询库存是否重复提交
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getKunnrStockNew1(Stock stock) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrStockNew1", stock);
	}
	/**
	 * 查询周库存是否重复提交
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getKunnrStockNew4Week(Stock stock) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrStockNew4Week", stock);
	}
	@Override
	public Long rollbackKunnerStockNew(Stock stock) {
		
		return  (long) getSqlMapClientTemplate().update("user.rollbackKunnerStockNew", stock);
	}
//恢复主分库中被覆盖的业务数据
	@Override
	public Long rollbackSuperKunnerStock(Stock stock) {
		
		return  (long) getSqlMapClientTemplate().update("user.rollbackSuperKunnerStock", stock);
	}
	
	@Override
	public Long coverKunnrStockNew(Stock stock) {
		return  (long) getSqlMapClientTemplate().update("user.coverKunnrStockNew", stock);
		
	}

	@Override
	public Long coverSuperKunnrStock(Stock stock) {
		return  (long) getSqlMapClientTemplate().update("user.coverSuperKunnrStock", stock);
		
	}
	
	@Override
	public void insertKunnrStockNew1(Stock stock) {
		getSqlMapClientTemplate().insert("user.insertKunnrStockNew1", stock);	
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Kpi> getKpiNeed(String userId) {
		return getSqlMapClientTemplate().queryForList("user.getKpiNeed", userId);
	}

	@Override
	public Kpi getKpi(Kpi orgId) {
		return (Kpi)getSqlMapClientTemplate().queryForObject("user.getKpi", orgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kpi> getKpiByKunnrId(String kunnrId) {
		return getSqlMapClientTemplate().queryForList("user.getKpiByKunnrId", kunnrId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kpi> getParentsOrg(Kpi need) {
		return getSqlMapClientTemplate().queryForList("user.getParentsOrg", need);
	}

	@Override
	public String getVisitActual(String userId) {
		return (String)getSqlMapClientTemplate().queryForObject("user.getVisitActual", userId);
	}

	@Override
	public String getSuperKunnrName(Stock stock) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("user.getSuperKunnrName", stock);
	}

	@Override
	public String getSuperKunnrQuantity(Stock stock) {
		return (String)getSqlMapClientTemplate().queryForObject("user.getSuperKunnrQuantity", stock);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseProduct> getSkuLastPrice(BaseProduct product) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getSkuLastPrice",product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPrintFormat> getKunnrOrderFormat(OrderPrintFormat format) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getKunnrOrderFormat",format);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Lstm> getLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getLstm",lstm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lstm> findLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.findLstm",lstm);
	}

	@Override
	public long updateLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update("user.updateLstm", lstm);
	}

	@Override
	public Long insertLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return  (Long) getSqlMapClientTemplate().insert("user.insertLstm", lstm);	
	}

	@Override
	public Long deleteLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update("user.deleteLstm", lstm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lstm> findallLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.findallLstm",lstm);
	}

	@Override
	public Long deleteallLstm(Lstm lstm) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update("user.deleteallLstm", lstm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String info) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getusers",info);
	}

	@Override
	public Long resetPwd(User rstUser) {
		// TODO Auto-generated method stub
		 return (long) getSqlMapClientTemplate().update("user.resetPwd", rstUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustSku> findallCustSku(CustSku custsku) {
		// TODO Auto-generated method stub
		 return getSqlMapClientTemplate().queryForList("user.findallCustSku",custsku);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getWeekOrderTotal(Order order) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getWeekOrderTotal", order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseProduct> getMantnr(String chuanhaosku) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getMantnr",chuanhaosku);
	}

	@Override
	public Long insertSign(Sign sign) {
		// TODO Auto-generated method stub
		return  (Long) getSqlMapClientTemplate().insert("user.insertSign",sign);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sign> getSignList(Sign sign) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getSignList",sign);
	}

	@Override
	public Long updateSign(Sign sign) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update("user.updateSign", sign);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VistCust> getVistCust(VistCust vistCust) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getVistCust",vistCust);
	}

	@Override
	public String getTime1() {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("user.getTime1");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SkuUnit> getSkuUnit() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("user.getSkuUnit");
	}
	@Override
	public Long updateCustomerStock(StockReport  stock) {
		return (long) getSqlMapClientTemplate().update("user.updateCustomerStock", stock);
	}
	@Override
	public Long createCustomerStock(StockReport stock) {
		return  (Long) getSqlMapClientTemplate().insert("user.createCustomerStock", stock);	
	}
	@Override
	public int getCustomerStockCount(StockReport stock) {
		return (Integer) getSqlMapClientTemplate().queryForObject("user.getCustomerStockCount",stock);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<StockReport> getCustomerStockByCustid(String custId) {
		return getSqlMapClientTemplate().queryForList("user.getCustomerStockByCustid",custId);
	}
	/**
	 * 检验活动Detail的实施状态
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void checkImplementStatusDetail(String miaDetailId) {
		Map<String, Object> resultCount = (Map<String, Object>) getSqlMapClientTemplate()
				.queryForObject("user.selectCountOfItemList", miaDetailId);
		resultCount.put("MIADETAILID", miaDetailId);
		if (resultCount.get("COUNT1").equals(resultCount.get("COUNT3"))) {
			// 实施完成了，更改默认核销总金额
			getSqlMapClientTemplate().update("user.modifyPlanCheckMoney",
					miaDetailId);
		}
		getSqlMapClientTemplate().update(
				"user.modifyImplementStatusDetail", resultCount);
	}

	@Override
	public List<BaseCustomer> getKunnrByGY(BaseCustomer customer) {
		return getSqlMapClientTemplate().queryForList("user.getKunnrByGY", customer);
	}

	@Override
	public void saveLogin(User user) {
		getSqlMapClientTemplate().insert("user.saveLogin", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kunnr> getKunnrForTop200(Kunnr kunnr) {
		try {
			return getSqlMapClientTemplate().queryForList("user.getKunnrForTop200", kunnr);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Kunnr getKunnrIdForUserId(String userId) {
		try {
			return (Kunnr) getSqlMapClientTemplate().queryForObject("user.getKunnrIdForUserId",userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}


}

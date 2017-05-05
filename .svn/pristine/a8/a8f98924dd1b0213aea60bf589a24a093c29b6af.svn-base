package com.kintiger.xplatform.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.openapi.IOpenapiService;
import com.kintiger.xplatform.api.user.IUserService;
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
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.user.dao.IUserDao;

/**
 * user service.
 * 
 * @author xujiakun
 * 
 */
public class UserServiceImpl implements IUserService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(UserServiceImpl.class);

	private IUserDao userDao;

	private TransactionTemplate transactionTemplate;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public List<User> getUserByMobile(String mobile) {
		if (StringUtil.isEmpty(mobile)) {
			return null;
		}
		try {
			return userDao.getUserByMobile(mobile);
		} catch (Exception e) {
			logger.error(mobile, e);
		}
		return null;
	}

	public List<User> getUserByCode(String mobile) {
		if (StringUtil.isEmpty(mobile)) {
			return null;
		}
		try {
			return userDao.getUserByCode(mobile);
		} catch (Exception e) {
			logger.error(mobile, e);
		}
		return null;
	}

	public List<BaseProduct> getSku() {
		try {
			return userDao.getSku();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseCustomer> getChannel() {
		try {
			return userDao.getChannel();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseCustomer> getDivision() {
		try {
			return userDao.getDivision();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseCustomer> getDivisionSearch(String zwl04t) {
		try {
			return userDao.getDivisionSearch(zwl04t);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public void saveLogin(User user) {
		userDao.saveLogin(user);
	}

	public List<BaseCustomer> getDivisionforSearch(String zwl04) {
		try {
			return userDao.getDivisionforSearch(zwl04);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseProduct> SkuByCloud(String cloudId) {
		try {
			return userDao.SkuByCloud(cloudId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseProduct> getOrderSkuByKunner(String cloudId) {
		try {
			return userDao.getOrderSkuByKunner(cloudId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseProduct> getTime() {
		try {
			return userDao.getTime();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<Dicts> getDict() {
		try {
			return userDao.getDict();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}
	public List<Dicts> getAdmDict() {
		try {
			return userDao.getAdmDict();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}
	public List<Dicts> getDictforAct() {
		try {
			return userDao.getDictforAct();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public List<BaseMenu> getMenu() {
		try {
			return userDao.getMenu();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public List<Dicts> getVersion(String dictTypeValue) {
		try {
			return userDao.getVersion(dictTypeValue);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BaseCustomer> getCus(String userId) {
		try {
			return userDao.getCus(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;

	}

	public List<BaseCustomer> getCusforSearch(BaseCustomer customer,
			String roleId) {
		try {
			if (IOpenapiService.MOBILE_CSJL.equals(roleId)) {
				return userDao.getCusforSearchBycsjl(customer);
			} else if (IOpenapiService.MOBILE_KHJL.equals(roleId)) {
				return userDao.getCusforSearchBykhjl(customer);
			} else if (IOpenapiService.MOBILE_YWY.equals(roleId)) {
				return userDao.getCusforSearchByywy(customer);
			} else if (IOpenapiService.MOBILE_ZG.equals(roleId)) {
				return userDao.getCusforSearchByzg(customer);
			}

			return userDao.getCusforSearch(customer);
		} catch (Exception e) {
			logger.error(customer, e);
		}

		return null;

	}
	/**
	 * 拜访管理之查找客户
	 */
	public List<BaseCustomer> getCusforSearch1(BaseCustomer customer,
			String roleId) {
		try {
			if (IOpenapiService.MOBILE_ORG.equals(roleId)) {
				System.out.println("按组织查询门店");
				return userDao.getCusforSearchByorg(customer);
			}else if (IOpenapiService.MOBILE_CSJL.equals(roleId)) {
				System.out.println("城市经理");
				return userDao.getCusforSearchBycsjl1(customer);
			} else if (IOpenapiService.MOBILE_KHJL.equals(roleId)) {
				System.out.println("客户经理");
				return userDao.getCusforSearchBykhjl1(customer);
			} else if (IOpenapiService.MOBILE_YWY.equals(roleId)) {
				System.out.println("业务员");
				return userDao.getCusforSearchByywy1(customer);
			} else if (IOpenapiService.MOBILE_ZG.equals(roleId)) {
				System.out.println("主管");
				return userDao.getCusforSearchByzg1(customer);
			} else if (IOpenapiService.MOBILE_DD.equals(roleId)) {
				System.out.println("督导"+roleId);
				return userDao.getCusforSearch1(customer);
			}else if (IOpenapiService.MOBILE_ALL.equals(roleId)) {
				System.out.println("全国"+roleId);
				return userDao.getCusforSearch1(customer);
			}
			return userDao.getCusforSearch1(customer);
		} catch (Exception e) {
			logger.error(customer, e);
		}
		return null;
	}
	public List<BaseCustomer> getCusforSearchXpp(BaseCustomer customer,
			String roleId) {
		try {
			if (IOpenapiService.MOBILE_ORG.equals(roleId)) {
				System.out.println("Xpp按组织查询门店");
				return userDao.getCusforSearchByorg(customer);
			}else if (IOpenapiService.MOBILE_CSJL.equals(roleId)) {
				System.out.println("Xpp城市经理");
				return userDao.getCusforSearchBycsjl1(customer);
			} else if (IOpenapiService.MOBILE_KHJL.equals(roleId)) {
				System.out.println("Xpp客户经理");
				return userDao.getCusforSearchBykhjl1(customer);
			} else if (IOpenapiService.MOBILE_YWY.equals(roleId)) {
				System.out.println("Xpp业务员");
				return userDao.getCusforSearchByywy1(customer);
			} else if (IOpenapiService.MOBILE_ZG.equals(roleId)) {
				System.out.println("Xpp主管");
				return userDao.getCusforSearchByzg1(customer);
			}
			 else if (IOpenapiService.MOBILE_DD.equals(roleId)) {
					System.out.println("Xpp督导"+roleId);
					return userDao.getCusforSearch1(customer);
				}
			 else if (IOpenapiService.MOBILE_ALL.equals(roleId)) {
					System.out.println("Xpp全国"+roleId);
					return userDao.getCusforSearch1(customer);
				}
		} catch (Exception e) {
			logger.error(customer, e);
		}
		return null;
	}

	public List<BaseCustomer> getKunnrforSearch(String info) {
		try {
			return userDao.getKunnrforSearch(info);
		} catch (Exception e) {
			logger.error(info, e);
		}

		return null;

	}

	public List<BaseMarketCheck> getAct(String userId) {
		try {
			return userDao.getAct(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;

	}

	public List<BaseMarketCheck> getActforSearch(BaseMarketCheck bmk1) {
		try {
			return userDao.getActforSearch(bmk1);
		} catch (Exception e) {
			logger.error(bmk1, e);
		}

		return null;

	}

	public List<BaseCustomer> getRouteforSearch(BaseCustomer customer) {
		try {
			return userDao.getRouteforSearch(customer);
		} catch (Exception e) {
			logger.error(customer, e);
		}

		return null;

	}

	public List<BaseCustomer> getRouteforJXS(BaseCustomer customer) {
		try {
			return userDao.getRouteforJXS(customer);
		} catch (Exception e) {
			logger.error(customer, e);
		}

		return null;

	}

	public List<BaseMarketCheck> getActByDid(String marketDetailId) {
		try {
			return userDao.getActByDid(marketDetailId);
		} catch (Exception e) {
			logger.error(marketDetailId, e);
		}

		return null;

	}

	public List<BaseCustomer> getModifier(String userId) {
		try {
			return userDao.getModifier(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;

	}

	public List<BaseCustomer> getKunnr(String kunnr) {
		try {
			return userDao.getKunnr(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
		}

		return null;

	}

	public int getBlCount(BaseCustomer customer) {
		try {
			return userDao.getBlCount(customer);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customer), e);
		}

		return 0;
	}

	public int getActCount(BaseMarketCheck bmk) {
		try {
			return userDao.getActCount(bmk);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bmk), e);
		}

		return 0;
	}

	public Long insertImage(Picture p) {
		try {
			return userDao.insertImage(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertPrice(AbnormalPrice ap) {
		try {
			return userDao.insertPrice(ap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertDistr(Distribution distr) {
		try {
			return userDao.insertDistr(distr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BooleanResult updateCustomer(BaseCustomer customer) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = userDao.updateCustomer(customer);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customer), e);
		}

		return result;
	}

	public BooleanResult updateSupervise(BaseMarketCheck bmk) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = userDao.updateSupervise(bmk);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bmk), e);
		}

		return result;
	}

	public BooleanResult insertOrder(final Order order) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);
		boolean o = (Boolean) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						// 插入订单
						try {
							List<OrderDetail> orderds = order.getOrderDetail();
							String flag = "Y";
							Long i = (long) userDao.insertOrder(order);
							if (i != null) {
								if (orderds != null && orderds.size() != 0) {
									for (OrderDetail ods : orderds) {
										ods.setOrderId(String.valueOf(i));
										if (userDao.insertOrderDetail(ods) == null) {
											flag = "N";
										}
									}
								}
							} else {
								ts.setRollbackOnly();
								return false;
							}
							if ("Y".equals(flag)) {
								result.setCode(String.valueOf(i));
								return true;
							} else {
								ts.setRollbackOnly();
								return false;
							}
						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();
							return false;
						}
					}
				});
		if (o) {
			result.setResult(true);
		} else {
			result.setResult(false);
		}

		return result;
	}

	public BooleanResult updateOrder(final Order order) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);
		boolean o = (Boolean) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						// 插入订单
						try {
							List<OrderDetail> orderds = order.getOrderDetail();
							String flag = "Y";
							Long i = (long) userDao.updateOrder(order);
							if (i != null) {
								Long s = (long) userDao
										.updateOrderDetail(order);
								if (orderds != null && orderds.size() != 0
										&& s != null) {
									for (OrderDetail ods : orderds) {
										ods.setOrderId(order.getOrderId());
										if (userDao.insertOrderDetail(ods) == null) {
											flag = "N";
										}
									}
								}
							} else {
								ts.setRollbackOnly();
								return false;
							}
							if ("Y".equals(flag)) {
								result.setCode(order.getOrderId());
								return true;
							} else {
								ts.setRollbackOnly();
								return false;
							}
						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();
							return false;
						}
					}
				});
		if (o) {
			result.setResult(true);
		} else {
			result.setResult(false);
		}

		return result;
	}

	public Long insertDisply(DisPlay disp) {
		try {
			return userDao.insertDisply(disp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertCus(BaseCustomer customer) {
		try {
			return userDao.insertCus(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long insertStage(StockAge stage) {
		try {
			return userDao.insertStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertSupervise(BaseMarketCheck bmk) {
		try {
			userDao.insertSupervise(bmk);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertCheck(BaseMarketCheck bmk) {
		try {
			userDao.insertCheck(bmk);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Long insertImagePinfo(Picture p) {
		try {
			return userDao.insertImagePinfo(p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getUsersByOrgId(String orgId) {
		if (StringUtil.isEmpty(orgId)) {
			return null;
		}
		try {
			return userDao.getUsersByOrgId(Long.valueOf(orgId));
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	public List<User> getUsersByIds(List<String> userIds) {
		try {
			return userDao.getUsersByIds(userIds);
		} catch (Exception e) {
			logger.error(userIds, e);
		}

		return null;
	}

	public List<Dicts> getIndexDetail(String cloudId) {
		try {
			return userDao.getIndexDetail(cloudId);
		} catch (Exception e) {
			logger.error(cloudId, e);
		}
		return null;
	}

	public BooleanResult updateUser(User user) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = userDao.updateUser(user);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return result;
	}

	public User getUser(String userId) {
		try {
			return userDao.getUser(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	public List<User> getUserList(User user) {
		try {
			return userDao.getUserList(user);
		} catch (Exception e) {
			logger.error(user, e);
		}

		return null;
	}

	public int getUserCount(User user) {
		try {
			return userDao.getUserCount(user);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}

		return 0;
	}

	public List<Order> getOrderTotal(Order order) {
		try {
			return userDao.getOrderTotal(order);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}
		return null;
	}

	public List<OrderDetail> getOrderDetail(String orderId) {
		try {
			return userDao.getOrderDetail(orderId);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(orderId), e);
		}
		return null;
	}

	public Long insertLoginLog(LoginLog loginLog) {
		try {
			return userDao.insertLoginLog(loginLog);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BooleanResult updateLoginLog(LoginLog loginLog) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		try {
			int c = userDao.updateLoginLog(loginLog);
			if (c == 1) {
				result.setResult(true);
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(loginLog), e);
		}

		return result;
	}
	/**
	 * 根据角色获取经销商
	 */
	public List<BaseCustomer> getKunnrByJL(BaseCustomer customer, String roleId) {
		try {
			if (IOpenapiService.MOBILE_KHJL.equals(roleId)) {
				return userDao.getKunnrByKHJL(customer);
			}
			if (IOpenapiService.MOBILE_CSJL.equals(roleId)) {
				return userDao.getKunnrByCSJL(customer);
			}
			if (IOpenapiService.MOBILE_YWY.equals(roleId)) {
				return userDao.getKunnrByYD(customer);
			}
			if ("jxs_role".equals(roleId)) {
				return userDao.getKunnrByGY(customer);
			}
			return userDao.getKunnrByZG(customer);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customer), e);
		}
		return null;
	}

	public boolean insertKunnrStock(List<Stock> list) {
		try {
			userDao.insertKunnrStock(list);
			return true;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(list), e);
		}
		return false;
	}

	@Override
	public List<KunnrStockDate> getKunnrStockDate(KunnrStockDate kunnrStockDate) {
		try {
			return userDao.getKunnrStockDate(kunnrStockDate);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnrStockDate), e);
		}
		return null;
	}

	@Override
	public Long deleteKunnrStock(Stock stock) {
		try {
			return userDao.deleteKunnrStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long updateKunnrStock(Stock stock) {
		try {
			return userDao.updateKunnrStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long insertKunnrStock(Stock stock) {
		try {
			return userDao.insertKunnrStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stock> getKunnrStock(Stock stock) {
		try {
			return userDao.getKunnrStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stock> getKunnrStockNew(Stock stock) {
		try {
			return userDao.getKunnrStockNew(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stock> getKunnrStockNew1(Stock stock) {
		try {
			return userDao.getKunnrStockNew1(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Stock> getKunnrStockNew4Week(Stock stock) {
		try {
			return userDao.getKunnrStockNew4Week(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public BooleanResult insertKunnrStockNew(final Stock stock) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);
		boolean o = (Boolean) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						// 插入库存
						Long i =0l;
						try {
							//if(checkSuperDD(stock)){ //在旧表中插入F的数据
								//i = userDao.insertKunnrStock1(stock);
								
							//}else{
								i = userDao.insertKunnrStock(stock);
							//}
							if ("D".equals(stock.getUserType())) {
								//userDao.coverKunnrStockNew(stock);
								userDao.coverSuperKunnrStock(stock);
							}
							stock.setId(i);
							//if (checkPermit(stock)) {//
							if (checkSuperDD(stock)) {	//判断能否写入正常数据到新表
							userDao.insertKunnrStockNew(stock);

							} else {// 在新表中插入F的数据
								userDao.insertKunnrStockNew1(stock);
							}
							if (i != 0) {
								result.setCode(i + "");
								return true;
							}

						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();
						}
						return false;
					}
				});
		if (o) {
			result.setResult(true);
		} else {
			result.setCode("上传失败");
			result.setResult(false);
		}

		return result;
	}

	protected boolean checkSuperDD(Stock stock) {
		if("D".equals(stock.getUserType())){
			return true;
		}
		List<Stock> list=userDao.getSuperKunnrStock(stock);
		if(list==null||list.size()==0){
			return true;
		}
		return false;
	}

	public BooleanResult deleteKunnrStockNew(final Stock stock) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);

		boolean o = (Boolean) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						// 删除库存

						try {
							
							Long i = userDao.deleteKunnrStock(stock);
//							if(!checkSuperDD(stock)){ //主分库中已不存在督导信息
//								//主分库中所有被覆盖的数据还原
//								userDao.coverSuperKunnrStock(stock);
//							}
							
							List<Stock> s = userDao.getKunnrStockById(stock);
							
							if (s != null && s.size() > 0) {
								userDao.deleteKunnrStockNew(stock);
								
								if ("D".equals(stock.getUserType())) {// 督导删除
									List<Stock> list = userDao
											.getSuperKunnrStock(stock);
									if (list == null || list.size() == 0) {
										//userDao.rollbackKunnerStockNew(stock);
										userDao.rollbackSuperKunnerStock(stock);
									}
								}

							}

							if (i != 0) {
								result.setCode(i + "");
								return true;
							}

						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();

						}
						return false;
					}
				});

		if (o) {
			result.setResult(true);
		} else {
			result.setCode("删除失败");
			result.setResult(false);
		}

		return result;
	}

	public BooleanResult updateKunnrStockNew(final Stock stock) {
		final BooleanResult result = new BooleanResult();
		result.setResult(false);
		boolean o = (Boolean) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						// 更新库存
						try {
							Long i = userDao.updateKunnrStock(stock);
							List<Stock> s = userDao.getKunnrStockById(stock);

							if (s != null && s.size() > 0) {

								userDao.updateKunnrStockNew(stock);
							}

							if (i != 0) {
								result.setCode(i + "");
								return true;
							}
						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();

						}

						return false;
					}
				});
		if (o) {
			result.setResult(true);
		} else {
			result.setCode("上传失败");
			result.setResult(false);
		}

		return result;
	}

	@Override
	public List<Kpi> getKpiNeed(String userId) {
		try {
			return userDao.getKpiNeed(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	@Override
	public Kpi getKpi(Kpi orgId) {
		try {
			return userDao.getKpi(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}

	@Override
	public List<Kpi> getKpiByKunnrId(String kunnrId) {
		try {
			return userDao.getKpiByKunnrId(kunnrId);
		} catch (Exception e) {
			logger.error(kunnrId, e);
		}

		return null;
	}
	
	private boolean checkPermit(Stock stock) {

		if ("D".equals(stock.getUserType())) {
			return true;
		}
		List<Stock> list = getKunnrStockNew(stock);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Kpi> getParentsOrg(Kpi need) {
		try {
			return userDao.getParentsOrg(need);
		} catch (Exception e) {
			logger.error(need, e);
		}

		return null;
	}

	@Override
	public String getVisitActual(String userId) {
		try {
			return userDao.getVisitActual(userId);
		} catch (Exception e) {
			logger.error(userId, e);
		}

		return null;
	}

	@Override
	public String getSuperKunnrInfo(Stock stock) {
		//获取提报箱数
		String quantity=userDao.getSuperKunnrQuantity(stock);
		//获取提报品类名称
		String name=userDao.getSuperKunnrName(stock);
		
		return name+","+quantity;
	}

	@Override
	public List<BaseProduct> getSkuLastPrice(BaseProduct product) {
		try {
			return userDao.getSkuLastPrice(product);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<OrderPrintFormat> getKunnrOrderFormat(OrderPrintFormat format) {
		try {
			return userDao.getKunnrOrderFormat(format);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Lstm> getLstm(Lstm lstm) {
		try {
			return userDao.getLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Lstm> findLstm(Lstm lstm) {
		try {
			return userDao.findLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long insertLstm(Lstm lstm) {
		try {
			return userDao.insertLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long updateLstm(Lstm lstm) {
		try {
			return userDao.updateLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long deleteLstm(Lstm lstm) {
		try {
			return userDao.deleteLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Lstm> findallLstm(Lstm lstm) {
		try {
			return userDao.findallLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long deleteallLstm(Lstm lstm) {
		try {
			return userDao.deleteallLstm(lstm);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<User> getUsers(String info) {
		try {
			return userDao.getUsers(info);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long resetPwd(User rstUser) {
		try {
			return userDao.resetPwd(rstUser);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<CustSku> findallCustSku(CustSku custsku) {
		try {
			return userDao. findallCustSku(custsku);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Order> getWeekOrderTotal(Order order) {
		try {
			return userDao.getWeekOrderTotal(order);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(order), e);
		}
		return null;
	}

	@Override
	public List<BaseProduct> getMantnr(String chuanhaosku) {
		try {
			return userDao.getMantnr(chuanhaosku);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(chuanhaosku), e);
		}
		return null;
	}

	@Override
	public Long insertSign(Sign sign) {
		try {
			return userDao.insertSign(sign);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Sign> getSignList(Sign sign) {
		try {
			return userDao.getSignList(sign);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(sign), e);
		}
		return null;
	}

	@Override
	public Long updateSign(Sign sign) {
		try {
			return userDao.updateSign(sign);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<BaseCustomer> getCusforSearchSet(BaseCustomer customer,
			String roleId) {
		try {
			return userDao.getCusforSearch1(customer);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<VistCust> getVistCust(VistCust vistCust) {
		try {
			return userDao.getVistCust(vistCust);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public String getTime1() {
		try {
			return userDao.getTime1();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<SkuUnit> getSkuUnit() {
		try {
			return userDao.getSkuUnit();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long uploadStockReport(StockReport stock) {
		try {
			if (userDao.getCustomerStockCount(stock)>0) {
				return userDao.updateCustomerStock(stock);
			} else {
				return userDao.createCustomerStock(stock); 
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<StockReport> getCustomerStockByCustid(String custId) {
		try {
			return userDao.getCustomerStockByCustid(custId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public void checkImplementStatusDetail(String miaDetailId) {
		try {
			 userDao.checkImplementStatusDetail(miaDetailId);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	public List<Kunnr> getKunnrForTop200(Kunnr kunnr) {
		try{
			return userDao.getKunnrForTop200(kunnr);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Kunnr getKunnrIdForUserId(String userId) {
		try {
			return userDao.getKunnrIdForUserId(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	

}

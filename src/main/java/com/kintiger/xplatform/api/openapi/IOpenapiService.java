package com.kintiger.xplatform.api.openapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.kintiger.xplatform.api.user.bo.StockReport;

/**
 * Title:  app客户端服务接口
 * Description: mobilePlatform
 * @author: xg.chen
 * @date:2016年12月2日 上午11:03:57
 */
public interface IOpenapiService {

	String XPLATFORM_USER_LOGIN = "xplatform.user.login";

	String ERROR = "-1";
	
	String SUCCESS = "success";
	
	String FAIL = "fail";
	String TIMEOUT = "timeout";
	String ERROR_MSG_PARAMS = "The params can not be empty.";

	String ERROR_MSG_METHOD = "The method can not be empty.";
	
	String COMPANYCODE= "0000000000";
	String ADMMOBILEVERSION="admmobileVersion";
	String MOBILE_DD = "mobile_dd";
	String MOBILE_KHJL = "mobile_khjl";
	String MOBILE_YWY = "mobile_ywy";
	String MOBILE_CSJL = "mobile_csjl";
	String MOBILE_ZG = "mobile_zg";
	String MOBILE_ORG = "mobile_org";
	String MOBILE_ALL= "mobile_all";

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String login(MultivaluedMap<String, String> params);
//	String login(String mobile,String password,String phoneSelfNum);
	@POST
	@Path("/admlogin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String admlogin(MultivaluedMap<String, String> params);
	@POST
	@Path("/resetPwd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String resetPwd(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/searchuserrstpsw")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchuserrstpsw(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/sku")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String sku();
	
	
	@POST
	@Path("/skuByCloud")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String skuByCloud(MultivaluedMap<String, String> params);
	
	/**
	 * 获取订单本品列表
	 * @param params
	 * @return
	 */
	@POST
	@Path("/getOrderSkuByKunner")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getOrderSkuByKunner(MultivaluedMap<String, String> params);
	
	
	
	
	@POST
	@Path("/menu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String menu();
	
	@POST
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String customer(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/searchCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchCustomer(MultivaluedMap<String, String> params);
	@POST
	@Path("/searchCustomerXpp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchCustomerXpp(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/searchKunnr")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchKunnr(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/activity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String activity(MultivaluedMap<String, String> params);

	@POST
	@Path("/searchActivity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchActivity(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/searchRoute")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchRoute(MultivaluedMap<String, String> params);
	
	/**
	 * 客户经理，城市经理获取关联经销商
	 * **/
	@POST
	@Path("/getKunnrByJL")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKunnrByJL(MultivaluedMap<String, String> params);
	
	
	@POST
	@Path("/photo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String photoUpload(MultivaluedMap<String, String> params);
	
	
	@POST
	@Path("/price")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String priceUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/distr")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String distrUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/dict")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String dict();
	//管理员手机端字典
	@POST
	@Path("/admdict")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String admdict();
	
	@POST
	@Path("/display")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String displayUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/createCust")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String custUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/createSign")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String signUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getSignList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getSignList(MultivaluedMap<String, String> params);
	
	
	@POST
	@Path("/orderUpload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String orderUpload(MultivaluedMap<String, String> params);
	
	

	
	@POST
	@Path("/stockage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String stockageUpload(MultivaluedMap<String, String> params);
	
//	@POST
//	@Path("/updateCustomer")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	String updateCustomer(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/supervise")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String superviseUpload(MultivaluedMap<String, String> params);
	/**
	 * MethodsTitle: 获取时间接口
	 * @author: xg.chen
	 * @date:2016年12月7日 下午3:07:44
	 * @version 1.0
	 * @return
	 */
	@POST
	@Path("/time")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String time();
	
	@POST
	@Path("/channel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String channel();
	
	@POST
	@Path("/division")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String division();
	
	
	@POST
	@Path("/searchDivision")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchDivision(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/admmobileVersion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String admmobileVersion(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/version")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String version();
	
	/************************ new *********************************/
	@POST
	@Path("/versionNew")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String version(MultivaluedMap<String, String> params);

	/**
	 * @Description  订单同步接口，同步当天订单
	 * */
	@POST
	@Path("/orderDownload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String orderDownload(MultivaluedMap<String, String> params);
	
	/**
	 * @Description  获取指标
	 * */
	@POST
	@Path("/indexDetail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getIndexDetail(MultivaluedMap<String, String> params);

	
	/**
	 * @Description  登陆信息
	 * */
	@POST
	@Path("/uploadLoginLog")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String uploadLoginLog(MultivaluedMap<String, String> params);

	
	/**
	 * @Description  库存上传
	 * */
	@POST
	@Path("/uploadkunnrStock")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String uploadkunnrStock(MultivaluedMap<String, String> params);
	
	/**
	 * @Description  库存上传(单条)
	 * */
	@POST
	@Path("/uploadkunnrStockNew")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String uploadkunnrStockNew(MultivaluedMap<String, String> params);
	
	
	/**
	 * @Description  同步库存信息
	 * */
	@POST
	@Path("/getKunnrStock")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKunnrStock(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getKunnrStockDate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKunnrStockDate(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getKpiBo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKpiBo(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/checkKpiMenu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String checkKpiMenu(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getKpiVisit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKpiVisit(MultivaluedMap<String, String> params);
	/**
	 * MethodsTitle:获取提报后数据汇总 
	 * @author: xg.chen
	 * @date:2016年12月9日 上午9:56:30
	 * @version 1.0
	 * @param params
	 * @return
	 */
	@POST
	@Path("/getSuperKunnrInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getSuperKunnrInfo(MultivaluedMap<String, String> params);
	/**
	 * 获取经销商sku列表信息new  2015-4-17 提报人-经销商-门店-sku-最后一次提报价格
	 * @param params
	 * @return
	 */
	@POST
	@Path("/getSkuLastPrice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getSkuLastPrice(MultivaluedMap<String, String> params);
	/**
	 * 获取经销商sku列表信息new  2015-4-17 提报人-经销商-门店-sku-最后一次提报价格
	 * @param params
	 * @return
	 */
	@POST
	@Path("/getKunnrOrderFormat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getKunnrOrderFormat(MultivaluedMap<String, String> params);
	
	/**
	 * 获取历史条码
	 * @param product
	 * @return
	 */
	@POST
	@Path("/getLstm")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getLstm(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/uploadlstm")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String uploadlstm(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getCustSku")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getCustSku(MultivaluedMap<String, String> params);
	@POST
	@Path("/getWeekOrderTotal")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getWeekOrderTotal(MultivaluedMap<String, String> params);
	@POST
	@Path("/searchCuanhuo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String searchCuanhuo(MultivaluedMap<String, String> params);
	@POST
	@Path("/getVistCust")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getVistCust(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getSkuUnit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getSkuUnit();
	
	@POST
	@Path("/createCustomerStock")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String createCustomerStock(MultivaluedMap<String, String> params);
	
	
	@POST
	@Path("/uploadCustomerStock")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String customerStockUpload(MultivaluedMap<String, String> params);
	
	@POST
	@Path("/getCustomerStockByCustid")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	String getCustomerStockByCustid(MultivaluedMap<String, String> params);
}

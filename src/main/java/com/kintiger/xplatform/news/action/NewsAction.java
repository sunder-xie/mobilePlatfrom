package com.kintiger.xplatform.news.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.news.INewsService;
import com.kintiger.xplatform.api.news.bo.NewsDetail;
import com.kintiger.xplatform.api.news.bo.NewsTotal;
import com.kintiger.xplatform.api.tree.bo.Tree4Ajax;
import com.kintiger.xplatform.api.user.bo.User;
import com.kintiger.xplatform.framework.action.BaseAction;
import com.kintiger.xplatform.framework.annotation.ActionLog;
import com.kintiger.xplatform.framework.annotation.Decode;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.webwork.annotations.JsonResult;

/**
 * news.
 * 
 * @author xujiakun
 * 
 */
public class NewsAction extends BaseAction {

	private static final long serialVersionUID = -4337250619666419253L;

	private Logger4jExtend logger = Logger4jCollection.getLogger(NewsAction.class);

	private INewsService newsService;

	private IMemcachedCacheService memcachedCacheService;

	private int total;

	private List<NewsTotal> newsTotalList = new ArrayList<NewsTotal>();

	private List<NewsDetail> newsDetailList = new ArrayList<NewsDetail>();

	// private List<NewsTotal> newsTypelist

	private String detailId;

	private NewsTotal newsTotal = new NewsTotal();

	private NewsDetail newsDetail = new NewsDetail();;

	private List<Tree4Ajax> treeList = new ArrayList<Tree4Ajax>();

	private String node;

	@Decode
	private String totalName;

	private String detailIds;

	@Decode
	private String detailTitle;

	private String totalId;

	private String orgName;

	/**
	 * 主页初始化.
	 * 
	 * @return
	 */
	public String index() {
		List<NewsDetail> detailList = newsService.getNewsList();

		if (detailList != null && detailList.size() > 0) {
			Map<Long, NewsTotal> map = new HashMap<Long, NewsTotal>();

			for (NewsDetail d : detailList) {
				if (map.containsKey(d.getTotalId())) {
					map.get(d.getTotalId()).getNewsDetailList().add(d);
				} else {
					NewsTotal t = new NewsTotal();
					t.setTotalId(d.getTotalId());
					t.setTotalName(d.getTotalName());
					t.setTotalSign(d.getTotalSign());
					t.setNewsDetailList(new ArrayList<NewsDetail>());
					t.getNewsDetailList().add(d);

					map.put(d.getTotalId(), t);

					newsTotalList.add(t);
				}
			}

			map = null;
		}

		return "index";
	}

	/**
	 * 获取单个新闻.
	 * 
	 * @return
	 */
	public String getNews() {
		if (StringUtil.isNotEmpty(detailId)) {
			NewsDetail d = new NewsDetail();
			try {
				d.setDetailId(Long.valueOf(detailId));
				// 获取单个明细
				newsDetail = newsService.getNewsDetail(d);
			} catch (NumberFormatException e) {
				logger.error("detailId:" + detailId, e);
			}

			if (newsDetail == null) {
				return "news";
			}

			newsDetail.setClicksRatio(newsDetail.getClicksRatio() + 1);

			// 修改点击次数
			newsService.updateNewsDetail(newsDetail);
		}

		return "news";
	}

	private boolean validateNewsTotal(NewsTotal newsTotal) {
		if (newsTotal == null) {
			return false;
		}

		return true;
	}

	/**
	 * 跳转到新闻版块创建.
	 * 
	 * @return
	 */
	public String createNewsTotalPrepare() {
		return "createNewsTotalPrepare";
	}

	/**
	 * 创建总单.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目创建")
	public String createNewsTotal() {
		if (!validateNewsTotal(newsTotal)) {
			this.setFailMessage(INewsService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult res = newsService.createNewsTotal(newsTotal);
		if (res.getResult()) {
			this.setSuccessMessage("公告栏目创建成功!");
		} else {
			this.setFailMessage("公告栏目创建失败!");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 跳转到修改新闻总单页面.
	 * 
	 * @return
	 */
	public String updateNewsTotalPrepare() {
		if (StringUtil.isNotEmpty(totalId) && StringUtil.isNotEmpty(totalId.trim())) {
			try {
				newsTotal.setTotalId(Long.valueOf(totalId));
				newsTotal = newsService.getNewsTotal(newsTotal);
			} catch (NumberFormatException e) {
				logger.error("totalId:" + totalId, e);
			}
		}

		return "updateNewsTotalPrepare";
	}

	/**
	 * 修改新闻总单.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目修改")
	public String updateNewsTotal() {
		if (!validateNewsTotal(newsTotal)) {
			this.setFailMessage(INewsService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		BooleanResult res = newsService.updateNewsTotal(newsTotal);

		if (res.getResult()) {
			this.setSuccessMessage("公告栏目修改成功！");
		} else {
			this.setFailMessage("公告栏目修改失败！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除新闻总单.
	 * 
	 * @returns
	 */
	@ActionLog(actionName = "公告栏目删除")
	public String deleteNewsTotal() {
		if (StringUtil.isNotEmpty(totalId)) {
			NewsTotal t = new NewsTotal();
			t.setTotalId(Long.valueOf(totalId));
			t.setTotalFlag("N");

			BooleanResult res = newsService.updateNewsTotal(t);

			if (res.getResult()) {
				this.setSuccessMessage("公告栏目删除成功！");
				return RESULT_MESSAGE;
			}
		}

		this.setFailMessage("公告栏目删除失败！");
		return RESULT_MESSAGE;
	}

	/**
	 * 跳转到新闻栏目查询及维护.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目查询")
	public String searchNewsTotal() {
		return "searchNewsTotal";
	}

	/**
	 * 跳转到新闻栏目查询及维护.
	 * 
	 * @return
	 */

	@JsonResult(field = "newsTotalList", include = { "totalId", "totalParentId", "totalName", "totalTitle",
		"totalDate", "totalCode", "totalShow", "totalSign", "totalUploadSign" }, total = "total")
	public String getNewsTotalJsonList() {
		NewsTotal t = new NewsTotal();
		t = this.getSearchInfo(t);

		if (StringUtil.isNotEmpty(totalName) && StringUtil.isNotEmpty(totalName.trim())) {
			t.setTotalName(totalName.trim());
		}

		t.setTotalParentId(1L);
		total = newsService.getNewsTotalCount(t);
		if (total > 0) {
			newsTotalList = newsService.getNewsTotalList(t);
		}

		return JSON;
	}

	/**
	 * 跳转到明细列表.
	 * 
	 * @return
	 */
	public String searchNews() {
		// 获取类型列表
		// newsTypelist = newsService.getNewsTotalList();

		return "searchNews";
	}

	/**
	 * 跳转到栏目明细创建及维护.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目内容查询")
	public String searchNewsDetail() {
		return "searchNewsDetail";
	}

	/**
	 * 跳转明细列表.
	 * 
	 * @return
	 */

	@JsonResult(field = "newsDetailList", include = { "detailId", "detailTitle", "detailDate", "clicksRatio",
		"totalName", "detailOperator" }, total = "total")
	public String getNewsDetailJsonList() {
		NewsDetail t = new NewsDetail();
		t = this.getSearchInfo(t);

		if (StringUtil.isNotEmpty(totalId) && StringUtil.isNotEmpty(totalId.trim())) {
			try {
				t.setTotalId(Long.valueOf(totalId.trim()));
			} catch (NumberFormatException e) {
				logger.error("totalId:" + totalId, e);
			}
		}

		if (StringUtil.isNotEmpty(detailTitle) && StringUtil.isNotEmpty(detailTitle.trim())) {
			t.setDetailTitle(detailTitle.trim());
		}

		if (StringUtil.isNotEmpty(orgName) && StringUtil.isNotEmpty(orgName.trim())) {
			t.setOrgName(orgName.trim());
		}

		if (StringUtil.isNotEmpty(totalName) && StringUtil.isNotEmpty(totalName.trim())) {
			t.setTotalName(totalName.trim());
		}

		total = newsService.getNewsDetailCount(t);
		if (total > 0) {
			newsDetailList = newsService.getNewsDetailList(t);
		}

		return JSON;
	}

	private boolean validateNewsDetail(NewsDetail newsDetail) {
		if (newsDetail == null) {
			return false;
		}

		return true;
	}

	/**
	 * 跳转到栏目明细创建.
	 * 
	 * @return
	 */
	public String createNewsDetailPrepare() {
		return "createNewsDetailPrepare";
	}

	/**
	 * 保存新闻栏目明细.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目内容创建")
	public String createNewsDetail() {
		if (!validateNewsDetail(newsDetail)) {
			this.setFailMessage(INewsService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		User user = this.getUser();
		newsDetail.setDetailOperator(user.getUserName());
		newsDetail.setOrgName("1");
		newsDetail.setClicksRatio(0L);

		BooleanResult res = newsService.createNewsDetail(newsDetail);

		if (res.getResult()) {
			this.setSuccessMessage("公告栏目内容创建成功！");
		} else {
			this.setFailMessage("公告栏目内容创建失败！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 跳转到修改新闻栏目明细页面.
	 * 
	 * @return
	 */
	public String updateNewsDetailPrepare() {
		if (StringUtil.isNotEmpty(detailId)) {
			try {
				newsDetail.setDetailId(Long.valueOf(detailId));
				// 获取单个明细
				newsDetail = newsService.getNewsDetail(newsDetail);
			} catch (NumberFormatException e) {
				logger.error("detailId:" + detailId, e);
			}
		}

		return "updateNewsDetailPrepare";
	}

	/**
	 * 修改新闻栏目明细.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目内容修改")
	public String updateNewsDetail() {
		if (!validateNewsDetail(newsDetail)) {
			this.setFailMessage(INewsService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 修改新闻明细
		BooleanResult res = newsService.updateNewsDetail(newsDetail);

		if (res.getResult()) {
			this.setSuccessMessage("公告栏目内容修改成功！");
			return RESULT_MESSAGE;
		}

		this.setFailMessage("公告栏目内容修改失败！");
		return RESULT_MESSAGE;
	}

	/**
	 * 栏目明细删除.
	 * 
	 * @return
	 */
	@ActionLog(actionName = "公告栏目内容删除")
	public String deleteNewsDetail() {
		if (StringUtil.isNotEmpty(detailIds)) {
			NewsDetail d = new NewsDetail();
			String[] codes = detailIds.split(",");
			d.setCodes(codes);

			BooleanResult res = newsService.deleteNewsDetail(d);

			if (res.getResult()) {
				this.setSuccessMessage("公告栏目内容删除成功！");
				return RESULT_MESSAGE;
			}
		}

		this.setFailMessage("公告栏目内容删除失败！");
		return RESULT_MESSAGE;
	}

	/**
	 * 获取父级栏目.
	 * 
	 * @return
	 */
	public String searchNewsTree() {
		this.node = "1";
		return "searchNewsTree";
	}

	@JsonResult(field = "treeList", include = { "id", "text" })
	public String getNewsTreeListByAjax() {
		if (StringUtil.isNotEmpty(node)) {
			List<NewsTotal> newsTreeList = null;
			try {
				NewsTotal t = new NewsTotal();
				t.setTotalParentId(Long.valueOf(node));
				t.setSort("totalId");
				t.setDir("asc");

				// 获取类型列表
				total = newsService.getNewsTotalCount(t);
				if (total > 0) {
					t.setStart(0);
					t.setLimit(total);
					newsTreeList = newsService.getNewsTotalList(t);
				}
			} catch (Exception e) {
				logger.error("node:" + node, e);
			}

			if (newsTreeList == null || newsTreeList.size() == 0) {
				return JSON;
			}

			for (NewsTotal t : newsTreeList) {
				Tree4Ajax tree = new Tree4Ajax();
				tree.setId(String.valueOf(t.getTotalId()));
				tree.setText(t.getTotalName());
				// tree.setCls("folder");
				treeList.add(tree);
			}
		}

		return JSON;
	}

	public Logger4jExtend getLogger() {
		return logger;
	}

	public void setLogger(Logger4jExtend logger) {
		this.logger = logger;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsTotal> getNewsTotalList() {
		return newsTotalList;
	}

	public void setNewsTotalList(List<NewsTotal> newsTotalList) {
		this.newsTotalList = newsTotalList;
	}

	public List<NewsDetail> getNewsDetailList() {
		return newsDetailList;
	}

	public void setNewsDetailList(List<NewsDetail> newsDetailList) {
		this.newsDetailList = newsDetailList;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public NewsTotal getNewsTotal() {
		return newsTotal;
	}

	public void setNewsTotal(NewsTotal newsTotal) {
		this.newsTotal = newsTotal;
	}

	public NewsDetail getNewsDetail() {
		return newsDetail;
	}

	public void setNewsDetail(NewsDetail newsDetail) {
		this.newsDetail = newsDetail;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public String getDetailIds() {
		return detailIds;
	}

	public void setDetailIds(String detailIds) {
		this.detailIds = detailIds;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}

package com.kintiger.xplatform.api.news;

import java.util.List;

import com.kintiger.xplatform.api.news.bo.NewsDetail;
import com.kintiger.xplatform.api.news.bo.NewsTotal;
import com.kintiger.xplatform.api.news.bo.NewsFile;
import com.kintiger.xplatform.framework.bo.BooleanResult;

/**
 * 新闻接口.
 * 
 * @author xujiakun
 * 
 */
public interface INewsService {

	String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	/**
	 * 获取oa内网新闻.
	 * 
	 * @return
	 */
	List<NewsDetail> getNewsList();

	/**
	 * 获取新闻明细表条数.
	 * 
	 * @param newsDetail
	 * @return
	 */
	int getNewsDetailCount(NewsDetail newsDetail);

	/**
	 * 获取新闻明细表分页.
	 * 
	 * @param newsDetail
	 * @return
	 */
	List<NewsDetail> getNewsDetailList(NewsDetail newsDetail);

	/**
	 * 插入新闻明细表.
	 * 
	 * @param newsDetail
	 * @return
	 */
	BooleanResult createNewsDetail(NewsDetail newsDetail);

	/**
	 * 获取新闻明细.
	 * 
	 * @param newsDetail
	 * @return
	 */
	NewsDetail getNewsDetail(NewsDetail newsDetail);

	/**
	 * 更新新闻点击次数.
	 * 
	 * @param newsDetail
	 * @return
	 */
	BooleanResult updateNewsDetail(NewsDetail newsDetail);

	/**
	 * 删除明细.
	 * 
	 * @param newsDetail
	 * @return
	 */
	BooleanResult deleteNewsDetail(NewsDetail newsDetail);

	/**
	 * 获取新闻总表条数.
	 * 
	 * @param newsTotal
	 * @return
	 */
	int getNewsTotalCount(NewsTotal newsTotal);

	/**
	 * 获取新闻总表分页.
	 * 
	 * @param newsTotal
	 * @return
	 */
	List<NewsTotal> getNewsTotalList(NewsTotal newsTotal);

	/**
	 * 插入新闻总表.
	 * 
	 * @param newsTotal
	 * @return
	 */
	BooleanResult createNewsTotal(NewsTotal newsTotal);

	/**
	 * 获取单个新闻总表.
	 * 
	 * @param newsTotal
	 * @return
	 */
	NewsTotal getNewsTotal(NewsTotal newsTotal);

	/**
	 * 更新新闻总表.
	 * 
	 * @param newsTotal
	 * @return
	 */
	BooleanResult updateNewsTotal(NewsTotal newsTotal);

	/**
	 * 获取新闻明细附件.
	 * 
	 * @param newsFile
	 * @return
	 */
	List<NewsFile> getNewsFileList(NewsFile newsFile);

	/**
	 * 
	 * @param newsFile
	 * @return
	 */
	Long createNewsFile(NewsFile newsFile);

	/**
	 * 修改新闻明细附件表.
	 * 
	 * @param newsFile
	 * @return
	 */
	int updateNewsFile(NewsFile newsFile);

}

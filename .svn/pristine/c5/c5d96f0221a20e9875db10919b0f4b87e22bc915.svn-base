package com.kintiger.xplatform.news.dao;

import java.util.List;

import com.kintiger.xplatform.api.news.bo.NewsDetail;
import com.kintiger.xplatform.api.news.bo.NewsTotal;
import com.kintiger.xplatform.api.news.bo.NewsFile;

/**
 * 
 * @author xujiakun
 * 
 */
public interface INewsDao {

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
	Long createNewsDetail(NewsDetail newsDetail);

	/**
	 * 获取新闻明细.
	 * 
	 * @param newsDetail
	 * @return
	 */
	NewsDetail getNewsDetail(NewsDetail newsDetail);

	/**
	 * 
	 * @param newsDetail
	 * @return
	 */
	int updateNewsDetail(NewsDetail newsDetail);

	/**
	 * 删除明细.
	 * 
	 * @param newsDetail
	 * @return
	 */
	int deleteNewsDetail(NewsDetail newsDetail);

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
	Long createNewsTotal(NewsTotal newsTotal);

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
	int updateNewsTotal(NewsTotal newsTotal);

	/**
	 * 获取新闻明细附件.
	 * 
	 * @param newsFile
	 * @return
	 */
	List<NewsFile> getNewsFileList(NewsFile newsFile);

	/**
	 * 插入新闻明细表.
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

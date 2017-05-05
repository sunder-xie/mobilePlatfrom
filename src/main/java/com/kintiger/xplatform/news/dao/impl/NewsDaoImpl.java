package com.kintiger.xplatform.news.dao.impl;

import java.util.List;

import com.kintiger.xplatform.api.news.bo.NewsDetail;
import com.kintiger.xplatform.api.news.bo.NewsTotal;
import com.kintiger.xplatform.api.news.bo.NewsFile;
import com.kintiger.xplatform.framework.dao.impl.BaseDaoImpl;
import com.kintiger.xplatform.news.dao.INewsDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class NewsDaoImpl extends BaseDaoImpl implements INewsDao {

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsList() {
		return getSqlMapClientTemplate().queryForList("news.getNewsList");
	}

	public int getNewsDetailCount(NewsDetail newsDetail) {
		return (Integer) getSqlMapClientTemplate().queryForObject("news.getNewsDetailCount", newsDetail);
	}

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsDetailList(NewsDetail newsDetail) {
		return getSqlMapClientTemplate().queryForList("news.getNewsDetailList", newsDetail);
	}

	public Long createNewsDetail(NewsDetail newsDetail) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsDetail", newsDetail);
	}

	public NewsDetail getNewsDetail(NewsDetail newsDetail) {
		return (NewsDetail) getSqlMapClientTemplate().queryForObject("news.getNewsDetail", newsDetail);
	}

	public int updateNewsDetail(NewsDetail newsDetail) {
		return getSqlMapClientTemplate().update("news.updateNewsDetail", newsDetail);
	}

	public int deleteNewsDetail(NewsDetail newsDetail) {
		return getSqlMapClientTemplate().update("news.deleteNewsDetail", newsDetail);
	}

	public int getNewsTotalCount(NewsTotal newsTotal) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("news.getNewsTotalCount", newsTotal);
	}

	@SuppressWarnings("unchecked")
	public List<NewsTotal> getNewsTotalList(NewsTotal newsTotal) {
		return getSqlMapClientTemplate().queryForList("news.getNewsTotalList", newsTotal);
	}

	public Long createNewsTotal(NewsTotal newsTotal) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsTotal", newsTotal);
	}

	public NewsTotal getNewsTotal(NewsTotal newsTotal) {
		return (NewsTotal) this.getSqlMapClientTemplate().queryForObject("news.getNewsTotal", newsTotal);
	}

	public int updateNewsTotal(NewsTotal newsTotal) {
		return getSqlMapClientTemplate().update("news.updateNewsTotal", newsTotal);
	}

	@SuppressWarnings("unchecked")
	public List<NewsFile> getNewsFileList(NewsFile newsFile) {
		return getSqlMapClientTemplate().queryForList("news.getNewsFileList", newsFile);
	}

	public Long createNewsFile(NewsFile newsFile) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsFile", newsFile);
	}

	public int updateNewsFile(NewsFile newsFile) {
		return getSqlMapClientTemplate().update("news.updateNewsFile", newsFile);
	}

}

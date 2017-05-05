package com.kintiger.xplatform.news.service.impl;

import java.util.List;

import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.news.INewsService;
import com.kintiger.xplatform.api.news.bo.NewsDetail;
import com.kintiger.xplatform.api.news.bo.NewsTotal;
import com.kintiger.xplatform.api.news.bo.NewsFile;
import com.kintiger.xplatform.framework.bo.BooleanResult;
import com.kintiger.xplatform.framework.log.Logger4jCollection;
import com.kintiger.xplatform.framework.log.Logger4jExtend;
import com.kintiger.xplatform.framework.util.LogUtil;
import com.kintiger.xplatform.news.dao.INewsDao;

/**
 * 
 * @author xujiakun
 * 
 */
public class NewsServiceImpl implements INewsService {

	private Logger4jExtend logger = Logger4jCollection.getLogger(NewsServiceImpl.class);

	private IMemcachedCacheService memcachedCacheService;

	private INewsDao newsDao;

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsList() {
		List<NewsDetail> newsDetailList = null;

		try {
			newsDetailList = (List<NewsDetail>) memcachedCacheService.get(IMemcachedCacheService.CACHE_KEY_NEWS);
		} catch (Exception e) {
			logger.error(e);
		}

		if (newsDetailList != null && newsDetailList.size() > 0) {
			return newsDetailList;
		}

		try {
			newsDetailList = newsDao.getNewsList();
			if (newsDetailList != null && newsDetailList.size() > 0) {
				memcachedCacheService.set(IMemcachedCacheService.CACHE_KEY_NEWS, newsDetailList,
					IMemcachedCacheService.CACHE_KEY_NEWS_DEFAULT_EXP);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return newsDetailList;
	}

	public int getNewsDetailCount(NewsDetail newsDetail) {
		try {
			return newsDao.getNewsDetailCount(newsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return 0;
	}

	public List<NewsDetail> getNewsDetailList(NewsDetail newsDetail) {
		try {
			return newsDao.getNewsDetailList(newsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return null;
	}

	public BooleanResult createNewsDetail(NewsDetail newsDetail) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			Long id = newsDao.createNewsDetail(newsDetail);
			result.setResult(true);
			result.setCode(String.valueOf(id));

			// 清空cache
			removeCache();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return result;
	}

	public NewsDetail getNewsDetail(NewsDetail newsDetail) {
		try {
			return newsDao.getNewsDetail(newsDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return null;
	}

	public BooleanResult updateNewsDetail(NewsDetail newsDetail) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = newsDao.updateNewsDetail(newsDetail);
			if (c == 1) {
				result.setResult(true);

				// 清空cache
				removeCache();
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return result;
	}

	public BooleanResult deleteNewsDetail(NewsDetail newsDetail) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = newsDao.deleteNewsDetail(newsDetail);
			result.setResult(true);
			result.setCode(String.valueOf(c));

			// 清空cache
			removeCache();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsDetail), e);
		}

		return result;
	}

	public int getNewsTotalCount(NewsTotal newsTotal) {
		try {
			return newsDao.getNewsTotalCount(newsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsTotal), e);
		}

		return 0;
	}

	public List<NewsTotal> getNewsTotalList(NewsTotal newsTotal) {
		try {
			return newsDao.getNewsTotalList(newsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsTotal), e);
		}

		return null;
	}

	public BooleanResult createNewsTotal(NewsTotal newsTotal) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			Long id = newsDao.createNewsTotal(newsTotal);
			result.setResult(true);
			result.setCode(String.valueOf(id));

			// 清空cache
			removeCache();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsTotal), e);
		}

		return result;
	}

	public NewsTotal getNewsTotal(NewsTotal newsTotal) {
		try {
			return newsDao.getNewsTotal(newsTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsTotal), e);
		}

		return null;
	}

	public BooleanResult updateNewsTotal(NewsTotal newsTotal) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);

		try {
			int c = newsDao.updateNewsTotal(newsTotal);
			if (c == 1) {
				result.setResult(true);

				// 清空cache
				removeCache();
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsTotal), e);
		}

		return result;
	}

	public List<NewsFile> getNewsFileList(NewsFile newsFile) {
		try {
			return newsDao.getNewsFileList(newsFile);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFile), e);
		}

		return null;
	}

	public Long createNewsFile(NewsFile newsFile) {
		try {
			return newsDao.createNewsFile(newsFile);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFile), e);
		}

		return null;
	}

	public int updateNewsFile(NewsFile newsFile) {
		try {
			return newsDao.updateNewsFile(newsFile);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(newsFile), e);
		}

		return 0;
	}

	private void removeCache() {
		try {
			memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_NEWS);
		} catch (Exception e) {
			logger.error(IMemcachedCacheService.CACHE_KEY_NEWS, e);
		}
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

}

package com.kintiger.xplatform.cache.service.impl;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.kintiger.xplatform.api.cache.IMemcachedCacheService;
import com.kintiger.xplatform.api.cache.bo.CacheStats;
import com.kintiger.xplatform.framework.exception.ServiceException;
import com.kintiger.xplatform.framework.util.DateUtil;

/**
 * cahce.
 * 
 * @author xujiakun
 * 
 */
public class MemcachedCacheServiceImpl implements IMemcachedCacheService {

	private Logger logger = Logger.getLogger(MemcachedCacheServiceImpl.class);

	private MemcachedClient memcachedClient;

	public Object add(String key, Object value) throws ServiceException {
		return add(key, value, IMemcachedCacheService.DEFAULT_EXP);
	}

	public Object add(String key, Object value, Date expiry) throws ServiceException {
		if (expiry == null) {
			return add(key, value);
		}

		return add(key, value, DateUtil.getQuotSeconds(new Date(), expiry));
	}

	public Object add(String key, Object value, int exp) throws ServiceException {
		try {
			return memcachedClient.add(key, exp, value);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached add.");
	}

	public Object set(String key, Object value) throws ServiceException {
		return set(key, value, IMemcachedCacheService.DEFAULT_EXP);
	}

	public Object set(String key, Object value, Date expiry) throws ServiceException {
		if (expiry == null) {
			return set(key, value);
		}

		return set(key, value, DateUtil.getQuotSeconds(new Date(), expiry));
	}

	public Object set(String key, Object value, int exp) throws ServiceException {
		try {
			return memcachedClient.set(key, exp, value);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached set.");
	}

	public Object replace(String key, Object value) throws ServiceException {
		return replace(key, value, IMemcachedCacheService.DEFAULT_EXP);
	}

	public Object replace(String key, Object value, Date expiry) throws ServiceException {
		if (expiry == null) {
			return replace(key, value);
		}

		return replace(key, value, DateUtil.getQuotSeconds(new Date(), expiry));
	}

	public Object replace(String key, Object value, int exp) throws ServiceException {
		try {
			return memcachedClient.replace(key, exp, value);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached replace.");
	}

	public Object get(String key) throws ServiceException {
		try {
			return memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached get.");
	}

	public Object remove(String key) throws ServiceException {
		try {
			return memcachedClient.delete(key);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached remove.");
	}

	public long incr(String key, int inc) throws ServiceException {
		try {
			return memcachedClient.incr(key, inc);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached incr.");
	}

	public long incr(String key, long inc) throws ServiceException {
		try {
			return memcachedClient.incr(key, inc);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached incr.");
	}

	public long decr(String key, int decr) throws ServiceException {
		try {
			return memcachedClient.decr(key, decr);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached decr.");
	}

	public long decr(String key, long decr) throws ServiceException {
		try {
			return memcachedClient.decr(key, decr);
		} catch (TimeoutException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (MemcachedException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached decr.");
	}

	public void flushAll(InetSocketAddress address) throws ServiceException {
		try {
			memcachedClient.flushAll(address);
		} catch (MemcachedException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (TimeoutException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached flushAll.");
	}

	public List<CacheStats> getStats() throws ServiceException {
		Map<InetSocketAddress, Map<String, String>> maps = getStatsDetail();

		List<CacheStats> cacheStatsList = new ArrayList<CacheStats>();

		for (Map.Entry<InetSocketAddress, Map<String, String>> m : maps.entrySet()) {
			CacheStats cacheStats = new CacheStats();

			InetSocketAddress address = m.getKey();
			cacheStats.setAddress(address.toString());
			cacheStats.setHostAddress(address.getAddress().getHostAddress());
			cacheStats.setHostName(address.getAddress().getHostName());
			cacheStats.setPort(String.valueOf(address.getPort()));

			Map<String, String> map = m.getValue();
			cacheStats.setDeleteHits(map.get("delete_hits"));
			cacheStats.setBytes(map.get("bytes"));
			cacheStats.setTotalItems(map.get("total_items"));
			cacheStats.setRusageSystem(map.get("rusage_system"));
			cacheStats.setTouchMisses(map.get("touch_misses"));
			cacheStats.setCmdTouch(map.get("cmd_touch"));
			cacheStats.setListenDisabledNum(map.get("listen_disabled_num"));
			cacheStats.setAuthErrors(map.get("auth_errors"));
			cacheStats.setEvictions(map.get("evictions"));
			cacheStats.setVersion(map.get("version"));
			cacheStats.setPointerSize(map.get("pointer_size"));
			cacheStats.setTime(map.get("time"));
			cacheStats.setIncrHits(map.get("incr_hits"));
			cacheStats.setThreads(map.get("threads"));
			cacheStats.setExpiredUnfetched(map.get("expired_unfetched"));
			cacheStats.setLimitMaxbytes(map.get("limit_maxbytes"));
			cacheStats.setHashIsExpanding(map.get("hash_is_expanding"));
			cacheStats.setBytesRead(map.get("bytes_read"));
			cacheStats.setCurrConnections(map.get("curr_connections"));
			cacheStats.setGetMisses(map.get("get_misses"));
			cacheStats.setReclaimed(map.get("reclaimed"));
			cacheStats.setBytesWritten(map.get("bytes_written"));
			cacheStats.setHashPowerLevel(map.get("hash_power_level"));
			cacheStats.setConnectionStructures(map.get("connection_structures"));
			cacheStats.setCasHits(map.get("cas_hits"));
			cacheStats.setDeleteMisses(map.get("delete_misses"));
			cacheStats.setTotalConnections(map.get("total_connections"));
			cacheStats.setRusageUser(map.get("rusage_user"));
			cacheStats.setCmdFlush(map.get("cmd_flush"));
			cacheStats.setLibevent(map.get("libevent"));
			cacheStats.setUptime(map.get("uptime"));
			cacheStats.setReservedFds(map.get("reserved_fds"));
			cacheStats.setTouchHits(map.get("touch_hits"));
			cacheStats.setCasBadval(map.get("cas_badval"));
			cacheStats.setPid(map.get("pid"));
			cacheStats.setGetHits(map.get("get_hits"));
			cacheStats.setCurrItems(map.get("curr_items"));
			cacheStats.setCasMisses(map.get("cas_misses"));
			cacheStats.setAcceptingConns(map.get("accepting_conns"));
			cacheStats.setEvictedUnfetched(map.get("evicted_unfetched"));
			cacheStats.setCmdGet(map.get("cmd_get"));
			cacheStats.setCmdSet(map.get("cmd_set"));
			cacheStats.setAuthCmds(map.get("auth_cmds"));
			cacheStats.setIncrMisses(map.get("incr_misses"));
			cacheStats.setHashBytes(map.get("hash_bytes"));
			cacheStats.setDecrMisses(map.get("decr_misses"));
			cacheStats.setDecrHits(map.get("decr_hits"));
			cacheStats.setConnYields(map.get("conn_yields"));

			cacheStatsList.add(cacheStats);
		}

		return cacheStatsList;
	}

	private Map<InetSocketAddress, Map<String, String>> getStatsDetail() throws ServiceException {
		try {
			return memcachedClient.getStats();
		} catch (MemcachedException e) {
			logger.error(e);
		} catch (InterruptedException e) {
			logger.error(e);
		} catch (TimeoutException e) {
			logger.error(e);
		}

		throw new ServiceException("memcached getStatsDetail.");
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}

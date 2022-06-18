package com.trigun.iim.redis.controllers;

/**
 * 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/velocity/admin/caches")
@RestController
public class RedisAdminController {

	@Autowired
	@Qualifier("myCacheManager")
	private CacheManager cacheManager;

	@Autowired
	private StringRedisTemplate template;

	// Delete all the keys of all the existing cacge DB.
	@RequestMapping(value = "/flush-all", method = RequestMethod.GET)
	public void clearAllCache() {
		template.getConnectionFactory().getConnection().flushAll();
		evictAllCaches();
	}

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> {
			System.out.println("cleared cache->" + cacheName);
			cacheManager.getCache(cacheName).clear();
		});
	}


	@Scheduled(fixedRate = 3600)
	public void evictAllcachesAtIntervals() {
		evictAllCaches();
	}

}

**/

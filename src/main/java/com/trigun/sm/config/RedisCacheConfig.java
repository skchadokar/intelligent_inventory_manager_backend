package com.trigun.sm.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/****
 * 
 * @author satishchadokar How to use : 
 * @Cacheable(value = "KEY_NAME",keyGenerator = "customKeyGenerator",cacheManager = "myCacheManager") 
 *   Can use this type of syntext in controller for any obj which is fix or very rear change
 */

/****
 * 

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Value("${spring.redis.host}")
	private String redisHostName;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.cache.redis.time-to-live}")
	private Long DEFAULT_TTL;

	@Value("${current.app.name}")
	private String appname;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		// Defaults
		redisConnectionFactory.setHostName(redisHostName);
		redisConnectionFactory.setPort(redisPort);
		return redisConnectionFactory;
	}

	@Bean(name = "customKeyGenerator")
	public KeyGenerator keyGenerator() {

		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				// This will generate a unique key of the class name, the method name,
				// and all method parameters appended.

				StringBuilder sb = new StringBuilder();
				sb.append(appname);
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	@Bean(name = "myCacheManager")
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory()),
				RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(DEFAULT_TTL)),
				this.getRedisCacheConfigurationMap());
	}

	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		StringBuffer sb = new StringBuffer();

		// Map<String, Long> expires = new HashMap<String, Long>();

		Set<byte[]> keys = redisConnectionFactory().getConnection().keys(new String(appname + "_").getBytes());

		Iterator<byte[]> it = keys.iterator();

		while (it.hasNext()) {

			byte[] data = (byte[]) it.next();
			sb.append(new String(data, 0, data.length));
			// expires.put(sb.toString(), DEFAULT_TTL);
			redisCacheConfigurationMap.put(sb.toString(), this.getRedisCacheConfigurationWithTtl(DEFAULT_TTL));
			System.out.println("Keys-->" + sb);
		}
		return redisCacheConfigurationMap;
	}

	private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Long seconds) {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		// om.setVisibility(PropertyAccesso.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration = redisCacheConfiguration
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.entryTtl(Duration.ofSeconds(seconds));

		return redisCacheConfiguration;
	}
}

 *
 */

package com.netposa.rom.common.redis.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 提供redis 相关操作
 */
@Component
public class RedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	private static RedisTemplate<String, Object> redisTemplate;

	public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
		RedisUtil.redisTemplate = redisTemplate;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 */
	public void set(final String key, final Object value, long time, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value.toString(), time, timeUnit);
	}

	/**
	 * 写入hahs缓存
	 *
	 * @param key
	 * @param value
	 */
	public void hSet(final String hashkey, final String key, final Object value) {
		try {
			redisTemplate.boundHashOps(hashkey).put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 写入hash缓存
	 *
	 * @param key
	 * @param value
	 */
	public void hSet(final String hashKey, final String key, final Object value, long time, TimeUnit timeUnit) {
		try {
			BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(hashKey);
			ops.put(key, value);
			ops.expire(time, timeUnit);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	public Object hGet(final String hashkey, final String key) {
		try {
			return redisTemplate.boundHashOps(hashkey).get(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public boolean hExists(final String hashkey, final String key) {
		try {
			return redisTemplate.boundHashOps(hashkey).hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 */
	public void set(final String key, final Object value) {
		try {
			redisTemplate.opsForValue().set(key, value.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(final String key, Class<T> clazz) {
		try {
			return (T) redisTemplate.boundValueOps(key).get();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public Object getObj(final String key) {
		try {
			return redisTemplate.boundValueOps(key).get();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 删除，根据key精确匹配
	 *
	 * @param key
	 */
	public void del(final String... key) {
		try {
			redisTemplate.delete(Arrays.asList(key));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

	public void hDel(final String hashKey, final String... key) {
		// redisTemplate.boundHashOps(hashkey).delete(Arrays.asList(key));
		try {
			redisTemplate.boundHashOps(hashKey).delete(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除，根据key模糊匹配
	 *
	 * @param pattern
	 */
	public void delpn(final String... pattern) {
		for (String kp : pattern) {
			try {
				redisTemplate.delete(redisTemplate.keys(kp + "*"));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * key是否存在
	 *
	 * @param key
	 */
	public boolean exists(final String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return false;
	}

	public Set<String> keys(String pattern) {
		try {
			Set<String> keyS = redisTemplate.keys(pattern);
			if (keyS != null) {
				for (String str : keyS) {
					logger.debug("redis key:" + str);
				}
			}
			return keyS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public boolean setNx(String key, String value) {
		try {
			return redisTemplate.opsForValue().setIfAbsent(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return false;
	}

	public boolean expire(String key, long timeout, TimeUnit timeUnit) {
		try {
			return redisTemplate.expire(key, timeout, timeUnit);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return false;
	}

	public void delete(String key) {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

	public Map getHashMap(String key) {
		try {
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public void setHashMap(String key, Map map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

}

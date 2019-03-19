/**
 * 
 */
package work.waynelee.utils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 *
 * @author 李文庆
 * 2019年1月22日 下午4:33:52
 */
@Component
public class RedisUtils {
	
	@Autowired
	private RedisTemplate<String, Object> template;
	private static RedisTemplate<String, Object> redisTemplate;
	
	@PostConstruct
	private void init(){
		redisTemplate = this.template;
	}
	/**
	 * 添加String结构的数据到redis中
	 * @param key 键：唯一标识，从redis中取值的时候用到。
	 * @param value 值：保存的具体数据
	 * @return true 成功; false 失败
	 */
	public static boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加String结构的数据到redis中，并指定保存数据的时间
	 * @param key 键：唯一标识，从redis中取值的时候用到。
	 * @param value 值：保存的具体数据
	 * @param expireTime 过期时间：redis保存该数据的时间，超过该时间，将删除数据
	 * @return true 成功; false 失败
	 */
	public static boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据keys，批量删除对应的key的数据
	 * @param keys 键：（可变参数）
	 */
	public static void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 模糊批量删除数据
	 * @param pattern key的匹配规则，通配符：*：0到任意多个字 ；?：1个字符
	 */
	public static void removePattern(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除指定key的数据
	 * @param key 键
	 */
	public static void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断redis中是否有指定key存在
	 * @param key
	 * @return true 有;false 没有
	 */
	public static boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 根据指定的key获取value值
	 * @param key 键
	 * @return value 数据值
	 */
	public static Object get(final String key) {
		Object result = null;
		ValueOperations<String, Object> operations = redisTemplate
				.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 添加哈希Hash结构的数据到redis中
	 * @param key 键
	 * @param hashKey 哈希key
	 * @param value 数据值
	 */
	public static void hmSet(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}

	/**
	 * 通过key和哈希key获取数据
	 * @param key 键
	 * @param hashKey 哈希key
	 * @return value 数据值
	 */
	public static Object hmGet(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}

	/**
	 * 添加列表List数据到redis中
	 * @param key 键
	 * @param value 值
	 */
	public static void listPush(String key, Object value) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(key, value);
	}
	
	public static void lpush(String key, Object value){
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.leftPush(key, value);
	}
	public static void rpop(String key){
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPop(key);
	}
	/**
	 * 根据key和索引获取部分列表List数据
	 * @param key 键
	 * @param start 开始索引
	 * @param end 结束索引
	 * @return value 值
	 */
	public static List<Object> listRange(String key, long start, long end) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return list.range(key, start, end);
	}

	/**
	 * 添加集合Set数据到redis中
	 * @param key 键
	 * @param value 值
	 */
	public static void add(String key, Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 根据键key获取集合Set
	 * @param key 键
	 * @return value 值：Set<Object>
	 */
	public static Set<Object> setMembers(String key) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 添加ZSet有序集合到redis中
	 * @param key 键
	 * @param value 值
	 * @param scoure 长度
	 */
	public static void zsetAdd(String key, Object value, double scoure) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}

	/**
	 * 根据key和索引获取部分有序集合ZSet数据
	 * @param key 键
	 * @param scoure 开始索引
	 * @param scoure1 结束索引
	 * @return value 值
	 */
	public static Set<Object> rangeByScore(String key, double scoure, double scoure1) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.rangeByScore(key, scoure, scoure1);
	}
}

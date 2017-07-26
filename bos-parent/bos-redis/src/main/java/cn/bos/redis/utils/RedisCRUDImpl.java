package cn.bos.redis.utils;

import org.apache.activemq.leveldb.JsonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

@Component
public class RedisCRUDImpl implements RedisCRUD{

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public void writeJsonStringToRedis(String key, String value) {
		
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void writJsonObjectToString(String key, Object obj) {
		String js = JSON.toJSONString(obj);
		redisTemplate.opsForValue().set(key, js);
		
	}

	@Override
	public void writJsonObjectToRedisIncluds(String key, Object obj, SerializeFilter filter) {
		String js = JSON.toJSONString(obj, filter);
		redisTemplate.opsForValue().set(key, js);
	}

	@Override
	public void writJsonObjectToRedisIncluds(String key, Object obj, String... filters) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(filters);
		String js = JSON.toJSONString(obj, filter);
		redisTemplate.opsForValue().set(key, js);
	}

	@Override
	public String getJsonStringFromRedis(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}

	@Override
	public void deleteJsonKeyFromRedis(String key) {
		redisTemplate.delete(key);
	}

}

package cn.bos.redis.utils;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public interface RedisCRUD {
	
	public void writeJsonStringToRedis(String key,String str);
	
	public void writJsonObjectToString(String key, Object obj);
	
	public void writJsonObjectToRedisIncluds(String key,Object obj,SerializeFilter filter);
	
	public void writJsonObjectToRedisIncluds(String key,Object obj,String...filters);
	
	public String getJsonStringFromRedis(String key);
	
	public void deleteJsonKeyFromRedis(String key);
	
}

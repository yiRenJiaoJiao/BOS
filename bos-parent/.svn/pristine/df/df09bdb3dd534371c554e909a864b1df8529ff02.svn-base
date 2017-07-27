package cn.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bos.dao.CityDao;
import cn.bos.domain.qupai.City;
import cn.bos.redis.utils.RedisCRUD;
import cn.bos.service.CityService;
@Service
public class CityServiceImpl implements CityService{
	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private RedisCRUD RedisCRUD;
	
	@Override
	public String  findCityByQid(int qid) {
		String key = "city"+qid;
		String city = RedisCRUD.getJsonStringFromRedis(key);
		if(city==null){
			List<City> list = cityDao.findByQid(qid);
			RedisCRUD.writJsonObjectToString(key, list);
			city = RedisCRUD.getJsonStringFromRedis(key);
		}
		
	
		return city;
	}

}

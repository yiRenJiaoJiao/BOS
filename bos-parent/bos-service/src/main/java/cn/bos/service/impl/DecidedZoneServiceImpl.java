package cn.bos.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.DecidedZoneDao;
import cn.bos.dao.StaffDao;
import cn.bos.dao.SubareaDao;
import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Subarea;
import cn.bos.redis.utils.RedisCRUD;
import cn.bos.service.DecidedZoneService;

@Service
@Transactional
public class DecidedZoneServiceImpl implements DecidedZoneService{
	
	@Autowired
	private DecidedZoneDao decidedZoneDao;
	@Autowired
	private SubareaDao subareaDao;
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private RedisCRUD redisCRUD;
	
	/**
	 * 更新定区，保存分区
	 */
	@Override
	public void save(String[] ids, DecidedZone model) {
		decidedZoneDao.saveAndFlush(model);
		if(ids!=null && ids.length!=0){
			for (String id : ids) {
				subareaDao.updateSubareaDesidedZone(id,model.getId());
			}
		}
	}
	@Override
	public DecidedZone findOneById(String id) {
		DecidedZone decidedZone = decidedZoneDao.findOne(id);
		if(decidedZone!=null){
			Hibernate.initialize(decidedZone.getStaff());
		}
		return decidedZone;
	}
	@Override
	public String pageQuery(Pageable pageRequest, Specification<DecidedZone> specification) {
		
		int pageNumber = pageRequest.getPageNumber();
		int pageSize = pageRequest.getPageSize();
		String key = pageNumber+""+pageSize;
		String js = redisCRUD.getJsonStringFromRedis(key);
		if(js==null){
			Page<DecidedZone> page = decidedZoneDao.findAll(specification, pageRequest);
			List<DecidedZone> list = page.getContent();
			for (DecidedZone d : list) {
				Hibernate.initialize(d.getStaff());
			}
			
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("total", page.getTotalElements());
			map.put("rows", page.getContent());
			
			redisCRUD.writJsonObjectToString(key, map);
			js = redisCRUD.getJsonStringFromRedis(key);
			
		}
		
		return js;
	}
	@Override
	public void deleteDecidedZone(String id) {
		decidedZoneDao.delete(id);
	}
	/**
	 * 查询分区信息
	 */
	@Override
	public DecidedZone findOneAssociationById(String id) {
		DecidedZone decidedZone = decidedZoneDao.findOne(id);
		Hibernate.initialize(decidedZone.getStaff());
		Hibernate.initialize(decidedZone.getSubareas());
		return decidedZone;
	}
	
	/**
	 * 关联删除
	 */
	@Override
	public void deleteDecidedZoneAndAssocistion(String id) {
		
		//删除分区
		List<Subarea>  suBareaList = subareaDao.findSubareasByDecidedZoneId(id);
		for (Subarea subarea : suBareaList) {
		//	subareaDao.delete(subarea);
			subarea.setDecidedZone(null);
			subareaDao.save(subarea);
		}
		//删除定区
		decidedZoneDao.delete(id);	
	}
	@Override
	public void saveDecidedZone(DecidedZone model) {
		Staff staff = model.getStaff();
		staffDao.saveAndFlush(staff);
		decidedZoneDao.save(model);
		
	}
	
	

}

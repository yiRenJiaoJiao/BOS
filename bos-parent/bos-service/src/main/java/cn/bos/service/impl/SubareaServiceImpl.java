package cn.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.SubareaDao;
import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Subarea;
import cn.bos.service.SubareaService;
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	
	@Autowired
	private  SubareaDao subareaDao;
	
	@Override
	public Page<Subarea> pageQuery(Pageable pageRequest, Specification<Subarea> specification) {
		Page<Subarea> pages = subareaDao.findAll(specification, pageRequest);
		/*List<Subarea> content = pages.getContent();
			for (Subarea s : content) {
			//Hibernate.initialize(s.getRegion());
			System.out.println(s.getRegion());
		}*/
		return pages;
	}
	@Override
	public void importData(ArrayList<Subarea> subareas) {
		subareaDao.save(subareas);
	}
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
		
	}
	@Override
	public Page<Subarea> findAll(Pageable page) {
		
		
		 Page<Subarea> pages = subareaDao.findAll(page);
		 //初始化Region
		 List<Subarea> content = pages.getContent();
			for (Subarea s : content) {
				Hibernate.initialize(s.getRegion());
				//Hibernate.initialize(s.getDecidedZone());
			}
		return pages;
	}
	/**
	 * 根据id删除定区,此时涉及到分区的联合删除
	 */
	@Override
	public void deleteSubarea(String[] arrId) {
		for (String id : arrId) {
			/*List<DecidedZone> decidedZone = subareaDao.findDecidedZoneBySubareaId(id);
			for (DecidedZone d : decidedZone) {
				decidedZoneDao.delete(d);
			}*/
			subareaDao.delete(id);
			
			
		}
		
	}
	@Override
	public List<Subarea> findAll() {
		 List<Subarea> list = subareaDao.findAll();
		 for (Subarea s : list) {
			Hibernate.initialize(s.getRegion());
		}
		return list;
	}
	/**
	 * 获取所有的subarea对象
	 * @param specification
	 * @return
	 */
	@Override
	public List<Subarea> findSubarea(Specification<Subarea> specification) {
		return subareaDao.findAll(specification);
	}
	@Override
	public List<Subarea> findSubareaListNOAssosiation() {
		
		return subareaDao.findAllNoAssosiation();
	}
	@Override
	public List<Subarea> findAllByDecidedZoneId(String decidedZoneId) {
		
		List<Subarea> list =subareaDao.findSubareasByDecidedZoneId(decidedZoneId);
		for (Subarea subarea : list) {
			Hibernate.initialize(subarea.getRegion());
		}
		return list;
	}
	/*@Override
	public List<Subarea> findSubareaLists() {
		return subareaDao.findAll();
	}*/
	@Override
	public List<Subarea> findSubareaListsAssociationAndMySelf(String id) {
		
		return subareaDao.finAllAssosiationAndMyself(id);
	}
	
	

}

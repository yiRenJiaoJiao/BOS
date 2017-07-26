package cn.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.RegionDao;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Staff;
import cn.bos.service.RegionService;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private RegionDao regionDao;
	
	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}

	@Override
	public void importData(ArrayList<Region> regions) {
		regionDao.save(regions);
	}

	@Override
	public void save(Region model) {
		regionDao.save(model);
		
	}

	@Override
	public Page<Region> findAll(Pageable request) {
		return regionDao.findAll(request);
	}

	@Override
	public void deleteRegion(String[] ids) {
		for (String id : ids) {
			regionDao.delete(id);
		}
	}

	@Override
	public Region findOne(String id) {
		return regionDao.findOne(id);
	}

	@Override
	public Page<Region> pageQuery(Pageable request, Specification<Region> regions) {
		
		return regionDao.findAll(regions, request);
	}
	
	public List<Region> findRregionsList() {
		return regionDao.findAll();
	}
	/**
	 * 根据输入的q动态获取region的集合
	 */
	@Override
	public List<Region> findRregionsList(String q) {
		List<Region> list ;
		if(q!=null){
			list = regionDao.findAllByQ("%"+q+"%");
		}else{
			list =findRregionsList();
		}
		return list;
		
	}


	/*public List<Subareea> findSubareaList() {
		List<Region> list = regionDao.findAll();
		
		return list;
	}*/

	
	
}

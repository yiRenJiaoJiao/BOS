package cn.bos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.bos.domain.base.Region;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Subarea;

public interface RegionService {



	void save(Region model);

	Page<Region> findAll(Pageable request);

	void deleteRegion(String[] arrId);

	Region findOne(String id);

	Page<Region> pageQuery(Pageable request, Specification<Region> regions);

	List<Region> findRregionsList(String q);

	void importData(ArrayList<Region> regions);

	
}

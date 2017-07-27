package cn.bos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Subarea;

public interface SubareaService {

	Page<Subarea> pageQuery(Pageable pageRequest, Specification<Subarea> specification);

	void importData(ArrayList<Subarea> aubareas);

	void save(Subarea model);

	Page<Subarea> findAll(Pageable pageRequest);

	void deleteSubarea(String[] arrId);

	List<Subarea> findAll();

	List<Subarea> findSubarea(Specification<Subarea> specification);

	List<Subarea> findSubareaListNOAssosiation();

	List<Subarea> findAllByDecidedZoneId(String decidedZoneId);

	//List<Subarea> findSubareaLists();

	List<Subarea> findSubareaListsAssociationAndMySelf(String id);


}

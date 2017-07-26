package cn.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Staff;

public interface DecidedZoneService {

	void save(String[] ids, DecidedZone model);

	DecidedZone findOneById(String id);

	String pageQuery(Pageable pageRequest, Specification<DecidedZone> specification);

	void deleteDecidedZone(String id);

	DecidedZone findOneAssociationById(String id);

	void deleteDecidedZoneAndAssocistion(String id);

	void saveDecidedZone(DecidedZone model);

}

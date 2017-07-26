package cn.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Subarea;

public interface SubareaDao extends JpaRepository<Subarea, String> ,JpaSpecificationExecutor<Subarea>{
	@Query("from Region where id = ?")
	Region findRegion(String id);
	
	@Query("from Subarea where decidedZone is null")
	List<Subarea> findAllNoAssosiation();
	
	@Modifying
	@Query(nativeQuery=true ,value="update bc_subarea set DECIDEDZONE_ID = ?2 where id = ?1")
	//@Query("update  Subarea set  decidedZone = ?2 where id = ?1")
	void updateSubareaDesidedZone(String id, String decidedId);
	
	@Modifying
	@Query(nativeQuery=true ,value="select * from bc_subarea where DECIDEDZONE_ID = ?")
	List<Subarea> findSubareasByDecidedZoneId(String id);
	
	@Query(" from Subarea where decidedZone is null or DECIDEDZONE_ID = ?")
	List<Subarea> finAllAssosiationAndMyself(String id);



}

package cn.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.bos.domain.qupai.City;

public interface CityDao extends JpaRepository<City, Integer>{
	
	@Query("from City where pid = ?1")
	List<City> findByQid(int qid);

}

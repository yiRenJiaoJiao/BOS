package cn.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.bos.domain.base.Standard;
@Repository
public interface StandardDao extends JpaRepository<Standard, Integer>{
	/**
	 * 作废
	 * @param id
	 */
	@Modifying
	@Query("Update Standard set delTag = 0 where id = ?")
	public void updateDelTagById(int id);
	
	/**
	 * 还原
	 * @param id
	 */
/*	@Modifying
	@Query("Update Standard set delTag = 1 where id = ?")
	public void restoreDelTagById(int id);*/
	
	@Query("from Standard where name = ?")
	public Standard findOneByName(String name);
	
	/**
	 * standard中0启用，1作废，查询已经启用的标准
	 * @return
	 */
	@Query("from Standard where deltag = 1")
	public List<Standard> findAllByDelTag();
	
	/**
	 * 还原
	 * @param id
	 */
	@Modifying
	@Query("Update Standard set delTag = 1 where id = ?")
	public void updateDelTagSetDelTtag1(int parseInt);

}

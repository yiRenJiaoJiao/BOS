package cn.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.StandardDao;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;
import cn.bos.service.StandardService;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {
	
	@Autowired
	private StandardDao standardDao;
	/**
	 * 分页查询
	 */
	@Override
	public Page<Standard> findAll(PageRequest pageAble) {
		return (Page<Standard>) standardDao.findAll(pageAble);
	}
	
	/**
	 * 根据id保存标准
	 */
	@Override
	public void save(Standard model) {
		standardDao.save(model);
		
	}
	/**
	 * 根据id标记标准失效
	 */
	@Override
	public void updateDelTagById(String[] arr) {
		for (String id : arr) {
			standardDao.updateDelTagById(Integer.parseInt(id));
		}
	}
	

	/**
	 * 根据id 还原标准
	 */
	@Override
	public void updateDelTagByIds(String[] arr) {
		for (String id : arr) {
			standardDao.updateDelTagSetDelTtag1(Integer.parseInt(id));
		}
		
	}
	
	/**
	 * 根据id 还原标准
	 */
//	@Override
	/*public void restoreDelTagById(int id) {
		standardDao.restoreDelTagById(id);
		
	}*/
	
	/**
	 * 根据id获取对象的快递标准
	 */
	@Override
	public Standard findOne(int id) {
		return standardDao.findOne(id);
	}

	@Override
	public Standard findOneByName(Standard model) {
		return standardDao.findOneByName(model.getName());
	}

	@Override
	public List<Standard> findList() {
		List<Standard> list = standardDao.findAllByDelTag();
		return list;
	}

}

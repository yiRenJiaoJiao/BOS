package cn.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.StaffDao;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;
import cn.bos.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
	@Autowired
	private StaffDao staffDao;

	@Override
	public void save(Staff staff) {
		staffDao.save(staff);
	}
	/**
	 * 根据id获取staff
	 */
	@Override
	public Staff findOne(String id) {
		return staffDao.findOne(id);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Page<Staff> findAll(Pageable page) {
		return staffDao.findAll(page);
	}

	/**
	 * 根据id删除快递员
	 */
	@Override
	public void deleteStaff(String id) {
		staffDao.delete(id);
	}
	
	/**
	 * 批量更新staff状态改为启用
	 */
	@Override
	public void deleteStaffByUpdataDelTagTo1(String id) {
		staffDao.deleteStaffByUpdataDelTagTo1(id);
		
	}
	
	/**
	 *批量更新staff的状态
	 */
	@Override
	public void updateDelTagByIds(String[] arr) {
		for (String id : arr) {
			staffDao.updateDelTagByIds(id);
		}
		
	}

	/**
	 * 通过电话号码查询staff
	 */
	@Override
	public Staff findOneByTelephone(String id) {
		Staff staff = staffDao.findOne(id);
		return staff;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<Staff> pageQuery(Pageable request, Specification<Staff> specification) {
		
		return staffDao.findAll(specification, request);
	}
	@Override
	public List<Staff> findAllStaffInUse() {
		
		return staffDao.finAllInUse();
	}
	
	/**
	 * 获取所有的staff
	 */
	
	
	
}

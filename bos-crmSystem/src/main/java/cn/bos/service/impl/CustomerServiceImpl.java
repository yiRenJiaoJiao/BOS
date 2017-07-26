package cn.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bos.dao.CustomerDao;
import cn.bos.domain.base.Customers;
import cn.bos.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void save(Customers customer) {
		System.out.println("aaaa");
		
	}

	@Override
	public List<Customers> getIsInAssosiationCustomer(String id) {
		List<Customers> list = customerDao.findIsInUseAssosiationCustomer(id);
		return list;
	}

	@Override
	public List<Customers> getNoAssasiationCustomer() {
		List<Customers> list = customerDao.findNoAssosiationCustomer();
		return list;
	}

	@Override
	public void updateCustomer(String ids, String desidedZoneId) {
		//取消定区关联客户
		customerDao.cancelAssosiation(desidedZoneId);
		//更新
		if(ids!=null){
			String[] arr = ids.split(",");
			for (String customerId : arr) {
				customerDao.updateCustomer(Integer.parseInt(customerId),desidedZoneId);
			}
		}
		
		
	}

	@Override
	public void updateCustomerNoDesidedZoneId(String desidedZoneId) {
		//取消定区关联客户
		customerDao.cancelAssosiation(desidedZoneId);
	}
	
	
}

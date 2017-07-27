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
	public Customers save(Customers customer) {
		return  customerDao.save(customer);
		
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

	/**
	 * 根据电话获取用户信息
	 */
	@Override
	public Customers findOneCustomerByTelephone(String telephone) {
		return customerDao.findOneCustomer(telephone);
	}

	@Override
	public void updateCustomersAdressById(String customerId, String address) {
		Customers customer = customerDao.findCustomersById(customerId);
		customer.setAddress(address);
		customerDao.save(customer);
		
	}

	/*@Override
	public Customers findOneCustomersById(String id) {
		return customerDao.findCustomersById(id);
	}*/

	@Override
	public Customers findOneCustomersByAddress(String address) {
		
		return customerDao.findOneCustomerByAddress(address);
	}
	
	
}

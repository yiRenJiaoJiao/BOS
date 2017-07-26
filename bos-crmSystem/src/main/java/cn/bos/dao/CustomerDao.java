package cn.bos.dao;

import java.util.List;

import cn.bos.domain.base.Customers;

public interface CustomerDao {

	List<Customers> findIsInUseAssosiationCustomer(String id);

	List<Customers> findNoAssosiationCustomer();

	void updateCustomer(int i, String desidedZoneId);

	void cancelAssosiation(String desidedZoneId);

}

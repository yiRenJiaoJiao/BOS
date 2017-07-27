package cn.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.bos.dao.CustomerDao;
import cn.bos.domain.base.Customers;

@Repository
@SuppressWarnings("all")
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
	
	@Autowired
	public void SetSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获取定区已经关联的用户
	 */
	@Override
	public List<Customers> findIsInUseAssosiationCustomer(String id) {
		
		List<Customers> list = getHibernateTemplate().find("from Customers where decidedzoneId = ?",id);
	//	List<Customers> list = getSession().createSQLQuery("select * from customers where DECIDEDZONE_ID = ? ").setParameter(0, id).list();
		System.out.println("inUse="+list);
		return list;
	}
	
	/**
	 * 获取没有关联的用户
	 */
	@Override
	public List<Customers> findNoAssosiationCustomer() {
		List<Customers> list = getHibernateTemplate().find("from Customers where decidedzoneId is null");
		//List<Customers> list = getSession().createSQLQuery("select * from customers where DECIDEDZONE_ID is null").list();
		System.out.println("noUse="+list);
		return list;
	}
	
	/**
	 * 更新定区关联的客户信息
	 */
	
	@Override
	public void updateCustomer(int customerId, String desidedZoneId) {
		getSession().createQuery("update Customers set decidedzoneId = ?  where id = ?")
		.setParameter(0,desidedZoneId)
		.setParameter(1, customerId).executeUpdate();
	}

	@Override
	public void cancelAssosiation(String desidedZoneId) {
		getSession().createQuery("update Customers set decidedzoneId = null where decidedzoneId = ?")
		.setParameter(0, desidedZoneId).executeUpdate();
		
		
	}

	/**
	 * 根据电话号码获取用户信息
	 */
	@Override
	public Customers findOneCustomer(String telephone) {
		List<Customers> list = getHibernateTemplate().find("from Customers where telephone = ?",telephone);
		return list.isEmpty()? null:list.get(0);
	}

	@Override
	public Customers save(Customers customer) {
		Integer id = (Integer) getHibernateTemplate().save(customer);
		customer.setId(id);
		return customer;
	}

	@Override
	public Customers findCustomersById(String customerId) {
		List<Customers> list = getHibernateTemplate().find("from Customers where id= ?",customerId);
		return list.isEmpty()? null:list.get(0);
	}

	@Override
	public Customers findOneCustomerByAddress(String address) {
		List<Customers> list = getSession().createQuery("from Customers where address = ?").setParameter(0, address).list();
		return list.isEmpty()? null: list.get(0);
	}
	
}

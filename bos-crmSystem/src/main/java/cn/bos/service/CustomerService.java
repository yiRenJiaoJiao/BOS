package cn.bos.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import cn.bos.domain.base.Customers;

@Produces("*/*")
public interface CustomerService {
	/**
	 * 保存
	 * @param customer
	 */
	@POST
	@Path("/customer/save")
	@Consumes("application/xml")
	public void save(Customers customer);
	
	
	/**
	 * 获取地区已经关联的客户
	 */
	@GET
	@Path("/customer/getIsInAssosiationCustomer/{deSidedZoneId}")
	@Produces({"application/xml","application/json"})
	@Consumes({"application/xml","application/json"})
	public List<Customers> getIsInAssosiationCustomer(@PathParam("deSidedZoneId")String id);
	
	/**
	 * 获取未关联的客户
	 * @return
	 */
	@GET
	@Path("/customer/getNoAssasiationCustomer")
	@Produces({"application/xml","application/json"})
//	@Produces({MediaType.APPLICATION_XML ,MediaType.APPLICATION_JSON})
	public List<Customers> getNoAssasiationCustomer();
	
	/**
	 * 更新定区关联的客户
	 * @param customer
	 * @param desidedZoneId
	 */
	@PUT
	@Path("/customer/updateCustomer/{ids}/{desidedZoneId}")
	@Consumes({"application/xml","application/json"})
	public void updateCustomer(@PathParam("ids")String ids,@PathParam("desidedZoneId")String desidedZoneId);
	
	/**
	 * 更新定区关联的客户
	 * @param customer
	 * @param desidedZoneId
	 */
	@PUT
	@Path("/customer/updateCustomer/{desidedZoneId}")
	@Consumes({"application/xml","application/json"})
	public void updateCustomerNoDesidedZoneId(@PathParam("desidedZoneId")String desidedZoneId);
	
}

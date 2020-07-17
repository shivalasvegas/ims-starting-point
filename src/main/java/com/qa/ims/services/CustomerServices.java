package com.qa.ims.services;

// Creates Dao object connected to CrudServices
// Creates/reads/updates and deletes customers

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Customer;

public class CustomerServices implements CrudServices<Customer> {

	private Dao<Customer> customerDao;
	
	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<Customer> readAll() {
		return customerDao.readAll();
	}

	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}

	public Customer update(Customer customer) {
		return customerDao.update(customer);
	}

	public void delete(Long customer_id) {
		customerDao.delete(customer_id);
	}

	@Override
	public void calc(Long id, Customer customer) {
		// TODO Auto-generated method stub
		
	}

}

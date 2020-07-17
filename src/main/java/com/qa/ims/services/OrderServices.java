package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServices<Order>{
private Dao<Order> orderDao;
	
	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}
	

	public Order update(Order order) {
		return orderDao.update(order);
	}

	public void delete(Long order_id) {
		orderDao.delete(order_id);
	}

	@Override
	public void calc(Long order_id, Order order) {
		orderDao.calc(order_id, order);	
	}

}

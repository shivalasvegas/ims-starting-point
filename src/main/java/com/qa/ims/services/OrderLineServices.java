package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineServices implements CrudServices<OrderLine> {
	
private Dao<OrderLine> orderLineDao;
	
	public OrderLineServices(Dao<OrderLine> orderLineDao) {
		this.orderLineDao = orderLineDao;
	}
	
	public List<OrderLine> readAll() {
		return orderLineDao.readAll();
	}

	public OrderLine create(OrderLine orderLine) {
		return orderLineDao.create(orderLine);
	}

	public OrderLine update(OrderLine orderLine) {
		return orderLineDao.update(orderLine);
	}

	public void delete(Long orderLine_id) {
		orderLineDao.delete(orderLine_id);
	}


}

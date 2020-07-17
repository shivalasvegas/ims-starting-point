package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderLine;

@RunWith(MockitoJUnitRunner.class)

public class OrderLineServicesTest {
	
	@Mock
	private Dao<OrderLine> orderLineDao;
	
	@InjectMocks
	private OrderLineServices orderLineServices;
	
	@Test
	public void orderLineServicesCreate() {
		OrderLine orderLine = new OrderLine(1L, 1L, 1L, 2L, 2L, 15.00);
		orderLineServices.create(orderLine);
		Mockito.verify(orderLineDao, Mockito.times(1)).create(orderLine);
	}
	

}

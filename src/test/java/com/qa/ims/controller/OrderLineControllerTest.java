package com.qa.ims.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.services.OrderLineServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineControllerTest {
	
	@Mock
	private OrderLineServices orderLineServices;
	
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private OrderLineController orderLineController;

	
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderLineController).getInput();
		orderLineController.delete();
		Mockito.verify(orderLineServices, Mockito.times(1)).delete(1L);
	}

}

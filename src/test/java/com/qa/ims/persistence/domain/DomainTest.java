package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DomainTest {
	
	Domain domainCustomer = Domain.CUSTOMER;
	Domain domainItem = Domain.ITEM;
	Domain domainOrder = Domain.ORDER;
	Domain domainExit = Domain.EXIT;
	
	@Test
	public void customerTest() {
		assertTrue(domainCustomer.getDescription().toLowerCase().contains("customer"));
		assertFalse(domainItem.getDescription().toLowerCase().contains("customer"));
	}
	
	
	@Test
	public void itemTest() {

		assertTrue(domainItem.getDescription().toLowerCase().contains("item"));
	}
	
	@Test
	public void orderTest() {
	
		assertTrue(domainOrder.getDescription().toLowerCase().contains("order"));
	}
	
	@Test
	public void stopTest() {
		assertTrue(domainExit.getDescription().toLowerCase().contains("close"));
		
	}
	

}

package com.qa.ims.persistence.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import org.junit.Before;

public class OrderLineTest {
	
	private OrderLine orderLine;
	private OrderLine other;
	
	@Before
	public void setUp() {
		orderLine = new OrderLine();
		other = new OrderLine();
	}
	
	
	@Test
	public void testAdd() {
		long orderLine_id = 1L;
		long fk_customer_id = 2L;
		long fk_order_id = 3L;
		long fk_product_id = 4L;			
		long product_qty = 5L;
		double product_total = 15.00;
		
		//check for equality
		
		assertEquals(1L, orderLine_id);
		assertEquals(2L, fk_customer_id);
		assertEquals(3L, fk_order_id);
		assertEquals(4L, fk_product_id);
		assertEquals(5L, product_qty);
		assertEquals(15.00, product_total, 0.1);
		
		//check for false
		assertFalse(orderLine_id == 1L);
		assertFalse(fk_customer_id == 2L);
		assertFalse(fk_order_id == 3L);
		assertFalse(fk_product_id == 4L);
		assertFalse(product_qty == 5L);
		assertFalse(product_total == 15.00);
		
		//check for not null
		assertNotNull(orderLine_id);
		
		
	}

}

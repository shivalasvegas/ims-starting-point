package com.qa.ims.persistence.domain;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class OrderTest {

	private Order order;
	private Order other;

	@Before
	public void setUp() {
		order = new Order(1L, 2L, "2020-07-17", 45.00);
		other = new Order(1L, 2L, "2020-07-17", 45.00);
	}

	@Test
	public void testAdd() {
		long orderId = 1L;
		long fkCustomerId = 2L;
		String orderDate = "2020-07-17";
		double orderTotal = 45.00;

		// check for equality

		assertEquals(1L, orderId);
		assertEquals(2L, fkCustomerId);
		assertEquals("2020-07-17", orderDate);
		assertEquals(45.00, orderTotal, 0.1);

		// check for false
		assertFalse(orderId == 2L);
		assertFalse(fkCustomerId == 3L);
		assertFalse(orderDate.equals("2020-07-16"));
		assertFalse(orderTotal == 25.00);

		// check for not null
		assertNotNull(orderId);
		assertNotNull(fkCustomerId);
		assertNotNull(orderDate);
		assertNotNull(orderTotal);
	}

	@Test
	public void settersTest() {

		// check for not null
		assertNotNull(order.getId());
		assertNotNull(order.getFkCustomerId());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderTotal());

		order.setId(null);
		assertNull(order.getId());
		order.setFkCustomerId(null);

	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(2L, order.getFkCustomerId(), 0);
		assertEquals("2020-07-17", order.getOrderDate());
		assertEquals(45.00, order.getOrderTotal(), 0.0000000001);
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	// name tests
	@Test
	public void orderNameNullButOtherNameNotNull() {
		order.setFkCustomerId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void orderNamesNotEqual() {
		other.setFkCustomerId(1L);
		assertFalse(order.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		order.setFkCustomerId(null);
		other.setFkCustomerId(null);
		assertTrue(order.equals(other));
	}

	// id tests
	@Test
	public void nullId() {
		order.setId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		order.setId(null);
		other.setId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(order.equals(other));
	}


	// product quantity
	@Test
	public void nullOrderDate() {
		order.setOrderDate(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullOrderDateOnBoth() {
		order.setOrderDate(null);
		other.setOrderDate(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherOrderDateDifferent() {
		other.setOrderDate("2020-07-17");
		assertTrue(order.equals(other));
	}

	// product total

	@Test
	public void otherOrderTotalDifferent() {
		other.setOrderTotal(45.00);
		assertTrue(order.equals(other));
	}

	// constructor tests
	@Test
	public void constructor1WithoutId() {
		Order order = new Order("2020-07-17", 45.00);
		assertNull(order.getId());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderTotal());	
	}

	@Test
	public void constructor2WithoutId() {
		Order order = new Order("2020-07-17", 45.00, 2L);
		assertNull(order.getId());
		assertNotNull(order.getFkCustomerId());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderTotal());
	}

	@Test
	public void constructor3WithId() {
		Order order = new Order(1L, "2020-07-17", 45.00);
		assertNotNull(order.getId());
		assertNotNull(order.getOrderDate());
		assertNotNull(order.getOrderTotal());
		
	}
	
	@Test
	public void constructorEmpty() {
		Order order = new Order();
		assertNull(order.getId());
		
	}



	// to string tests
	@Test
	public void toStringTest() {
		String toString  = order.toString();
		assert(toString.contains("order total"));
	}

}

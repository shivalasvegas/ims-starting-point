package com.qa.ims.persistence.domain;

import org.junit.Test;
import static org.junit.Assert.*;

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
		long orderLineId = 1L;
		long fkCustomerId = 2L;
		long fkOrderId = 3L;
		long fkProductId = 4L;			
		long productQty = 5L;
		double productTotal = 15.00;
		
		//check for equality
		
		assertEquals(1L, orderLineId);
		assertEquals(2L, fkCustomerId);
		assertEquals(3L, fkOrderId);
		assertEquals(4L, fkProductId);
		assertEquals(5L, productQty);
		assertEquals(15.00, productTotal, 0.1);
		
		//check for false
		assertFalse(orderLineId == 2L);
		assertFalse(fkCustomerId == 3L);
		assertFalse(fkOrderId == 4L);
		assertFalse(fkProductId == 5L);
		assertFalse(productQty == 6L);
		assertFalse(productTotal == 15.00);
		
//		//check for not null
//		assertNotNull(orderLineId);
//		assertNotNull(fkCustomerId);
//		assertNotNull(fkOrderId);
//		assertNotNull(fkProductId);
//		assertNotNull(productQty);
//		assertNotNull(productTotal);
	}
	
	@Test
	public void settersTest() {
		
		//check for not null
		assertNotNull(orderLine.getId());
		assertNotNull(orderLine.getFkCustomerId());
		assertNotNull(orderLine.getFkOrderId());
		assertNotNull(orderLine.getFkProductId());
		assertNotNull(orderLine.getProductQty());
		assertNotNull(orderLine.getProductTotal());
		
		orderLine.setId(null);
		assertNull(orderLine.getId());
		orderLine.setFkCustomerId(null);
		assertNull(orderLine.getFkCustomerId());
		orderLine.setFkOrderId(null);
		assertNull(orderLine.getFkOrderId());
		orderLine.setFkProductId(null);
		assertNull(orderLine.getFkProductId());
		orderLine.setProductQty(null);
		assertNull(orderLine.getProductQty());
		
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orderLine.equals(new Object()));
	}
	
	@Test
	public void createOrderLineWithId() {
		assertEquals(1L, orderLine.getId(), 0);
		assertEquals(2L, orderLine.getFkCustomerId(), 0);
		assertEquals(3L, orderLine.getFkOrderId(), 0);
		assertEquals(4L, orderLine.getFkProductId(), 0);
		assertEquals(5L, orderLine.getProductQty(), 0);
		assertEquals(15.00, orderLine.getProductTotal(), 0.1);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(orderLine.equals(orderLine));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orderLine.equals(other));
	}
	
	
	//name tests
	@Test
	public void orderLineNameNullButOtherNameNotNull() {
		orderLine.setFkCustomerId(null);
		assertFalse(orderLine.equals(other));
	}
	
	@Test
	public void orderLineNamesNotEqual() {
		other.setFkCustomerId(1L);
		assertFalse(orderLine.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		orderLine.setFkCustomerId(null);
		other.setFkCustomerId(null);
		assertTrue(orderLine.equals(other));
	}
	
	//id tests
	@Test
	public void nullId() {
		orderLine.setId(null);
		assertFalse(orderLine.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		orderLine.setId(null);
		other.setId(null);
		assertTrue(orderLine.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(orderLine.equals(other));
	}
	
	
	//fk orderid
	@Test
	public void nullFkOrderId() {
		orderLine.setFkOrderId(null);
		assertFalse(orderLine.equals(other));
	}
	
	@Test
	public void nullFkOrderIdOnBoth() {
		orderLine.setFkOrderId(null);
		other.setFkOrderId(null);
		assertTrue(orderLine.equals(other));
	}
	
	@Test
	public void otherFkOrderIdDifferent() {
		other.setFkOrderId(1L);
		assertFalse(orderLine.equals(other));
	}
	
	// fk product id
	@Test
	public void nullFkProductId() {
		orderLine.setFkProductId(null);
		assertFalse(orderLine.equals(other));
	}
	
	@Test
	public void nullFkProductIdOnBoth() {
		orderLine.setFkProductId(null);
		other.setFkProductId(null);
		assertTrue(orderLine.equals(other));
	}
	
	@Test
	public void otherFkProductIdDifferent() {
		other.setFkProductId(1L);
		assertFalse(orderLine.equals(other));
	}
	
	
	// product quantity
		@Test
		public void nullProductQty() {
			orderLine.setProductQty(null);
			assertFalse(orderLine.equals(other));
		}
		
		@Test
		public void nullProductQtyOnBoth() {
			orderLine.setProductQty(null);
			other.setProductQty(null);
			assertTrue(orderLine.equals(other));
		}
		
		@Test
		public void otherProductQtyDifferent() {
			other.setProductQty(1L);
			assertTrue(orderLine.equals(other));
		}
		
		// product total
				
				@Test
				public void otherProductTotalDifferent() {
					other.setProductTotal(25.00);
					assertTrue(orderLine.equals(other));
				}
		
	//constructor tests
	@Test
	public void constructor1WithoutId() {
		OrderLine orderLine = new OrderLine(2L, 3L, 4L, 5L);
		assertNull(orderLine.getId());
		assertNotNull(orderLine.getFkCustomerId());
		assertNotNull(orderLine.getFkOrderId());
	}
	
	@Test
	public void constructor2WithoutId() {
		OrderLine orderLine = new OrderLine(2L, 3L, 4L, 5L, 15.00);
		assertNull(orderLine.getId());
		assertNotNull(orderLine.getFkCustomerId());
		assertNotNull(orderLine.getFkOrderId());
		assertNotNull(orderLine.getFkProductId());
		assertNotNull(orderLine.getProductQty());
		assertNotNull(orderLine.getProductTotal());
	}
	
	@Test
	public void constructor3WithId() {
		OrderLine orderLine = new OrderLine(1L, 2L, 3L, 4L, 5L, 15.00);
		assertNull(orderLine.getId());
		assertNotNull(orderLine.getFkCustomerId());
		assertNotNull(orderLine.getFkOrderId());
	}
	
	
	// hashcode
	@Test
	public void hashCodeTest() {
		assertEquals(orderLine.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		OrderLine orderLine = new OrderLine(null, null, null, null);
		OrderLine other = new OrderLine(null, null, null, null);
		assertEquals(orderLine.hashCode(), other.hashCode());
	}
	
	
	//to string tests
	@Test
	public void toStringTest() {
		String toString = "id:1  fkCustomerId:2  fkOrderId:3 fkProductId:4  productQty:5 productTotal:15.00";
		assertEquals(toString, orderLine.toString());
	}

}



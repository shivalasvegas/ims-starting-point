package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	private Product product;
	private Product other;
	
	@Before
	public void setUp() {
		product = new Product(1L, "Elsa", 15.00, 2L);
		other = new Product(1L, "Elsa", 15.00, 2L);
	}
	
	@SuppressWarnings({ "null", "unused" })
	@Test
	public void settersTest() {
		assertNotNull(product.getId());
		assertNotNull(product.getProduct_name());
		assertNotNull(product.getProduct_price());
		assertNotNull(product.getCategory_id());
		
		
		product.setId(null);
		assertNull(product.getId());
		product.setProduct_name(null);
		assertNull(product.getProduct_name());
		product.setProduct_price((Double) null);
		assertNull(product.getProduct_price());
		product.setCategory_id(null);
		assertNull(product.getCategory_id());
		
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(product.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(product.equals(new Object()));
	}
	
	@Test
	public void createProductWithId() {
		assertEquals(1L, product.getId(), 0);
		assertEquals("Elsa", product.getProduct_name());
		assertEquals (15.00, product.getProduct_price(), 0.00);
		assertEquals(2L, product.getCategory_id(), 0);
		
	}
	
	@Test
	public void checkEquality() {
		assertTrue(product.equals(product));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(product.equals(other));
	}
	
	
	//name tests
	@Test
	public void productNameNullButOtherNameNotNull() {
		product.setProduct_name(null);
		assertFalse(product.equals(other));
	}
	
	@Test
	public void productNamesNotEqual() {
		other.setProduct_name("rhys");
		assertFalse(product.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		product.setProduct_name(null);
		other.setProduct_name(null);
		assertTrue(product.equals(other));
	}
	
	//id tests
	@Test
	public void nullId() {
		product.setId(null);
		assertFalse(product.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		product.setId(null);
		other.setId(null);
		assertTrue(product.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(product.equals(other));
	}
	
	
	//Product price tests
	@SuppressWarnings("null")
	@Test
	public void nullProduct_price() {
		product.setProduct_price((Double) null);
		assertFalse(product.equals(other));
	}
	
	@SuppressWarnings("null")
	@Test
	public void nullProduct_priceOnBoth() {
		product.setProduct_price((Double) null);
		other.setProduct_price((Double) null);
		assertTrue(product.equals(other));
	}
	
	@Test
	public void otherProduct_priceDifferent() {
		other.setProduct_price(25.00);
		assertFalse(product.equals(other));
	}
	
	// Category tests
	@Test
	public void nullCategory_id() {
		product.setCategory_id(null);
		assertFalse(product.equals(other));
	}
	
	@Test
	public void nullCategory_idOnBoth() {
		product.setCategory_id(null);
		other.setCategory_id(null);
		assertTrue(product.equals(other));
	}
	
	@Test
	public void otherCategory_idDifferent() {
		other.setCategory_id(1L);
		assertFalse(product.equals(other));
	}
	
	
		
	//constructor tests
	@Test
	public void constructor1WithoutId() {
		Product product = new Product("Elsa", 15.00);
		assertNull(product.getId());
		assertNotNull(product.getProduct_name());
		assertNotNull(product.getProduct_price());
	}
	
	@Test
	public void constructor2WithoutId() {
		Product product = new Product("Elsa", 15.00, 1L);
		assertNull(product.getId());
		assertNotNull(product.getProduct_name());
		assertNotNull(product.getProduct_price());
		assertNotNull(product.getCategory_id());
	
	}
	
	@Test
	public void constructor3WithId() {
		Product product = new Product(1L, "Elsa", 15.00, 1L);
		assertNull(product.getId());
		assertNotNull(product.getProduct_name());
		assertNotNull(product.getProduct_price());
	}
	
	
	// hashcode
	@Test
	public void hashCodeTest() {
		assertEquals(product.hashCode(), other.hashCode());
	}
	
	
	@SuppressWarnings("null")
	@Test
	public void hashCodeTestWithNull() {
		@SuppressWarnings("null")
		Product product = new Product(null, (Double) null);
		Product other = new Product(null, (Double) null);
		assertEquals(product.hashCode(), other.hashCode());
	}
	
	
	//to string tests
	@Test
	public void toStringTest() {
		String toString = "id:1 product name:Elsa product price:15.00 category id:1L";
		assertEquals(toString, product.toString());
	}
}

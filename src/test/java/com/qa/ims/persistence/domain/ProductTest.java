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
	
	
	@Test
	public void settersTest() {
		assertNotNull(product.getId());
		assertNotNull(product.getProductName());
		assertNotNull(product.getProductPrice());
		assertNotNull(product.getFkCategoryId());
		
		
		product.setId(null);
		assertNull(product.getId());
		product.setProductName(null);
		assertNull(product.getProductName());
		product.setFkCategoryId(null);
		assertNull(product.getFkCategoryId());	
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
		assertEquals("Elsa", product.getProductName());
		assertEquals (15.00, product.getProductPrice(), 0.00);
		assertEquals(2L, product.getFkCategoryId(), 0);
		
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
		product.setProductName(null);
		assertFalse(product.equals(other));
	}
	
	@Test
	public void productNamesNotEqual() {
		other.setProductName("rhys");
		assertFalse(product.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		product.setProductName(null);
		other.setProductName(null);
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
	
	@Test
	public void otherProduct_priceDifferent() {
		other.setProductPrice(25.00);
		assertFalse(product.equals(other));
	}
	
	// Category tests
	@Test
	public void nullCategory_id() {
		product.setFkCategoryId(null);
		assertFalse(product.equals(other));
	}
	
	@Test
	public void nullCategory_idOnBoth() {
		product.setFkCategoryId(null);
		other.setFkCategoryId(null);
		assertTrue(product.equals(other));
	}
	
	@Test
	public void otherCategory_idDifferent() {
		other.setFkCategoryId(1L);
		assertFalse(product.equals(other));
	}
	
	
		
	//constructor tests
	@Test
	public void constructor1WithoutId() {
		Product product = new Product("Elsa", 15.00);
		assertNull(product.getId());
		assertNotNull(product.getProductName());
		assertNotNull(product.getProductPrice());
	}
	
	@Test
	public void constructor2WithoutId() {
		Product product = new Product("Elsa", 15.00, 1L);
		assertNull(product.getId());
		assertNotNull(product.getProductName());
		assertNotNull(product.getProductPrice());
		assertNotNull(product.getFkCategoryId());
	
	}
	
	@Test
	public void constructor3WithId() {
		Product product = new Product(1L, "Elsa", 15.00, 1L);
		assertNotNull(product.getId());
		assertNotNull(product.getProductName());
		assertNotNull(product.getProductPrice());
	}
	
	
	// hashcode
	@Test
	public void hashCodeTest() {
		assertEquals(product.hashCode(), other.hashCode());
	}
	
	
	
	//to string tests
	@Test
	public void toStringTest() {
		String toString = product.toString();
		assert(toString.contains("Product"));
	}
}

package com.qa.ims.persistence.domain;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;



public class CustomerTest {
	
	
	
	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1L, "Chris", "Perrins", "42 The Drive", "chrisP@hotmail.com", "12345");
		other = new Customer(1L, "Chris", "Perrins", "42 The Drive", "chrisP@hotmail.com", "12345");
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getForename());
		assertNotNull(customer.getSurname());
		assertNotNull(customer.getAddress());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getPassword());
		
		customer.setId(null);
		assertNull(customer.getId());
		customer.setForename(null);
		assertNull(customer.getForename());
		customer.setSurname(null);
		assertNull(customer.getSurname());
		customer.setAddress(null);
		assertNull(customer.getAddress());
		customer.setEmail(null);
		assertNull(customer.getEmail());
		customer.setPassword(null);
		assertNull(customer.getPassword());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(customer.equals(new Object()));
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, customer.getId(), 0);
		assertEquals("Chris", customer.getForename());
		assertEquals("Perrins", customer.getSurname());
		assertEquals("42 The Drive", customer.getAddress());
		assertEquals("chrisP@hotmail.com", customer.getEmail());
		assertEquals("12345", customer.getPassword());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(customer.equals(customer));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(customer.equals(other));
	}
	
	
	//name tests
	@Test
	public void customerNameNullButOtherNameNotNull() {
		customer.setForename(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void customerNamesNotEqual() {
		other.setForename("rhys");
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		customer.setForename(null);
		other.setForename(null);
		assertTrue(customer.equals(other));
	}
	
	//id tests
	@Test
	public void nullId() {
		customer.setId(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		customer.setId(null);
		other.setId(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(customer.equals(other));
	}
	
	
	//surname tests
	@Test
	public void nullSurname() {
		customer.setSurname(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullSurnameOnBoth() {
		customer.setSurname(null);
		other.setSurname(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void otherSurnameDifferent() {
		other.setSurname("thompson");
		assertFalse(customer.equals(other));
	}
	
	// address tests
	@Test
	public void nullAddress() {
		customer.setAddress(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullAddressOnBoth() {
		customer.setAddress(null);
		other.setAddress(null);
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void otherAddressDifferent() {
		other.setAddress("23 South Moles");
		assertFalse(customer.equals(other));
	}
	
	
	// email tests
		@Test
		public void nullEmail() {
			customer.setEmail(null);
			assertFalse(customer.equals(other));
		}
		
		@Test
		public void nullEmailOnBoth() {
			customer.setEmail(null);
			other.setEmail(null);
			assertTrue(customer.equals(other));
		}
		
		@Test
		public void otherEmailDifferent() {
			other.setEmail("dizzyP@mac.com");
			assertFalse(customer.equals(other));
		}
		
		// email password
				@Test
				public void nullPassword() {
					customer.setPassword(null);
					assertFalse(customer.equals(other));
				}
				
				@Test
				public void nullPasswordOnBoth() {
					customer.setPassword(null);
					other.setPassword(null);
					assertTrue(customer.equals(other));
				}
				
				@Test
				public void otherPasswordDifferent() {
					other.setPassword("45678");
					assertFalse(customer.equals(other));
				}
		
	//constructor tests
	@Test
	public void constructor1WithoutId() {
		Customer customer = new Customer("Chris", "Perrins");
		assertNull(customer.getId());
		assertNotNull(customer.getForename());
		assertNotNull(customer.getSurname());
	}
	
	@Test
	public void constructor2WithoutId() {
		Customer customer = new Customer("Chris", "Perrins", "42 The Drive", "chrisP@hotmail.com", "12345");
		assertNull(customer.getId());
		assertNotNull(customer.getForename());
		assertNotNull(customer.getSurname());
		assertNotNull(customer.getAddress());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getPassword());
	}
	
	@Test
	public void constructor3WithId() {
		Customer customer = new Customer(1L, "Chris", "Perrins");
		assertNotNull(customer.getId());
		assertNotNull(customer.getForename());
		assertNotNull(customer.getSurname());
	}
	
	
	// hashcode
	@Test
	public void hashCodeTest() {
		assertEquals(customer.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Customer customer = new Customer(null, null);
		Customer other = new Customer(null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	
	//to string tests
	@Test
	public void toStringTest1() {
		String toString = customer.toString();
		assert(toString.contains("id"));
	}
}

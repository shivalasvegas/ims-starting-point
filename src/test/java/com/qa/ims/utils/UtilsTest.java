package com.qa.ims.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;


public class UtilsTest {
	
	// need to change Utils() to public in Utils class to run
	Utils utils = new Utils();
	
	
	@Test
	public void testAdd() {
		final String MYSQL_URL = "34.105.145.205:3306";
		final String READ = "READ";
		final String CALC = "CALC";
		final String FALSE = "FALSE";
		
		//check for equality
		assertEquals("34.105.145.205:3306", MYSQL_URL);
		assertEquals("READ", READ);
		assertEquals("CALC", CALC);
		
		//check for false
		assertFalse(MYSQL_URL.equals(FALSE));
		assertFalse(READ.equals(FALSE));
		assertFalse(CALC.equals(FALSE));
		
		//check for not null
		assertNotNull(MYSQL_URL);
		assertNotNull(READ);
		assertNotNull(CALC);
		
	}
	
	@Test
	public void testGetRead() {
		final String READ = "READ";
		assertEquals(READ, Utils.getRead());	
	}
	
	@Test
	public void testGetCalc() {
		final String CALC = "CALC";
		assertEquals(CALC, Utils.getCalc());
	}
	

}

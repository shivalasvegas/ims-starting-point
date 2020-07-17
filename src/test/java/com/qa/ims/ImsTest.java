package com.qa.ims;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ImsTest {
	
	@Test
	public void testAdd() {
		String checkReturn = "return";
		String checkExit = "exit";
		String checkCreate = "create";
		boolean keepGoing = true;
		boolean keepOnGoing = true;
		boolean returnToDatabase = true;
		String username = "root";
		String password = "root";

		// check for equality
		assertEquals("return", checkReturn);
		assertEquals("exit", checkExit);
		assertEquals("create", checkCreate);
		assertEquals("root", username);
		assertEquals("root", password);
		

		// check for false
		assertFalse(checkReturn.equals("ret"));
		assertFalse(checkExit.equals("ex"));
		assertFalse(checkCreate.equals("crte"));
		assertFalse(username.equals("rt"));
		assertFalse(password.equals("rot"));

		// check for not null
		assertNotNull(checkReturn);
		assertNotNull(checkExit);
		assertNotNull(checkCreate);
		assertNotNull(username);
		assertNotNull(password);
		assertNotNull(keepGoing);
		assertNotNull(keepOnGoing);
		assertNotNull(returnToDatabase);
	}

}

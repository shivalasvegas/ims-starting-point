package com.qa.ims.controller;
//import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.services.ProductServices;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	@Mock
	private ProductServices productServices;
	
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private ProductController productController;

	
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(productController).getInput();
		productController.delete();
		Mockito.verify(productServices, Mockito.times(1)).delete(1L);
	}
}

package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProcuctServicesTest {
	
	@Mock
	private Dao<Product> productDao;
	
	@InjectMocks
	private ProductServices productServices;
	
	@Test
	public void productServicesCreate() {
		Product product = new Product(1L, "Bertie",15.00, 2L);
		productServices.create(product);
		Mockito.verify(productDao, Mockito.times(1)).create(product);
	}

}

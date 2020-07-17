package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Product;


public class ProductServices implements CrudServices<Product> {
	
private Dao<Product> productDao;
	
	public ProductServices(Dao<Product> productDao) {
		this.productDao = productDao;
	}
	
	public List<Product> readAll() {
		return productDao.readAll();
	}

	public Product create(Product product) {
		return productDao.create(product);
	}

	public Product update(Product product) {
		return productDao.update(product);
	}

	public void delete(Long product_id) {
		productDao.delete(product_id);
	}

	@Override
	public void calc(Long id, Product product) {
		// TODO Auto-generated method stub
		
	}
	
}

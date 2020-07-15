package com.qa.ims.controller;
import java.util.List;

import org.apache.log4j.Logger;


import com.qa.ims.persistence.domain.Product;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in product details for CRUD functionality
 *
 */
public class ProductController implements CrudController<Product>{
	
public static final Logger LOGGER = Logger.getLogger(ProductController.class);
	
	private CrudServices<Product> productService;
	
	public ProductController(CrudServices<Product> productService) {
		this.productService = productService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Reads all products to the logger
	 */
	@Override
	public List<Product> readAll() {
		List<Product> products = productService.readAll();
		for(Product product: products) {
			LOGGER.info(product.toString());
		}
		return products;
	}

	/**
	 * Creates a product by taking in user input
	 */
	@Override
	public Product create() {
		LOGGER.info("Please enter the product name");
		String product_name = getInput();
		LOGGER.info("Please enter the product price");
		double product_price = Double.valueOf(getInput());	
		LOGGER.info("Please enter the category id");
		Long fk_category_id = Long.valueOf(getInput());
		Product product = productService.create(new Product(product_name, product_price, fk_category_id));
		LOGGER.info("Product created");
		return product;
	}

	/**
	 * Updates an existing product by taking in user input
	 */
	@Override
	public Product update() {
		LOGGER.info("Please enter the id of the product you would like to update");
		Long product_id = Long.valueOf(getInput());
		
		LOGGER.info("Please enter the product name");
		String product_name = getInput();
		LOGGER.info("Please enter the product price");
		double product_price = Double.valueOf(getInput());
		LOGGER.info("Please enter the fk_category_id");
		Long fk_category_id = Long.valueOf(getInput());
		Product product = productService.update(new Product(product_id, product_name, product_price, fk_category_id));
		LOGGER.info("Product Updated");
		return product;
	}

	/**
	 * Deletes an existing product by the id of the product
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long product_id = Long.valueOf(getInput());
		productService.delete(product_id);
	}


	@Override
	public void calc() {
		// TODO Auto-generated method stub
		
	}
	

}

package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	
public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the date");
		String order_date = getInput();
		LOGGER.info("Please enter the customer id");
		Long fk_customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter an product id");
		Long fk_product_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the product quantity");
		Long product_qty = Long.valueOf(getInput());
		Order order = orderService.create(new Order(order_date, fk_customer_id, fk_product_id, product_qty));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the date");
		String order_date = getInput();
		LOGGER.info("Please enter the customer id");
		Long fk_customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter an product id");
		Long fk_product_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the product quantity");
		Long product_qty = Long.valueOf(getInput());
		Order order = orderService.create(new Order(order_id, order_date, fk_customer_id, fk_product_id, product_qty));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long order_id = Long.valueOf(getInput());
		orderService.delete(order_id);
	}
	
}

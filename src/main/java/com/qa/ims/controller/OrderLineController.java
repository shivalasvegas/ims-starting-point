package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderLineController implements CrudController<OrderLine> {
	
public static final Logger LOGGER = Logger.getLogger(OrderLineController.class);
	
	private CrudServices<OrderLine> orderLineService;
	
	public OrderLineController(CrudServices<OrderLine> orderLineService) {
		this.orderLineService = orderLineService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Reads all orderLines to the logger
	 */
	@Override
	public List<OrderLine> readAll() {
		List<OrderLine> orderLines = orderLineService.readAll();
		for(OrderLine orderLine: orderLines) {
			LOGGER.info(orderLine.toString());
		}
		return orderLines;
	}

	/**
	 * Creates a orderLine by taking in user input
	 */
	@Override
	public OrderLine create() {
		LOGGER.info("Please enter the customer id");
		Long fk_customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the order id");
		Long fk_order_id = Long.valueOf(getInput());
		LOGGER.info("Please enter an product id");
		Long fk_product_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the product quantity");
		Long product_qty = Long.valueOf(getInput());
		OrderLine orderLine = orderLineService.create(new OrderLine(fk_customer_id, fk_order_id, fk_product_id, product_qty));
		LOGGER.info("OrderLine created");
		return orderLine;
	}

	/**
	 * Updates an existing orderLine by taking in user input
	 */
	@Override
	public OrderLine update() {
		LOGGER.info("Please enter the id of the orderLine you would like to update");
		Long orderLine_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the customer id");
		Long fk_customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the order id");
		Long fk_order_id = Long.valueOf(getInput());
		LOGGER.info("Please enter an product id");
		Long fk_product_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the product quantity");
		Long product_qty = Long.valueOf(getInput());
		OrderLine orderLine = orderLineService.create(new OrderLine(orderLine_id, fk_customer_id, fk_order_id, fk_product_id, product_qty));
		LOGGER.info("OrderLine created");
		return orderLine;
	}

	/**
	 * Deletes an existing orderLine by the id of the orderLine
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the orderLine you would like to delete");
		Long orderLine_id = Long.valueOf(getInput());
		orderLineService.delete(orderLine_id);
	}
	

}

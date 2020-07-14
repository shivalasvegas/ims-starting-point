package com.qa.ims.persistence.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.Utils;

public class OrderDaoMysql implements Dao<Order>{
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String product_qty;

	public OrderDaoMysql(String username, String product_qty) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
		this.username = username;
		this.product_qty = product_qty;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String product_qty) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.product_qty = product_qty;
	}
	
	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		String order_date = resultSet.getString("order_date");
		Long fk_customer_id = resultSet.getLong("fk_customer_id");
		Long fk_product_id = resultSet.getLong("fk_product_id");
		Long product_qty = resultSet.getLong("product_qty");
		return new Order(order_id, order_date, fk_customer_id, fk_product_id, product_qty);
	}

	/**
	 * Reads all orders from the database
	 *
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	//Executes ReadLastest Query on database  selects order at id 1 throws exception
	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order in the database
	 *
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("Insert into orders(order_date, order_total, fk_customer_id, fk_product_id, product_qty) values('"
				+ order.getOrder_date()  
				+ "','" + order.getFk_customer_id() 
				+ "','" + order.getFk_product_id()
				+ "','" + order.getProduct_qty()
 				+ "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Select from order with id
	public Order readOrder(Long order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM orders where id = " + order_id);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 *
	 * @param order - takes in a order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set order_date ='" 
				+ order.getOrder_date() 
				+ "', fk_customer_id = '" + order.getFk_customer_id()
				+ "', fk_product_id = '" + order.getFk_product_id()
				+ "', product_qty = '" + order.getProduct_qty()
				+ "' where id =" + order.getId());
			return readOrder(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Follows Dao convention of long id (no uppercase)
	 * Deletes a order in the database
	 *
	 * @param id - id of the order
	 */
	@Override
	public void delete(long order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders where id = " + order_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}

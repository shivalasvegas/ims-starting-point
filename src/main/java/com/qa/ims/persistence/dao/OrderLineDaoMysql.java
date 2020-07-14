package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.utils.Utils;

public class OrderLineDaoMysql implements Dao<OrderLine> {
	
	public static final Logger LOGGER = Logger.getLogger(OrderLineDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String product_qty;

	public OrderLineDaoMysql(String username, String product_qty) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
		this.username = username;
		this.product_qty = product_qty;
	}

	public OrderLineDaoMysql(String jdbcConnectionUrl, String username, String product_qty) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.product_qty = product_qty;
	}
	
	OrderLine orderLineFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderLine_id = resultSet.getLong("orderLine_id");
		Long fk_customer_id = resultSet.getLong("fk_customer_id");
		Long fk_order_id = resultSet.getLong("fk_order_id");
		Long fk_product_id = resultSet.getLong("fk_product_id");
		Long product_qty = resultSet.getLong("product_qty");
		double product_total = resultSet.getLong("product_total");
		return new OrderLine(orderLine_id, fk_customer_id, fk_order_id, fk_product_id, product_qty, product_total);
	}

	/**
	 * Reads all orderLines from the database
	 *
	 * @return A list of orderLines
	 */
	@Override
	public List<OrderLine> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orderLines");) {
			ArrayList<OrderLine> orderLines = new ArrayList<>();
			while (resultSet.next()) {
				orderLines.add(orderLineFromResultSet(resultSet));
			}
			return orderLines;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	//Executes ReadLastest Query on database  selects orderLine at id 1 throws exception
	public OrderLine readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderLines ORDER BY orderLine_id DESC LIMIT 1");) {
			resultSet.next();
			return orderLineFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a orderLine in the database
	 *
	 * @param orderLine - takes in a orderLine object. id will be ignored
	 */
	@Override
	public OrderLine create(OrderLine orderLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("Insert into orderLines(fk_customer_id, fk_order_id, fk_product_id, product_qty) values('"
				+ orderLine.getFk_customer_id() 
				+ "','" + orderLine.getFk_order_id()
				+ "','" + orderLine.getFk_product_id()
				+ "','" + orderLine.getProduct_qty()
 				+ "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Select from orderLine with id
	public OrderLine readOrderLine(Long orderLine_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM orderLines where id = " + orderLine_id);) {
			resultSet.next();
			return orderLineFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a orderLine in the database
	 *
	 * @param orderLine - takes in a orderLine object, the id field will be used to
	 *                 update that orderLine in the database
	 * @return
	 */
	@Override
	public OrderLine update(OrderLine orderLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orderLines set orderLine ='"  
				+ "', fk_customer_id = '" + orderLine.getFk_customer_id()
				+ "', fk_product_id = '" + orderLine.getFk_product_id()
				+ "', product_qty = '" + orderLine.getProduct_qty()
				+ "' where id =" + orderLine.getId());
			return readOrderLine(orderLine.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Follows Dao convention of long id (no uppercase)
	 * Deletes a orderLine in the database
	 *
	 * @param id - id of the orderLine
	 */
	@Override
	public void delete(long orderLine_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, product_qty);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderLines where id = " + orderLine_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}


}

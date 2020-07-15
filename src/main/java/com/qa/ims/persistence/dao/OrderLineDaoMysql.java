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
	private String password;

	public OrderLineDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
		this.username = username;
		this.password = password;
	}

	public OrderLineDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	OrderLine orderLineFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderLine_id = resultSet.getLong("orderLine_id");
		Long fk_customer_id = resultSet.getLong("fk_customer_id");
		Long fk_order_id = resultSet.getLong("fk_order_id");
		Long fk_product_id = resultSet.getLong("fk_product_id");
		Long product_qty = resultSet.getLong("product_quantity");
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
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orderlines");) {
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

	// Executes ReadLastest Query on database selects orderLine at id 1 throws
	// exception
	public OrderLine readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderlines ORDER BY orderLine_id DESC LIMIT 1");) {
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
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
//			statement.executeUpdate(
//					"create trigger calc_productTotal before insert on joining for each row set new.product_total = select"
//							+ orderLine.getProduct_qty()
//							+ " * products.product_price from products where products.product_id = "
//							+ orderLine.getFk_product_id());

			statement.executeUpdate(
					"Insert into orderlines(fk_customer_id, fk_order_id, fk_product_id, product_quantity, product_total) values('"
							+ orderLine.getFk_customer_id() + "','" + orderLine.getFk_order_id() + "','"
							+ orderLine.getFk_product_id() + "','" + orderLine.getProduct_qty() + "','" + orderLine.getProduct_total() + "')");
			
			statement.executeUpdate(
					"update orderlines ord inner join products prod on prod.product_id = ord.fk_product_id set ord.product_total = ord.product_quantity * prod.product_price;");
		
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Select from orderLine with id
	public OrderLine readOrderLine(Long orderLine_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM orderlines where orderline_id = " + orderLine_id);) {
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
	 *                  update that orderLine in the database
	 * @return
	 */
	@Override
	public OrderLine update(OrderLine orderLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orderlines set orderLine ='" + "', fk_customer_id = '"
					+ orderLine.getFk_customer_id() + "', fk_product_id = '" + orderLine.getFk_product_id()
					+ "', product_quantity = '" + orderLine.getProduct_qty() + "' where orderline_id =" + orderLine.getId());
			return readOrderLine(orderLine.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Follows Dao convention of long id (no uppercase) Deletes a orderLine in the
	 * database
	 *
	 * @param id - id of the orderLine
	 */
	@Override
	public void delete(long orderLine_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderlines where orderline_id = " + orderLine_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}

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
		Long orderLineId = resultSet.getLong("orderLineId");
		Long fkCustomerId = resultSet.getLong("fkCustomerId");
		Long fkOrderId = resultSet.getLong("fkOrderId");
		Long fkProductId = resultSet.getLong("fkProductId");
		Long product_qty = resultSet.getLong("productQuantity");
		double productTotal = resultSet.getLong("productTotal");
		return new OrderLine(orderLineId, fkCustomerId, fkOrderId, fkProductId, product_qty, productTotal);
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
						.executeQuery("SELECT * FROM orderlines ORDER BY orderline_id DESC LIMIT 1");) {
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

			statement.executeUpdate(
					"Insert into orderlines(fk_customer_id, fk_order_id, fk_product_id, product_quantity, product_total) values('"
							+ orderLine.getFkCustomerId() + "','" + orderLine.getFkOrderId() + "','"
							+ orderLine.getFkProductId() + "','" + orderLine.getProductQty() + "','" + orderLine.getProductTotal() + "')");
			
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
	public OrderLine readOrderLine(Long orderLineId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM orderlines where orderline_id = " + orderLineId);) {
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
			statement.executeUpdate("update orderlines set fk_product_id = '" + orderLine.getFkProductId()
					+ "', product_quantity = '" + orderLine.getProductQty() + "' where orderline_id =" + orderLine.getId());
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
	public void delete(long orderLineId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderlines where orderline_id = " + orderLineId);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void calc(long id) {
		// TODO Auto-generated method stub
		
	}

}

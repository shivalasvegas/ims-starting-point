package com.qa.ims.persistence.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

public class CustomerDaoMysql implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public CustomerDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
		this.username = username;
		this.password = password;
	}

	public CustomerDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long customer_id = resultSet.getLong("customer_id");
		String forename = resultSet.getString("forename");
		String surname = resultSet.getString("surname");
		String address = resultSet.getString("address");
		String email = resultSet.getString("email");
		String password = resultSet.getString("password");
		return new Customer(customer_id, forename, surname, address, email, password);
	}

	/**
	 * Reads all customers from the database
	 *
	 * @return A list of customers
	 */
	@Override
	public List<Customer> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from customers");) {
			
			ArrayList<Customer> customers = new ArrayList<>();
			
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
			
			
			return customers;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		//return new ArrayList<>();
		return Collections.emptyList();
	}

	// Executes ReadLastest Query on database selects customer at id 1 throws
	// exception
	public Customer readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1");) {
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 *
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Customer create(Customer customer) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("Insert into customers(forename, surname, address, email, password) values('"
					+ customer.getForename() + "','" + customer.getSurname() + "','" + customer.getAddress() + "','"
					+ customer.getEmail() + "','" + customer.getPassword() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Select from customer with id
	public Customer readCustomer(Long customer_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();

				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM customers WHERE customer_id = " + customer_id)) {
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 *
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Customer update(Customer customer) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE customers SET forename = '" + customer.getForename() + "', surname ='"
					+ customer.getSurname() + "', address ='" + customer.getAddress() + "', email ='"
					+ customer.getEmail() + "', password ='" + customer.getPassword() + "' WHERE customer_id ="
					+ customer.getId());
			
			
			return readCustomer(customer.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Follows Dao convention of long id (no uppercase) Deletes a customer in the
	 * database
	 *
	 * @param id - id of the customer
	 */
	@Override
	public void delete(long customer_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from customers where customer_id = " + customer_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

//	@Override
//	public void calc(long id, Customer customer) {
//		// TODO Auto-generated method stub
//
//	}
	@Override
	public Customer calc(long id) {
		return null;
		// TODO Auto-generated method stub

	}
}

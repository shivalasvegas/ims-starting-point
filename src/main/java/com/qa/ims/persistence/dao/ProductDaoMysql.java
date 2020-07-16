package com.qa.ims.persistence.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

public class ProductDaoMysql implements Dao<Product>{
	
	public static final Logger LOGGER = Logger.getLogger(ProductDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public ProductDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
		this.username = username;
		this.password = password;
	}

	public ProductDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	
	Product productFromResultSet(ResultSet resultSet) throws SQLException {
		Long productId = resultSet.getLong("productId");
		Long fkCategoryId = resultSet.getLong("fkCategoryId");
		String productName = resultSet.getString("productName");
		double productPrice = resultSet.getDouble("productPrice");
		
		return new Product(productId, productName, productPrice, fkCategoryId);
	}

	/**
	 * Reads all products from the database
	 *
	 * @return A list of products
	 */
	@Override
	public List<Product> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from products");) {
			ArrayList<Product> products = new ArrayList<>();
			while (resultSet.next()) {
				products.add(productFromResultSet(resultSet));
			}
			return products;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	//Executes ReadLastest Query on database  selects product at id 1 throws exception
	public Product readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products ORDER BY product_id DESC LIMIT 1");) {
			resultSet.next();
			return productFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a product in the database
	 *
	 * @param product - takes in a product object. id will be ignored
	 */
	@Override
	public Product create(Product product) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("Insert into products(product_name, product_price, fk_category_id) values('"
				+ product.getProductName()  
				+ "','" + product.getProductPrice()
				+ "','" + product.getFkCategoryId()
 				+ "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Select from product with id
	public Product readProduct(Long productId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM products where product_id = " + productId);) {
			resultSet.next();
			return productFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a product in the database
	 *
	 * @param product - takes in a product object, the id field will be used to
	 *                 update that product in the database
	 * @return
	 */
	@Override
	public Product update(Product product) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update products set product_name ='" 
				+ product.getProductName()
				+ "', product_price = '" + product.getProductPrice()
				+ "', fk_category_id = '" + product.getFkCategoryId() 
				+ "' where product_id =" + product.getId());
			return readProduct(product.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Follows Dao convention of long id (no uppercase)
	 * Deletes a product in the database
	 *
	 * @param id - id of the product
	 */
	@Override
	public void delete(long productId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from products where product_id = " + productId);
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

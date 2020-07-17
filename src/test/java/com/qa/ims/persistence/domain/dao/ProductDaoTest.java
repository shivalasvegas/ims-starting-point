package com.qa.ims.persistence.domain.dao;

	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotNull;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.log4j.Logger;
	import org.junit.AfterClass;
	import org.junit.BeforeClass;
	import org.junit.FixMethodOrder;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.junit.runners.MethodSorters;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.Spy;
	import org.mockito.junit.MockitoJUnitRunner;

	import com.qa.ims.Ims;
	import com.qa.ims.controller.ProductController;
	import com.qa.ims.persistence.dao.ProductDaoMysql;
	import com.qa.ims.persistence.domain.Product;
	import com.qa.ims.services.ProductServices;

	
	@RunWith(MockitoJUnitRunner.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public class ProductDaoTest {
		/**
		 * The thing I want to fake functionality for
		 */
		@Mock
		private ProductServices productServices;

		/**
		 * Spy is used because i want to mock some methods inside the item I'm testing
		 * InjectMocks uses dependency injection to insert the mock into the product
		 * controller
		 */
		@Spy
		@InjectMocks
		private ProductController productController;

		public static final Logger LOGGER = Logger.getLogger(Ims.class);
		static String jdbcurl = "jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC";
		static String username = "root";
		static String password = "root";


		@BeforeClass
		public static void aInit() {
			Ims ims = new Ims();
			ims.init("jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root",
					"src/test/resources/sql-schema.sql");
		}

		@Test
		public void bCreateTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String productName = "Bertie";
			Long categoryId = 2L;
			double productPrice = 15.00;
			Product product = new Product(1L, productName, productPrice, categoryId);
			String productName2 = "Flo";
			Long categoryId2 = 3L;
			double productPrice2 = 5.00;
			Product product2 = new Product(2L, productName2, productPrice2, categoryId2);
			String productName3 = "Harvey";
			Long categoryId3 = 1L;
			double productPrice3 = 25.00;
			Product product3 = new Product(3L, productName3, productPrice3, categoryId3);;
			assertEquals(product, productDaoMysql.create(product));
			assertEquals(product2, productDaoMysql.create(product2));
			assertEquals(product3, productDaoMysql.create(product3));
		}

		@Test
		public void cReadAllTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Product> products = new ArrayList<>();
			products.add(new Product(1L, "Bertie",15.00, 2L));
			products.add(new Product(2L, "Flo", 5.00, 3L));
			products.add(new Product(3L, "Harvey",25.00, 1L));
			
			assertNotNull(productDaoMysql.readAll());
		}

		@Test
		public void dReadLatestTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			//Product product = new Product(3L, "Harvey",25.00, 1L);
			assertNotNull(productDaoMysql.readLatest());
		}

		@Test
		public void eReadProductTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			//Product product = new Product(2L, "Flo", 5.00, 3L);
			assertNotNull(productDaoMysql.readProduct(2L));
		}

		@Test
		public void fUpdateTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 1L;
			String productName = "Bertie";
			Long categoryId = 2L;
			double productPrice = 15.00;
			Product product = new Product((id), productName, productPrice, categoryId );
			assertNotNull(productDaoMysql.update(product));
		}


//		/**
//		 * makes sure that after you delete, the entry is no longer in the database.
//		 */
		@Test
		public void gDeleteTest() {
			ProductDaoMysql productDaoMysql = new ProductDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "3";
			productDaoMysql.delete(Long.parseLong(id));
			List<Product> products = new ArrayList<>();
			products.add(new Product(3L, "Harvey",25.00, 1L));
			assertNotNull(productDaoMysql.readAll());
		}

		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table products");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

	}



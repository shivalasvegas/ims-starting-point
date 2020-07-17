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
	import com.qa.ims.controller.CustomerController;
	import com.qa.ims.persistence.dao.CustomerDaoMysql;
	import com.qa.ims.persistence.domain.Customer;
	import com.qa.ims.services.CustomerServices;

	@RunWith(MockitoJUnitRunner.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)

	public class OrderDaoTest {

		@Mock
		private CustomerServices orderServices;

		@Spy
		@InjectMocks
		private CustomerController orderController;

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
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String firstName = "Vinesh";
			String surname = "Ghela";
			String address = "42 The pines, Jordan";
			String email = "vin@gmail.com";
			String password = "werty123";
			Customer order = new Customer(1L, firstName, surname, address, email, password);
			String firstName2 = "James";
			String surname2 = "Peach";
			String address2 = "3a forbes Row, Darlington";
			String email2 = "jamesP@hotmail.com";
			String password2 = "wer56dfdg";
			Customer order2 = new Customer(2L, firstName2, surname2, address2, email2, password2);
			String firstName3 = "Bob";
			String surname3 = "Perry";
			String address3 = "456 Down the Lane";
			String email3 = "bobbyP@aol.com";
			String password3 = "gjoghrgwgh";
			Customer order3 = new Customer(3L, firstName3, surname3, address3, email3, password3);
			assertEquals(order, orderDaoMysql.create(order));
			assertEquals(order2, orderDaoMysql.create(order2));
			assertEquals(order3, orderDaoMysql.create(order3));
		}

		@Test
		public void cReadAllTest() {
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Customer> orders = new ArrayList<>();
			orders.add(new Customer(1L, "Vinesh", "Ghela", "42 The pines, Jordan", "vin@gmail.com", "werty123"));
			orders.add(new Customer(2L, "James", "Peach", "3a forbes Row, Darlington", "jamesPeachy@hotmail.com", "wer56dfdg"));
			orders.add(new Customer(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh"));
			
			assertNotNull(orderDaoMysql.readAll());
		}

		@Test
		public void dReadLatestTest() {
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Customer order = new Customer(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh");
			assertEquals(order, orderDaoMysql.readLatest());
		}

		@Test
		public void eReadCustomerTest() {
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Customer order = new Customer(2L, "James", "Peach", "3a forbes Row, Darlington", "jamesPeachy@hotmail.com", "wer56dfdg");
			assertNotNull(orderDaoMysql.readCustomer(2L));
		}

		@Test
		public void fUpdateTest() {
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 1L;
			String firstName = "Vinesh";
			String surname = "Ghela";
			String address = "42 The pines, Jordan";
			String email = "vin@gmail.com";
			String password = "werty123";
			Customer order = new Customer((id), firstName, surname, address, email, password);
			assertEquals(order, orderDaoMysql.update(order));
		}


		@Test
		public void gDeleteTest() {
			CustomerDaoMysql orderDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "3";
			orderDaoMysql.delete(Long.parseLong(id));
			List<Customer> orders = new ArrayList<>();
			orders.add(new Customer(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh"));
			assertNotNull(orderDaoMysql.readAll());
		}

		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table orders");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

	}


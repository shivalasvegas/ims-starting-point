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
	import com.qa.ims.controller.OrderController;
	import com.qa.ims.persistence.dao.OrderDaoMysql;
	import com.qa.ims.persistence.domain.Order;
	import com.qa.ims.services.OrderServices;

	@RunWith(MockitoJUnitRunner.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)

	public class OrderDaoTest {

		@Mock
		private OrderServices orderServices;

		@Spy
		@InjectMocks
		private OrderController orderController;

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
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long fkCustomerId = 1L;
			String orderDate = "2020-07-17";
			double orderTotal = 45.00;
			//Order order = new Order(3L, 3L,"2017-02-01", 35.00);
			Order order = new Order(1L, fkCustomerId, orderDate, orderTotal);
			Long fkCustomerId2 = 2L;
			String orderDate2 = "2019-09-27";
			double orderTotal2 = 15.00;
			Order order2 = new Order(2L, fkCustomerId2, orderDate2, orderTotal2);
			Long fkCustomerId3 =3L;
			String orderDate3 = "2017-02-01";
			double orderTotal3 = 35.00;
			Order order3 = new Order(3L, fkCustomerId3, orderDate3, orderTotal3);
			assertEquals(order, orderDaoMysql.create(order));
			assertEquals(order2, orderDaoMysql.create(order2));
			assertEquals(order3, orderDaoMysql.create(order3));
		}

		@Test
		public void cReadAllTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(1L, 1L,"2020-07-17", 45.00));
			orders.add(new Order(2L, 2L,"2019-09-27", 15.00));
			orders.add(new Order(3L, 3L,"2017-02-01", 35.00));
			
			assertNotNull(orderDaoMysql.readAll());
		}

		@Test
		public void dReadLatestTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Order order = new Order(3L, 3L,"2017-02-01", 35.00);
			assertEquals(order, orderDaoMysql.readLatest());
		}

		@Test
		public void eReadOrderTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Order order = new Order(2L, 2L,"2019-09-27", 15.00);
			assertNotNull(orderDaoMysql.readOrder(2L));
		}

		@Test
		public void fUpdateTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 1L;
			Long fkCustomerId = 1L;
			String orderDate = "2020-07-17";
			double orderTotal = 45.00;
			Order order = new Order((id),fkCustomerId, orderDate, orderTotal);
			assertEquals(order, orderDaoMysql.update(order));
		}


		@Test
		public void gDeleteTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "3";
			orderDaoMysql.delete(Long.parseLong(id));
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(3L, 3L,"2017-02-01", 35.00));
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


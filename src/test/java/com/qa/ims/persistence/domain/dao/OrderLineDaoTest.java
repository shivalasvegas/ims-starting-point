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
	import com.qa.ims.controller.OrderLineController;
	import com.qa.ims.persistence.dao.OrderLineDaoMysql;
	import com.qa.ims.persistence.domain.OrderLine;
	import com.qa.ims.services.OrderLineServices;

	@RunWith(MockitoJUnitRunner.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)

	public class OrderLineDaoTest {
		/**
		 * The thing I want to fake functionality for
		 */
		@Mock
		private OrderLineServices orderLineServices;

		@Spy
		@InjectMocks
		private OrderLineController orderLineController;

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
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String firstName = "Vinesh";
			String surname = "Ghela";
			String address = "42 The pines, Jordan";
			String email = "vin@gmail.com";
			String password = "werty123";
			OrderLine orderLine = new OrderLine(1L, firstName, surname, address, email, password);
			String firstName2 = "James";
			String surname2 = "Peach";
			String address2 = "3a forbes Row, Darlington";
			String email2 = "jamesP@hotmail.com";
			String password2 = "wer56dfdg";
			OrderLine orderLine2 = new OrderLine(2L, firstName2, surname2, address2, email2, password2);
			String firstName3 = "Bob";
			String surname3 = "Perry";
			String address3 = "456 Down the Lane";
			String email3 = "bobbyP@aol.com";
			String password3 = "gjoghrgwgh";
			OrderLine orderLine3 = new OrderLine(3L, firstName3, surname3, address3, email3, password3);
			assertEquals(orderLine, orderLineDaoMysql.create(orderLine));
			assertEquals(orderLine2, orderLineDaoMysql.create(orderLine2));
			assertEquals(orderLine3, orderLineDaoMysql.create(orderLine3));
		}

		@Test
		public void cReadAllTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<OrderLine> orderLines = new ArrayList<>();
			orderLines.add(new OrderLine(1L, "Vinesh", "Ghela", "42 The pines, Jordan", "vin@gmail.com", "werty123"));
			orderLines.add(new OrderLine(2L, "James", "Peach", "3a forbes Row, Darlington", "jamesPeachy@hotmail.com", "wer56dfdg"));
			orderLines.add(new OrderLine(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh"));
			
			assertNotNull(orderLineDaoMysql.readAll());
		}

		@Test
		public void dReadLatestTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			OrderLine orderLine = new OrderLine(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh");
			assertEquals(orderLine, orderLineDaoMysql.readLatest());
		}

		@Test
		public void eReadOrderLineTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			OrderLine orderLine = new OrderLine(2L, "James", "Peach", "3a forbes Row, Darlington", "jamesPeachy@hotmail.com", "wer56dfdg");
			assertNotNull(orderLineDaoMysql.readOrderLine(2L));
		}

		@Test
		public void fUpdateTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 1L;
			String firstName = "Vinesh";
			String surname = "Ghela";
			String address = "42 The pines, Jordan";
			String email = "vin@gmail.com";
			String password = "werty123";
			OrderLine orderLine = new OrderLine((id), firstName, surname, address, email, password);
			assertEquals(orderLine, orderLineDaoMysql.update(orderLine));
		}


		@Test
		public void gDeleteTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "3";
			orderLineDaoMysql.delete(Long.parseLong(id));
			List<OrderLine> orderLines = new ArrayList<>();
			orderLines.add(new OrderLine(3L, "Bob", "Perry", "456 Down the Lane",  "bobbyP@aol.com", "gjoghrgwgh"));
			assertNotNull(orderLineDaoMysql.readAll());
		}

		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table orderLines");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

	}


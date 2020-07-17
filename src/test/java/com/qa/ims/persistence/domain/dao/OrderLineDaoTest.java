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
		static String productTotal = "root";


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
			Long fkCustomerId = 1L;
			Long fkOrderId = 1L;
			Long fkProductId = 1L;
			Long productQty = 2L;
			double productTotal = 15.00;
			OrderLine orderLine = new OrderLine(1L, fkCustomerId, fkOrderId, fkProductId, productQty, productTotal);
			Long fkCustomerId2 = 2L;
			Long fkOrderId2 = 2L;
			Long fkProductId2 = 2L;
			Long productQty2 = 3L;
			double productTotal2 = 10.00;
			OrderLine orderLine2 = new OrderLine(2L, fkCustomerId2, fkOrderId2, fkProductId2, productQty2, productTotal2);
			Long fkCustomerId3 = 3L;
			Long fkOrderId3 = 3L;
			Long fkProductId3 = 3L;
			Long productQty3 = 4L;
			double productTotal3 = 5.00;
			OrderLine orderLine3 = new OrderLine(3L, fkCustomerId3, fkOrderId3, fkProductId3, productQty3, productTotal3);
			assertEquals(orderLine, orderLineDaoMysql.create(orderLine));
			assertEquals(orderLine2, orderLineDaoMysql.create(orderLine2));
			assertEquals(orderLine3, orderLineDaoMysql.create(orderLine3));
		}

		@Test
		public void cReadAllTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<OrderLine> orderLines = new ArrayList<>();
			orderLines.add(new OrderLine(1L, 1L, 1L, 1L, 2L, 15.00));
			orderLines.add(new OrderLine(2L, 2L, 2L, 2L, 3L, 10.00));
			orderLines.add(new OrderLine(3L, 3L, 3L, 3L, 3L, 5.00));
			
			assertNotNull(orderLineDaoMysql.readAll());
		}

		@Test
		public void dReadLatestTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			OrderLine orderLine = new OrderLine(3L,3L, 3L, 3L, 3L, 5.00 );
			assertEquals(orderLine, orderLineDaoMysql.readLatest());
		}

		@Test
		public void eReadOrderLineTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			//OrderLine orderLine = new OrderLine(2L, 2L, 2L, 2L, 3L, 10.00);
			assertNotNull(orderLineDaoMysql.readOrderLine(2L));
		}

		@Test
		public void fUpdateTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 1L;
			Long fkCustomerId = 1L;
			Long fkOrderId = 1L;
			Long fkProductId = 1L;
			Long productQty = 2L;
			double productTotal = 15.00;
			OrderLine orderLine = new OrderLine((id), fkCustomerId, fkOrderId, fkProductId, productQty, productTotal);
			assertEquals(orderLine, orderLineDaoMysql.update(orderLine));
		}


		@Test
		public void gDeleteTest() {
			OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql(
					"jdbc:mysql://34.105.145.205:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "3";
			orderLineDaoMysql.delete(Long.parseLong(id));
			List<OrderLine> orderLines = new ArrayList<>();
			orderLines.add(new OrderLine(3L, 3L, 3L, 3L, 3L, 5.00));
			assertNotNull(orderLineDaoMysql.readAll());
		}

		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection(jdbcurl, username, productTotal);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table orderLines");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

	}


package com.qa.ims;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.controller.OrderLineController;
import com.qa.ims.controller.ProductController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ProductDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.dao.OrderLineDaoMysql;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.services.OrderLineServices;
import com.qa.ims.services.ProductServices;
import com.qa.ims.utils.Utils;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	
	private CustomerController customerController;
	private ProductController productController;
	private OrderController OrderController;
	private OrderLineController OrderLineController;
	
	private String checkReturn = "return";
	private String checkExit = "exit";
	private String checkCreate = "create";
	private boolean keepGoing;
	private boolean keepOnGoing = true;
	private boolean returnToDatabase = true;
	private String username;
	private String password;

	public void imsSystem() {
		LOGGER.info("Please enter your username: ");
		this.username = Utils.getInput();
		LOGGER.info("Pease enter your password: ");
		this.password = Utils.getInput();
		keepGoing= true;
		
		// Initialise database connection
		init(this.username, this.password);

		do {
			LOGGER.info("Which part of the database would you like to access?");
			Domain.printDomains();

			Domain domain = Domain.getDomain();
			if (Domain.getStringDomain().equals(checkExit)) {
				closeDb();
				LOGGER.info("Exiting the program ... Bye!");
				System.exit(0);
			}
			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction();

			switch (domain) {
			case CUSTOMER:
				this.customerController = new CustomerController(
						new CustomerServices(new CustomerDaoMysql(username, password)));
				keepGoing=true;
				domainLoop(customerController, action);

				break;
			case ITEM:
				this.productController = new ProductController(
						new ProductServices(new ProductDaoMysql(username, password)));
				keepGoing=true;
				domainLoop(productController, action);
				
				break;
			case ORDER:
				this.OrderController = new OrderController(
						new OrderServices(new OrderDaoMysql(username, password)));
				do {

						if (Action.getStringAction().equals(checkCreate)) {
						ProductController productControl = new ProductController(
								new ProductServices(new ProductDaoMysql(username, password)));

						this.OrderLineController = new OrderLineController(
								new OrderLineServices(new OrderLineDaoMysql(username, password)));
						keepGoing=true;
						doAction(OrderController, action);
						
						do {
							LOGGER.info("Choose from our range of amazing products!");
							action = Action.getReadAction();
							keepOnGoing=true;
							doAction(productControl, action);
							LOGGER.info("Redirecting you for more order information ...");
							LOGGER.info("Please enter your detailed order choice below:");	
							Action.printActions();
							action = Action.getAction();
							
							if (!Action.getStringAction().equals(checkExit) || !Action.getStringAction().equals(checkReturn)) {
								doAction(OrderLineController, action);
							}
							
							if (Action.getStringAction().equals(checkExit)) {
								closeDb();
								LOGGER.info("Exiting the program ... Bye!");
								System.exit(0);
							}
							
							if (Action.getStringAction().equals(checkReturn)) {
								LOGGER.info("Returning to order selection ... ");
								keepOnGoing = false;
							}
						
						} while (keepOnGoing);

					}
					
					if (!Action.getStringAction().equals(checkExit) || !Action.getStringAction().equals(checkReturn) || !Action.getStringAction().equals(checkCreate)) {
						doAction(OrderController, action);
						LOGGER.info("Please make another selection ... ");
						Action.printActions();
						action = Action.getAction();
					}
					
					
					
					if (Action.getStringAction().equals(checkExit)) {
						closeDb();
						LOGGER.info("Exiting the program ... Bye!");
						System.exit(0);

					}
					
					
					
					if (Action.getStringAction().equals(checkReturn)&&!keepOnGoing) {
						LOGGER.info("Returning to the database selection ... ");
						keepGoing = false;
					}
					
					

				} while (keepGoing);
				
				break;
			case EXIT:
				break;
			default:
				returnToDatabase = false;
				break;
			}
		} while (returnToDatabase);
	}

	public void doAction(CrudController<?> crudController, Action action) {

		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case CALC:
			crudController.calc();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		case EXIT:
			break;
		default:
			break;
		}

	}

	public void domainLoop(CrudController<?> crudController, Action action) {
		
		do {

			if (!Action.getStringAction().equals(checkExit) || !Action.getStringAction().equals(checkReturn)) {
				doAction(crudController, action);
				LOGGER.info("Please make another selection ... ");
				Action.printActions();
				action = Action.getAction();
			}
			
			if (Action.getStringAction().equals(checkExit)) {
				closeDb();
				LOGGER.info("Exiting the program ... Bye!");
				System.exit(0);
			}

				if (Action.getStringAction().equals(checkReturn)) {
				LOGGER.info("Returning to the database selection ... ");
				keepGoing = false;
			}

		} while (keepGoing);
		
	}
	
	/**
	 * To initialise the database schema. DatabaseConnectionUrl will default to
	 * localhost.
	 *
	 * @param username
	 * @param password
	 */
	public void init(String username, String password) {
		init("jdbc:mysql://" + Utils.MYSQL_URL + "/", username, password, "src/main/resources/sql-schema.sql");
	}

	public String readFile(String fileLocation) {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				stringBuilder.append(string);
				stringBuilder.append("\r\n");
			}
		} catch (IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
		return stringBuilder.toString();
	}

	/**
	 * To initialise the database with the schema needed to run the application
	 */
	public void init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);

				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);

			}
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * To close the database on user choosing EXIT
	 */
	public void closeDb() {
		closeDb("jdbc:mysql://" + Utils.MYSQL_URL + "/", this.username, this.password);
	}

	public void closeDb(String jdbcConnectionUrl, String username, String password) {
		this.username = username;
		this.password = password;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
		} catch (SQLException e) {

			LOGGER.error(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					LOGGER.info("Exiting database...");
					connection.close();
				}
			} catch (SQLException e) {

				LOGGER.error(e.getMessage());
			}
		}
	}
}

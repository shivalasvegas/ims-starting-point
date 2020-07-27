Coverage: 34%
IMS Project

IMS is a functional java application, using a GCP instance mysql.
It uses backend java CRUD statements to modify the GCP Mysql database.
IMS displays a user menu to the CLI.  Via the CLI you can choose to do CRUD (Create/Read/Update/Delete) tasks for Customers/Items/Orders 
and also exit the program.  It provides users access to creating and updating orders, items and customers.

To fork a copy of the application on github: [shivalasavegas](https://github.com/shivalasvegas/ims-starting-point

### Prerequisites for the project
Jira 
Git 
Git bash
Git hub
Eclipse
Java
Maven
Junit
GCP Mysql instance

### Requirements
The application requires the following to run:
Java
Eclipse (or another IDE)
Maven
Junit
GCP Sql instance


### Installing (Windows)
Java install: follow instructions https://www.java.com/en/download/help/windows_manual_download.xml
Eclipse install: https://www.eclipse.org/downloads/
For GCP: https://cloud.google.com/sql/docs/mysql/quickstart
Maven install: https://maven.apache.org/guides/getting-started/windows-prerequisites.html
Junit install: https://junit.org/junit4/faq.html#started_1

### Example from the CLI

INFO - id:2 forename:Amy surname:Propsaddress: 152 North Ave, Wealdon
INFO - id:3 forename:Theo surname:Haridesaddress: 23 Princess Ave, Muswill Hill
INFO - id:4 forename:Asherah surname:Yorkeaddress: 6 School Lane, Lulworth
INFO - id:6 forename:Florence surname:Doddingsaddress: Castle Heights, Florida
INFO - id:7 forename:Dave surname:Cormackaddress: The Wispers, Sutton
INFO - Please make another selection ... 
INFO - CREATE: To save a new item into the database
INFO - READ: To read an item from the database
INFO - UPDATE: To change an item already in the database
INFO - DELETE: To remove an item from the database
INFO - RETURN: To return to database selection
INFO - EXIT: Exit the program
INFO - CALC: To calculate your order total

## Running the tests
You can run tests through eclipse and git bash.
On git bash:cd ~/Documents/Path/ims-starting-point/src/test/ 
run: mvn test

On eclipse with the Maven builder tool installed you can run Junit tests.
The program can be tested as a whole unit, package, class, or small snippet of code.
There are different ways to run the tests from eclipse.  

### Unit Tests 

Junit tests are small units of test code that cover specific parts of the main source code.
example: 
@Test
	public void customerServicesCreate() {
		Order order = new Order(1L, 2L, "2020-07-17", 45.00);
		orderServices.create(order);
		Mockito.verify(orderDao, Mockito.times(1)).create(order);
	}
	
Here the junit test is accessing the CustomerServices class and testing it with mockito.

### Debugging:
Eclipse has a good debugging system, which can be used in conjunction with Junit tests for errors and failures.
Click the debug icon and choose which part of the programme you want to focus on.
The errors will be shown on the cli console. To the right the Task List console will show more information.
You can use the debug too to step into, over or return to different bits of code by using code markers on your code.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Nick Johnson** - *Refinements* - [nickrstewarttds](https://github.com/nickrstewarttds)
* **Shiva King** - * Smaller Refinements* - [shivalasavegas](https://github.com/shivalasvegas)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to Vinesh and Alan for all their hard work and support.


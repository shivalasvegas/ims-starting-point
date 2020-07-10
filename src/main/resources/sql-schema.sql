create database if not exists ims;
create table if not exists ims.customers(customer_id int(11) NOT NULL AUTO_INCREMENT,forename varchar(20) NOT NULL,surname varchar(20) NOT NULL,address varchar(100) NOT NULL,email varchar(20) NOT NULL,password varchar(20) NOT NULL,PRIMARY KEY (customer_id));
create table if not exists ims.categories(category_id int(11) NOT NULL AUTO_INCREMENT,category varchar(50) NOT NULL,PRIMARY KEY (category_id));
create table if not exists ims.products(product_id int(11) NOT NULL AUTO_INCREMENT,product_name varchar(20) NOT NULL,product_price decimal(7,2) NOT NULL,fk_category_id int(11) NOT NULL,PRIMARY KEY (product_id),KEY fk_category_id (fk_category_id),CONSTRAINT products_ibfk_1 FOREIGN KEY (fk_category_id) REFERENCES categories (category_id));
create table if not exists ims.orders(order_id int(11) NOT NULL AUTO_INCREMENT,order_date date NOT NULL,order_total decimal(7,2) NOT NULL,fk_customer_id int(11) NOT NULL,fk_product_id int(11) NOT NULL,PRIMARY KEY (order_id),KEY fk_customer_id (fk_customer_id),KEY fk_product_id (fk_product_id),CONSTRAINT orders_ibfk_1 FOREIGN KEY (fk_customer_id) REFERENCES customers (customer_id),CONSTRAINT orders_ibfk_2 FOREIGN KEY (fk_product_id) REFERENCES products (product_id));
create table if not exists ims.orderdetails(fk_order_id int(11) NOT NULL,fk_product_id int(11) NOT NULL,product_quantity int(11) NOT NULL,KEY fk_order_id (`fk_order_id`),KEY fk_product_id (`fk_product_id`),CONSTRAINT orderdetails_ibfk_1 FOREIGN KEY (fk_order_id) REFERENCES orders (order_id),CONSTRAINT orderdetails_ibfk_2 FOREIGN KEY (fk_product_id) REFERENCES products (product_id));
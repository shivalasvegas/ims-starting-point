create database if not exists ims_test;
CREATE TABLE IF NOT EXISTS ims_test.customers (customer_id int(11) NOT NULL AUTO_INCREMENT,forename varchar(50) NOT NULL,surname varchar(50) NOT NULL,address varchar(150) NOT NULL,email varchar(50) NOT NULL,password varchar(20) NOT NULL,PRIMARY KEY (customer_id));

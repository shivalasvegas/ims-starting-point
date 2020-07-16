create database if not exists ims_test;
CREATE TABLE IF NOT EXISTS ims_test.customers (customer_id int(11) NOT NULL AUTO_INCREMENT,forename varchar(20) NOT NULL,surname varchar(20) NOT NULL,address varchar(100) NOT NULL,email varchar(20) NOT NULL,password varchar(20) NOT NULL,PRIMARY KEY (customer_id));

package com.qa.ims.persistence.domain;

public class Product {
	// variables
		private Long product_id;
		private Long category_id;
		private String product_name;
		private double product_price;

		// constructors
		public Product(String product_name, double product_price) {
			this.product_name = product_name;
			this.product_price = product_price;
		}
		public Product(String product_name, double product_price) {
			this.product_name = product_name;
			this.product_price = product_price;
			this.address = address;
			this.email = email;
			this.password = password;
		}

		public Product(Long product_id, String product_name, double product_price) {
			this.product_id = product_id;
			this.product_name = product_name;
			this.product_price = product_price;
		}

		public Product(Long product_id, String product_name, double product_price, String address, String email, String password) {
			this.product_id = product_id;
			this.product_name = product_name;
			this.product_price = product_price;
			this.address = address;
			this.email = email;
			this.password = password;
		}

}

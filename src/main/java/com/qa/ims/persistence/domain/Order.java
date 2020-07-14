package com.qa.ims.persistence.domain;

public class Order {
	// variables
		private Long order_id;
		private String order_date;
		private double order_total;
		private Long fk_customer_id;
		private Long fk_product_id;
		private Long product_qty;
		private double product_total;

		// constructors
		public Order() {
			
		}
		public Order(String order_date, double order_total) {
			this.order_date = order_date;
			this.order_total = order_total;
		}
		public Order(String order_date, double order_total, Long fk_customer_id, Long fk_product_id, Long product_qty, double product_total) {
			this.order_date = order_date;
			this.order_total = order_total;
			this.fk_customer_id = fk_customer_id;
			this.fk_product_id = fk_product_id;
			this.product_qty = product_qty;
			this.product_total = product_total;
		}

		public Order(Long order_id, String order_date, double order_total) {
			this.order_id = order_id;
			this.order_date = order_date;
			this.order_total = order_total;
		}

		public Order(Long order_id, String order_date, double order_total, Long fk_customer_id, Long fk_product_id, Long product_qty, double product_total) {
			this.order_id = order_id;
			this.order_date = order_date;
			this.order_total = order_total;
			this.fk_customer_id = fk_customer_id;
			this.fk_product_id = fk_product_id;
			this.product_qty = product_qty;
			this.product_total = product_total;
		}
}

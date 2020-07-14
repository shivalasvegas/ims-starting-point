package com.qa.ims.persistence.domain;

public class OrderLine {
	// variables
			private Long orderLine_id;
			private Long fk_customer_id;
			private Long fk_order_id;
			private Long fk_product_id;			
			private Long product_qty;
			private double product_total;

			// constructors
			public OrderLine() {	
			}
			
			public OrderLine(Long fk_customer_id, Long fk_order_id, Long fk_product_id, Long product_qty) {
				this.fk_customer_id = fk_customer_id;
				this.fk_order_id = fk_order_id;
				this.fk_product_id = fk_product_id;
				this.product_qty = product_qty;
	
			}
			
			public OrderLine(Long orderLine_id, Long fk_customer_id, Long fk_order_id, Long fk_product_id, Long product_qty) {
				this.orderLine_id = orderLine_id;
				this.fk_customer_id = fk_customer_id;
				this.fk_order_id = fk_order_id;
				this.fk_product_id = fk_product_id;
				this.product_qty = product_qty
			}
			
			public OrderLine(Long fk_customer_id, Long fk_order_id, Long fk_product_id, Long product_qty, double product_total) {
				this.fk_customer_id = fk_customer_id;
				this.fk_order_id = fk_order_id;
				this.fk_product_id = fk_product_id;
				this.product_qty = product_qty;
				this.product_total = product_total;
			}
			
			public OrderLine(Long orderLine_id, Long fk_customer_id, Long fk_order_id, Long fk_product_id, Long product_qty, double product_total) {
				this.orderLine_id = orderLine_id;
				this.fk_customer_id = fk_customer_id;
				this.fk_order_id = fk_order_id;
				this.fk_product_id = fk_product_id;
				this.product_qty = product_qty;
				this.product_total = product_total;
			}
			

}

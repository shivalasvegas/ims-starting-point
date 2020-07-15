package com.qa.ims.persistence.domain;

public class OrderLine {
	// variables
			private Long orderLine_id;
			private Long fk_customer_id;
			private Long fk_order_id;
			private Long fk_product_id;			
			private Long product_qty;
			private double product_total = 0.00;

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
				this.product_qty = product_qty;
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

			
			//getters and setters
			public Long getId() {
				return orderLine_id;
			}

			public void setId(Long orderLine_id) {
				this.orderLine_id = orderLine_id;
			}

			public Long getFk_customer_id() {
				return fk_customer_id;
			}

			public void setFk_customer_id(Long fk_customer_id) {
				this.fk_customer_id = fk_customer_id;
			}

			public Long getFk_order_id() {
				return fk_order_id;
			}

			public void setFk_order_id(Long fk_order_id) {
				this.fk_order_id = fk_order_id;
			}

			public Long getFk_product_id() {
				return fk_product_id;
			}

			public void setFk_product_id(Long fk_product_id) {
				this.fk_product_id = fk_product_id;
			}

			public Long getProduct_qty() {
				return product_qty;
			}

			public void setProduct_qty(Long product_qty) {
				this.product_qty = product_qty;
			}

			public double getProduct_total() {
				return product_total;
			}

			public void setProduct_total(double product_total) {
				this.product_total = product_total;
			}
			
			// string to string
			@Override
			public String toString() {
				return "OrderLine [orderLine_id=" + orderLine_id + ", product_qty=" + product_qty + ", product_total="
						+ product_total + "]";
			}
			// hashcode and equals
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
				result = prime * result + ((fk_order_id == null) ? 0 : fk_order_id.hashCode());
				result = prime * result + ((fk_product_id == null) ? 0 : fk_product_id.hashCode());
				result = prime * result + ((orderLine_id == null) ? 0 : orderLine_id.hashCode());
				result = prime * result + ((product_qty == null) ? 0 : product_qty.hashCode());
				long temp;
				temp = Double.doubleToLongBits(product_total);
				result = prime * result + (int) (temp ^ (temp >>> 32));
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				OrderLine other = (OrderLine) obj;
				if (fk_customer_id == null) {
					if (other.fk_customer_id != null)
						return false;
				} else if (!fk_customer_id.equals(other.fk_customer_id))
					return false;
				if (fk_order_id == null) {
					if (other.fk_order_id != null)
						return false;
				} else if (!fk_order_id.equals(other.fk_order_id))
					return false;
				if (fk_product_id == null) {
					if (other.fk_product_id != null)
						return false;
				} else if (!fk_product_id.equals(other.fk_product_id))
					return false;
				if (orderLine_id == null) {
					if (other.orderLine_id != null)
						return false;
				} else if (!orderLine_id.equals(other.orderLine_id))
					return false;
				if (product_qty == null) {
					if (other.product_qty != null)
						return false;
				} else if (!product_qty.equals(other.product_qty))
					return false;
				if (Double.doubleToLongBits(product_total) != Double.doubleToLongBits(other.product_total))
					return false;
				return true;
			}
		
		
			
			

}

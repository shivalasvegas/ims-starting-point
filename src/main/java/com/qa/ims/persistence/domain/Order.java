package com.qa.ims.persistence.domain;


public class Order {
	// variables
		private Long order_id;
		private String order_date;
		private double order_total = 0.00;
		private Long fk_customer_id;
		private Long fk_orderline_id;
		

		// constructors
		public Order() {
			
		}
		public Order(String order_date, double order_total) {
			this.order_date = order_date;
			this.order_total = order_total;
		}
		
		public Order(String order_date, double order_total, Long fk_customer_id, Long fk_orderline_id) {
			this.order_date = order_date;
			this.order_total = order_total;
			this.fk_customer_id = fk_customer_id;
			this.fk_orderline_id = fk_orderline_id;
			
		}
		
		public Order(String order_date, Long fk_customer_id) {
			this.order_date = order_date;
			this.fk_customer_id = fk_customer_id;
			
		}
		
		public Order(String order_date, double order_total, Long fk_customer_id) {
			this.order_date = order_date;
			this.fk_customer_id = fk_customer_id;
			
		}

		public Order(Long order_id, String order_date, Long fk_customer_id) {
			this.order_id = order_id;
			this.order_date = order_date;
			this.fk_customer_id = fk_customer_id;
		
		
		}
		public Order(Long order_id, String order_date, double order_total) {
			this.order_id = order_id;
			this.order_date = order_date;
			this.order_total = order_total;
		}

		public Order(Long order_id, String order_date, Long fk_customer_id, Long fk_orderline_id) {
			this.order_id = order_id;
			this.order_date = order_date;
			this.fk_customer_id = fk_customer_id;
			this.fk_orderline_id = fk_orderline_id;
			
		}
		
		// getters and setters
		public Long getId() {
			return order_id;
		}
		public void setId(Long order_id) {
			this.order_id = order_id;
		}
		public String getOrder_date() {
			return order_date;
		}
		public void setOrder_date(String order_date) {
			this.order_date = order_date;
		}
		
		
		public double getOrder_total() {
			return order_total;
		}
		public void setOrder_total(double order_total) {
			this.order_total = order_total;
		}
		public Long getFk_customer_id() {
			return fk_customer_id;
		}
		public void setFk_customer_id(Long fk_customer_id) {
			this.fk_customer_id = fk_customer_id;
		}
		public Long getFk_product_id() {
			return fk_orderline_id;
		}
		public void setFk_product_id(Long fk_orderline_id) {
			this.fk_orderline_id = fk_orderline_id;
		}
		
		//Hashcode and equals
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fk_customer_id == null) ? 0 : fk_customer_id.hashCode());
			result = prime * result + ((fk_orderline_id == null) ? 0 : fk_orderline_id.hashCode());
			result = prime * result + ((order_date == null) ? 0 : order_date.hashCode());
			result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
			long temp;
			temp = Double.doubleToLongBits(order_total);
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
			Order other = (Order) obj;
			if (fk_customer_id == null) {
				if (other.fk_customer_id != null)
					return false;
			} else if (!fk_customer_id.equals(other.fk_customer_id))
				return false;
			if (fk_orderline_id == null) {
				if (other.fk_orderline_id != null)
					return false;
			} else if (!fk_orderline_id.equals(other.fk_orderline_id))
				return false;
			if (order_date == null) {
				if (other.order_date != null)
					return false;
			} else if (!order_date.equals(other.order_date))
				return false;
			if (order_id == null) {
				if (other.order_id != null)
					return false;
			} else if (!order_id.equals(other.order_id))
				return false;
			if (Double.doubleToLongBits(order_total) != Double.doubleToLongBits(other.order_total))
				return false;
			return true;
		}
		
		
		
		
		
		
		
		
}

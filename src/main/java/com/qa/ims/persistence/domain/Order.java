package com.qa.ims.persistence.domain;


public class Order {
	// variables
		private Long orderId;
		private String orderDate;
		private double orderTotal = 0.00;
		private Long fkCustomerId;

		// constructors
		public Order() {			
		}
		
		public Order(String orderDate, double orderTotal) {
			this.orderDate = orderDate;
			this.orderTotal = orderTotal;
		}
		
//		public Order(String orderDate, Long fkCustomerId) {
//			this.orderDate = orderDate;
//			this.fkCustomerId = fkCustomerId;
//			
//		}
//		
		
		public Order(String orderDate, double orderTotal, Long fkCustomerId) {
			this.orderDate = orderDate;
			this.orderTotal = orderTotal;
			this.fkCustomerId = fkCustomerId;

			
		}
		

		
		public Order(Long orderId, String orderDate, double orderTotal) {
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.orderTotal = orderTotal;
		}

		public Order(Long orderId, String orderDate, Long fkCustomerId) {
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.fkCustomerId = fkCustomerId;
		}
		
		public Order(Long orderId, Long fkCustomerId, String orderDate, double orderTotal) {
			this.orderId = orderId;
			this.orderDate = orderDate;
			this.orderTotal = orderTotal;
			this.fkCustomerId = fkCustomerId;
		}
		
		
		// getters and setters
		public Long getId() {
			return orderId;
		}
		public void setId(Long orderId) {
			this.orderId = orderId;
		}
		public String getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}
		
		
		public double getOrderTotal() {
			return orderTotal;
		}
		public void setOrderTotal(double orderTotal) {
			this.orderTotal = orderTotal;
		}
		
		public long getFkCustomerId() {		
			return fkCustomerId;
		}
		public void setFkCustomerId(Long fkCustomerId) {
			this.fkCustomerId = fkCustomerId;
		}
	
		//String-to-String
		@Override
		public String toString() {
			return "Order:: order id:" + orderId + "  order date:" + orderDate + "  order total:" + orderTotal;
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
			if (fkCustomerId == null) {
				if (other.fkCustomerId != null)
					return false;
			} else if (!fkCustomerId.equals(other.fkCustomerId))
				return false;
			if (orderDate == null) {
				if (other.orderDate != null)
					return false;
			} else if (!orderDate.equals(other.orderDate))
				return false;
			if (orderId == null) {
				if (other.orderId != null)
					return false;
			} else if (!orderId.equals(other.orderId))
				return false;
			if (Double.doubleToLongBits(orderTotal) != Double.doubleToLongBits(other.orderTotal))
				return false;
			return true;
		}
		
		
		
		
		
		
		
		
}

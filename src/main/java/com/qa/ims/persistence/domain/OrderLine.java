package com.qa.ims.persistence.domain;



public class OrderLine {
	
	
	
	// variables
			private Long orderLine_id;
			private Long fkCustomerId;
			private Long fkOrderId;
			private Long fkProductId;			
			private Long productQty;
			private double productTotal = 0.00;

			// constructors
			public OrderLine() {	
			}
			
			public OrderLine(Long fkCustomerId, Long fkOrderId, Long fkProductId, Long productQty) {
				this.fkCustomerId = fkCustomerId;
				this.fkOrderId = fkOrderId;
				this.fkProductId = fkProductId;
				this.productQty = productQty;
	
			}
			
			public OrderLine(Long orderLine_id, Long fkCustomerId, Long fkOrderId, Long fkProductId, Long productQty) {
				this.orderLine_id = orderLine_id;
				this.fkCustomerId = fkCustomerId;
				this.fkOrderId = fkOrderId;
				this.fkProductId = fkProductId;
				this.productQty = productQty;
			}
			
			public OrderLine(Long fkCustomerId, Long fkOrderId, Long fkProductId, Long productQty, double productTotal) {
				this.fkCustomerId = fkCustomerId;
				this.fkOrderId = fkOrderId;
				this.fkProductId = fkProductId;
				this.productQty = productQty;
				this.productTotal = productTotal;
			}
			
			public OrderLine(Long orderLine_id, Long fkCustomerId, Long fkOrderId, Long fkProductId, Long productQty, double productTotal) {
				this.orderLine_id = orderLine_id;
				this.fkCustomerId = fkCustomerId;
				this.fkOrderId = fkOrderId;
				this.fkProductId = fkProductId;
				this.productQty = productQty;
				this.productTotal = productTotal;
			}

			
			//getters and setters
			public Long getId() {
				return orderLine_id;
			}

			public void setId(Long orderLine_id) {
				this.orderLine_id = orderLine_id;
			}

			public Long getFkCustomerId() {
				return fkCustomerId;
			}

			public void setFkCustomerId(Long fkCustomerId) {
				this.fkCustomerId = fkCustomerId;
			}

			public Long getFkOrderId() {
				return fkOrderId;
			}

			public void setFkOrderId(Long fkOrderId) {
				this.fkOrderId = fkOrderId;
			}

			public Long getFkProductId() {
				return fkProductId;
			}

			public void setFkProductId(Long fkProductId) {
				this.fkProductId = fkProductId;
			}

			public Long getProductQty() {
				return productQty;
			}

			public void setProductQty(Long productQty) {
				this.productQty = productQty;
			}

			public double getProductTotal() {
				return productTotal;
			}

			public void setProductTotal(double productTotal) {
				this.productTotal = productTotal;
			}
			
			// string to string
			@Override
			public String toString() {
				return "OrderLine [orderLine_id=" + orderLine_id + ", productQty=" + productQty + ", productTotal="
						+ productTotal + "]";
			}
			// hashcode and equals
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((fkCustomerId == null) ? 0 : fkCustomerId.hashCode());
				result = prime * result + ((fkOrderId == null) ? 0 : fkOrderId.hashCode());
				result = prime * result + ((fkProductId == null) ? 0 : fkProductId.hashCode());
				result = prime * result + ((orderLine_id == null) ? 0 : orderLine_id.hashCode());
				result = prime * result + ((productQty == null) ? 0 : productQty.hashCode());
				long temp;
				temp = Double.doubleToLongBits(productTotal);
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
				if (fkCustomerId == null) {
					if (other.fkCustomerId != null)
						return false;
				} else if (!fkCustomerId.equals(other.fkCustomerId))
					return false;
				if (fkOrderId == null) {
					if (other.fkOrderId != null)
						return false;
				} else if (!fkOrderId.equals(other.fkOrderId))
					return false;
				if (fkProductId == null) {
					if (other.fkProductId != null)
						return false;
				} else if (!fkProductId.equals(other.fkProductId))
					return false;
				if (orderLine_id == null) {
					if (other.orderLine_id != null)
						return false;
				} else if (!orderLine_id.equals(other.orderLine_id))
					return false;
				if (productQty == null) {
					if (other.productQty != null)
						return false;
				} else if (!productQty.equals(other.productQty))
					return false;
				if (Double.doubleToLongBits(productTotal) != Double.doubleToLongBits(other.productTotal))
					return false;
				return true;
			}
		
		
			
			

}

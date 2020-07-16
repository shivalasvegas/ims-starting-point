package com.qa.ims.persistence.domain;

public class Product {
	// variables
		private Long productId;
		private Long fkCategoryId;
		private String productName;
		private double productPrice;

		// constructors
		public Product(String productName, double productPrice) {
			this.productName = productName;
			this.productPrice = productPrice;
		}
		

		public Product(Long productId, String productName, double productPrice, Long fkCategoryId) {
			this.productId = productId;
			this.fkCategoryId = fkCategoryId;
			this.productName = productName;
			this.productPrice = productPrice;
		}

		public Product(String productName, double productPrice, Long fkCategoryId) {
			this.fkCategoryId = fkCategoryId;
			this.productName = productName;
			this.productPrice = productPrice;
			
		}

		//getters and setters
		public Long getId() {
			return productId;
		}


		public void setId(Long productId) {
			this.productId = productId;
		}


		public Long getFkCategoryId() {
			return fkCategoryId;
		}


		public void setFkCategoryId(Long fkCategoryId) {
			this.fkCategoryId = fkCategoryId;
		}


		public String getProductName() {
			return productName;
		}


		public void setProductName(String productName) {
			this.productName = productName;
		}


		public double getProductPrice() {
			return productPrice;
		}


		public void setProductPrice(double productPrice) {
			this.productPrice = productPrice;
		}

		// string to string 
		
		@Override
		public String toString() {
			return "Product [productId=" + productId + ", productName=" + productName + ", productPrice="
					+ productPrice + "]";
		}


		//override hashcode and equals 
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fkCategoryId == null) ? 0 : fkCategoryId.hashCode());
			result = prime * result + ((productId == null) ? 0 : productId.hashCode());
			result = prime * result + ((productName == null) ? 0 : productName.hashCode());
			long temp;
			temp = Double.doubleToLongBits(productPrice);
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
			Product other = (Product) obj;
			if (fkCategoryId == null) {
				if (other.fkCategoryId != null)
					return false;
			} else if (!fkCategoryId.equals(other.fkCategoryId))
				return false;
			if (productId == null) {
				if (other.productId != null)
					return false;
			} else if (!productId.equals(other.productId))
				return false;
			if (productName == null) {
				if (other.productName != null)
					return false;
			} else if (!productName.equals(other.productName))
				return false;
			if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
				return false;
			return true;
		}

		
		
}

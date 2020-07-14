package com.qa.ims.persistence.domain;

public class Product {
	// variables
		private Long product_id;
		private Long fk_category_id;
		private String product_name;
		private double product_price;

		// constructors
		public Product(String product_name, double product_price) {
			this.product_name = product_name;
			this.product_price = product_price;
		}
		

		public Product(Long product_id, String product_name, double product_price, Long fk_category_id) {
			this.product_id = product_id;
			this.fk_category_id = fk_category_id;
			this.product_name = product_name;
			this.product_price = product_price;
		}

		public Product(String product_name, double product_price, Long fk_category_id) {
			this.fk_category_id = fk_category_id;
			this.product_name = product_name;
			this.product_price = product_price;
			
		}

		//getters and setters
		public Long getId() {
			return product_id;
		}


		public void setId(Long product_id) {
			this.product_id = product_id;
		}


		public Long getCategory_id() {
			return fk_category_id;
		}


		public void setCategory_id(Long fk_category_id) {
			this.fk_category_id = fk_category_id;
		}


		public String getProduct_name() {
			return product_name;
		}


		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}


		public double getProduct_price() {
			return product_price;
		}


		public void setProduct_price(double product_price) {
			this.product_price = product_price;
		}

		//override hashcode and equals 
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fk_category_id == null) ? 0 : fk_category_id.hashCode());
			result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
			result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
			long temp;
			temp = Double.doubleToLongBits(product_price);
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
			if (fk_category_id == null) {
				if (other.fk_category_id != null)
					return false;
			} else if (!fk_category_id.equals(other.fk_category_id))
				return false;
			if (product_id == null) {
				if (other.product_id != null)
					return false;
			} else if (!product_id.equals(other.product_id))
				return false;
			if (product_name == null) {
				if (other.product_name != null)
					return false;
			} else if (!product_name.equals(other.product_name))
				return false;
			if (Double.doubleToLongBits(product_price) != Double.doubleToLongBits(other.product_price))
				return false;
			return true;
		}

		
		
}

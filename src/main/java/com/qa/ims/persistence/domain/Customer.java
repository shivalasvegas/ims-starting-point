package com.qa.ims.persistence.domain;

public class Customer {
	// variables
	private Long customer_id;
	private String forename;
	private String surname;
	private String address;
	private String email;
	private String password;

	// constructors
	public Customer(String forename, String surname) {
		this.forename = forename;
		this.surname = surname;
	}

	public Customer(String forename, String surname, String address, String email, String password) {
		this.forename = forename;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public Customer(Long customer_id, String forename, String surname) {
		this.customer_id = customer_id;
		this.forename = forename;
		this.surname = surname;
	}

	public Customer(Long customer_id, String forename, String surname, String address, String email, String password) {
		this.customer_id = customer_id;
		this.forename = forename;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	// getters and setters
	public Long getId() {
		return customer_id;
	}

	public void setId(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "id:" + customer_id + " forename:" + forename + " surname:" + surname + "address: " + address;
	}

	// override methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());

		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Customer other = (Customer) obj;
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;

		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}

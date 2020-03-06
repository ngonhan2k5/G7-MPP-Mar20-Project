package g7.library.domain;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address address;
	
	public User(String firstName, String lastName, String phoneNumber, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFullName() {
		return firstName + lastName;
	}
}

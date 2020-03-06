package g7.library.domain;

import java.io.Serializable;

public final class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String state;
	private int zip;
	public Address(String street, String city, String state, int zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getZip() {
		return zip;
	}
	@Override
	public String toString() {
		return "(" + street + ", " + city + ", " + zip + ")";
		
	}
}
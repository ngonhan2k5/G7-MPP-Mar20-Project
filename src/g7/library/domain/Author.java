package g7.library.domain;

import java.util.HashSet;
import java.util.Set;

public final class Author extends User {
	private static final long serialVersionUID = 1L;
	private String bio;
	private Set<Book> books;
	
	public Author(String bio, String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.bio = bio;
		this.books = new HashSet<Book>();
	}
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}

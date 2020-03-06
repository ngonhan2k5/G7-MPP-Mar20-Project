package g7.library.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isbn;
	private String title;
	private int maxCheckoutLength;
	
	private Set<Author> authors;
	private Set<BookCopy> copies;
	
	public Book(String isbn, String title, int maxCheckoutLength) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = new HashSet<Author>();
		this.copies = new HashSet<BookCopy>();
	}
	
	public void addNewAuthor(Author author) {
		this.authors.add(author);
	}
	
	public Book makeABookCopy(int copyNumber) {
		copies.add(new BookCopy(this, copyNumber));
		return this;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public Set<BookCopy> getCopies() {
		return copies;
	}
	public void setCopies(Set<BookCopy> copies) {
		this.copies = copies;
	}
}
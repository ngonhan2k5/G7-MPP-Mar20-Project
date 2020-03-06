package g7.library.domain;

import java.io.Serializable;
import java.util.Objects;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Book book;
	private final int copyNumber;
	private boolean isAvailable;
	
	public BookCopy(Book book, int copyNumber) {
		if(book == null) throw new IllegalArgumentException("Book is null.");
		this.book = book;
		this.copyNumber = copyNumber;
		this.isAvailable = true;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Book getBook() {
		return book;
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + copyNumber;
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
		
		BookCopy other = (BookCopy) obj;
		return Objects.equals(book, other.book) && Objects.equals(copyNumber, other.copyNumber);
	}
	
	
}

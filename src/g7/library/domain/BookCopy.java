package g7.library.domain;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Book book;
	private final int copyNumber;
	private boolean isAvailable;
	
	public BookCopy(Book book, int copyNumber) {
		if(book == null) throw new IllegalArgumentException("Book is null.");
		this.book = book;
		this.copyNumber = copyNumber;
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
}

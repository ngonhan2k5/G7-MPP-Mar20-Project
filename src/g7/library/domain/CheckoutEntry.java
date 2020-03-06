package g7.library.domain;

import java.io.Serializable;
import java.util.Date;

public final class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Book book;
	private final Date checkoutDate;
	private final Date returnDueDate;
	
	private CheckoutEntry(Book book, Date checkoutDate, Date returnDueDate) {
		this.book = book;
		this.checkoutDate = checkoutDate;
		this.returnDueDate = returnDueDate;
	}
	
	public static CheckoutEntry newCheckoutEntry(Book book, Date checkoutDate, Date returnDueDate) {
		if(book == null) throw new IllegalArgumentException("Not Allowed to create CheckoutEntry.");
		return new CheckoutEntry(book, checkoutDate, returnDueDate);
	}
	
	public Book getBook() {
		return book;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public Date getReturnDueDate() {
		return returnDueDate;
	}
	
}

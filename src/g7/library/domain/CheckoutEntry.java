package g7.library.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public final class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private final BookCopy book;
	private final Date checkoutDate;
	private final Date returnDueDate;
	
	private CheckoutEntry(BookCopy book, Date checkoutDate, Date returnDueDate) {
		this.book = book;
		this.checkoutDate = checkoutDate;
		this.returnDueDate = returnDueDate;
	}
	
	public static CheckoutEntry newCheckoutEntry(BookCopy book, Date checkoutDate, Date returnDueDate) {
		if(book == null) throw new IllegalArgumentException("Not Allowed to create CheckoutEntry.");
		return new CheckoutEntry(book, checkoutDate, returnDueDate);
	}
	
	public BookCopy getBook() {
		return book;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public Date getReturnDueDate() {
		return returnDueDate;
	}

	public Date getDueDate() {

		if (checkoutDate == null || book.getBook() == null || book.getBook().getMaxCheckoutLength() < 1) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(checkoutDate);
		calendar.add(Calendar.DAY_OF_MONTH, book.getBook().getMaxCheckoutLength());
		return calendar.getTime();
	}

	public boolean isOverDue() {
		return checkoutDate != null && getDueDate().before(new Date());
	}
	
}

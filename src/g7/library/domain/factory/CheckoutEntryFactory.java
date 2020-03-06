package g7.library.domain.factory;

import java.util.Date;

import g7.library.dataaccess.DataLoader;
import g7.library.domain.Book;
import g7.library.domain.CheckoutEntry;

public class CheckoutEntryFactory {
	private Book book;
	
	private CheckoutEntryFactory(Book book) {
		this.book = book;
	}
	
	public static CheckoutEntryFactory getInstance(String bookIsbn) {
		Book book = DataLoader.getInstance().getBooks().get(bookIsbn);
		if(book == null) throw new IllegalArgumentException("Book not found.");
		return new CheckoutEntryFactory(book);
	}
	
	public CheckoutEntry createNewCheckoutEntry(Date checkoutDate, Date returnDueDate) {
		return CheckoutEntry.newCheckoutEntry(book, checkoutDate, returnDueDate);
	}

}

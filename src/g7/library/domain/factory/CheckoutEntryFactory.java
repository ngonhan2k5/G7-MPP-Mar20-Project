package g7.library.domain.factory;

import java.util.Date;
import java.util.Optional;

import g7.library.dataaccess.DataLoader;
import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.CheckoutEntry;

public class CheckoutEntryFactory {
	private BookCopy book;
	
	private CheckoutEntryFactory(BookCopy book) {
		this.book = book;
	}
	
	public static CheckoutEntryFactory getInstance(String bookIsbn) {
		Book book = DataLoader.getInstance().getBooks().get(bookIsbn);
		Optional<BookCopy> copy = null;
		if(book != null) {
			copy = book.getCopies().stream().filter(p -> p.isAvailable()).findFirst();
			if(!copy.isPresent())
				throw new IllegalArgumentException("Book not found.");
		}
			
		return new CheckoutEntryFactory(copy.get());
	}
	
	public CheckoutEntry createNewCheckoutEntry(Date checkoutDate, Date returnDueDate) {
		return CheckoutEntry.newCheckoutEntry(book, checkoutDate, returnDueDate);
	}

}

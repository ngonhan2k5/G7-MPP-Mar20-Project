package g7.library.frontcontroller;

import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.service.LibraryServiceInterface;
import g7.library.service.impl.LibraryServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LibraryController {
	private LibraryServiceInterface libraryService = new LibraryServiceImpl();

	public Book findBookByISBN(String isbn) {
		return libraryService.searchBookByIBSN(isbn);
	}

	public Collection<Book> findAllBooks() {
		return libraryService.fetchAllBooks().values();
	}

	public Set<BookCopy> findAllBookCopies() {
		return libraryService.fetchAllBookCopies();
	}
}

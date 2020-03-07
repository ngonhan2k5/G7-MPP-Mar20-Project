package g7.library.frontcontroller;

import java.util.Collection;
import java.util.Set;

import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.LoginCredentials;
import g7.library.domain.SystemUser;
import g7.library.model.BookSearchCriteria;
import g7.library.service.AdvancedSearchInterface;
import g7.library.service.LibraryServiceInterface;
import g7.library.service.impl.AdvancedSearchImpl;
import g7.library.service.impl.LibraryServiceImpl;

public class LibraryController {
	private LibraryServiceInterface libraryService = new LibraryServiceImpl();
	private AdvancedSearchInterface searchService = new AdvancedSearchImpl();

	public Book findBookByISBN(String isbn) {
		return libraryService.searchBookByIBSN(isbn);
	}

	public Collection<Book> findAllBooks() {
		return libraryService.fetchAllBooks().values();
	}

	public Set<BookCopy> findAllBookCopies() {
		return libraryService.fetchAllBookCopies();
	}
	
	public SystemUser login(String loginUserName, String password) {
		return libraryService.login(new LoginCredentials(loginUserName, password));
	}	
	
	public Set<Book> searchBook(String searchString) {
		return searchService.searchBook(new BookSearchCriteria().withSearchString(searchString));
	}
}

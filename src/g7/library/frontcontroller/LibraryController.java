package g7.library.frontcontroller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.LibraryMember;
import g7.library.domain.LoginCredentials;
import g7.library.domain.SystemUser;
import g7.library.model.BookSearchCriteria;
import g7.library.model.SearchUserCriteria;
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
		return Optional.ofNullable(libraryService.fetchAllBooks())
				.map(Map::values)
				.orElse(Collections.emptyList());
	}

	public Set<BookCopy> findAllBookCopies() {
		return libraryService.fetchAllBookCopies();
	}
	
	public SystemUser login(String loginUserName, String password) {
		return libraryService.login(new LoginCredentials(loginUserName, password));
	}
	
	public Set<Book> searchBook(String searchString) {
		if("".equals(searchString))
			return new HashSet<Book>(findAllBooks());
		return searchService.searchBook(new BookSearchCriteria().withSearchString(searchString));
	}
	
	public Set<LibraryMember> searchLibraryMember(String searchString) {
		if("".equals(searchString))
			return new HashSet<LibraryMember>(findAllMembers());
		return searchService.searchLibraryMember(new SearchUserCriteria().withSearchString(searchString));
	}

	public LibraryMember findMemberById(String memberId) {
		return libraryService.searchLibraryMemberById(memberId);
	}

	public Collection<LibraryMember> findAllMembers() {
		return Optional.ofNullable(libraryService.fetchAllMembers())
				.map(Map::values)
				.orElse(Collections.emptyList());
	}
	
	public SaveMessage checkoutBook(String bookIsbn, String memberId) {
		Book book = findBookByISBN(bookIsbn);
		
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, book.getMaxCheckoutLength());
		
		Date checkoutDate = new Date();
		Date returnDueDate = ca.getTime();
		
		return libraryService.checkoutBook(bookIsbn, memberId, checkoutDate, returnDueDate);
	}
	
	public SaveMessage saveBook(Book book) {
		return libraryService.saveBook(book);
	}
}

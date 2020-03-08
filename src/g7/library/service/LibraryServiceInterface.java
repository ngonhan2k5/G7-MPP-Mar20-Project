package g7.library.service;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.CheckoutRecord;
import g7.library.domain.LibraryMember;
import g7.library.domain.LoginCredentials;
import g7.library.domain.SystemUser;
import g7.library.domain.User;

public interface LibraryServiceInterface {
	public SystemUser login(LoginCredentials credentials) throws RuntimeException;
	
	public SaveMessage addNewLibraryMember(LibraryMember libraryMember) throws RuntimeException;
	
	public SaveMessage checkoutBook(String bookIsbn, String memberId, Date checkoutDate, 
			Date returnDueDate) throws RuntimeException;
	
	public SaveMessage saveUser(User user) throws RuntimeException;
	
	public SaveMessage saveBook(Book book) throws RuntimeException;
	
	public Book searchBookByIBSN(String ibsn);
	
	public CheckoutRecord retrieveCheckoutRecord(String memberId);
	
	public Map<String, LibraryMember> fetchAllMembers();
	
	public Map<String, Book> fetchAllBooks();
	
	public Set<BookCopy> fetchAllBookCopies();
	
}

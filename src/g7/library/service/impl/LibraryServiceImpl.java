package g7.library.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import g7.library.dataaccess.SingletoneDataLoader;
import g7.library.dataaccess.SerializableDataPersistor;
import g7.library.dataaccess.SerializableDataPersistor.SaveMessage;
import g7.library.dataaccess.storage.StorageType;
import g7.library.domain.Book;
import g7.library.domain.BookCopy;
import g7.library.domain.CheckoutRecord;
import g7.library.domain.LibraryMember;
import g7.library.domain.LoginCredentials;
import g7.library.domain.SystemUser;
import g7.library.domain.User;
import g7.library.domain.factory.LibraryMemberFactory;
import g7.library.service.LibraryServiceInterface;

public class LibraryServiceImpl implements LibraryServiceInterface {

	@Override
	public SystemUser login(LoginCredentials credentials) throws RuntimeException {
		for(SystemUser u : SingletoneDataLoader.getInstance().getSystemUsers().values()) {
			if(u.login(credentials) != null) return u;
		}
		return null;
	}

	@Override
	public SaveMessage addNewLibraryMember(LibraryMember libraryMember) throws RuntimeException {
		SingletoneDataLoader.getInstance().getLibraryMember().put(libraryMember.getMemberId(), libraryMember);
		return new SerializableDataPersistor<Map<String, LibraryMember>>(
				StorageType.MEMBERS, SingletoneDataLoader.getInstance().getLibraryMember()).save();
	}

	@Override
	public SaveMessage checkoutBook(String bookIsbn, String memberId, Date checkoutDate, Date returnDueDate)
			throws RuntimeException {
		
		SaveMessage saveMessage = new SaveMessage();
		try {
			LibraryMember member = LibraryMemberFactory.getInstance(memberId).addNewCheckoutEntry(
					bookIsbn, checkoutDate, returnDueDate);
			
			//save checkout record
			SingletoneDataLoader.getInstance().getLibraryMember().put(member.getMemberId(), member);
			saveMessage = new SerializableDataPersistor<Map<String, LibraryMember>>(
					StorageType.MEMBERS, SingletoneDataLoader.getInstance().getLibraryMember()).save();
		} catch (Exception e) {
			saveMessage.setSuccessed(false);
			saveMessage.setE(e);
			saveMessage.getErrors().add("Bad Data. Checkout Failed.");
		}
		
		return saveMessage;
	}

	@Override
	public SaveMessage saveUser(User user) throws RuntimeException {
		
		if(user instanceof LibraryMember) {
			LibraryMember member = (LibraryMember)user;
			SingletoneDataLoader.getInstance().getLibraryMember().put(member.getMemberId(), member);
			return new SerializableDataPersistor<Map<String, LibraryMember>>(
					StorageType.MEMBERS, SingletoneDataLoader.getInstance().getLibraryMember()).save();
		}
		
		SystemUser systemUser = (SystemUser)user;
		SingletoneDataLoader.getInstance().getSystemUsers().put(systemUser.getLoginUserName(), systemUser);
		return new SerializableDataPersistor<Map<String, SystemUser>>(
				StorageType.USERS, SingletoneDataLoader.getInstance().getSystemUsers()).save();
	}

	@Override
	public Book searchBookByIBSN(String ibsn) {
		return SingletoneDataLoader.getInstance().getBooks().get(ibsn);
	}

	@Override
	public CheckoutRecord retrieveCheckoutRecord(String memberId) {
		LibraryMember member = SingletoneDataLoader.getInstance().getLibraryMember().get(memberId);
		if(member != null) return member.getCheckoutRecord();
		return null;
	}

	@Override
	public Map<String, LibraryMember> fetchAllMembers() {
		return SingletoneDataLoader.getInstance().getLibraryMember();
	}

	@Override
	public Map<String, Book> fetchAllBooks() {
		return SingletoneDataLoader.getInstance().getBooks();
	}

	@Override
	public SaveMessage saveBook(Book book) throws RuntimeException {
		SingletoneDataLoader.getInstance().getBooks().put(book.getIsbn(), book);
		return new SerializableDataPersistor<Map<String, Book>>(
				StorageType.BOOKS, SingletoneDataLoader.getInstance().getBooks()).save();
	}

	@Override
	public Set<BookCopy> fetchAllBookCopies() {
		Set<BookCopy> bookCopies = new HashSet<BookCopy>();
		Map<String, Book> books = SingletoneDataLoader.getInstance().getBooks();
		for(String isbn : books.keySet()) {
			Book book = books.get(isbn);
			if(book != null) {
				bookCopies.addAll(book.getCopies());
			}
		}
		
		return bookCopies;
	}

}

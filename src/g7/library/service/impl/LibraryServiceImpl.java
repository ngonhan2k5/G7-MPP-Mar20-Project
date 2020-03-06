package g7.library.service.impl;

import java.util.Date;
import java.util.Map;

import g7.library.dataaccess.DataLoader;
import g7.library.dataaccess.DataPersistor;
import g7.library.dataaccess.DataPersistor.SaveMessage;
import g7.library.dataaccess.storage.StorageType;
import g7.library.domain.Book;
import g7.library.domain.CheckoutRecord;
import g7.library.domain.LibraryMember;
import g7.library.domain.LoginCredentials;
import g7.library.domain.SystemUser;
import g7.library.domain.User;
import g7.library.service.LibraryServiceInterface;

public class LibraryServiceImpl implements LibraryServiceInterface {

	@Override
	public SystemUser login(LoginCredentials credentials) throws RuntimeException {
		for(SystemUser u : DataLoader.getInstance().getSystemUsers().values()) {
			if(u.login(credentials) != null) return u;
		}
		return null;
	}

	@Override
	public SaveMessage addNewLibraryMember(LibraryMember libraryMember) throws RuntimeException {
		
		return null;
	}

	@Override
	public SaveMessage checkoutBook(String bookIsbn, String memberId, Date checkoutDate, Date returnDueDate)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaveMessage saveUser(User user) throws RuntimeException {
		
		if(user instanceof LibraryMember) {
			LibraryMember member = (LibraryMember)user;
			DataLoader.getInstance().getLibraryMember().put(member.getMemberId(), member);
			return new DataPersistor<Map<String, LibraryMember>>(
					StorageType.MEMBERS, DataLoader.getInstance().getLibraryMember()).save();
		}
		
		SystemUser systemUser = (SystemUser)user;
		DataLoader.getInstance().getSystemUsers().put(systemUser.getLoginUserName(), systemUser);
		return new DataPersistor<Map<String, SystemUser>>(
				StorageType.USERS, DataLoader.getInstance().getSystemUsers()).save();
	}

	@Override
	public Book searchBookByIBSN(String ibsn) {
		return DataLoader.getInstance().getBooks().get(ibsn);
	}

	@Override
	public CheckoutRecord retrieveCheckoutRecord(String memberId) {
		LibraryMember member = DataLoader.getInstance().getLibraryMember().get(memberId);
		if(member != null) return member.getCheckoutRecord();
		return null;
	}

	@Override
	public Map<String, LibraryMember> fetchAllMembers() {
		return DataLoader.getInstance().getLibraryMember();
	}

	@Override
	public Map<String, Book> fetchAllBooks() {
		return DataLoader.getInstance().getBooks();
	}

	@Override
	public SaveMessage saveBook(Book book) throws RuntimeException {
		DataLoader.getInstance().getBooks().put(book.getIsbn(), book);
		return new DataPersistor<Map<String, Book>>(
				StorageType.BOOKS, DataLoader.getInstance().getBooks()).save();
	}

}

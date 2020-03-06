package g7.library.dataaccess;

import java.util.HashMap;
import java.util.Map;

import g7.library.dataaccess.DataPersistor.SaveMessage;
import g7.library.dataaccess.storage.StorageType;
import g7.library.domain.Address;
import g7.library.domain.Author;
import g7.library.domain.Book;
import g7.library.domain.LibraryMember;
import g7.library.domain.LoginCredentials;
import g7.library.domain.PermissionType;
import g7.library.domain.SystemUser;
import g7.library.domain.UserRole;
import g7.library.domain.UserRoleType;

public class DataCleanOneTimeCreator {
	public final static DataCleanOneTimeCreator instance = new DataCleanOneTimeCreator();
	
	static Map<UserRoleType, UserRole> createUserRoles() {
		Map<UserRoleType, UserRole> userRoles = new HashMap<UserRoleType, UserRole>();
		UserRole role = new UserRole(UserRoleType.ADMIN);
		role.addNewAccessPermission(PermissionType.UPDATE_MEMBER);
		role.addNewAccessPermission(PermissionType.ADD_BOOK);
		role.addNewAccessPermission(PermissionType.DELETE_MEMBER);
		userRoles.put(UserRoleType.ADMIN, role);
		
		role = new UserRole(UserRoleType.LIBRARIAN);
		role.addNewAccessPermission(PermissionType.CHECKOUT_BOOK);
		userRoles.put(UserRoleType.LIBRARIAN, role);
		
		return userRoles;
	}
	
	static Map<String, SystemUser> createSystemUsers(Map<UserRoleType, UserRole> userRoles) {
		Map<String, SystemUser> systemUsers = new HashMap<String, SystemUser>();
		SystemUser admin = new SystemUser(new LoginCredentials("admin", "12345"), 
				"Admin", "Admin", "319-111-222", new Address("1311 2nd street", "Fairfield", "IA", 525567));
		admin.addNewRole(userRoles.get(UserRoleType.ADMIN));
		systemUsers.put(admin.getLoginUserName(), admin);
		
		SystemUser librarian = new SystemUser(new LoginCredentials("librarian", "12345"), 
				"Librarian", "Librarian", "319-111-333", new Address("1311 4nd street", "Fairfield", "IA", 525567));
		librarian.addNewRole(userRoles.get(UserRoleType.LIBRARIAN));
		systemUsers.put(librarian.getLoginUserName(), librarian);
		
		SystemUser superAdmin = new SystemUser(new LoginCredentials("superadmin", "12345"), 
				"Super", "Admin", "319-111-444", new Address("1311 5nd street", "Fairfield", "IA", 525567));
		superAdmin.addNewRole(userRoles.get(UserRoleType.LIBRARIAN));
		superAdmin.addNewRole(userRoles.get(UserRoleType.ADMIN));
		systemUsers.put(superAdmin.getLoginUserName(), superAdmin);
		
		return systemUsers;
	}
	
	static Map<String, Book> createBooks() {
		Map<String, Book> books = new HashMap<String, Book>();
		
		Book book = new Book("ISBN1", "Java Core 8", 30);
		book.addNewAuthor(new Author("BIO 111", "Jone", "Kathy", "", null));
		book.makeABookCopy(1).makeABookCopy(2).makeABookCopy(3).makeABookCopy(4);
		books.put(book.getIsbn(), book);
		
		book = new Book("ISBN2", "Java Core 11", 30);
		book.addNewAuthor(new Author("BIO 112", "Jone", "Kathy", "", null));
		book.makeABookCopy(1).makeABookCopy(2).makeABookCopy(3).makeABookCopy(4);
		books.put(book.getIsbn(), book);
		
		book = new Book("ISBN3", "Java Core 9", 30);
		book.addNewAuthor(new Author("BIO 113", "Jone", "Kathy", "", null));
		book.makeABookCopy(1).makeABookCopy(2).makeABookCopy(3).makeABookCopy(4);
		books.put(book.getIsbn(), book);
		
		return books;
	}
	
	static Map<String, LibraryMember> createLibraryMembers() {
		Map<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		LibraryMember member = new LibraryMember("spring201", "FAAA", "LAAA", "", null);
		members.put(member.getMemberId(), member);
		
		member = new LibraryMember("spring202", "FBBB", "LBBB", "", null);
		members.put(member.getMemberId(), member);
		
		return members;
	}
	
	public static void main(String[] args) {
		Map<UserRoleType, UserRole> userRoles = createUserRoles();
		SaveMessage saveMessage = new DataPersistor<Map<String, SystemUser>>(StorageType.USERS, createSystemUsers(userRoles)).save();
		System.out.println(saveMessage);
		
		saveMessage = new DataPersistor<Map<String, LibraryMember>>(StorageType.MEMBERS, createLibraryMembers()).save();
		System.out.println(saveMessage);
		
		saveMessage = new DataPersistor<Map<String, Book>>(StorageType.BOOKS, createBooks()).save();
		System.out.println(saveMessage);
		
	}

}

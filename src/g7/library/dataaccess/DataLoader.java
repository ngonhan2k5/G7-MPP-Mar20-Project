package g7.library.dataaccess;

import java.io.IOException;
import java.util.Map;

import g7.library.dataaccess.storage.StorageType;
import g7.library.domain.Book;
import g7.library.domain.LibraryMember;
import g7.library.domain.SystemUser;
import g7.library.utils.SerializableUtil;

public class DataLoader {
	private final static DataLoader instance = new DataLoader();
	
	private Map<String, LibraryMember> libraryMember;
	private Map<String, Book> books;
	private Map<String, SystemUser> systemUsers;
	
	public Map<String, LibraryMember> getLibraryMember() {
		return libraryMember;
	}

	public Map<String, Book> getBooks() {
		return books;
	}

	public Map<String, SystemUser> getSystemUsers() {
		return systemUsers;
	}

	private DataLoader() {
		try {
			libraryMember = SerializableUtil.readFromStorage(StorageType.MEMBERS);
			books = SerializableUtil.readFromStorage(StorageType.BOOKS);
			systemUsers = SerializableUtil.readFromStorage(StorageType.USERS);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DataLoader getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		DataLoader instance = DataLoader.getInstance();
		
		System.out.println(instance.getBooks());
		System.out.println(instance.getLibraryMember());
		System.out.println(instance.getSystemUsers());
	}
	
}

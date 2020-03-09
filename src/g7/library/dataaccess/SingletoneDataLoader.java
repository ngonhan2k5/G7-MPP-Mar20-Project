package g7.library.dataaccess;

import java.io.IOException;
import java.util.Map;

import g7.library.dataaccess.storage.StorageType;
import g7.library.domain.Author;
import g7.library.domain.Book;
import g7.library.domain.LibraryMember;
import g7.library.domain.SystemUser;
import g7.library.utils.SerializableUtil;

public class SingletoneDataLoader {
	private final static SingletoneDataLoader instance = new SingletoneDataLoader();
	
	private Map<String, LibraryMember> libraryMember;
	private Map<String, Book> books;
	private Map<String, SystemUser> systemUsers;
	private Map<String, Author> authors;
	
	public Map<String, LibraryMember> getLibraryMember() {
		return libraryMember;
	}

	public Map<String, Book> getBooks() {
		return books;
	}

	public Map<String, SystemUser> getSystemUsers() {
		return systemUsers;
	}
	
	public Map<String, Author> getAuthors() {
		return authors;
	}


	private SingletoneDataLoader() {
		try {
			libraryMember = SerializableUtil.readFromStorage(StorageType.MEMBERS);
			books = SerializableUtil.readFromStorage(StorageType.BOOKS);
			systemUsers = SerializableUtil.readFromStorage(StorageType.USERS);
			authors = SerializableUtil.readFromStorage(StorageType.AUTHORS);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SingletoneDataLoader getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		SingletoneDataLoader instance = SingletoneDataLoader.getInstance();
		
		System.out.println(instance.getBooks());
		System.out.println(instance.getLibraryMember());
		System.out.println(instance.getSystemUsers());
		System.out.println(instance.getAuthors());
	}
	
}

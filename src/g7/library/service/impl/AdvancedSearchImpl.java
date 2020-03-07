package g7.library.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import g7.library.dataaccess.DataLoader;
import g7.library.domain.Book;
import g7.library.domain.SystemUser;
import g7.library.model.BookSearchCriteria;
import g7.library.model.SearchUserCriteria;
import g7.library.service.AdvancedSearchInterface;

public class AdvancedSearchImpl implements AdvancedSearchInterface {

	@Override
	public Set<SystemUser> findUser(SearchUserCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> searchBook(BookSearchCriteria criteria) {
		Set<Book> results = new HashSet<Book>();
		
		Map<String, Book> books = DataLoader.getInstance().getBooks();
		
		for(String isbn : books.keySet()) {
			//by isbn //by title //by Author name
			if(isbn.contains(criteria.searchString()) 
					|| books.get(isbn).getTitle().contains(criteria.searchString())
					|| books.get(isbn).getAuthors().stream().anyMatch(
							p -> p.getFirstName().contains(criteria.searchString()) 
							|| p.getLastName().contains(criteria.searchString())))
				results.add(books.get(isbn));
			
		}
		
		return results;
	}

}

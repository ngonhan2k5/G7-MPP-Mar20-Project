package g7.library.service;

import java.util.Set;

import g7.library.domain.Book;
import g7.library.domain.SystemUser;
import g7.library.model.BookSearchCriteria;
import g7.library.model.SearchUserCriteria;

public interface AdvancedSearchInterface {
	public Set<SystemUser> findUser(SearchUserCriteria criteria);
	public Set<Book> searchBook(BookSearchCriteria criteria);
}

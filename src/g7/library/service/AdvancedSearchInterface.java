package g7.library.service;

import java.util.List;

import g7.library.domain.Book;
import g7.library.domain.SystemUser;
import g7.library.model.BookSearchCriteria;
import g7.library.model.SearchUserCriteria;

public interface AdvancedSearchInterface {
	public List<SystemUser> findUser(SearchUserCriteria criteria);
	public List<Book> searchBook(BookSearchCriteria criteria);
}

package g7.library.model;

public class BookSearchCriteria {
	private String searchString;
	
	public BookSearchCriteria withSearchString(String searchString) {
		this.searchString = searchString;
		return this;
	}
	
	public String searchString() {
		return this.searchString;
	}

}

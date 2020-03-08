package g7.library.model;

public class SearchUserCriteria {
	private String searchString;
	
	public SearchUserCriteria withSearchString(String searchString) {
		this.searchString = searchString;
		return this;
	}
	
	public String searchString() {
		return this.searchString;
	}

}

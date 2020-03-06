package g7.library.domain;

public enum UserRoleType {
	ADMIN("ROLE_ADMIN", "Administrator"), LIBRARIAN("ROLE_LIBRARIAN", "Librarian");
	
	private final String type;
	private final String description;
	UserRoleType(String type, String description) {
		this.type = type;
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	
}

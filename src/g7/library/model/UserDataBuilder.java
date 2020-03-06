package g7.library.model;

import g7.library.domain.SystemUser;

public class UserDataBuilder {
	private SystemUser systemUser;
	
	public UserDataBuilder(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	public UserDataBuilder withSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
		return this;
	}
	public SystemUser systemUser() {
		return systemUser;
	}
}

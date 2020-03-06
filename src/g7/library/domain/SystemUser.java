package g7.library.domain;

import java.util.HashSet;
import java.util.Set;

public final class SystemUser extends User implements SystemUserInterface {
	private static final long serialVersionUID = 1L;
	private final LoginCredentials loginCredentials;
	private final Set<UserRole> userRoles;

	public SystemUser(LoginCredentials loginCredentials, String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.loginCredentials = loginCredentials;
		this.userRoles = new HashSet<UserRole>();
	}

	public void addNewRole(UserRole role) {
		userRoles.add(role);
	}
	
	public LoginCredentials getLoginCredentials() {
		return loginCredentials;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	public Set<PermissionType> getPermissionsGranted() {
		Set<PermissionType> permissions = new HashSet<PermissionType>();
		for(UserRole role : userRoles) {
			permissions.addAll(role.getAccessPermissions());
		}
		return permissions;
	}
	
	public String getLoginUserName() {
		return loginCredentials.getLoginUserName();
	}

	@Override
	public SystemUser login(LoginCredentials loginCredentials) {
		if(this.loginCredentials.equals(loginCredentials)) return this;
		return null;
	}
}

package g7.library.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private final UserRoleType role;
	private final Set<PermissionType> accessPermissions;

	public UserRole(UserRoleType role, Set<PermissionType> accessPermissions) {
		this.role = role;
		this.accessPermissions = accessPermissions;
	}
	
	public UserRole(UserRoleType role) {
		this.role = role;
		this.accessPermissions = new HashSet<PermissionType>();
	}

	public UserRoleType getRole() {
		return role;
	}

	public Set<PermissionType> getAccessPermissions() {
		return accessPermissions;
	}
	
	public void addNewAccessPermission(PermissionType accessType) {
		this.accessPermissions.add(accessType);
	}
}

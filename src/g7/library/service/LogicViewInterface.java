package g7.library.service;

import g7.library.domain.PermissionType;
import g7.library.domain.SystemUser;

public interface LogicViewInterface {
	public boolean isPermissionGranted(SystemUser user, PermissionType access);
}

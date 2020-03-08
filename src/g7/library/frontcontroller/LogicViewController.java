package g7.library.frontcontroller;

import g7.library.domain.PermissionType;
import g7.library.domain.SystemUser;
import g7.library.service.LogicViewInterface;

public class LogicViewController {
	private SystemUser systemUser;
	public LogicViewController(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	private LogicViewInterface logicView = (user, access) -> {
		return user.getPermissionsGranted().contains(access);
	};

	public boolean isPermissionGranted(PermissionType accessPermission) {
		return logicView.isPermissionGranted(systemUser, accessPermission);
	}
	
	public boolean isLibMemberUpdatePermited() {
		return isPermissionGranted(PermissionType.UPDATE_MEMBER);
	}
	
	public boolean isLibMemberAddPermited() {
		return isPermissionGranted(PermissionType.ADD_MEMBER);
	}
	
	public boolean isLibMemberDeletePermited() {
		return isPermissionGranted(PermissionType.DELETE_MEMBER);
	}
	
	public boolean isBookCheckoutPermited() {
		return isPermissionGranted(PermissionType.CHECKOUT_BOOK);
	}
	
	public boolean isBookAddPermited() {
		return isPermissionGranted(PermissionType.ADD_BOOK);
	}
}

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

	public boolean isLibMemberUpdatePermited() {
		return logicView.isPermissionGranted(systemUser, PermissionType.UPDATE_MEMBER);
	}
	
	public boolean isLibMemberAddPermited() {
		return logicView.isPermissionGranted(systemUser, PermissionType.ADD_MEMBER);
	}
	
	public boolean isLibMemberDeletePermited() {
		return logicView.isPermissionGranted(systemUser, PermissionType.DELETE_MEMBER);
	}
	
	public boolean isBookCheckoutPermited() {
		return logicView.isPermissionGranted(systemUser, PermissionType.CHECKOUT_BOOK);
	}
	
	public boolean isBookAddPermited() {
		return logicView.isPermissionGranted(systemUser, PermissionType.ADD_BOOK);
	}
}

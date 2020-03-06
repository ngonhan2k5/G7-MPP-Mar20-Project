package g7.library.domain;

import java.io.Serializable;
import java.util.Objects;

public class LoginCredentials implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String loginUserName;
	private final String password;
	
	public LoginCredentials(String loginUserName, String password) {
		this.loginUserName = loginUserName;
		this.password = password;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginUserName == null) ? 0 : loginUserName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		LoginCredentials other = (LoginCredentials) obj;
		return Objects.equals(loginUserName, other.loginUserName)
				&& Objects.equals(password, other.password);
		
	}
}

package ar.edu.unnoba.poo.login.userDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserLoggedIn extends User {

	private ar.edu.unnoba.poo.login.entities.User user;
	
	public UserLoggedIn(String email, String password, Collection<? extends GrantedAuthority> authorities, ar.edu.unnoba.poo.login.entities.User user) {
		super(email, password, authorities);
		this.user = user;
	}

	public ar.edu.unnoba.poo.login.entities.User getUser() {
		return user;
	}

	public void setUser(ar.edu.unnoba.poo.login.entities.User user) {
		this.user = user;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.getEnabled();
	}
}
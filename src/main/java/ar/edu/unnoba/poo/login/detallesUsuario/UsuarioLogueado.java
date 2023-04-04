package ar.edu.unnoba.poo.login.detallesUsuario;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioLogueado extends User {

	private ar.edu.unnoba.poo.login.entidades.Usuario usuario;
	
	public UsuarioLogueado(String email, String password, Collection<? extends GrantedAuthority> authorities, ar.edu.unnoba.poo.login.entidades.Usuario user) {
		super(email, password, authorities);
		this.usuario = user;
	}

	public ar.edu.unnoba.poo.login.entidades.Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(ar.edu.unnoba.poo.login.entidades.Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.usuario.getBloqueado();
	}
}
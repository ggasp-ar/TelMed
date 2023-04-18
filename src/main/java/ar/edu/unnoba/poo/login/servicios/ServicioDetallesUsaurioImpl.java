package ar.edu.unnoba.poo.login.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ar.edu.unnoba.poo.login.detallesUsuario.UsuarioLogueado;

import java.util.NoSuchElementException;

@Component
public class ServicioDetallesUsaurioImpl implements UserDetailsService {

	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
 
    	try { 
    		ar.edu.unnoba.poo.login.entidades.Usuario u = servicioUsuario.obtenerPorEmail(email);
    		
    		List grantedAuthorities = new ArrayList();
    		// Agrego el toString al obtener el Rol
    		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(u.getRol().toString());
    		grantedAuthorities.add(grantedAuthority);
    		UsuarioLogueado usuario = new UsuarioLogueado(u.getEmail(), u.getContrasenia(), grantedAuthorities, u);

			if (!usuario.isEnabled()){
				throw new Exception("Usuario bloqueado.");
			}

    		return usuario;
    	}	
    	catch(NoSuchElementException e) {
    		throw new UsernameNotFoundException("Usuario no encontrado.");
    	}catch(Exception e){
			throw new UsernameNotFoundException(e.getMessage());
		}
    }
}

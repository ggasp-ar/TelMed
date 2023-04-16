package ar.edu.unnoba.poo.login.servicios;

import java.util.List;

import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.repositorios.RepositorioUsuario;
import ar.edu.unnoba.poo.login.util.Rol;
import ar.edu.unnoba.poo.login.util.encriptador.EncriptadorInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private EncriptadorInterface encriptador;

	public List<Usuario> obtenerTodos() {
		return repositorioUsuario.findAll();
	}
	
	public Usuario nuevo(Usuario usuario, Rol rol) {
		usuario.setBloqueado(true);
		usuario.setRol(rol/*.name()*/);
		usuario.setContrasenia(encriptador.passwordEncrypt(usuario.getContrasenia()));
	    return repositorioUsuario.save(usuario);
	}
	
	public Usuario obtenerPorId(Long id) {
	    return repositorioUsuario.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un usuario con ID: " + id));
	}
	
	public Usuario modificar(Usuario usuario, Long id) {
	    return repositorioUsuario.findById(id)
	      .map(u -> {
	        u.setEmail(usuario.getEmail());
	        u.setContrasenia(encriptador.passwordEncrypt(usuario.getContrasenia()));
//	        u.setNombre(usuario.getNombre());
	        u.setRol(usuario.getRol());
	        return repositorioUsuario.save(u);
	      })
	      .orElseGet(() -> {
	        return repositorioUsuario.save(usuario);
	      });
	}

	public void eliminar(Long id) {
		repositorioUsuario.deleteById(id);
	}
	
	public Usuario obtenerPorEmail(String email) {
		return repositorioUsuario.findByEmail(email);
	}
}

package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import ar.edu.unnoba.poo.login.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.repositorios.RepositorioPaciente;
import ar.edu.unnoba.poo.login.util.Rol;
import ar.edu.unnoba.poo.login.util.encriptador.EncriptadorInterface;

@Service
public class ServicioPaciente {
	@Autowired
	private RepositorioPaciente repositorioPaciente;
	
	@Autowired
	private EncriptadorInterface encriptador;
	
	public List<Paciente> obtenerTodos() {
		return repositorioPaciente.findAll();
	}
	
	public Paciente nuevo(Paciente paciente) {
		paciente.getUsuario().setContrasenia(encriptador.passwordEncrypt(paciente.getUsuario().getContrasenia()));
		paciente.getUsuario().setRol(Rol.ROLE_USER);
		paciente.getUsuario().setBloqueado(true);
		return repositorioPaciente.save(paciente);
	}
	
	public Paciente obtenerPorId(Long id) {
		return repositorioPaciente.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	public Paciente obtenerPorUsuario(Usuario u) {
		return repositorioPaciente.findByUsuario(u);
	}
	
	public Paciente obtenerPorDni(Long dni) {
		return repositorioPaciente.findByDni(dni);
	}
	
	public void cambiarEstado(Long id) {
		repositorioPaciente.findById(id)
		.map(p -> {
			Usuario u = p.getUsuario();
			u.setBloqueado(!u.getBloqueado());
			return repositorioPaciente.save(p);
		});
	}
	
	public Paciente modificar(Paciente paciente, Long id) {
		return repositorioPaciente.findById(id)
			      .map(p -> {
			        p.setDni(paciente.getDni());
			        return repositorioPaciente.save(p);
			      })
			      .orElseGet(() -> {
			        return repositorioPaciente.save(paciente);
			      });
	}
	
	public void eliminar(Long id) {
		repositorioPaciente.deleteById(id);
	}
}

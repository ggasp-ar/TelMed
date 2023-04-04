package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.repositorios.RepositorioPaciente;

@Service
public class ServicioPaciente {
	@Autowired
	private RepositorioPaciente repositorioPaciente;
	
	public List<Paciente> obtenerTodos() {
		return repositorioPaciente.findAll();
	}
	
	public Paciente nuevo(Paciente paciente) {
		return repositorioPaciente.save(paciente);
	}
	
	public Paciente obtenerPorId(Long id) {
		return repositorioPaciente.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public Paciente obtenerPorDni(Long dni) {
		return repositorioPaciente.findByDni(dni);
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

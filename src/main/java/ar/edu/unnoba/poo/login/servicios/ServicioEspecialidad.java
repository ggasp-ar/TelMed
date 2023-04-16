package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Especialidad;
import ar.edu.unnoba.poo.login.repositorios.RepositorioEspecialidad;

@Service
public class ServicioEspecialidad {
	@Autowired
	private RepositorioEspecialidad repositorioEspecialidad;
	
	public ServicioEspecialidad() {
		super();
	}
	
	public List<Especialidad> obtenerTodas() {
		return repositorioEspecialidad.findAll();
	}
	
	public Especialidad obtenerPorNombre(String nombre) {
		return repositorioEspecialidad.findByNombre(nombre).orElseThrow(() -> new NoSuchElementException("No se ha encontrado una especialidad con nombre: " + nombre));
	}
	
	public Especialidad obtenerPorId(Long id) {
		return repositorioEspecialidad.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado una especialidad con ID: " + id));
	}
	
	public Especialidad nueva(Especialidad especialidad) {
		return repositorioEspecialidad.save(especialidad);
	}
	
	public Especialidad modificar(Especialidad especialidad, Long id) {
		return repositorioEspecialidad.findById(id)
				.map(e -> {
					e.setNombre(especialidad.getNombre());
					e.setObservaciones(especialidad.getObservaciones());
					return repositorioEspecialidad.save(e);
				}).orElseGet(() -> {
					return repositorioEspecialidad.save(especialidad);
				});
	}
	
	public void eliminar(Long id) {
		repositorioEspecialidad.deleteById(id);
	}
}

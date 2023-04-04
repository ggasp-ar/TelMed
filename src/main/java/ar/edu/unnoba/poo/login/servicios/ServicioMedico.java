package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.repositorios.RepositorioMedico;

@Service
public class ServicioMedico {
	@Autowired
	private RepositorioMedico repositorioMedico;
	
	public List<Medico> obtenerTodos() {
		return repositorioMedico.findAll();
	}
	
	public Medico nuevo(Medico medico) {
		return repositorioMedico.save(medico);
	}
	
	public Medico obtenerPorId(Long id) {
		return repositorioMedico.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un medico con ID: " + id));
	}
	
	public Medico obtenerPorEspecialidad(String especialidad) {
		return repositorioMedico.findByEspecialidad(especialidad);
	}
	
	public Medico modificar(Medico medico, Long id) {
		return repositorioMedico.findById(id)
			      .map(m -> {
			        m.setMatricula(medico.getMatricula());
			        m.setEspecialidad(medico.getEspecialidad());
			        return repositorioMedico.save(m);
			      })
			      .orElseGet(() -> {
			        return repositorioMedico.save(medico);
			      });
	}
	
	public void eliminar(Long id) {
		repositorioMedico.deleteById(id);
	}
}

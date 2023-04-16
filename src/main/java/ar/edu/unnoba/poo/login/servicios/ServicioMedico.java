package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.repositorios.RepositorioMedico;
import ar.edu.unnoba.poo.login.util.Rol;
import ar.edu.unnoba.poo.login.util.encriptador.EncriptadorInterface;

@Service
public class ServicioMedico {
	@Autowired
	private RepositorioMedico repositorioMedico;
	@Autowired
	private EncriptadorInterface encriptador;
	
	public List<Medico> obtenerTodos() {
		return repositorioMedico.findAll();
	}
	
	public Medico nuevo(Medico medico) {
		medico.getUsuario().setContrasenia(encriptador.passwordEncrypt(medico.getUsuario().getContrasenia()));
		medico.getUsuario().setRol(Rol.ROLE_MEDIC);
		medico.getUsuario().setBloqueado(true);
		medico.setAgenda(new Agenda());
		return repositorioMedico.save(medico);
	}
	
	public Medico obtenerPorId(Long id) {
		return repositorioMedico.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un medico con ID: " + id));
	}
	
	public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) {
		return repositorioMedico.findMedicosByEspecialidad(especialidad);
	}
	
	public Medico modificar(Medico medico, Long id) {
		return repositorioMedico.findById(id)
			      .map(m -> {
			        m.setMatricula(medico.getMatricula());
			        m.setEspecialidades(medico.getEspecialidades());
			        m.setUsuario(medico.getUsuario());
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

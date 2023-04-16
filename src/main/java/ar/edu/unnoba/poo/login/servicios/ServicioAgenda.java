package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.repositorios.RepositorioAgenda;

@Service
public class ServicioAgenda {
	@Autowired
	private RepositorioAgenda repositorioAgenda;
	
	public List<Agenda> obtenerTodas() {
		return repositorioAgenda.findAll();
	}
	
	public Agenda obtenerPorId(Long id) {
		return repositorioAgenda.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado una Agenda con ID: " + id));
	}
	
	public Agenda nueva(Agenda agenda) {
		return repositorioAgenda.save(agenda);
	}
	
	public Agenda modificar(Long id, Agenda agenda) {
		return repositorioAgenda.findById(id)
				.map(a -> {
					a.setDuracionTurno(agenda.getDuracionTurno());
					a.setFranjasHorarias(agenda.getFranjasHorarias());
					a.setTurnos(agenda.getTurnos());
					return repositorioAgenda.save(a);
				}).orElseGet(() -> {
					return repositorioAgenda.save(agenda);
				});
	}
	
	public void eliminar(Long id) {
		repositorioAgenda.deleteById(id);
	}
}

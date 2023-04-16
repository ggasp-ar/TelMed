package ar.edu.unnoba.poo.login.servicios;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;
import ar.edu.unnoba.poo.login.repositorios.RepositorioAgenda;
import ar.edu.unnoba.poo.login.repositorios.RepositorioFranjaHoraria;

@Service
public class ServicioFranjaHoraria {
	@Autowired
	private RepositorioFranjaHoraria repositorioFranjaHoraria;
	@Autowired
	private RepositorioAgenda repositorioAgenda;
	
	public ServicioFranjaHoraria() {
		super();
	}
	
	public List<FranjaHoraria> obtenerTodas() {
		return repositorioFranjaHoraria.findAll();
	}
	
	public List<FranjaHoraria> obtenerTodasDe(Long idMedico) {
		return repositorioFranjaHoraria.findFranjasHorariasPorIdMedico(idMedico);
	}
	
	public FranjaHoraria obtenerPorId(Long id) {
		return repositorioFranjaHoraria.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado una franja horaria con ID: " + id));
	}
	
	public FranjaHoraria nueva(FranjaHoraria franjaHoraria) {
		//Agenda agenda = repositorioAgenda.findById(franjaHoraria.getAgenda().getId()).orElseThrow(() -> new NoSuchElementException("No se ha encontrado una Agenda con ID: " + franjaHoraria.getAgenda().getId()));
		//FranjaHoraria nuevaFranjaHoraria = repositorioFranjaHoraria.save(franjaHoraria);
		//agenda.addFranjaHoraria(nuevaFranjaHoraria);
		//repositorioAgenda.save(agenda);
		//return nuevaFranjaHoraria;
		return repositorioFranjaHoraria.save(franjaHoraria);
	}
	
	public FranjaHoraria modificar(FranjaHoraria franjaHoraria, Long id) {
		return repositorioFranjaHoraria.findById(id)
				.map(fh -> {
					fh.setHoraInicio(franjaHoraria.getHoraInicio());
					fh.setHoraFin(franjaHoraria.getHoraFin());
					fh.setDiasAtencion(franjaHoraria.getDiasAtencion());
					return repositorioFranjaHoraria.save(fh);
				}).orElseGet(() -> {
					return repositorioFranjaHoraria.save(franjaHoraria);
				});
	}
	
	public void eliminar(Long id) {
		repositorioFranjaHoraria.deleteById(id);
	}
}

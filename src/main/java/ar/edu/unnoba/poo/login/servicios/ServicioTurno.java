package ar.edu.unnoba.poo.login.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Turno;
import ar.edu.unnoba.poo.login.repositorios.RepositorioMedico;
import ar.edu.unnoba.poo.login.repositorios.RepositorioPaciente;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;

@Service
public class ServicioTurno {
	@Autowired
	private RepositorioTurno repositorioTurno;
	@Autowired
	private RepositorioPaciente repositorioPaciente;
	@Autowired
	private RepositorioMedico repositorioMedico;
	
	public ServicioTurno() {
		super();
	}
	
	public List<Turno> obtenerTurnosPorMedicoYFecha(Long id, LocalDate fecha) {
		return repositorioTurno.findAllByMedicoAndFecha(id, fecha);
	}
	
	public List<Turno> obtenerTurnosPorPaciente(Long id) {
		Paciente paciente = repositorioPaciente.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un Paciente con ID: " + id));
		return repositorioTurno.findAllByPacienteOrderByFecha(paciente);
	}
	
	public List<Turno> obtenerTurnosPorMedico(Long id) {
		Medico medico = repositorioMedico.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un Paciente con ID: " + id));
		return repositorioTurno.findAllByMedicoOrderByFecha(medico);
	}
}

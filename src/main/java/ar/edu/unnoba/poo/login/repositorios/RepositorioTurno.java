package ar.edu.unnoba.poo.login.repositorios;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioTurno extends JpaRepository<Turno, Long>{
	
	public Turno findByFecha(@Param("fecha") Long fecha);
	public List<Turno> findAllByOrderByFecha();
	public List<Turno> findAllByPacienteOrderByFecha(@Param("paciente") Paciente p);
	public List<Turno> findAllByMedicoOrderByFecha(@Param("medico") Medico p);




}

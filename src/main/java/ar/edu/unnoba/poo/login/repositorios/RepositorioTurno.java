package ar.edu.unnoba.poo.login.repositorios;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioTurno extends JpaRepository<Turno, Long>{
	public Turno findByFecha(@Param("fecha") LocalDate fecha);
	public List<Turno> findAllByOrderByFecha();
	public List<Turno> findAllByPacienteOrderByFecha(@Param("paciente") Paciente p);
	public List<Turno> findAllByMedicoOrderByFecha(@Param("medico") Medico m);
	
	@Query("SELECT t FROM Turno t WHERE t.medico.id = :idMedico AND t.fecha = :fecha")
	public List<Turno> findAllByMedicoAndFecha(@Param("idMedico") Long idMedico, @Param("fecha") LocalDate fecha);
}

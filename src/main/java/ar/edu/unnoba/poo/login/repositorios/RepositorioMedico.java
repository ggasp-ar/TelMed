package ar.edu.unnoba.poo.login.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entidades.Medico;

public interface RepositorioMedico extends JpaRepository<Medico, Long>{
	
	public Medico findByMatricula(Long matricula);
	
	@Query(value="SELECT m "
			+ "FROM medicos m INNER JOIN medicos_especialidades me ON (m.id = me.medico_id) "
			+ "INNER JOIN especialidades e ON (me.especialidad_id = e.id) "
			+ "WHERE e.nombre = :especialidad",
			nativeQuery=true)
	public List<Medico> findMedicosByEspecialidad(@Param("especialidad") String especialidad);
}

package ar.edu.unnoba.poo.login.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entidades.Medico;

public interface RepositorioMedico extends JpaRepository<Medico, Long>{
	
	public Medico findByMatricula(@Param("matricula") Long matricula);
	
	public Medico findByEspecialidad(@Param("especialidad") String especialidad);
}

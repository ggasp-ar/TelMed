package ar.edu.unnoba.poo.login.repositorios;

import ar.edu.unnoba.poo.login.entidades.TurnoEjemplo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioTurno extends JpaRepository<TurnoEjemplo, Long>{
	
	public TurnoEjemplo findByFecha(@Param("fecha") Long fecha);
	public List<TurnoEjemplo> findAllByOrderByFecha();

}

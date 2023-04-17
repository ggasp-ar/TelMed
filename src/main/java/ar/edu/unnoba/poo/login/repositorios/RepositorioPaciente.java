package ar.edu.unnoba.poo.login.repositorios;

import ar.edu.unnoba.poo.login.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entidades.Paciente;

public interface RepositorioPaciente extends JpaRepository<Paciente, Long> {
	public Paciente findByDni(@Param("dni") Long dni);
	public Paciente findByUsuario(Usuario usuario);
}

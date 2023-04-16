package ar.edu.unnoba.poo.login.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unnoba.poo.login.entidades.Especialidad;

public interface RepositorioEspecialidad extends JpaRepository<Especialidad, Long> {
	public Optional<Especialidad> findByNombre(String nombre);
}

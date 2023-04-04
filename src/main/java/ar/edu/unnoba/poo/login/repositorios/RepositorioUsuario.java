package ar.edu.unnoba.poo.login.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entidades.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	public Usuario findByEmail(@Param("email") String email);
}

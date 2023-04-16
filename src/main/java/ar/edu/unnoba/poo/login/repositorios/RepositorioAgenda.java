package ar.edu.unnoba.poo.login.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unnoba.poo.login.entidades.Agenda;

public interface RepositorioAgenda extends JpaRepository<Agenda, Long> {
	
}

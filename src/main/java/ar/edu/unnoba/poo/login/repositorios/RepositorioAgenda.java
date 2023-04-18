package ar.edu.unnoba.poo.login.repositorios;

import ar.edu.unnoba.poo.login.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import org.springframework.data.repository.query.Param;

public interface RepositorioAgenda extends JpaRepository<Agenda, Long> {
	public Agenda getAgendaByMedico(@Param("Medico")Medico medico);
}

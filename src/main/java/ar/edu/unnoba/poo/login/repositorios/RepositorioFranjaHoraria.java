package ar.edu.unnoba.poo.login.repositorios;

import java.util.List;

import ar.edu.unnoba.poo.login.util.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;

public interface RepositorioFranjaHoraria extends JpaRepository<FranjaHoraria, Long> {
	@Query("SELECT fh FROM FranjaHoraria fh WHERE fh.agenda.medico.id = :idMedico")
	public List<FranjaHoraria> findFranjasHorariasPorIdMedico(@Param("idMedico") Long idMedico);
	public List<FranjaHoraria> findFranjaHorariaByDiasAtencionAndAgenda_Medico_Id(@Param("DiasAtencion") Dia DiasAtencion, @Param("idMedico") Long idMedico );
	public List<FranjaHoraria> findFranjaHorariaByDiasAtencion(@Param("DiasAtencion") Dia DiasAtencion);

}

package ar.edu.unnoba.poo.login.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;
import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Turno;
import ar.edu.unnoba.poo.login.repositorios.RepositorioFranjaHoraria;
import ar.edu.unnoba.poo.login.repositorios.RepositorioMedico;
import ar.edu.unnoba.poo.login.repositorios.RepositorioPaciente;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;
import ar.edu.unnoba.poo.login.util.Dia;

@Service
public class ServicioTurno {
	@Autowired
	private RepositorioTurno repositorioTurno;
	@Autowired
	private RepositorioPaciente repositorioPaciente;
	@Autowired
	private RepositorioMedico repositorioMedico;
	@Autowired
	private RepositorioFranjaHoraria repositorioFranjaHoraria;
	
	public ServicioTurno() {
		super();
	}
	
	public List<Turno> obtenerTurnosPorMedicoYFecha(Long id, LocalDate fecha) {
		return repositorioTurno.findAllByMedicoAndFecha(id, fecha);
	}
	
	public List<Turno> obtenerTurnosPorPaciente(Long id) {
		Paciente paciente = repositorioPaciente.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un Paciente con ID: " + id));
		return repositorioTurno.findAllByPacienteOrderByFecha(paciente);
	}
	
	public List<Turno> obtenerTurnosPorMedico(Long id) {
		Medico medico = repositorioMedico.findById(id).orElseThrow(() -> new NoSuchElementException("No se ha encontrado un Paciente con ID: " + id));
		return repositorioTurno.findAllByMedicoOrderByFecha(medico);
	}
	
	public void confirmarTurno(Turno turno) {
		repositorioTurno.save(turno);
	}
	
	public void eliminarTurno(Long id) throws Exception {
        if(validaFechaPorIdTurno(id, LocalDateTime.now())) {
            try {
                repositorioTurno.deleteById(id);
            } catch(IllegalArgumentException e) {
                throw new IllegalArgumentException("No fue posible eliminar el turno."); // Excepcion por si no encuentra el turno por el ID.
            }
        } else {
            throw new Exception("No se puede eliminar un turno que ya finalizó o empezó."); // Excepcion para cuando quiere eliminar turno pasado a la fecha y hora actual
        }
    }
	
	// Valido que el turno sea uno con fecha y hora anterior a cuando se quiera eliminar.
	public boolean validaFechaPorIdTurno(Long idTurno, LocalDateTime fechaActual) {
		Turno turno = repositorioTurno.findById(idTurno).orElseThrow(()-> new NoSuchElementException("No se encontró turno con id: "+idTurno));
		if(turno.getHoraInicio().isAfter(fechaActual)) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<Turno> turnosdisponibles(LocalDate dt, Medico med){

		Map<String, Dia> mapatodia = new HashMap<>();

		mapatodia.put("Monday",Dia.LUNES);
		mapatodia.put("Tuesday",Dia.MARTES);
		mapatodia.put("Wednesday",Dia.MIERCOLES);
		mapatodia.put("Thursday",Dia.JUEVES);
		mapatodia.put("Friday",Dia.VIERNES);
		mapatodia.put("Saturday",Dia.SABADO);
		mapatodia.put("Sunday",Dia.DOMINGO);

		Dia dia = mapatodia.get(dt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));


		LocalTime ini;
		LocalTime fin;
		int diff;


		List<Turno> turnos_oc = repositorioTurno.findAllByFecha(dt);
		List<LocalTime> horarios_ocupados = new ArrayList<>();

		for (Turno t:
				turnos_oc) {
			horarios_ocupados.add(t.getHoraInicio().toLocalTime())  ;
		}

		List<Turno> turnosdisponibles = new ArrayList<>();
		Turno t;
		for (FranjaHoraria f:
				repositorioFranjaHoraria.findFranjaHorariaByDiasAtencionAndAgenda_Medico_Id(dia,med.getId())) {
			ini = f.getHoraInicio();
			fin = f.getHoraFin();
			diff = ini.compareTo(fin);
			while(diff<0){
				if (horarios_ocupados.contains(ini)){
					System.out.println("Hora skippeada");
					System.out.println(ini);
					System.out.println("----------");
				}else{
					System.out.println(ini);
					t = new Turno();
					t.setMedico(med);
					t.setFecha(dt);
					t.setHoraInicio( LocalDateTime.of(dt,ini));
					turnosdisponibles.add(t);
				}

				//ini = ini.plusMinutes(m.getAgenda().getDuracionTurno());
				ini = ini.plusMinutes(30);
				diff = ini.compareTo(fin);
			}
		}
		return turnosdisponibles;
	}
}

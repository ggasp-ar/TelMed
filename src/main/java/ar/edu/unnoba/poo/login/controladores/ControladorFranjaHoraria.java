package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Turno;
import ar.edu.unnoba.poo.login.repositorios.RepositorioFranjaHoraria;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;
import ar.edu.unnoba.poo.login.servicios.ServicioAgenda;
import ar.edu.unnoba.poo.login.servicios.ServicioFranjaHoraria;
import ar.edu.unnoba.poo.login.util.Dia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

@Controller
@RequestMapping("/franja-horaria")
public class ControladorFranjaHoraria {
	@Autowired
	private ServicioFranjaHoraria servicioFranjaHoraria;
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private RepositorioTurno repTurno;
	@Autowired
	private RepositorioFranjaHoraria repFH;

	@Autowired
	private ServicioAgenda servicioAgenda;
	
	public ControladorFranjaHoraria() {
		super();
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model modelo) {
		FranjaHoraria franjaHoraria = servicioFranjaHoraria.obtenerPorId(id);
		modelo.addAttribute("franjaHoraria", franjaHoraria);
		System.out.println(franjaHoraria.toString());
		modelo.addAttribute("dias", Dia.values());
		return "franja-horaria/formulario";
	}
	
	@PutMapping("/{id}")
	public String modificar(@PathVariable Long id, @Valid @ModelAttribute("franjaHoraria") FranjaHoraria franjaHoraria, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "franja-horaria/formulario";
		}
		servicioFranjaHoraria.modificar(franjaHoraria, id);
		return "redirect:/medico/inicio";
	}
	
	@PostMapping("/nueva/{id}")
	public String nueva(@PathVariable Long id, @Valid @ModelAttribute("franjaHoraria") FranjaHoraria franjaHoraria, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "franja-horaria/formulario";
		}
		franjaHoraria.setId(null);
		franjaHoraria.setAgenda(servicioAgenda.obtenerPorId(id));
		servicioFranjaHoraria.nueva(franjaHoraria);
		return "redirect:/medico/inicio";
	}

	@GetMapping("/turnosdisponibles")
	public List<Turno> turnosdisponibles(LocalDate dt, @Valid @ModelAttribute("Medico") Medico med){

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


		List<Turno> turnos_oc = repTurno.findAllByFecha(dt);
		List<LocalTime> horarios_ocupados = new ArrayList<>();

		for (Turno t:
				turnos_oc) {
			horarios_ocupados.add(t.getHoraInicio().toLocalTime())  ;
		}

		List<Turno> turnosdisponibles = new ArrayList<>();
		Turno t;
		for (FranjaHoraria f:
				repFH.findFranjaHorariaByDiasAtencionAndAgenda_Medico_Id(dia,med.getId())) {
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

package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;
import ar.edu.unnoba.poo.login.servicios.ServicioAgenda;
import ar.edu.unnoba.poo.login.servicios.ServicioFranjaHoraria;
import ar.edu.unnoba.poo.login.util.Dia;

@Controller
@RequestMapping("/franja-horaria")
public class ControladorFranjaHoraria {
	@Autowired
	private ServicioFranjaHoraria servicioFranjaHoraria;
	@Autowired
	private ServicioAgenda servicioAgenda;
	
	public ControladorFranjaHoraria() {
		super();
	}
	
	@GetMapping("/nueva/{id}")
	public String nueva(@PathVariable Long id, Model modelo) {
		FranjaHoraria franjaHoraria = new FranjaHoraria();
		modelo.addAttribute("franjaHoraria", franjaHoraria);
		modelo.addAttribute("dias", Dia.values());
		return "franja-horaria/formulario";
	}
	
	@PostMapping("/nueva/{id}")
	public String nueva(@PathVariable Long id, @Valid @ModelAttribute("franjaHoraria") FranjaHoraria franjaHoraria, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "franja-horaria/formulario";
		}
		franjaHoraria.setAgenda(servicioAgenda.obtenerPorId(id));
		servicioFranjaHoraria.nueva(franjaHoraria);
		return "redirect:/medico/inicio";
	}
}

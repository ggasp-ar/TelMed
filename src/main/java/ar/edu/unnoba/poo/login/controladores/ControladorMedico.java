package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unnoba.poo.login.entidades.Agenda;
import ar.edu.unnoba.poo.login.entidades.FranjaHoraria;
import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioAgenda;
import ar.edu.unnoba.poo.login.servicios.ServicioEspecialidad;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.util.Dia;

@Controller
@RequestMapping("/medico")
public class ControladorMedico {
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private ServicioEspecialidad servicioEspecialidad;
	@Autowired
	private ServicioAgenda servicioAgenda;
	
	@GetMapping("/{id}")
	public String obtenerPorId(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("medico", servicioMedico.obtenerPorId(id));
		return "medico/detalles";
	}
	
	@GetMapping("/inicio")
	public String obtenerTodos(Model modelo) {
		modelo.addAttribute("medicos", servicioMedico.obtenerTodos());
		return "medico/index";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		Usuario usuario = new Usuario();
		Medico medico = new Medico();
		Agenda agenda = new Agenda();
		medico.setUsuario(usuario);
		medico.setAgenda(agenda);
		modelo.addAttribute("medico", medico/*new Medico()*/);
		modelo.addAttribute("especialidades", servicioEspecialidad.obtenerTodas());
		//modelo.addAttribute("usuario", new Usuario());
		return "medico/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model modelo) {
		Medico medico = servicioMedico.obtenerPorId(id);
		//Usuario usuario = medico.getUsuario();
		modelo.addAttribute("medico", medico);
		//modelo.addAttribute("usuario", usuario);
		return "medico/formulario";
	}
	
	@GetMapping("agenda/{id}")
	public String agenda(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("medico", servicioMedico.obtenerPorId(id));
		modelo.addAttribute("franjaHoraria", new FranjaHoraria());
		modelo.addAttribute("dias", Dia.values());
		return "agenda/index";
	}
	
	@PostMapping
	public String nuevo(@Valid @ModelAttribute("medico") Medico medico/*, @Valid Usuario usuario*/, Model modelo) {
		servicioMedico.nuevo(medico);
		return "redirect:/medico/inicio";
//		return "redirect:/medico/agenda/" + medico.getId();
//		return "redirect:/medico/agenda";
	}
	
	@PutMapping("/{id}")
	public String modificar(@PathVariable Long id, @Valid @ModelAttribute("medico") Medico medico, Model modelo) {
		servicioMedico.modificar(medico, id);
		return "redirect:/medico/inicio";
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable("id") Long id, Model modelo) {
		servicioMedico.eliminar(id);
		return "redirect:/medico/inicio";
	}
}

package ar.edu.unnoba.poo.login.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import ar.edu.unnoba.poo.login.detallesUsuario.UsuarioLogueado;
import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Turno;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioTurno;

@Controller
public class HomeController {
	@Autowired
	private ServicioPaciente servicioPaciente;
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private ServicioTurno servicioTurno;

	@GetMapping({"/","/home"})
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro(Model modelo) {
		Usuario usuario = new Usuario();
		Paciente paciente = new Paciente();
		paciente.setUsuario(usuario);
		modelo.addAttribute("paciente", paciente);
		return "registro";
	}
	
	@GetMapping("/inicio")
	public String index(Model modelo) {
		modelo.addAttribute("medicos", servicioMedico.obtenerTodos());
		return "usuario/index";
	}
	
	@GetMapping("/solicitar-turno/{id}")
	public String solicitarTurno(@PathVariable Long id, Model modelo) {
		Medico medico = servicioMedico.obtenerPorId(id);
		modelo.addAttribute("medico", medico);
		return "usuario/solicitar-turno";
	}
	
	@GetMapping(value="/solicitar-turno/{id}", params="fecha")
	public String seleccionarFecha(@PathVariable Long id, @RequestParam("fecha") String fecha, Model modelo) {
		Medico medico = servicioMedico.obtenerPorId(id);
		LocalDate fechaSeleccionada = LocalDate.parse(fecha);
		Turno turno = new Turno();
		List<Turno> turnos = servicioTurno.turnosdisponibles(fechaSeleccionada, medico);
		modelo.addAttribute("medico", medico);
		modelo.addAttribute("turnos", turnos);
		modelo.addAttribute("turnoSeleccionado", turno);
		return "usuario/solicitar-turno";
	}
	
	@PostMapping("/confirmar-turno")
	public String confirmarTurno(@ModelAttribute("turnoSeleccionado") Turno turnoSeleccionado, 
			@AuthenticationPrincipal UsuarioLogueado usuario, Model modelo) {
		Paciente p = servicioPaciente.obtenerPorUsuario(usuario.getUsuario());
		turnoSeleccionado.setPaciente(p);
		servicioTurno.confirmarTurno(turnoSeleccionado);
		return "usuario/perfil";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult bindingResult, Model modelo) {
		if(bindingResult.hasErrors()) {
			return "registro";
		}
		servicioPaciente.nuevo(paciente);
		
		return "login";
	}
}

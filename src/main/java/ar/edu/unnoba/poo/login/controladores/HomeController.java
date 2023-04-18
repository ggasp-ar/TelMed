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
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;

@Controller
public class HomeController {
//	@Autowired
//	private ServicioUsuario servicioUsuario;
	@Autowired
	private ServicioPaciente servicioPaciente;
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private ServicioTurno servicioTurno;
	@Autowired
	private ControladorFranjaHoraria controladorFH;

	@GetMapping({"/","/home"})
	public String home() {
		return "home";
	}
	/*
	@GetMapping("/hello")
	public String menu(@AuthenticationPrincipal UsuarioLogueado usuario) {
		return "hello";
	}
	*/
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro(Model modelo) {
		Usuario usuario = new Usuario();
		Paciente paciente = new Paciente();
		paciente.setUsuario(usuario);
		//model.addAttribute("usuario", new Usuario());
		modelo.addAttribute("paciente", paciente /*new Paciente()*/);
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
		List<Turno> turnos = servicioTurno.turnosdisponibles(fechaSeleccionada, medico);
		modelo.addAttribute("medico", medico);
		modelo.addAttribute("turnos", turnos);
		return "usuario/solicitar-turno";
	}
	
	@PostMapping("/confirmar-turno")
	public String confirmarTurno(@Valid @ModelAttribute("turno") Turno turno, 
			@AuthenticationPrincipal UsuarioLogueado usuario, Model modelo) {
		Paciente p = servicioPaciente.obtenerPorUsuario(usuario.getUsuario());
		turno.setPaciente(p);
		turno.setId(null);
		servicioTurno.confirmarTurno(turno);
		return "usuario/perfil";
	}
	
	@PostMapping("/registro")
	public String registro(/*@Valid Usuario usuario,*/ @Valid @ModelAttribute("paciente") Paciente paciente, BindingResult bindingResult, Model modelo) {
		//Usuario nuevoUsuario = servicioUsuario.nuevo(usuario, Roles.ROLE_USER);
		//paciente.setUsuario(nuevoUsuario);
		if(bindingResult.hasErrors()) {
			return "registro";
		}
		servicioPaciente.nuevo(paciente);
		//modelo.addAttribute("message", "Usuario registrado correctamente.");
		return "login";
	}
}

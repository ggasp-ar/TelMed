package ar.edu.unnoba.poo.login.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.unnoba.poo.login.detallesUsuario.UsuarioLogueado;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;
import ar.edu.unnoba.poo.login.util.Roles;

@Controller
public class HomeController {
	@Autowired
	private ServicioUsuario servicioUsuario;
	@Autowired
	private ServicioPaciente servicioPaciente;

	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	@GetMapping("/hello")
	public String menu(@AuthenticationPrincipal UsuarioLogueado usuario) {
		return "hello";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("paciente", new Paciente());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid Usuario usuario, @Valid Paciente paciente, Model model) {
		Usuario nuevoUsuario = servicioUsuario.nuevo(usuario, Roles.ROLE_USER);
		paciente.setUsuario(nuevoUsuario);
		servicioPaciente.nuevo(paciente);
		model.addAttribute("message", "Usuario registrado correctamente.");
		return "login";
	}
}

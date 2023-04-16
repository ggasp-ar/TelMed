package ar.edu.unnoba.poo.login.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import ar.edu.unnoba.poo.login.detallesUsuario.UsuarioLogueado;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;

@Controller
public class HomeController {
//	@Autowired
//	private ServicioUsuario servicioUsuario;
	@Autowired
	private ServicioPaciente servicioPaciente;

	@GetMapping({"/","/home"})
	public String index() {
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

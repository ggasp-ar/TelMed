package ar.edu.unnoba.poo.login.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.unnoba.poo.login.entities.Patient;
import ar.edu.unnoba.poo.login.entities.User;
import ar.edu.unnoba.poo.login.services.PatientService;
import ar.edu.unnoba.poo.login.services.UserService;
import ar.edu.unnoba.poo.login.userDetails.UserLoggedIn;
import ar.edu.unnoba.poo.login.util.Roles;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;

	@GetMapping({"/","/home"})
	public String index() {
		return "home";
	}
	
	@GetMapping("/hello")
	public String menu(@AuthenticationPrincipal UserLoggedIn user) {
		return "hello";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("patient", new Patient());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String register(@Valid User user, @Valid Patient patient, Model model) {
		User u = userService.addUser(user, Roles.ROLE_USER);
		patient.setUser(u);
		patientService.addPatient(patient);
		model.addAttribute("message", "Usuario registrado correctamente");
		return "home";
	}

}

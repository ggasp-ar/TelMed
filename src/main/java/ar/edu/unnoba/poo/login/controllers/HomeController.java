package ar.edu.unnoba.poo.login.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ar.edu.unnoba.poo.login.entities.User;
import ar.edu.unnoba.poo.login.services.UserService;
import ar.edu.unnoba.poo.login.userDetails.UserLoggedIn;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

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
	public String signup(Model modelo) {
		modelo.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String register(@Valid User user, Model model) {
		userService.addUser(user);
		model.addAttribute("message", "Usuario registrado correctamente");
		return "home";
	}

}

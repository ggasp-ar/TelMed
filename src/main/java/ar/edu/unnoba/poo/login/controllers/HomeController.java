package ar.edu.unnoba.poo.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import ar.edu.unnoba.poo.login.userDetails.UserLoggedIn;

@Controller
public class HomeController {

	@GetMapping({"/","/home"})
	public String index() {
		return "home";
	}
	
	@GetMapping("/hello")
	public String menu(@AuthenticationPrincipal UserLoggedIn user) {
		System.out.println("--------------------->>>> " + user.getUser().getEmail());
		System.out.println("--------------------->>>> " + user.getUser().getId());
		return "hello";
	}
	
	@GetMapping("/login")
	public String user() {
		return "login";
	}

}

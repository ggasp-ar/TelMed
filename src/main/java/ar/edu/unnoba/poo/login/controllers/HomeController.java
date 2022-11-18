package ar.edu.unnoba.poo.login.controllers;

import ar.edu.unnoba.poo.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import ar.edu.unnoba.poo.login.userDetails.UserLoggedIn;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	@Autowired
	private UserControl uc;
	@GetMapping("/userlist")
	public String userlist(@AuthenticationPrincipal UserLoggedIn user, Model model) {
		model.addAttribute("users",  uc.retrieveAllUsers());
		return "userlist";
	}
	@GetMapping("/eliminar/{id}")
	public String deleteUser(@PathVariable Long id) {
		uc.deleteUser(id);
		return "redirect:/userlist";
	}


	@GetMapping("/login")
	public String user() {
		return "login";
	}

}

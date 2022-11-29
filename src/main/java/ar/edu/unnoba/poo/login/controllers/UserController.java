package ar.edu.unnoba.poo.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ar.edu.unnoba.poo.login.services.UserService;
import ar.edu.unnoba.poo.login.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String retrieveAllUsers(Model model) {
		model.addAttribute("users", userService.retrieveAllUsers());
		return "userslist";
	}
	
	@GetMapping("/users/{id}")
	public String getUser(@PathVariable Long id, Model model) {
	   model.addAttribute("users", userService.retrieveAllUsers());
	   return "userslist";
	}
	
	@PutMapping("/users/{id}")
	public String replaceUser(@Valid User user, @PathVariable Long id, Model model) {
	    userService.replaceUser(user, id);
	    model.addAttribute("users", userService.retrieveAllUsers());
	    return "userslist";
	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		  userService.deleteUser(id);
		  model.addAttribute("users", userService.retrieveAllUsers());
		  return "userslist";
	  }
}

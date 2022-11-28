package ar.edu.unnoba.poo.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return "userlist";
	}
	
	@PostMapping("/users")
	public String addUser(@Valid User user, Model model) {
		userService.addUser(user);
		model.addAttribute("users", userService.retrieveAllUsers());
		return "userlist";
	}
	
	@GetMapping("/users/{id}")
	public String getUser(@PathVariable Long id, Model model) {
	   model.addAttribute("user", userService.getUser(id));
	   return "userlist";
	}
	
	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User user, @PathVariable Long id) {
	    return userService.replaceUser(user, id);
	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		  userService.deleteUser(id);
		  model.addAttribute("users", userService.retrieveAllUsers());
		  return "userlist";
	  }
}

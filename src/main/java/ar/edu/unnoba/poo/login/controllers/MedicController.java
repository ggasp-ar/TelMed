package ar.edu.unnoba.poo.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unnoba.poo.login.entities.Medic;
import ar.edu.unnoba.poo.login.entities.User;
import ar.edu.unnoba.poo.login.services.MedicService;
import ar.edu.unnoba.poo.login.services.UserService;
import ar.edu.unnoba.poo.login.util.Roles;

@Controller
public class MedicController {
	@Autowired
	private MedicService medicService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/medics")
	public String retrieveAlMedics(Model model) {
		model.addAttribute("medics", medicService.retrieveAllMedics());
		return "medicslist";
	}
	
	@PostMapping("/medics")
	public String addUser(@Valid Medic medic, @Valid User user, Model model) {
		userService.addUser(user, Roles.ROLE_MEDIC);
		medicService.addMedic(medic);
		model.addAttribute("medics", medicService.retrieveAllMedics());
		return "medicslist";
	}
}

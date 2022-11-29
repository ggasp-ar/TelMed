package ar.edu.unnoba.poo.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
		User u = userService.addUser(user, Roles.ROLE_MEDIC);
		medic.setUser(u);
		medicService.addMedic(medic);
		model.addAttribute("medics", medicService.retrieveAllMedics());
		return "medicslist";
	}
	
	@PutMapping("/medics/{id}")
	public String replaceMedic(@PathVariable Long id, @Valid Medic medic, Model model) {
		medicService.replaceMedic(medic, id);
		return "redirect:/medics";
	}
	
	@DeleteMapping("/medics/{id}")
	public String deleteMedic(@PathVariable("id") Long id, Model model) {
		medicService.deleteMedic(id);
		model.addAttribute("medics", medicService.retrieveAllMedics());
		return "medicslist";
	}
	
	@GetMapping("/medics/add")
	public String newMedic(Model model) {
		model.addAttribute("medic", new Medic());
		model.addAttribute("user", new User());
		return "medic-form";
	}
	
	@GetMapping("/medics/{id}")
	public String editMedic(@PathVariable Long id, Model model) {
		Medic medic = medicService.getMedic(id);
		User user = medic.getUser();
		model.addAttribute("medic", medic);
		model.addAttribute("user", user);
		return "medic-form";
	}
}

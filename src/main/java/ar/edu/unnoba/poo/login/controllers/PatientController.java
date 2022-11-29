package ar.edu.unnoba.poo.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import ar.edu.unnoba.poo.login.entities.Patient;
import ar.edu.unnoba.poo.login.services.PatientService;

@Controller
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/patients")
	public String retrieveAllUsers(Model model) {
		model.addAttribute("patients", patientService.retrieveAllPatients());
		return "patientslist";
	}
	
	@GetMapping("/patients/{id}")
	public String getUser(@PathVariable Long id, Model model) {
	   model.addAttribute("patients", patientService.retrieveAllPatients());
	   return "patientslist";
	}
	
	@PutMapping("/patients/{id}")
	public String replaceUser(@Valid Patient patient, @PathVariable Long id, Model model) {
	    patientService.replacePatient(patient, id);
	    model.addAttribute("users", patientService.retrieveAllPatients());
	    return "patientslist";
	}
}

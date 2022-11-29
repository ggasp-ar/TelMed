package ar.edu.unnoba.poo.login.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entities.Patient;
import ar.edu.unnoba.poo.login.repositories.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> retrieveAllPatients() {
		return patientRepository.findAll();
	}
	
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public Patient getPatient(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public Patient getPatientByDni(Long dni) {
		return patientRepository.findPatientByDni(dni);
	}
	
	public Patient replacePatient(Patient patient, Long id) {
		return patientRepository.findById(id)
			      .map(p -> {
			        p.setDni(patient.getDni());
			        return patientRepository.save(p);
			      })
			      .orElseGet(() -> {
			        return patientRepository.save(patient);
			      });
	}
	
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
}

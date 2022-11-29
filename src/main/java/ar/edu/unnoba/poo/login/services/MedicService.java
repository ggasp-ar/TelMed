package ar.edu.unnoba.poo.login.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo.login.entities.Medic;
import ar.edu.unnoba.poo.login.repositories.MedicRepository;

@Service
public class MedicService {
	@Autowired
	private MedicRepository medicRepository;
	
	public List<Medic> retrieveAllMedics() {
		return medicRepository.findAll();
	}
	
	public Medic addMedic(Medic medic) {
		return medicRepository.save(medic);
	}
	
	public Medic getMedic(Long id) {
		return medicRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public Medic getMedicBySpeciality(String speciality) {
		return medicRepository.findBySpeciality(speciality);
	}
	
	public Medic replaceMedic(Medic medic, Long id) {
		return medicRepository.findById(id)
			      .map(m -> {
			        m.setLicenseNumber(medic.getLicenseNumber());
			        m.setSpecialty(medic.getSpecialty());
			        return medicRepository.save(m);
			      })
			      .orElseGet(() -> {
			        return medicRepository.save(medic);
			      });
	}
	
	public void deleteMedic(Long id) {
		medicRepository.deleteById(id);
	}
}

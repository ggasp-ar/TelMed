package ar.edu.unnoba.poo.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	@Query("SELECT p FROM Patient p WHERE p.dni = :dni")
	public Patient findPatientByDni(@Param("dni") Long dni);
	
}

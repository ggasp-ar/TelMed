package ar.edu.unnoba.poo.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unnoba.poo.login.entities.Medic;

public interface MedicRepository extends JpaRepository<Medic, Long>{
	
	@Query("SELECT m FROM Medic m WHERE m.licenseNumber = :licenseNumber")
	public Medic findByLicenseNumber(@Param("licenseNumber") Long licenseNumber);
	
	@Query("SELECT m FROM Medic m WHERE m.speciality = :speciality")
	public Medic findBySpeciality(@Param("speciality") String speciality);
}

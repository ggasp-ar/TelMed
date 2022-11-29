package ar.edu.unnoba.poo.login.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicos")
public class Medic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne @MapsId
	private User user;
	@Column(name="matricula")
	private Long licenseNumber;
	@Column(name="especialidad")
	private String speciality;
	
	public Medic() {
		super();
	}
	
	public Medic(User user, Long licenseNumber, String speciality) {
		super();
		this.user = user;
		this.licenseNumber = licenseNumber;
		this.speciality = speciality;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(Long licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getSpecialty() {
		return speciality;
	}

	public void setSpecialty(String specialty) {
		this.speciality = specialty;
	}
}

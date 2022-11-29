package ar.edu.unnoba.poo.login.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pacientes")
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="usuarios_id", referencedColumnName="id")
	private User user;
	private long dni;
	
	public Patient() {
		super();
	}
	
	public Patient(User user, Long dni) {
		super();
		this.user = user;
		this.dni = dni;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}
}

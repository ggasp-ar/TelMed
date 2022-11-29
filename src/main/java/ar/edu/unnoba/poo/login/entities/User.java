package ar.edu.unnoba.poo.login.entities;

import javax.persistence.*;

@Entity
@Table(name= "usuarios")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	@Column(name="contrasenia")
	private String password;
	@Column(name="rol")
	private String role;
	@Column(name="nombre")
	private String name;
	@Column(name="bloqueado")
	private Boolean enabled;
	@OneToOne(mappedBy="user")
	private Medic medic;
	@OneToOne(mappedBy="user")
	private Patient patient;
	
	public User() {
		super();
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String firstName) {
		this.name = firstName;
	}
	
	public Boolean getEnabled() {
		return !enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = !enabled;
	}	
}

package ar.edu.unnoba.poo.login.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pacientes")
public class Paciente extends Persona {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="usuario_id", referencedColumnName="id")
//	private Usuario usuario;
//	@Column(nullable=false, unique=true, length=8)
//	private String dni;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="paciente")
	private List<Turno> turnos;
	
	public Paciente() {
		super();
	}
	
	public Paciente(String nombre, String dni, Usuario usuario) {
		super(nombre, dni, usuario);
//		this.usuario = usuario;
//		this.dni = dni;
	}

	public Paciente(String nombre, String dni, String telefono, 
			Date fechaNacimiento, Usuario usuario, List<Turno> turnos) {
		super(nombre, dni, usuario);
		this.turnos = turnos;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	
	public void addTurno(Turno turno) {
		this.turnos.add(turno);
	}
	
	public void removeTurno(Turno turno) {
		this.turnos.remove(turno);
	}
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	public String getDni() {
//		return dni;
//	}
//
//	public void setDni(String dni) {
//		this.dni = dni;
//	}
}

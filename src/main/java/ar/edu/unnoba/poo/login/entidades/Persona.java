package ar.edu.unnoba.poo.login.entidades;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column(name="nombre", nullable=false)
	private String nombre;
	@Column(name="dni", nullable=false, unique=true, length=8)
	private String dni;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id", referencedColumnName="id")
	private Usuario usuario;
	
	public Persona() {
		super();
	}
	
	public Persona(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

	public Persona(String nombre, String dni, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.usuario = usuario;
	}

	public Persona(Long id, String nombre, String dni, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", usuario=" + usuario + "]";
	}
}

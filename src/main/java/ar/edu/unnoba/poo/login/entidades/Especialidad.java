package ar.edu.unnoba.poo.login.entidades;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="especialidades")
public class Especialidad {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nombre", nullable=false, unique=true)
	private String nombre;
	@Column(name="observaciones")
	private String observaciones;
	@ManyToMany(mappedBy="especialidades")
	private List<Medico> medicos;
	
	public Especialidad() {
		super();
	}

	public Especialidad(String nombre, String observaciones) {
		super();
		this.nombre = nombre;
		this.observaciones = observaciones;
	}

	public Especialidad(Long id, String nombre, String observaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.observaciones = observaciones;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
		Especialidad other = (Especialidad) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Especialidad [id=" + id + ", nombre=" + nombre + ", observaciones=" + observaciones + "]";
	}
}

package ar.edu.unnoba.poo.login.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicos")
public class Medico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="usuarios_id", referencedColumnName="id")
	private Usuario usuario;
	@Column(name="matricula")
	private Long matricula;
	@Column(name="especialidad")
	private String especialidad;
	
	public Medico() {
		super();
	}
	
	public Medico(Usuario usuario, Long matricula, String especialidad) {
		super();
		this.usuario = usuario;
		this.matricula = matricula;
		this.especialidad = especialidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}

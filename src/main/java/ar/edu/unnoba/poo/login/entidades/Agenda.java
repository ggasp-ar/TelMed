package ar.edu.unnoba.poo.login.entidades;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="agendas")
public class Agenda {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy="agenda")
	private Medico medico;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="agenda")
	private List<Turno> turnos;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="agenda")
	private List<FranjaHoraria> franjasHorarias;
	@Column(name="duracion_turno", nullable=false)
	@Value("0")
	private float duracionTurno;
	
	public Agenda() {
		super();
	}
	/*
	public Agenda(Medico medico, List<Turno> turnos, 
			List<FranjaHoraria> franjasHorarias, float duracionTurno) {
		super();
		this.medico = medico;
		this.turnos = turnos;
		this.franjasHorarias = franjasHorarias;
		this.duracionTurno = duracionTurno;
	}

	public Agenda(Long id, Medico medico, List<Turno> turnos, 
			List<FranjaHoraria> franjasHorarias, float duracionTurno) {
		super();
		this.id = id;
		this.medico = medico;
		this.turnos = turnos;
		this.franjasHorarias = franjasHorarias;
		this.duracionTurno = duracionTurno;
	}
	*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public List<FranjaHoraria> getFranjasHorarias() {
		return franjasHorarias;
	}

	public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
		this.franjasHorarias = franjasHorarias;
	}
	
	public float getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(float duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	public void addFranjaHoraria(FranjaHoraria franjaHoraria) {
		this.franjasHorarias.add(franjaHoraria);
	}
	
	public void removeFranjaHoraria(FranjaHoraria franjaHoraria) {
		this.franjasHorarias.remove(franjaHoraria);
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
		Agenda other = (Agenda) obj;
		return Objects.equals(id, other.id);
	}
}

package ar.edu.unnoba.poo.login.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="turnos")
public class Turno {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="fecha", nullable=false)
	private LocalDate fecha;
	@Column(name="hora_inicio", nullable=false)
	private LocalDateTime horaInicio;
//	@Column(name="hora_fin", nullable=false)
//	private LocalDateTime horaFin;
	@ManyToOne(optional=false)
	@JoinColumn(name="paciente_id", nullable=false, updatable=false)
	private Persona paciente;
	@ManyToOne(optional=false)
	@JoinColumn(name="medico_id", nullable=false, updatable=false)
	private Persona medico;
	@ManyToOne(optional=false)
	@JoinColumn(name="agenda_id", nullable=false, updatable=false)
	private Agenda agenda;
	
	public Turno() {
		super();
	}

	public Turno(LocalDate fecha, LocalDateTime horaInicio, Persona paciente, Persona medico, Agenda agenda) {
		super();
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.paciente = paciente;
		this.medico = medico;
		this.agenda = agenda;
	}

	public Turno(Long id, LocalDate fecha, LocalDateTime horaInicio, Persona paciente, Persona medico, Agenda agenda) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.paciente = paciente;
		this.medico = medico;
		this.agenda = agenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
//	public LocalDateTime getHoraFin() {
//		return horaFin;
//	}
//
//	public void setHoraFin(LocalDateTime horaFin) {
//		this.horaFin = horaFin;
//	}

	public Persona getPaciente() {
		return paciente;
	}

	public void setPaciente(Persona paciente) {
		this.paciente = paciente;
	}

	public Persona getMedico() {
		return medico;
	}

	public void setMedico(Persona medico) {
		this.medico = medico;
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
		Turno other = (Turno) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Turno [id=" + id + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", paciente=" + paciente
				+ ", medico=" + medico + ", agenda=" + agenda + "]";
	}
}

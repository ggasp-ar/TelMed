package ar.edu.unnoba.poo.login.entidades;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.unnoba.poo.login.util.Dia;

@Entity
@Table(name="franjas_horarias")
public class FranjaHoraria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="hora_inicio")
	private LocalTime horaInicio;
	@Column(name="hora_fin")
	private LocalTime horaFin;
	@Enumerated
	@ElementCollection
	private List<Dia> diasAtencion;
	@ManyToOne(optional=false, fetch=FetchType.LAZY) 
    @JoinColumn(name="agenda_id")
	private Agenda agenda;
	
	public FranjaHoraria() {
		super();
	}

	public FranjaHoraria(LocalTime horaInicio, LocalTime horaFin, List<Dia> diasAtencion, Agenda agenda) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.diasAtencion = diasAtencion;
		this.agenda = agenda;
	}

	public FranjaHoraria(Long id, LocalTime horaInicio, LocalTime horaFin, List<Dia> diasAtencion, Agenda agenda) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.diasAtencion = diasAtencion;
		this.agenda = agenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public List<Dia> getDiasAtencion() {
		return diasAtencion;
	}

	public void setDiasAtencion(List<Dia> diasAtencion) {
		this.diasAtencion = diasAtencion;
	}
	
	public void addDiaAtencion(Dia diaAtencion) {
		this.diasAtencion.add(diaAtencion);
	}
	
	public void removeDiaAtencion(Dia diaAtencion) {
		this.diasAtencion.remove(diaAtencion);
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
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
		FranjaHoraria other = (FranjaHoraria) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FranjaHoraria [id=" + id + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", diasAtencion="
				+ diasAtencion + ", agenda=" + agenda + "]";
	}
}

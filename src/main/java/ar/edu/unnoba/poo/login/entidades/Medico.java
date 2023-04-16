package ar.edu.unnoba.poo.login.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicos")
public class Medico extends Persona{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="usuarios_id", referencedColumnName="id")
//	private Usuario usuario;
	@Column(name="matricula")
	private Long matricula;
//	@Column(name="especialidad")
//	private String especialidad;
	@ManyToMany 
	@JoinTable(name="medicos_especialidades",
        joinColumns=
            @JoinColumn(name="medico_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="especialidad_id", referencedColumnName="id")
	)
	private List<Especialidad> especialidades;
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="agenda_id", referencedColumnName="id")
	private Agenda agenda;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="medico", fetch=FetchType.LAZY)
	private List<Turno> turnos;
	
	public Medico() {
		super();
	}
	
	public Medico(String nombre, String dni, Usuario usuario, Long matricula, Agenda agenda/*, String especialidad*/) {
		super(nombre, dni, usuario);
//		this.usuario = usuario;
		this.matricula = matricula;
		this.agenda = agenda;
//		this.especialidad = especialidad;
	}

	public Medico(String nombre, String dni, Usuario usuario, 
			Long matricula, List<Especialidad> especialidades, Agenda agenda) {
		super(nombre, dni, usuario);
//		this.usuario = usuario;
		this.matricula = matricula;
		this.especialidades = especialidades;
		this.agenda = agenda;
	}
	

	public Medico(String nombre, String dni, Usuario usuario, Long matricula, 
			List<Especialidad> especialidades, List<Turno> turnos, Agenda agenda) {
		super(nombre, dni, usuario);
		this.matricula = matricula;
		this.especialidades = especialidades;
		this.turnos = turnos;
		this.agenda = agenda;
	}

//	public Medico(Long id, Usuario usuario, Long matricula, List<Especialidad> especialidades) {
//		super();
//		this.id = id;
//		this.usuario = usuario;
//		this.matricula = matricula;
//		this.especialidades = especialidades;
//	}
//
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

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void addEspecialidad(Especialidad especialidad) {
		this.especialidades.add(especialidad);
	}
	
	public void removeEspecialidad(Especialidad especialidad) {
		this.especialidades.remove(especialidad);
	}

	@Override
	public String toString() {
		return "Medico [Pesona=" + super.toString() + ", matricula=" + matricula + ", especialidades=" + especialidades + ", agenda=" + agenda
				+ ", turnos=" + turnos + "]";
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Medico other = (Medico) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	@Override
//	public String toString() {
//		return "Medico [id=" + id + ", usuario=" + usuario + ", matricula=" + matricula + ", especialidades="
//				+ especialidades + "]";
//	}
//
//	public String getEspecialidad() {
//		return especialidad;
//	}
//
//	public void setEspecialidad(String especialidad) {
//		this.especialidad = especialidad;
//	}
}

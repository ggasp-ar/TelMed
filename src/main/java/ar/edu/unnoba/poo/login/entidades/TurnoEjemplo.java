package ar.edu.unnoba.poo.login.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="turnos")
public class TurnoEjemplo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="dia")
    private Date fecha;

    @Column(name="inicio")
    private Time horaInicio;

    @Column(name="fin")
    private Time horaFin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

}

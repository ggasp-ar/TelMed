package ar.edu.unnoba.poo.login;

import ar.edu.unnoba.poo.login.entidades.*;
import ar.edu.unnoba.poo.login.repositorios.RepositorioAgenda;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;
import ar.edu.unnoba.poo.login.servicios.ServicioEspecialidad;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;
import ar.edu.unnoba.poo.login.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Controller
public class TestController {

    @Autowired
    ServicioUsuario usrsvc;
    @Autowired
    ServicioPaciente pacsvc;
    @Autowired
    ServicioMedico medsvc;
    @Autowired
    ServicioEspecialidad espsvc;
    @Autowired
    private RepositorioTurno repturno;
    @Autowired
    private RepositorioAgenda repagenda;

    @GetMapping("/usertest")
    private String regTest() {
        System.out.println("Creando usuarios de testeo");

        //"usr: admin@telmed.com pwd: 123"
        Usuario usr = new Usuario();
        usr.setEmail("admin@telmed.com");
        usr.setContrasenia("123");
        usrsvc.nuevo(usr, Rol.ROLE_ADMIN);

        usr = new Usuario();
        usr.setEmail("admin2@telmed.com");
        usr.setContrasenia("123");
        usrsvc.nuevo(usr, Rol.ROLE_ADMIN);

        //"usr: usuario1@telmed.com pwd: 123"
        usr = new Usuario();
        usr.setEmail("usuario1@telmed.com");
        usr.setContrasenia("123");
        Paciente pac = new Paciente();
        pac.setUsuario(usr);
        pac.setDni("28349032");
        pac.setNombre("Kun Aguero");
        pac.setTurnos(new ArrayList<>());
        pacsvc.nuevo(pac);

        //"usr: usuario2@telmed.com pwd: 123"
        usr = new Usuario();
        usr.setEmail("usuario2@telmed.com");
        usr.setContrasenia("123");
        pac = new Paciente();
        pac.setUsuario(usr);
        pac.setDni("24349032");
        pac.setNombre("Pepe");
        pac.setTurnos(new ArrayList<>());
        pacsvc.nuevo(pac);

        //"usr: usuario3@telmed.com pwd: 123"

        Usuario usr3 = new Usuario();
        usr3.setEmail("usuario3@telmed.com");
        usr3.setContrasenia("123");
        Paciente pac3 = new Paciente();
        pac3.setUsuario(usr3);
        pac3.setDni("14349332");
        pac3.setNombre("Jorge");
        pac3.setTurnos(new ArrayList<>());
        pacsvc.nuevo(pac3);


        //"usr: medico1@telmed.com pwd: 123"

        Especialidad e = new Especialidad();
        e.setNombre("Testesp");
        e.setObservaciones("none");

        espsvc.nueva(e);

        ArrayList<Especialidad> esps = new ArrayList<>();
        esps.add(e);

        usr = new Usuario();
        usr.setEmail("medico1@telmed.com");
        usr.setContrasenia("123");
        Medico med = new Medico();
        med.setUsuario(usr);
        med.setNombre("Demian Frasnedo");
        med.setDni("12345678");
        med.setEspecialidades(esps);
        med.setMatricula((long)123182301);
        med.setAgenda(new Agenda());
        medsvc.nuevo(med);

        System.out.println("usr: admin@telmed.com       pwd: 123");
        System.out.println("usr: usuario1@telmed.com    pwd: 123");
        System.out.println("usr: medico@telmed.com      pwd: 123");

        Turno t;
        LocalDateTime date;
        for (int i = 0; i < 10; i++) {
            t = new Turno();
            date = LocalDateTime.of(2023,4,12+(i%4),12+i,30*(i%2));
            t.setFecha(date.toLocalDate());
            t.setHoraInicio(date);
            t.setPaciente(pac);
            t.setMedico(med);
            t.setAgenda(med.getAgenda());
            repturno.save(t);
        }

        t = new Turno();
        date = LocalDateTime.of(2023,2,12,12,30);
        t.setFecha(date.toLocalDate());
        t.setHoraInicio(date);
        t.setPaciente(pac3);
        t.setMedico(med);
        t.setAgenda(med.getAgenda());
        repturno.save(t);
        //HomeController usrctrl = new HomeController();
        //usrctrl.registro(,,null);
        return "home";
    }

}

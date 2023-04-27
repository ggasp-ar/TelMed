package ar.edu.unnoba.poo.login;

import ar.edu.unnoba.poo.login.controladores.ControladorFranjaHoraria;
import ar.edu.unnoba.poo.login.entidades.*;
import ar.edu.unnoba.poo.login.repositorios.RepositorioAgenda;
import ar.edu.unnoba.poo.login.repositorios.RepositorioFranjaHoraria;
import ar.edu.unnoba.poo.login.repositorios.RepositorioMedico;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;
import ar.edu.unnoba.poo.login.servicios.ServicioEspecialidad;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;
import ar.edu.unnoba.poo.login.util.Dia;
import ar.edu.unnoba.poo.login.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;


@Controller
public class TestController {

    @Autowired
    ControladorFranjaHoraria cfh;
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
    @Autowired
    private RepositorioMedico repmedico;
    @Autowired
    private RepositorioFranjaHoraria repFH;

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
        Agenda a = new Agenda();
        a.setDuracionTurno(30);
        a.setMedico(med);
        med.setAgenda(a);
        medsvc.nuevo(med);


        System.out.println("usr: admin@telmed.com       pwd: 123");
        System.out.println("usr: usuario1@telmed.com    pwd: 123");
        System.out.println("usr: medico@telmed.com      pwd: 123");

        Turno t;
        LocalDateTime date;
        for (int i = 0; i < 3; i++) {
            t = new Turno();
                    date = LocalDateTime.of(2023,4,10,12+(2*i),30*(i%2));
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
        return "home";
    }

    @GetMapping("/fjh")
    private String fjh() {

        Agenda a = repmedico.findByMatricula((long)123182301).getAgenda();
        System.out.println(a);
        System.out.println("---------------");

        FranjaHoraria fj = new FranjaHoraria();
        fj.setAgenda(a);
        fj.setHoraInicio(LocalTime.of(12,00));
        fj.setHoraFin(LocalTime.of(15,00));
        ArrayList<Dia> d = new ArrayList<>();
        d.add(Dia.VIERNES);
        d.add(Dia.LUNES);
        fj.setDiasAtencion(d);

        FranjaHoraria fj2 = new FranjaHoraria();
        fj2.setAgenda(a);
        fj2.setHoraInicio(LocalTime.of(19,00));
        fj2.setHoraFin(LocalTime.of(21,00));

        d = new ArrayList<>();
        d.add(Dia.MIERCOLES);
        d.add(Dia.LUNES);
        fj2.setDiasAtencion(d);

        ArrayList<FranjaHoraria> fjs = new ArrayList<>();

        fjs.add(fj);
        fjs.add(fj2);

        repFH.save(fj);
        repFH.save(fj2);


        a.setFranjasHorarias(fjs);



        LocalDateTime dt = LocalDateTime.of(2023,4,10,12,00);
        Map<String, Dia> mapatodia = new HashMap<>();

        mapatodia.put("Monday",Dia.LUNES);
        mapatodia.put("Tuesday",Dia.MARTES);
        mapatodia.put("Wednesday",Dia.MIERCOLES);
        mapatodia.put("Thursday",Dia.JUEVES);
        mapatodia.put("Friday",Dia.VIERNES);
        mapatodia.put("Saturday",Dia.SABADO);
        mapatodia.put("Sunday",Dia.DOMINGO);

        Dia dia = mapatodia.get(dt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));



        LocalTime ini;
        LocalTime fin;
        int diff;
        Medico m = repmedico.findByMatricula((long)123182301);

//        List<Turno> asd = cfh.turnosdisponibles(dt.toLocalDate(),m);

        /*
        List<Turno> turnos_oc = repturno.findAllByFecha(dt.toLocalDate());
        List<LocalTime> horarios_ocupados = new ArrayList<>();

        for (Turno t:
             turnos_oc) {
            horarios_ocupados.add(t.getHoraInicio().toLocalTime())  ;
        }

        for (FranjaHoraria f:
                repFH.findFranjaHorariaByDiasAtencionAndAgenda_Medico_Id(dia,m.getId())) {
            ini = f.getHoraInicio();
            fin = f.getHoraFin();
            diff = ini.compareTo(fin);
            while(diff<0){
                if (horarios_ocupados.contains(ini)){
                    System.out.println("Hora skippeada");
                    System.out.println(ini);
                    System.out.println("----------");

                }else{
                    System.out.println(ini);
                }

                //ini = ini.plusMinutes(m.getAgenda().getDuracionTurno());
                ini = ini.plusMinutes(30);
                diff = ini.compareTo(fin);
            }
        }
        */
        //HomeController usrctrl = new HomeController();
        //usrctrl.registro(,,null);
        return "home";
    }
}

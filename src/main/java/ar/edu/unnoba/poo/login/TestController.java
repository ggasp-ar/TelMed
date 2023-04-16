package ar.edu.unnoba.poo.login;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;
import ar.edu.unnoba.poo.login.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    ServicioUsuario usrsvc;
    @Autowired
    ServicioPaciente pacsvc;
    @Autowired
    ServicioMedico medsvc;

    @GetMapping("/usertest")
    private String regTest() {
        System.out.println("Creando usuarios de testeo");

        //"usr: admin@telmed.com pwd: 123"
        Usuario usr = new Usuario();
        usr.setNombre("Mark Zuckerberg");
        usr.setEmail("admin@telmed.com");
        usr.setContrasenia("123");
        usrsvc.nuevo(usr, Roles.ROLE_ADMIN);

        //"usr: usuario1@telmed.com pwd: 123"
        Paciente pac = new Paciente();
        usr = new Usuario();
        usr.setNombre("Kun Aguero");
        usr.setEmail("usuario1@telmed.com");
        usr.setContrasenia("123");
        usrsvc.nuevo(usr, Roles.ROLE_USER);
        pac.setUsuario(usr);
        pac.setDni((long)28349032);
        pacsvc.nuevo(pac);

        //"usr: usuario1@telmed.com pwd: 123"
        Medico med = new Medico();
        usr = new Usuario();
        usr.setNombre("Demian Frasnedo");
        usr.setEmail("medico1@telmed.com");
        usr.setContrasenia("123");
        usrsvc.nuevo(usr, Roles.ROLE_MEDIC);
        med.setUsuario(usr);
        med.setEspecialidad("N/A");
        med.setMatricula((long)123182301);
        medsvc.nuevo(med);

        System.out.println("usr: admin@telmed.com       pwd: 123");
        System.out.println("usr: usuario1@telmed.com    pwd: 123");
        System.out.println("usr: medico@telmed.com      pwd: 123");

        //HomeController usrctrl = new HomeController();
        //usrctrl.registro(,,null);
        return "home";
    }
}

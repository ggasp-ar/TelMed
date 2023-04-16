package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import ar.edu.unnoba.poo.login.entidades.TurnoEjemplo;
import ar.edu.unnoba.poo.login.repositorios.RepositorioTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.entidades.Paciente;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Controller
@RequestMapping("/paciente")
public class ControladorPaciente {
	@Autowired
	private ServicioPaciente servicioPaciente;
	
	@GetMapping("/inicio")
	public String obtenerTodos(Model model) {
		model.addAttribute("pacientes", servicioPaciente.obtenerTodos());
		return "paciente/index";
	}
	
	@GetMapping("/{id}")
	public String obtenerPorId(@PathVariable Long id, Model model) {
	   model.addAttribute("paciente", servicioPaciente.obtenerPorId(id));
	   return "paciente/detalles";
	}

	@Autowired
	private RepositorioTurno repturno;
	@GetMapping("/historialturnos")
	public String obtenerPorId(Model model) {
		ArrayList<TurnoEjemplo> asd = new ArrayList<TurnoEjemplo>();
		TurnoEjemplo t;
		Date date;
		Time ti;
		for (int i = 0; i < 10; i++) {
			t = new TurnoEjemplo();
			t.setId((long)i);
			date = new Date(2023,4,12+(i%4));
			t.setFecha(date );
			ti=new Time(15+i,00,00);
			t.setHoraInicio(ti);
			ti=new Time(15+i,30,00);
			t.setHoraFin(ti);
			repturno.save(t);
			asd.add(t);
		}
		System.out.println(asd);
		model.addAttribute("turnos", repturno.findAllByOrderByFecha() );
		return "paciente/historialturnos";
	}
	@GetMapping(params="dni")
	public String obtenerPorDni(@PathVariable Long dni, Model model) {
	   model.addAttribute("paciente", servicioPaciente.obtenerPorDni(dni));
	   return "paciente/detalles";
	}
	
	@PutMapping("/editar/{id}")
	public String modificar(@Valid Paciente paciente, @PathVariable Long id, Model model) {
	    servicioPaciente.modificar(paciente, id);
	    model.addAttribute("pacientes", servicioPaciente.obtenerTodos());
	    return "redirect:/paciente/inicio";
	}
}

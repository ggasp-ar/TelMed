package ar.edu.unnoba.poo.login.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unnoba.poo.login.servicios.ServicioTurno;


@Controller
@RequestMapping("/turno")
public class ControladorTurno {

	@Autowired
	private ServicioTurno servicioTurno;
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable("id") Long id, Model modelo, RedirectAttributes redAtt) throws Exception, IllegalArgumentException{
		servicioTurno.eliminarTurno(id);
		redAtt.addFlashAttribute("mensaje","El turno se canceló correctamente.");
		redAtt.addFlashAttribute("tipo","success");
		return "redirect:/paciente/historial-turnos";
	}
	
	@PostMapping("/{id}")
	public String turnoSemanaSiguiente(@PathVariable("id") Long id, Model modelo, RedirectAttributes redAtt) throws CloneNotSupportedException {
		servicioTurno.clonarTurnoSemanaSiguiente(id);
		redAtt.addFlashAttribute("mensaje","El turno se copió correctamente.");
		redAtt.addFlashAttribute("tipo","success");
		return "redirect:/paciente/historial-turnos";
	}
}
package ar.edu.unnoba.poo.login.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.servicios.ServicioTurno;


@Controller
@RequestMapping("/turno")
public class ControladorTurno {

	@Autowired
	private ServicioTurno servicioTurno;
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable("id") Long id, Model modelo) {
		servicioTurno.eliminarTurno(id);
		return "redirect:/paciente/historial-turnos";
	}
}
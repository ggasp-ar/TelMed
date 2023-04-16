package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.entidades.Especialidad;
import ar.edu.unnoba.poo.login.servicios.ServicioEspecialidad;

@Controller
@RequestMapping("/especialidad")
public class ControladorEspecialidad {
	@Autowired
	private ServicioEspecialidad servicioEspecialidad;
	
	@GetMapping("/inicio")
	public String inicio(Model modelo) {
		modelo.addAttribute("especialidades", servicioEspecialidad.obtenerTodas());
		return "especialidad/index";
	}
	
	@GetMapping("/nueva")
	public String nueva(Model modelo) {
		modelo.addAttribute("especialidad", new Especialidad());
		return "especialidad/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String modificar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("especialidad", servicioEspecialidad.obtenerPorId(id));
		return "especialidad/formulario";
	}
	
	@PostMapping
	public String nueva(@Valid @ModelAttribute("especialidad") Especialidad especialidad, Model modelo) {
		servicioEspecialidad.nueva(especialidad);
		return "redirect:/especialidad/inicio";
	}
	
	@PutMapping("/{id}")
	public String modificar(@PathVariable Long id, @Valid @ModelAttribute("especialidad") Especialidad especialidad, Model modelo) {
		servicioEspecialidad.modificar(especialidad, id);
		return "redirect:/especialidad/inicio";
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable Long id, Model modelo) {
		servicioEspecialidad.eliminar(id);
		return "redirect:/especialidad/inicio";
	}
}

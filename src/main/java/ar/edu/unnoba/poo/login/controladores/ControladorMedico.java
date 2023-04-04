package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unnoba.poo.login.entidades.Medico;
import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;
import ar.edu.unnoba.poo.login.util.Roles;

@Controller
@RequestMapping("/medico")
public class ControladorMedico {
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/{id}")
	public String obtenerPorId(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("medico", servicioMedico.obtenerPorId(id));
		return "medico/detalles";
	}
	
	@GetMapping("/inicio")
	public String obtenerTodos(Model modelo) {
		modelo.addAttribute("medicos", servicioMedico.obtenerTodos());
		return "medico/index";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("medico", new Medico());
		modelo.addAttribute("usuario", new Usuario());
		return "medico/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model modelo) {
		Medico medico = servicioMedico.obtenerPorId(id);
		Usuario usuario = medico.getUsuario();
		modelo.addAttribute("medico", medico);
		modelo.addAttribute("usuario", usuario);
		return "medico/formulario";
	}
	
	@PostMapping
	public String nuevo(@Valid Medico medico, @Valid Usuario usuario, Model modelo) {
		Usuario nuevoUsuario = servicioUsuario.nuevo(usuario, Roles.ROLE_MEDIC);
		medico.setUsuario(nuevoUsuario);
		servicioMedico.nuevo(medico);
		modelo.addAttribute("medics", servicioMedico.obtenerTodos());
		return "redirect:/medico/inicio";
	}
	
	@PutMapping("/{id}")
	public String modificar(@PathVariable Long id, @Valid Medico medico, Model modelo) {
		servicioMedico.modificar(medico, id);
		return "redirect:/medico/inicio";
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable("id") Long id, Model modelo) {
		servicioMedico.eliminar(id);
		modelo.addAttribute("medics", servicioMedico.obtenerTodos());
		return "redirect:/medico/inicio";
	}
}

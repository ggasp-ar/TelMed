package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import ar.edu.unnoba.poo.login.entidades.Usuario;
import ar.edu.unnoba.poo.login.servicios.ServicioUsuario;

@Controller
@RequestMapping("/usuario")
public class ControladorUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/inicio")
	public String obtenerTodos(Model model) {
		model.addAttribute("usuarios", servicioUsuario.obtenerTodos());
		return "usuario/index";
	}
	
	@GetMapping("/{id}")
	public String obtenerPorId(@PathVariable Long id, Model model) {
	   model.addAttribute("usuarios", servicioUsuario.obtenerTodos());
	   return "detalles";
	}
	
	@GetMapping(params="email")
	public String obtenerPorEmail(@RequestParam String email, Model modelo) {
		modelo.addAttribute("usuario", servicioUsuario.obtenerPorEmail(email));
		return "detalles";
	}
	
	@PutMapping("/{id}")
	public String modificar(@Valid Usuario user, @PathVariable Long id, Model model) {
	    servicioUsuario.modificar(user, id);
	    model.addAttribute("usuarios", servicioUsuario.obtenerTodos());
	    return "redirect:/usuario/index";
	}

	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable Long id, Model model) {
		  servicioUsuario.eliminar(id);
		  model.addAttribute("usuarios", servicioUsuario.obtenerTodos());
		  return "redirect:/usuario/inicio";
	}

	@GetMapping("/perfil/{id}")
	public String userProfile(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", servicioUsuario.obtenerPorId(id));
		return "usuario/perfil";
	}
}

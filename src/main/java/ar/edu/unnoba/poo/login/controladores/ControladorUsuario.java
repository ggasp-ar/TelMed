package ar.edu.unnoba.poo.login.controladores;

import javax.validation.Valid;

import ar.edu.unnoba.poo.login.detallesUsuario.UsuarioLogueado;
import ar.edu.unnoba.poo.login.servicios.ServicioMedico;
import ar.edu.unnoba.poo.login.servicios.ServicioPaciente;
import ar.edu.unnoba.poo.login.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	@Autowired
	private ServicioMedico servicioMedico;
	@Autowired
	private ServicioPaciente servicioPaciente;
	
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

	@GetMapping("/perfil")
	public String userProfile(Model model, @AuthenticationPrincipal UsuarioLogueado usuario) {
		Usuario user = servicioUsuario.obtenerPorId(usuario.getUsuario().getId());
		model.addAttribute("usuario", user);
		Rol rol = usuario.getUsuario().getRol();

		if (rol == Rol.ROLE_MEDIC) {
			model.addAttribute("medico", servicioMedico.obtenerPorUsuario(user));
		}
		if (rol == Rol.ROLE_USER) {
			model.addAttribute("paciente", servicioPaciente.obtenerPorUsuario(user));
		}
		return "usuario/perfil";
	}
	@GetMapping("/perfil/{id}")
	public String userProfile(@PathVariable Long id, Model model) {
		Usuario user = servicioUsuario.obtenerPorId(id);
		model.addAttribute("usuario", user);
		Rol rol = user.getRol();

		if (rol == Rol.ROLE_MEDIC) {
			model.addAttribute("medico", servicioMedico.obtenerPorUsuario(user));
		}
		if (rol == Rol.ROLE_USER) {
			model.addAttribute("paciente", servicioPaciente.obtenerPorUsuario(user));
		}
		return "usuario/perfil";
	}
}

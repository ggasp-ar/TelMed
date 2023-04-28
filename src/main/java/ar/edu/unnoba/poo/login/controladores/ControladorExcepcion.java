package ar.edu.unnoba.poo.login.controladores;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    
@ControllerAdvice
public class ControladorExcepcion {
	
    @ExceptionHandler(Exception.class)
    public String excepcion(Exception ex, RedirectAttributes redirectAttr) {
        redirectAttr.addFlashAttribute("mensaje", ex.getMessage());
        redirectAttr.addFlashAttribute("tipo","danger");
        return "redirect:/paciente/historial-turnos";
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String excepcion(IllegalArgumentException il, RedirectAttributes redirectAttr) {
        redirectAttr.addFlashAttribute("mensaje", il.getMessage());
        redirectAttr.addFlashAttribute("tipo","danger");
        return "redirect:/paciente/historial-turnos";
    }
}

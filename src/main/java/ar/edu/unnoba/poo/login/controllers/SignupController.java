package ar.edu.unnoba.poo.login.controllers;

import ar.edu.unnoba.poo.login.entities.User;
import ar.edu.unnoba.poo.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private UserService uss;
    @GetMapping("/signup")
    public String signuppage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute User user, Model model) {
        System.out.println("Registrando usuario...");
        try{
            user.setRole("USUARIO");
            user.setEnabled(true);
            uss.addUser(user);
            System.out.println("Se creo el usuario" + user.getId());
            return "redirect:/login";
        }catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
            return "redirect:/signup";
        }
    }
}

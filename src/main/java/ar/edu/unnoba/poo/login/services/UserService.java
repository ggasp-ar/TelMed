package ar.edu.unnoba.poo.login.services;

import java.util.List;

import ar.edu.unnoba.poo.login.repositories.UserRepository;
import ar.edu.unnoba.poo.login.util.Roles;
import ar.edu.unnoba.poo.login.util.encriptador.EncriptadorInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import ar.edu.unnoba.poo.login.entities.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EncriptadorInterface encriptador;

	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	public User addUser(User user, Roles roles) {
		user.setEnabled(true);
		user.setRole(roles.name());
		user.setPassword(encriptador.passwordEncrypt(user.getPassword()));
	    return userRepository.save(user);
	}
	
	public User getUser(Long id) {
	    return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public User replaceUser(User user, Long id) {
	    return userRepository.findById(id)
	      .map(u -> {
	        u.setEmail(user.getEmail());
	        u.setPassword(encriptador.passwordEncrypt(user.getPassword()));
	        u.setName(user.getName());
	        return userRepository.save(u);
	      })
	      .orElseGet(() -> {
	        return userRepository.save(user);
	      });
	}

	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
}

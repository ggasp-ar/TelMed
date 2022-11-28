package ar.edu.unnoba.poo.login.services;

import java.util.List;

import ar.edu.unnoba.poo.login.repositories.UserRepository;
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
	
	public void addUser(User user) {
		user.setEnabled(true);
		user.setRole("USER");
		user.setPassword(encriptador.passwordEncrypt(user.getPassword()));
	    userRepository.save(user);
	}
	
	public User getUser(Long id) {
	    return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	public User replaceUser(User user, Long id) {
	    return userRepository.findById(id)
	      .map(u -> {
	        u.setEmail(user.getEmail());
	        u.setPassword(user.getPassword());
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
	
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	
}

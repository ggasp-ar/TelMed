package ar.edu.unnoba.poo.login.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ar.edu.unnoba.poo.login.userDetails.UserLoggedIn;

import java.util.NoSuchElementException;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
    	try { 
    		ar.edu.unnoba.poo.login.entities.User u = userService.findUserByUsername(username);
    		
    		List grantedAuthorities = new ArrayList();
    		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(u.getRole());
    		grantedAuthorities.add(grantedAuthority);
    		UserLoggedIn user = new UserLoggedIn(u.getEmail(), u.getPassword(), grantedAuthorities, u);

			if (!user.isEnabled()){
				throw new Exception("Usuario Deshabilitado");
			}

    		return user;
    	}	
    	catch(NoSuchElementException e) {
    		throw new UsernameNotFoundException("User no found!");
    	}catch(Exception e){
			throw new UsernameNotFoundException(e.getMessage());
		}
   
    }
}

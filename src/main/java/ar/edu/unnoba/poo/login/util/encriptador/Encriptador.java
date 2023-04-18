package ar.edu.unnoba.poo.login.util.encriptador;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("encriptador")
public class Encriptador implements EncriptadorInterface {
	
	private static final int LONG = 4;
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(LONG);
	
	public Encriptador() {
		super();
	}
	
	public String passwordEncrypt(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
}


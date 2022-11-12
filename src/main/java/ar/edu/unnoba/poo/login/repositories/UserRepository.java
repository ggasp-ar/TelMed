package ar.edu.unnoba.poo.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.unnoba.poo.login.entities.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.email = :email")
	public User findUserByUsername(@Param("email") String email);
	
}

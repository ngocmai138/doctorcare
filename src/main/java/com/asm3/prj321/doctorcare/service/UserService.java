package com.asm3.prj321.doctorcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.asm3.prj321.doctorcare.entities.User;

public interface UserService extends UserDetailsService{
	public List<User> findAll();
	public User saveUser(User user);
	public Optional<User> findByEmail(String email);
	public List<Object[]> getPersonalInfo(int userId);
	public List<User> searchBySpecialization(String specializationName); 
	public boolean existsById(int userId);
	public List<User> findAllPatients();
}

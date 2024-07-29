package com.asm3.prj321.doctorcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.entities.User;

public interface UserService extends UserDetailsService{
	public User save(User user);
	public Optional<User> findById(int userId);
	public Optional<User> findByEmail(String email);
	public List<User> findAll();
	public List<User> searchBySpecialization(String specializationName); 
	public List<User> findAllPatients();
	public List<Object[]> getPersonalInfo(int userId);
	public boolean existsById(int userId);
}

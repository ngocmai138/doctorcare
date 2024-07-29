package com.asm3.prj321.doctorcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		if(user==null) throw new UsernameNotFoundException("User's email not found: "+email);
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.getAuthorities());
	}
	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public List<Object[]> getPersonalInfo(int userId) {
		return userRepository.getPersonalInfo(userId);
	}
	@Override
	public List<User> searchBySpecialization(String specializationName) {
		return userRepository.searchBySpecialization(specializationName);
	}
	@Override
	public boolean existsById(int userId) {
		return userRepository.existsById(userId);
	}
	@Override
	public List<User> findAllPatients() {
		return userRepository.findAllPatients();
	}
	@Override
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}
	

}

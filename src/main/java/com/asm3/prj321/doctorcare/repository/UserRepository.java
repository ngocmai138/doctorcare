package com.asm3.prj321.doctorcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asm3.prj321.doctorcare.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User>  findByEmail(String email);
	@Procedure(procedureName = "proc_getPersonalInfo")
	List<Object[]> getPersonalInfo(@Param("userId") int userId);
	@Procedure(procedureName = "proc_search_by_specialization")
	List<User> searchBySpecialization(@Param("keyword") String specializationName); 
	@Query("select u from User u where u.role.id=2")
	List<User> findAllPatients();
}

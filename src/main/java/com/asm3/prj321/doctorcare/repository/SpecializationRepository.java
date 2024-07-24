package com.asm3.prj321.doctorcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asm3.prj321.doctorcare.entities.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
	@Procedure(procedureName ="proc_getHighlightedSpecializations")
	List<Specialization> findHighlightedSpecializations(@Param("limitCount") int limitCount);
}

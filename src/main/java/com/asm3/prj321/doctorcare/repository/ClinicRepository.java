package com.asm3.prj321.doctorcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asm3.prj321.doctorcare.entities.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
	@Procedure(procedureName = "proc_getHighlightedClinics")
	List<Clinic> getHighlightedClinics(@Param("limitCount") int limitCount);
	@Procedure(procedureName = "proc_general_search")
	List<Clinic> seachWithKeyword(@Param("p_region") String p_region,
									@Param("p_category") String p_specialization,
									@Param("p_price") String p_price,
									@Param("p_clinicName") String p_clinicName);
}

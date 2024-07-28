package com.asm3.prj321.doctorcare.service;

import java.util.Optional;

import com.asm3.prj321.doctorcare.entities.Status;

public interface StatusService {
	public Optional<Status> findById(int statusId);
}

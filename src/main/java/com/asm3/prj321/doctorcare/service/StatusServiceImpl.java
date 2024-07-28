package com.asm3.prj321.doctorcare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.Status;
import com.asm3.prj321.doctorcare.repository.StatusRepository;
@Service
@Transactional
public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public Optional<Status> findById(int statusId) {
		return statusRepository.findById(statusId);
	}

}

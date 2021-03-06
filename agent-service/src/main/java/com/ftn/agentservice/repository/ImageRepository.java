package com.ftn.agentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findAllByAccUnitId(Long id);
}

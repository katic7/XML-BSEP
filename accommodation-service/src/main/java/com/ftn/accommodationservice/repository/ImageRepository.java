package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
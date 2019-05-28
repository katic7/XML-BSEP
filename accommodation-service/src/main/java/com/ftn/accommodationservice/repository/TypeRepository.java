package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}

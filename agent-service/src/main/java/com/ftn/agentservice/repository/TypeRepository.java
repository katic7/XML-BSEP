package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.Type;


@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}

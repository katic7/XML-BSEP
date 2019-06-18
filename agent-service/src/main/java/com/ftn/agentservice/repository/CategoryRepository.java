package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

} 
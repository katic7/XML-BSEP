package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}

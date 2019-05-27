package com.ftn.accommodationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.dto.CommentVisibilityDTO;
import com.ftn.accommodationservice.model.Comment;
import com.ftn.accommodationservice.repository.CommentRepository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	
	@PostMapping("/visibility")
	public ResponseEntity<?> commentVisibility(@RequestBody CommentVisibilityDTO cv){
		Comment com = commentRepository.getOne(cv.getId());
		com.setVisible(cv.isFlag());
		commentRepository.save(com);
		return new ResponseEntity<String>("SET VISIBILITY!", HttpStatus.OK);
	}
}

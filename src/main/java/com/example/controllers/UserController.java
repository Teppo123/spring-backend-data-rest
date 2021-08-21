package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repositories.UserRepository;

@Controller
@RequestMapping("/special-user-actions")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/deactivateUserById/{" + UserRepository.PARAM_ID + "}")
	@Transactional
	public ResponseEntity<Integer> deactivateUserById(@PathVariable(name = UserRepository.PARAM_ID) long id) {
		return ResponseEntity.ok(this.userRepository.deactivateUserById(id));
	}

}

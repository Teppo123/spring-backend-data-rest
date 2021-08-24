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
@RequestMapping(UserController.PATH_ROOT)
public class UserController {

	public static final String PATH_ROOT = "/users";

	protected static final String PATH_DEACTIVATE_USER_BY_ID_PATH = "/deactivate/{" + UserRepository.PARAM_ID
			+ "}";

	@Autowired
	private UserRepository userRepository;

	@PutMapping(UserController.PATH_DEACTIVATE_USER_BY_ID_PATH)
	@Transactional
	public ResponseEntity<Integer> deactivateUserById(@PathVariable(name = UserRepository.PARAM_ID) long id) {
		return ResponseEntity.ok(this.userRepository.deactivateUserById(id));
	}

}

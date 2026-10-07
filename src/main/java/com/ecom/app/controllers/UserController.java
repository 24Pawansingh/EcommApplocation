package com.ecom.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.app.Services.UserService;
import com.ecom.app.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {

		return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		return userService.fetchUsers(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody User user) {

		userService.addUser(user);

		return ResponseEntity.ok("User added succesfully..");

	}

}

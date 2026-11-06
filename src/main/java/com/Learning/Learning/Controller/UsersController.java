package com.Learning.Learning.Controller;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Learning.Learning.Configurations.CustomBeanConfiguration;
import com.Learning.Learning.Configurations.JwtConfig;
import com.Learning.Learning.Service.UsersService;
import com.Learning.Learning.beans.Users;

@RestController
@RequestMapping("/api/users/")
public class UsersController {

	private final UsersService usersService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	// Save User
	@PostMapping
	public ResponseEntity<Users> saveUser(@RequestBody Users user) {
		System.out.println("i am in user controller");
		Users savedUser = usersService.saveUser(user);

		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Users users) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
		if (authenticate.isAuthenticated()) {
			Users user = (Users) authenticate.getPrincipal();

			String token = JwtConfig.generateToken(user);

			user.setToken(token);
			return ResponseEntity.ok(user);

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	// Get All Users
	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {

		return ResponseEntity.ok(usersService.getAllUsers());
	}

	// Get User By ID
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable String id) {

		return usersService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Get User By Username
	@GetMapping("/username/{username}")
	public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {

		return usersService.getUserByUsername(username).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {

		if (usersService.getUserById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		usersService.deleteUser(id);

		return ResponseEntity.ok("User deleted successfully");
	}
}
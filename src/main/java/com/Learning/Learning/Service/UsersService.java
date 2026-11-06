package com.Learning.Learning.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Learning.Learning.Repository.UsersRepository;
import com.Learning.Learning.beans.Users;

@Service
public class UsersService implements UserDetailsService {

	private final UsersRepository usersRepository;

	private final PasswordEncoder passwordEncoder;

	public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Save User
	public Users saveUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return usersRepository.save(user);
	}

	// Get All Users
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	// Get User By ID
	public Optional<Users> getUserById(String id) {
		return usersRepository.findById(id);
	}

	// Get User By Username
	public Optional<Users> getUserByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	// Delete User
	public void deleteUser(String id) {
		usersRepository.deleteById(id);
	}

	// Check Username
	public boolean usernameExists(String username) {
		return usersRepository.existsByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usersRepository.findByUsername(username).orElseThrow();
	}
}
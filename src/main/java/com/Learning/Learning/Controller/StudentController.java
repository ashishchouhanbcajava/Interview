package com.Learning.Learning.Controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Learning.Learning.Service.StudentService;
import com.Learning.Learning.beans.Student;

@RequestMapping("/api/student/")
@RestController
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
		// TODO Auto-generated constructor stub
	}

	@PostMapping
	public ResponseEntity<?> saveStudent(@RequestBody Student student) {
		// TODO: process POST request
		return ResponseEntity.ok(studentService.save(student));
	}

}

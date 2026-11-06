package com.Learning.Learning.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Learning.Learning.Service.CourseService;
import com.Learning.Learning.beans.Course;

import jakarta.validation.Valid;

import java.awt.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/api/course/")
@RestController
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		// TODO Auto-generated constructor stub

		this.courseService = courseService;
	}

	@PreAuthorize("""
			hasAuthority(T(com.Learning.Learning.Enums.Permission).Write.name())
			or hasAuthority(T(com.Learning.Learning.Enums.Permission).All.name())
			""")
//	@PreAuthorize("hasAuthority(T(com.Learning.Learning.Enums.Permission).Write.name())")
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Course course) {
		return ResponseEntity.ok(courseService.save(course));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCourse(@PathVariable String id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("'id' is required");
		}

		return ResponseEntity.ok(courseService.getOne(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("'id' is required");
		}
		courseService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(courseService.getCourses());
	}

	@GetMapping("/test")
	public String test() {

		System.out.println("Main Thread: " + Thread.currentThread().getName());

		System.out.println("Main Authentication: " + SecurityContextHolder.getContext().getAuthentication());

		CompletableFuture.runAsync(() -> {

			System.out.println("Async Thread: " + Thread.currentThread().getName());

			System.out.println("Async Authentication: " + SecurityContextHolder.getContext().getAuthentication());

		});

		return "Done";
	}
}

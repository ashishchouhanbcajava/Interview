package com.Learning.Learning.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Learning.Learning.Repository.CourseRepository;
import com.Learning.Learning.beans.Course;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	private CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Course save(Course course) {
		return courseRepository.save(course);
	}

	public Course getOne(String id) {
		return courseRepository.findById(id).orElseThrow();
	}

	public void delete(String id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	public List<Course> getCourses() {
		List<Course> all = courseRepository.findAll();
		return all == null ? new ArrayList<>() : all;
	}

}

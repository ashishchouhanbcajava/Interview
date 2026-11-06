package com.Learning.Learning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learning.Learning.Repository.StudentRepository;
import com.Learning.Learning.beans.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public Student save(Student student) {
		// TODO Auto-generated method stub
		return repository.save(student);
	}

}

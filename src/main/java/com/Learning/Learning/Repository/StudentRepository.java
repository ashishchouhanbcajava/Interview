package com.Learning.Learning.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Learning.Learning.beans.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}

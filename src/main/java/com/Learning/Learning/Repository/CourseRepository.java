package com.Learning.Learning.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Learning.Learning.beans.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

}

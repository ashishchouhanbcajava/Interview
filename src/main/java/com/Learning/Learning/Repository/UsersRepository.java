package com.Learning.Learning.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Learning.Learning.beans.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

}
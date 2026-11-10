package com.Learning.Learning.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Learning.Learning.beans.RefreshTokenDetails;

@Repository
public interface RefreshTokenDetailsRepository extends MongoRepository<RefreshTokenDetails, String> {

}

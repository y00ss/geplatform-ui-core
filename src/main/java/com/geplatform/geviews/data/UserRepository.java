package com.geplatform.geviews.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByUsername(String username);
}

package com.geplatform.geviews.data.questionario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface DomandaRepository extends MongoRepository<Question, Long>{
}

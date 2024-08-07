package com.geplatform.geviews.data.pratica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface PraticaRepository extends MongoRepository<Pratica,String> {
}

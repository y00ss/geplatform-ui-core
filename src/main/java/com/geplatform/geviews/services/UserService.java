package com.geplatform.geviews.services;

import com.geplatform.geviews.data.User;
import com.geplatform.geviews.data.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final MongoTemplate mongoTemplate;

    public UserService(UserRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        Query query = new Query().with(pageable);
        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(query, User.class);
        return new PageImpl<>(users, pageable, count);
    }

    public Page<User> list(Pageable pageable, Criteria criteria) {
        Query query = new Query(criteria).with(pageable);
        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(query, User.class);
        return new PageImpl<>(users, pageable, count);
    }


    public int count() {
        return (int) repository.count();
    }

}

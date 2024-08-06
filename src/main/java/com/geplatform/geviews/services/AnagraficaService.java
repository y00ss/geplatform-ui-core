package com.geplatform.geviews.services;

import com.geplatform.geviews.data.User;
import com.geplatform.geviews.data.anagrafica.Anagrafica;
import com.geplatform.geviews.data.anagrafica.AnagraficaRepository;
import com.geplatform.geviews.dto.Company;
import com.geplatform.geviews.mapper.CompanyMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AnagraficaService {

    private final AnagraficaRepository repository;
    private final CompanyMapper companyMapper;
    private final MongoTemplate mongoTemplate;


    AnagraficaService(AnagraficaRepository anagraficaRepository, CompanyMapper companyMapper, MongoTemplate mongoTemplate){
        this.repository = anagraficaRepository;
        this.companyMapper = companyMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Anagrafica> getAll(){

        return repository.findAll();
    }
    public Optional<Anagrafica> get(Long id) {
        return repository.findById(id);
    }

    public Company save(Company entity){
        Anagrafica anagrafica = companyMapper.toEntity(entity);

        anagrafica = repository.save(anagrafica);

        return companyMapper.toDTO(anagrafica);
    }

    public Anagrafica update(Anagrafica entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Anagrafica> list(Pageable pageable) {
        Query query = new Query().with(pageable);
        long count = mongoTemplate.count(query, Anagrafica.class);
        List<Anagrafica> anagrafiche = mongoTemplate.find(query, Anagrafica.class);
        return new PageImpl<>(anagrafiche, pageable, count);
    }

    public Page<Anagrafica> list(Pageable pageable, Criteria criteria) {
        Query query = new Query(criteria).with(pageable);
        long count = mongoTemplate.count(query, Anagrafica.class);
        List<Anagrafica> anagrafiche = mongoTemplate.find(query, Anagrafica.class);
        return new PageImpl<>(anagrafiche, pageable, count);
    }

}

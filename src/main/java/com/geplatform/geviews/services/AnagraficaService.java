package com.geplatform.geviews.services;

import com.geplatform.geviews.data.anagrafica.Anagrafica;
import com.geplatform.geviews.data.anagrafica.AnagraficaRepository;
import com.geplatform.geviews.dto.Company;
import com.geplatform.geviews.mapper.CompanyMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AnagraficaService {

    private final AnagraficaRepository repository;
    private final CompanyMapper companyMapper;


    AnagraficaService(AnagraficaRepository anagraficaRepository, CompanyMapper companyMapper){
        this.repository = anagraficaRepository;
        this.companyMapper = companyMapper;
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
        return repository.findAll(pageable);
    }

    public Page<Anagrafica> list(Pageable pageable, Specification<Anagrafica> filter) {
        return repository.findAll(filter, pageable);
    }

}

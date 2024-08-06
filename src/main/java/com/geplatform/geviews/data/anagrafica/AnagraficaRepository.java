package com.geplatform.geviews.data.anagrafica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface AnagraficaRepository extends MongoRepository<Anagrafica, Long>  {

    //Anagrafica findCompanyByRegioneSociale(String regioneSociale);
}

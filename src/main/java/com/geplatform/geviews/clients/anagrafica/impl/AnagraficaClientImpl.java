package com.geplatform.geviews.clients.anagrafica.impl;

import com.geplatform.geviews.clients.MicroserviceClient;
import com.geplatform.geviews.clients.anagrafica.AnagraficaClient;
import com.geplatform.geviews.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagraficaClientImpl implements AnagraficaClient {

    @Autowired
    MicroserviceClient microserviceClient;


    @Override
    public List<Company> getAllAnagrafiche() {
        String url = "http://localhost:8082/api/v1/anagrafiche";
        return microserviceClient.postDataToAnotherService(url, null, Company.class);
    }

    @Override
    public Company getCompanyById(String id) {
        String url = "http://localhost:8082/api/v1/anagrafiche/" + id;
        return microserviceClient.getDataFromAnotherService(url, Company.class);
    }

    @Override
    public Company save(Company company) {
        String url = "http://localhost:8082/api/v1/anagrafiche";
        return microserviceClient.postDataToAnotherService(url, company, Company.class).get(0);
    }

    @Override
    public Company update(String id, Company company) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        String url = "localhost:8082/api/v1/anagrafiche" + id;
        return microserviceClient.deleteDataFromAnotherService(url);
    }
}

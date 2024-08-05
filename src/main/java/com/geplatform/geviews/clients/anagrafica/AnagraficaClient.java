package com.geplatform.geviews.clients.anagrafica;

import com.geplatform.geviews.dto.Company;

import java.util.List;

public interface AnagraficaClient {

    List<Company> getAllAnagrafiche();
    Company getCompanyById(String id);
    Company save(Company company);
    Company update(String id, Company company);
    Boolean delete(String id);
}

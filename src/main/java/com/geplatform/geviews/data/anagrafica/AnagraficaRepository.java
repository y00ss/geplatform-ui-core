package com.geplatform.geviews.data.anagrafica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long>, JpaSpecificationExecutor<Anagrafica> {

    //Anagrafica findCompanyByRegioneSociale(String regioneSociale);
}

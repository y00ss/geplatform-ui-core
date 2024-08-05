package com.geplatform.geviews.data.anagrafica;

import com.geplatform.geviews.data.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "ge_anagrafica")
public class Anagrafica extends AbstractEntity {

    private String ragioneSociale;

    private Double numeroDipendenti;

    private String sedeLegale;

    private String sediTerritoriali;

    private String codiceAteco;

    private String industryRiferimento;

    private String altriSistemiGestione;

    private String composizioneDipartimentoHR;

    private String dipQualityInternalAuditing;

    private String industryRiferimento2;
}
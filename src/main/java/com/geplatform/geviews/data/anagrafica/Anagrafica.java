package com.geplatform.geviews.data.anagrafica;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document( "ge_anagrafica")
public class Anagrafica {

    @Id
    private String id;

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
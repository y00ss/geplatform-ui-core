package com.geplatform.geviews.data.anagrafica;



import com.geplatform.geviews.constants.CompanyCluster;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document( "ge_anagrafica")
public class Anagrafica {

    @Id
    private String id;

    @NotNull
    private String ragioneSociale;

    @NotNull
    private Double numeroDipendenti;

    @NotNull
    private CompanyCluster cluster;

    private String sedeLegale;

    private String sediTerritoriali;

    @NotNull
    private String codiceAteco;

    private String industryRiferimento;

    private String altriSistemiGestione;

    private String composizioneDipartimentoHR;

    private String dipQualityInternalAuditing;

    private String industryRiferimento2;
}
package com.geplatform.geviews.dto;

import com.geplatform.geviews.constants.CompanyCluster;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private  String id;

    @NotBlank(message = "Ragione Sociale è obbligatorio")
    private String ragioneSociale;

    @NotNull(message = "Numero Dipendenti è obbligatorio")
    private Double numeroDipendenti;

    @NotNull(message = "Company Cluster è obbligatorio")
    private CompanyCluster companyCluster;

    @NotBlank(message = "Sede Legale è obbligatorio")
    private String sedeLegale;

    private String sediTerritoriali;

    @NotBlank(message = "Codice Ateco è obbligatorio")
    private String codiceAteco;

    private String industryRiferimento;

    private String altriSistemiGestione;

    @NotBlank(message = "Composizione Dipartimento HR è obbligatorio")
    private String composizioneDipartimentoHR;

    private String dipQualityInternalAuditing;

    private String industryRiferimento2;
}
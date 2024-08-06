package com.geplatform.geviews.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @NotBlank(message = "Ragione Sociale è obbligatorio")
    private String ragioneSociale;

    @NotNull(message = "Numero Dipendenti is mandatory")
    private Double numeroDipendenti;

    @NotBlank(message = "Sede Legale is mandatory")
    private String sedeLegale;

    private String sediTerritoriali;

    @NotBlank(message = "Codice Ateco is mandatory")
    private String codiceAteco;

    private String industryRiferimento;

    private String altriSistemiGestione;

    @NotBlank(message = "Composizione Dipartimento HR is mandatory")
    private String composizioneDipartimentoHR;

    private String dipQualityInternalAuditing;

    private String industryRiferimento2;
}
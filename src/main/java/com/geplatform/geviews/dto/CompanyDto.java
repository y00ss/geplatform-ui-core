package com.geplatform.geviews.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    @NotBlank(message = "Ragione Sociale è obbligatorio")
    private String ragioneSociale;

    @NotNull(message = "Numero Dipendenti is mandatory")
    private Integer numeroDipendenti;

    @NotBlank(message = "Sede Legale is mandatory")
    private String sedeLegale;

    @NotBlank(message = "Sedi Territoriali is mandatory")
    private String sediTerritoriali;

    @NotBlank(message = "Codice Ateco is mandatory")
    private String codiceAteco;

    @NotBlank(message = "Industry di Riferimento is mandatory")
    private String industryRiferimento;

    @NotBlank(message = "Altri Sistemi di Gestione is mandatory")
    private String altriSistemiGestione;

    @NotBlank(message = "Composizione Dipartimento HR is mandatory")
    @Size(max = 1000, message = "Composizione Dipartimento HR must be less than 1000 characters")
    private String composizioneDipartimentoHR;

    @NotBlank(message = "Dip. Quality/Internal Auditing is mandatory")
    private String dipQualityInternalAuditing;

    @NotBlank(message = "Industry di Riferimento is mandatory")
    private String industryRiferimento2;
}
package com.geplatform.geviews.views.anagrafiche;


import com.geplatform.geviews.constants.UiConstants;
import com.geplatform.geviews.dto.CompanyDto;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;


/**
 * Tabella in cui ci sono tutte le anagrafiche.
 * Ogni record ha la possibilità di essere modificato,
 * pagina di nuova Anagrafica e ricerca
 */

@PageTitle("Anagrafiche")
@Route(value = "/anagrafica", layout = MainLayout.class)
@RouteAlias(value = "/anagrafica", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AnagraficaFormView extends VerticalLayout {

    private TextField ragioneSociale;
    private NumberField numeroDipendenti;
    private ComboBox<Integer> numeroCollaboratori;
    private TextField sedeLegale;
    private TextField sediTerritoriali;
    private TextField codiceAteco;
    private TextField industryRiferimento;
    private TextField altriSistemiGestione;
    private TextArea composizioneDipartimentoHR;
    private TextField dipQualityInternalAuditing;
    private TextField industryRiferimento2;

    public AnagraficaFormView() {

        FormLayout formLayout = new FormLayout();

         ragioneSociale = new TextField("Ragione Sociale");
         numeroDipendenti = new NumberField("Numero Dipendenti");
         numeroCollaboratori = new ComboBox<>("Numero Collaboratori");
         sedeLegale = new TextField("Sede Legale");
         sediTerritoriali = new TextField("Sedi Territoriali");
         codiceAteco = new TextField("Codice Ateco");
         industryRiferimento = new TextField("Industry di Riferimento");
         altriSistemiGestione = new TextField("Altri Sistemi di Gestione");
         composizioneDipartimentoHR = new TextArea("Composizione Dipartimento HR");
         dipQualityInternalAuditing = new TextField("Dip. Quality/Internal Auditing");
         industryRiferimento2 = new TextField("Industry di Riferimento");

         numeroCollaboratori.setItems(9, 49, 249, 999);
         ragioneSociale.setRequired(true);
        numeroCollaboratori.setRequired(true);


        formLayout.add(ragioneSociale, numeroDipendenti, numeroCollaboratori, sedeLegale, sediTerritoriali, codiceAteco, industryRiferimento, altriSistemiGestione, composizioneDipartimentoHR, dipQualityInternalAuditing, industryRiferimento2);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490", 2,  FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        RichTextEditor rte = new RichTextEditor();
        rte.setMaxHeight("400px");
        rte.setValue("Inserisci qui la descrizione dell'azienda");

        formLayout.setColspan(ragioneSociale, 2);
        formLayout.setColspan(sedeLegale, 2);
        formLayout.setColspan(sediTerritoriali, 2);
        formLayout.setColspan(altriSistemiGestione, 2);

        Button submitButton = new Button(UiConstants.BUTTON_SUBMIT, event -> {
            // Handle form submission
            // get response from the server
            // fai la validazione con anagraficheDTO

            Binder<CompanyDto> binder = new BeanValidationBinder<>(CompanyDto.class);

            binder.forField(ragioneSociale).withValidator(s -> !s.isEmpty() && s.length() >= 3, "Ragione Sociale deve essere almeno di 3 caratteri")
                    .bind(CompanyDto::getRagioneSociale, CompanyDto::setRagioneSociale);

            System.out.println(rte.getValue());
            System.out.println(rte.getEmptyValue());
            System.out.println(rte.getI18n().toString());

            CompanyDto anagraficaDTO = new CompanyDto();
            anagraficaDTO.setRagioneSociale(ragioneSociale.getValue());
            anagraficaDTO.setNumeroDipendenti(numeroDipendenti.getValue() != null ? numeroDipendenti.getValue().intValue() : null);
            anagraficaDTO.setSedeLegale(sedeLegale.getValue());
            anagraficaDTO.setSediTerritoriali(sediTerritoriali.getValue());
            anagraficaDTO.setCodiceAteco(codiceAteco.getValue());
            anagraficaDTO.setIndustryRiferimento(industryRiferimento.getValue());
            anagraficaDTO.setAltriSistemiGestione(altriSistemiGestione.getValue());
            anagraficaDTO.setComposizioneDipartimentoHR(composizioneDipartimentoHR.getValue());
            anagraficaDTO.setDipQualityInternalAuditing(dipQualityInternalAuditing.getValue());
            anagraficaDTO.setIndustryRiferimento2(industryRiferimento2.getValue());

            // Validate the DTO
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CompanyDto>> violations = validator.validate(anagraficaDTO);

            if (violations.isEmpty()) {
                // Handle form submission
                Notification.show("Anagrafica inserita correttamente");
            } else {
                // Handle validation errors
                for (ConstraintViolation<CompanyDto> violation : violations) {
                    Notification.show(violation.getMessage());
                }
            }

            binder.bindInstanceFields(this);

        });

        Button cancelButton = new Button(UiConstants.BUTTON_CANCEL, event -> {
            // Handle form submission
            cleanForm();
        });

        HorizontalLayout  buttons = new HorizontalLayout();
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttons.setSpacing(true);
        buttons.add(submitButton, cancelButton);

        add(formLayout, buttons, rte);

        setPadding(true);
        setSpacing(true);
    }

    private void cleanForm() {
        ragioneSociale.clear();
        numeroDipendenti.clear();
        sedeLegale.clear();
        sediTerritoriali.clear();
        codiceAteco.clear();
        industryRiferimento.clear();
        altriSistemiGestione.clear();
        composizioneDipartimentoHR.clear();
        dipQualityInternalAuditing.clear();
        industryRiferimento2.clear();
    }

}

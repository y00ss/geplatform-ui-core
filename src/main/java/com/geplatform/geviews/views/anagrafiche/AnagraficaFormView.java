package com.geplatform.geviews.views.anagrafiche;


import com.geplatform.geviews.constants.UiConstants;
import com.geplatform.geviews.dto.Company;
import com.geplatform.geviews.components.NotificationUi;
import com.geplatform.geviews.services.AnagraficaService;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;


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


    private List<Runnable> completionListeners = new ArrayList<>();
    private NotificationUi notificationUi;
    private final AnagraficaService anagraficaService;

    private TextField  ragioneSociale = new TextField("Ragione Sociale");
    private NumberField numeroDipendenti = new NumberField("Numero Dipendenti");
    //private ComboBox<CompanySize> numeroCollaboratori = new ComboBox<>("Numero Collaboratori");
    private TextField  sedeLegale = new TextField("Sede Legale");
    private TextField  sediTerritoriali = new TextField("Sedi Territoriali");
    private TextField codiceAteco = new TextField("Codice Ateco");
    private TextField industryRiferimento = new TextField("Industry di Riferimento");
    private TextField altriSistemiGestione = new TextField("Altri Sistemi di Gestione");
    private TextArea  composizioneDipartimentoHR = new TextArea("Composizione Dipartimento HR");
    private TextField  dipQualityInternalAuditing = new TextField("Dip. Quality/Internal Auditing");
    private TextField  industryRiferimento2 = new TextField("Industry di Riferimento");

    private Binder<Company> binderCompany = new Binder<>(Company.class);

    private Button submitButton = new Button(UiConstants.BUTTON_SUBMIT);
    private Button cancelButton = new Button(UiConstants.BUTTON_CANCEL);

    private Company company;


    public AnagraficaFormView(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;

        addClassName("anagrafica-form");

        FormLayout formLayout = new FormLayout();
        notificationUi = new NotificationUi();

         ragioneSociale.setRequired(true);

        formLayout.add(ragioneSociale,
                numeroDipendenti,
                sedeLegale,
                sediTerritoriali,
                codiceAteco,
                industryRiferimento,
                altriSistemiGestione,
                composizioneDipartimentoHR,
                dipQualityInternalAuditing,
                industryRiferimento2);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490", 2,  FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Configura il binder
        binderCompany.forField(ragioneSociale)
                .asRequired("Ragione Sociale è obbligatorio")
                .bind(Company::getRagioneSociale, Company::setRagioneSociale);

        binderCompany.forField(numeroDipendenti)
                .asRequired("Numero Dipendenti è obbligatorio")
                .bind(Company::getNumeroDipendenti, Company::setNumeroDipendenti);

        binderCompany.forField(sedeLegale)
                .asRequired("Sede Legale è obbligatorio")
                .bind(Company::getSedeLegale, Company::setSedeLegale);

        binderCompany.forField(sediTerritoriali)
                .bind(Company::getSediTerritoriali, Company::setSediTerritoriali);

        binderCompany.forField(codiceAteco)
                .asRequired("Codice Ateco è obbligatorio")
                .bind(Company::getCodiceAteco, Company::setCodiceAteco);

        binderCompany.forField(industryRiferimento)
                .bind(Company::getIndustryRiferimento, Company::setIndustryRiferimento);

        binderCompany.forField(altriSistemiGestione)
                .bind(Company::getAltriSistemiGestione, Company::setAltriSistemiGestione);

        binderCompany.forField(composizioneDipartimentoHR)
                .asRequired("Composizione Dipartimento HR è obbligatorio")
                .bind(Company::getComposizioneDipartimentoHR, Company::setComposizioneDipartimentoHR);

        binderCompany.forField(dipQualityInternalAuditing)
                .bind(Company::getDipQualityInternalAuditing, Company::setDipQualityInternalAuditing);

        binderCompany.forField(industryRiferimento2)
                .bind(Company::getIndustryRiferimento2, Company::setIndustryRiferimento2);

        binderCompany.readBean(company);

        add(formLayout, createButtonsLayout());

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

    private HorizontalLayout createButtonsLayout() {

        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickShortcut(Key.ENTER);

        submitButton.addClickListener(buttonClickEvent -> {
            Notification notification = new Notification();
            try {
                if (company == null) {
                    company = new Company();
                }
                binderCompany.writeBean(company);

                anagraficaService.save(company);

                notificationUi.successNotification("Anagrafica salvata con successo!");
                notifyCompletion();
            } catch (ValidationException e) {
                notificationUi.errorNotification("Errore di validazione " ); // + e.getMessage());
            }
        });

        return new HorizontalLayout(submitButton, cancelButton);
    }

    public void addCompletionListener(Runnable listener) {
        completionListeners.add(listener);
    }

    private void notifyCompletion() {
        for (Runnable listener : completionListeners) {
            listener.run();
        }
    }

        public Company getCompany() {
            return company;
        }

}

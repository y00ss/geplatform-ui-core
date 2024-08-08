package com.geplatform.geviews.views.questionario;

import com.geplatform.geviews.constants.CompanyCluster;
import com.geplatform.geviews.constants.UiConstants;
import com.geplatform.geviews.dto.Company;
import com.geplatform.geviews.components.NotificationUi;
import com.geplatform.geviews.services.AnagraficaService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class AnagraficaFormComponent extends VerticalLayout {

    public interface AnagraficaSaveListener {
        void onAnagraficaSaved(Company company);
    }

    private List<AnagraficaSaveListener> saveListeners = new ArrayList<>();
    private NotificationUi notificationUi;
    private final AnagraficaService anagraficaService;

    private TextField ragioneSociale = new TextField("Ragione Sociale");
    private NumberField numeroDipendenti = new NumberField("Numero Dipendenti");
    private TextField sedeLegale = new TextField("Sede Legale");
    private TextField sediTerritoriali = new TextField("Sedi Territoriali");
    private TextField codiceAteco = new TextField("Codice Ateco");
    private TextField industryRiferimento = new TextField("Industry di Riferimento");
    private TextField altriSistemiGestione = new TextField("Altri Sistemi di Gestione");
    private TextArea composizioneDipartimentoHR = new TextArea("Composizione Dipartimento HR");
    private TextField dipQualityInternalAuditing = new TextField("Dip. Quality/Internal Auditing");
    private TextField industryRiferimento2 = new TextField("Industry di Riferimento");
    private ComboBox<CompanyCluster> companyCluster = new ComboBox<>("Cluster Aziendale");

    private Binder<Company> binderCompany = new Binder<>(Company.class);

    private Button submitButton = new Button(UiConstants.BUTTON_SUBMIT);
    private Button cancelButton = new Button(UiConstants.BUTTON_CANCEL);

    Company company;

    public AnagraficaFormComponent(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;

        addClassName("anagrafica-form");

        FormLayout formLayout = new FormLayout();
        notificationUi = new NotificationUi();

        ragioneSociale.setRequired(true);
        companyCluster.setItems(CompanyCluster.values());

        formLayout.add(ragioneSociale,
                numeroDipendenti,
                companyCluster,
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
                new FormLayout.ResponsiveStep("490", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Configura il binder
        binderCompany.forField(ragioneSociale)
                .asRequired("Ragione Sociale è obbligatorio")
                .bind(Company::getRagioneSociale, Company::setRagioneSociale);

        binderCompany.forField(numeroDipendenti)
                .asRequired("Numero Dipendenti è obbligatorio")
                .bind(Company::getNumeroDipendenti, Company::setNumeroDipendenti);
        binderCompany.forField(companyCluster)
                .asRequired("Cluster è obbligatorio")
                .bind(Company::getCompanyCluster, Company::setCompanyCluster);



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

                notificationUi.successNotification("Anagrafica salvata con successo   !");
                notifySaveListeners(company);

            } catch (ValidationException e) {
                notificationUi.errorNotification("Errore di validazione");
            }
        });

        return new HorizontalLayout(submitButton, cancelButton);
    }

    private void notifySaveListeners(Company company) {
        for (AnagraficaSaveListener listener : saveListeners) {
            listener.onAnagraficaSaved(company);
        }
    }

    public void addSaveListener(AnagraficaSaveListener listener) {
        saveListeners.add(listener);
    }

    public Company getCompany() {
        return company;
    }
}

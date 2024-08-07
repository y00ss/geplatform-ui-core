package com.geplatform.geviews.views.questionario;

import com.geplatform.geviews.components.NotificationUi;
import com.geplatform.geviews.constants.UiConstants;
import com.geplatform.geviews.data.pratica.Pratica;
import com.geplatform.geviews.data.pratica.PraticaQuery;
import com.geplatform.geviews.data.pratica.PraticaRepository;
import com.geplatform.geviews.data.questionario.Questionnaire;
import com.geplatform.geviews.dto.Company;
import com.geplatform.geviews.services.AnagraficaService;
import com.geplatform.geviews.services.questionnaire.QuestionnaireService;
import com.geplatform.geviews.views.MainLayout;
import com.geplatform.geviews.views.anagrafiche.AnagraficaFormView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageTitle("Questionario")
//@Menu(icon = "line-awesome/svg/question-solid.svg", order = 2)
@Route(value = "/questionario-new", layout = MainLayout.class)
@RouteAlias(value = "/questionario-new", layout = MainLayout.class)
@PermitAll
public class QuestionarioViewNew extends Composite<VerticalLayout> {

    private final QuestionnaireService questionnaireService;
    private final List<Questionnaire> questionnaires;
    private Tabs tabs;

    HorizontalLayout layoutRow = new HorizontalLayout();
    VerticalLayout layoutColumn2 = new VerticalLayout();

    VerticalLayout layoutColumn3 = new VerticalLayout();
    H3 h2 = new H3();
    H3 h3 = new H3();
    Paragraph textLarge = new Paragraph();
    TextArea textArea = new TextArea();
    Hr hr = new Hr();
    Button buttonTertiary = new Button();
    TextArea textArea2 = new TextArea();

    private Button submitButton = new Button(UiConstants.BUTTON_SUBMIT);
    private Button cancelButton = new Button("Indietro");
    private int index = 0 ;

    private final PraticaRepository praticaRepository;
    private Pratica pratica = new Pratica();

    AnagraficaFormView anagraficaFormView;

    private final AnagraficaService anagraficaService;
    public QuestionarioViewNew(QuestionnaireService questionnaireService, PraticaRepository praticaRepository, AnagraficaService anagraficaService) {
        this.praticaRepository = praticaRepository;
        this.anagraficaService = anagraficaService;

        getContent().setWidth("80%");
        getContent().getStyle().set("flex-grow", "1");

        this.questionnaireService = questionnaireService;
        this.questionnaires = this.questionnaireService.getAllQuestionnaires();
        initTabByTopic(questionnaires);
        setLayout();
        setQuestionnaireContent(questionnaires.get(index).getName());
    }

    private void setLayout() {
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("20%");


        textArea2.setLabel("Note");
        textArea2.setWidth("100%");
        h3.setWidth("max-content");
        h2.setWidth("max-content");
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(tabs);
        setLayoutCore();


        //layoutColumn3.add(buttonTertiary);
        //layoutColumn3.add(textArea2);
        layoutRow.add(layoutColumn3);

    }

    private void setLayoutCore() {
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        textLarge.setWidthFull();
        textLarge.setMinHeight("100px");
        textArea.setMinHeight("100px");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-l)");
        textArea.setLabel("Text area");
        textArea.setWidth("100%");
        textArea.setRequired(true);

        layoutColumn3.add(h2);
        layoutColumn3.add(h3);
        layoutColumn3.add(textLarge);
        layoutColumn3.add(hr);
        layoutColumn3.add(textArea);
        layoutColumn3.add(createButtonsLayout());
    }

    private Tabs initTabByTopic(List<Questionnaire> questionnaires) {
        tabs = new Tabs();
        if (questionnaires == null || questionnaires.isEmpty()) {
            return tabs;
        }

        tabs.add(new Tab("ANAGRAFICA"));

        for (Questionnaire questionnaire : questionnaires) {
            Tab tab = new Tab(questionnaire.getName());
            tabs.add(tab);
        }
        //tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab().getLabel().equals("ANAGRAFICA")) {
                layoutColumn3.removeAll();
                layoutColumn3.add(getAnagraficaFormDiv());
                return;
            }else{
                layoutColumn3.removeAll();
                setLayoutCore();
                //setLayout();
                //index = 0;
                setQuestionnaireContent(event.getSelectedTab().getLabel());
            }

        });

        return tabs;
    }

    private void setQuestionnaireContent(String label) {

        Questionnaire questionnaire = getContentDataByLabel(label);

        assert questionnaire != null;
        h2.setText(questionnaire.getName());
        h3.setText((index + 1) + questionnaire.getSection()); // puo diventare il nostro id univoco

        textLarge.setText(questionnaire.getQueries().get(index).getQuestion());

    }

    private Questionnaire getContentDataByLabel(String label) {

        // fammi un swich case per gestire label e popolare la form
        // con i dati del questionario
        return switch (label) {
            case "CULTURA E STRATEGIA" -> getQuestionnaireBySection("A");
            // Populate form with data for Questionnaire 1
            case "GOVERNANCE" -> getQuestionnaireBySection("B");
            default ->
                // Handle default case
                    null;
        };

    }

    private Questionnaire getQuestionnaireBySection(String section) {
        return questionnaires.stream().filter(q -> q.getSection().equals(section)).findFirst().orElse(null);

    }

    private HorizontalLayout createButtonsLayout() {

        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickShortcut(Key.ENTER);

        submitButton.addClickListener(buttonClickEvent -> {
            updatePratica();
            if (isLastQuestion()) {
                new NotificationUi().successNotification("Questionario completato con successo!");
            }else{
                index+=1;
                setQuestionnaireContent(tabs.getSelectedTab().getLabel());
            }
        });

        cancelButton.addClickListener(buttonClickEvent -> {
            if (index == 0) {
                return;
            }
            index-=1;
            setQuestionnaireContent(tabs.getSelectedTab().getLabel());
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton, submitButton);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        return buttonsLayout;
    }

    private void updatePratica() {
        Map<String, List<PraticaQuery>> praMap = new HashMap<>();
        PraticaQuery praticaQuery = new PraticaQuery();

        Questionnaire questionnaire = getContentDataByLabel(tabs.getSelectedTab().getLabel());
        if (pratica.getQuestionnarie() == null) {
            pratica.setQuestionnarie(praMap);
        }else {
            praMap = pratica.getQuestionnarie();
        }

        praticaQuery.setIndicatore(questionnaire.getQueries().get(index));
        praticaQuery.setResponse(textArea.getValue());

        List<PraticaQuery> praticaQueries = praMap.get(tabs.getSelectedTab().getLabel());
        if (praticaQueries == null) {
            praticaQueries = new ArrayList<>();
        }
        praticaQueries.add(praticaQuery);
        praMap.put(tabs.getSelectedTab().getLabel(), praticaQueries);
        pratica.setQuestionnarie(praMap);

        if (pratica.getCompanyId() == null) {
            Company company = anagraficaFormView.getCompany();
            pratica.setCompanyName(company.getRagioneSociale());
        }

        praticaRepository.save(pratica);
    }

    private void switchToNextTab() {
        int selectedIndex = tabs.getSelectedIndex();
        if (selectedIndex < tabs.getComponentCount() - 1) {
            tabs.setSelectedIndex(selectedIndex + 1);
        }
    }

    private Div getAnagraficaFormDiv() {
        Div container = new Div();
        H3 h3 = new H3("ANAGRAFICA");
        H3 h2 = new H3("");
         anagraficaFormView = new AnagraficaFormView(anagraficaService);
        anagraficaFormView.addCompletionListener(this::switchToNextTab);
        container.add(h3,h2, anagraficaFormView);
        return container;
    }
    private boolean isLastQuestion() {
        return index == getContentDataByLabel(tabs.getSelectedTab().getLabel()).getQueries().size() - 1;
    }
}

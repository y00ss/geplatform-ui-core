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
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
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

@PageTitle("Pratica")
@Route(value = "/questionario-new", layout = MainLayout.class)
@RouteAlias(value = "/questionario-new", layout = MainLayout.class)
@PermitAll
public class QuestionarioViewNew extends Composite<VerticalLayout> {

    private static final String LABEL_ANAGRAFICA = "ANAGRAFICA";
    private static final String LABEL_CULTURA_E_STRATEGIA = "CULTURA E STRATEGIA";
    private static final String LABEL_GOVERNANCE = "GOVERNANCE";

    private final QuestionnaireService questionnaireService;
    private  List<Questionnaire> questionnaires;
    private final NotificationUi notificationUi;
    private final PraticaRepository praticaRepository;
    private final AnagraficaService anagraficaService;

    private Tabs tabs;
    private final HorizontalLayout layoutRow = new HorizontalLayout();
    private final VerticalLayout layoutColumn2 = new VerticalLayout();
    private final VerticalLayout layoutColumn3 = new VerticalLayout();
    private final H3 h2 = new H3();
    private final H3 h3 = new H3();
    private final Paragraph textLarge = new Paragraph();
    private final TextArea textArea = new TextArea();
    private final Hr hr = new Hr();
    private final TextArea textArea2 = new TextArea();

    private final Button submitButton = new Button(UiConstants.BUTTON_SUBMIT);
    private final Button cancelButton = new Button("Indietro");
    private int index = 0;
    private Pratica pratica = new Pratica();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private  AnagraficaFormComponent anagraficaFormComponent ;

    public QuestionarioViewNew(QuestionnaireService questionnaireService, PraticaRepository praticaRepository, AnagraficaService anagraficaService, NotificationUi notificationUi) {
        this.questionnaireService = questionnaireService;
        this.praticaRepository = praticaRepository;
        this.anagraficaService = anagraficaService;
        this.notificationUi = notificationUi;

        this.anagraficaFormComponent = new AnagraficaFormComponent(anagraficaService);

        this.questionnaires = this.questionnaireService.getAllQuestionnaires();
        createButtonsLayout();
        initTabs();
        initLayout();
        tabs.setSelectedIndex(0);
        anagraficaLayout();
    //    setInitialContent();
    }
    private void onAnagraficaSaved(Company company) {

        notificationUi.successNotification("Anagrafica salvata con successo!");
        layoutColumn3.removeAll();
        layoutColumn3.add(h2, h3, textLarge, hr, textArea, buttonsLayout);

        this.questionnaires = this.questionnaireService.getQuestionnairesByCompanyCluster(company.getCompanyCluster());

        setInitialContent();
        switchToNextTab();
    }


    private void initLayout() {
        getContent().setWidth("80%");
        getContent().getStyle().set("flex-grow", "1");

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
        layoutRow.add(layoutColumn3);

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        textLarge.setWidthFull();
        textLarge.setMinHeight("100px");
        textArea.setMinHeight("100px");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-l)");
        textArea.setLabel("Text area");
        textArea.setWidth("100%");
        textArea.setRequired(true);


        layoutColumn3.add(h2, h3, textLarge, hr, textArea, buttonsLayout);
    }

    private void initTabs() {
        tabs = new Tabs();
        tabs.add(new Tab(LABEL_ANAGRAFICA));
        for (Questionnaire questionnaire : questionnaires) {
            Tab questionTab = new Tab(questionnaire.getName());
            questionTab.setEnabled(false);
            tabs.add(questionTab);
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab().getLabel().equals(LABEL_ANAGRAFICA)) {
                anagraficaLayout();
            }
            index = 0;
            setQuestionnaireContent(event.getSelectedTab().getLabel());
        });

        layoutColumn2.add(tabs);
    }

    private void anagraficaLayout() {
        H3 title = new H3("Anagrafica");
        layoutColumn3.removeAll();
        anagraficaFormComponent.addSaveListener(this::onAnagraficaSaved); // Aggiungi il listener qui
        layoutColumn3.add(title, this.anagraficaFormComponent );
    }

    private void setInitialContent() {
        if (!questionnaires.isEmpty()) {
            setQuestionnaireContent(questionnaires.get(index).getName());
        }
    }

    private void resetContent() {
        //layoutColumn3.removeAll();
        //layoutColumn3.add(h2, h3, textLarge, hr, textArea, createButtonsLayout());
    }

    private void setQuestionnaireContent(String label) {
        Questionnaire questionnaire = getContentDataByLabel(label);
        if (questionnaire != null) {
            h2.setText(questionnaire.getName());
            h3.setText((this.index + 1) + ". " + questionnaire.getSection());
            textLarge.setText(questionnaire.getQueries().get(this.index).getQuestion());
            textArea.clear();
        }
    }

    private Questionnaire getContentDataByLabel(String label) {
        return switch (label) {
            case LABEL_CULTURA_E_STRATEGIA -> getQuestionnaireBySection("A");
            case LABEL_GOVERNANCE -> getQuestionnaireBySection("B");
            default -> null;
        };
    }

    private Questionnaire getQuestionnaireBySection(String section) {
        return questionnaires.stream().filter(q -> q.getSection().equals(section)).findFirst().orElse(null);
    }

    private void createButtonsLayout() {
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickShortcut(Key.ENTER);
        submitButton.addClickListener(buttonClickEvent -> handleSubmitButtonClick());
        cancelButton.addClickListener(buttonClickEvent -> handleCancelButtonClick());

        this.buttonsLayout.add(cancelButton, submitButton);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
    }

    private void handleSubmitButtonClick() {
        updatePratica();
        if (isLastQuestion()) {
            notificationUi.successNotification("Questionario completato per la sezione " + tabs.getSelectedTab().getLabel());
            switchToNextTab();
        } else {
            index++;
            setQuestionnaireContent(tabs.getSelectedTab().getLabel());
        }
    }

    private void handleCancelButtonClick() {
        if (index > 0) {
            index--;
            setQuestionnaireContent(tabs.getSelectedTab().getLabel());
        }
    }

    private void updatePratica() {
        Map<String, List<PraticaQuery>> praMap = pratica.getQuestionnarie();
        if (praMap == null) {
            praMap = new HashMap<>();
        }

        Questionnaire questionnaire = getContentDataByLabel(tabs.getSelectedTab().getLabel());
        PraticaQuery praticaQuery = new PraticaQuery();
        praticaQuery.setIndicatore(questionnaire.getQueries().get(index));
        praticaQuery.setResponse(textArea.getValue());

        List<PraticaQuery> praticaQueries = praMap.computeIfAbsent(tabs.getSelectedTab().getLabel(), k -> new ArrayList<>());
        praticaQueries.add(praticaQuery);

        pratica.setQuestionnarie(praMap);
        praticaRepository.save(pratica);
    }

    private void switchToNextTab() {
        int selectedIndex = tabs.getSelectedIndex();
        if (selectedIndex < tabs.getComponentCount() - 1) {
            tabs.getTabAt(selectedIndex +1).setEnabled(true);
            tabs.setSelectedIndex(selectedIndex + 1);
        } else {
            pratica.setCompleted(true);
            praticaRepository.save(pratica);
            notificationUi.successNotification("Questionario completato con successo, analizziamo e faremo il report!");
        }
    }

    private boolean isLastQuestion() {
        Questionnaire questionnaire = getContentDataByLabel(tabs.getSelectedTab().getLabel());
        return questionnaire != null && index == questionnaire.getQueries().size() - 1;
    }
}

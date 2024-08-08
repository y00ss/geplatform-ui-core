package com.geplatform.geviews.views.questionario;


import com.geplatform.geviews.data.pratica.Pratica;
import com.geplatform.geviews.data.pratica.PraticaQuery;
import com.geplatform.geviews.data.pratica.PraticaRepository;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;
import java.util.Map;

@Route(value = "/questionario", layout = MainLayout.class)
@RouteAlias(value = "/questionario", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@PageTitle("Pratica")
public class QuestionarioView extends VerticalLayout {

    private final PraticaRepository praticaRepository;
    private FormLayout praticaForm;
    private TextField idField;
    private TextField companyNameField;
    private TextField sezioneField;
    private TextArea questionField;
    private List<TextArea> sourceField;
   // private TextArea otherDetailsField;  // Aggiungi altri campi necessari

    private Button deleteButton;
    private Button editButton;
    private Button analyzeButton;
    private Grid<Pratica> grid;

    public QuestionarioView(PraticaRepository praticaRepository) {
        this.praticaRepository = praticaRepository;
        initGrid();
       }

    private void initGrid(){
        //List<Company> companies = anagraficaClient.getAllAnagrafiche();
        List<Pratica>   pratiche = praticaRepository.findAll();
         grid = new Grid<>();

        grid.setItems(pratiche);
        grid.addColumn(Pratica::getId).setHeader("ID Pratica");
        grid.addColumn(Pratica::getCompanyName).setHeader("Nome Azienda");


        grid.addComponentColumn(pratica -> createStatusIcon(pratica.isCompleted()))
                .setHeader("Completato")
                .setAutoWidth(true);


        grid.addSelectionListener(selectionEvent -> {
            // Popola la form con i dettagli della pratica selezionata
            enableButtons(true);
            selectionEvent.getFirstSelectedItem().ifPresent(this::populateForm);
        });


        add(grid);

        initButtons();

    }
    private Pratica getSelectedPratica() {
        // Ottieni la pratica selezionata dalla griglia
        return praticaForm.isVisible() ? praticaRepository.findById((idField.getValue())).orElse(null) : null;
    }
    private void initButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();

        deleteButton = new Button("Cancella", VaadinIcon.TRASH.create());
        editButton = new Button("Modifica", VaadinIcon.EDIT.create());
        analyzeButton = new Button("Analizza", VaadinIcon.SEARCH.create());

        // Inizialmente i bottoni sono disabilitati
        enableButtons(false);

        deleteButton.addClickListener(event -> handleDeleteAction());
        editButton.addClickListener(event -> handleEditAction());
        analyzeButton.addClickListener(event -> handleAnalyzeAction());

        buttonsLayout.add(deleteButton, editButton, analyzeButton);
        add(buttonsLayout);
    }

    private void enableButtons(boolean enabled) {
        deleteButton.setEnabled(enabled);
        editButton.setEnabled(enabled);
        analyzeButton.setEnabled(enabled);
    }

    private void handleDeleteAction() {
        // Logica per cancellare la pratica selezionata
        Pratica selectedPratica = getSelectedPratica();
        if (selectedPratica != null) {
            praticaRepository.delete(selectedPratica);
            Notification.show("Pratica cancellata con successo");
            refreshGrid();
        }
    }


    private void refreshGrid() {
        // Ricarica i dati della griglia dopo un'azione
        grid.setItems(praticaRepository.findAll());
    }


    private void handleEditAction() {
        // Logica per modificare la pratica selezionata
        Pratica selectedPratica = getSelectedPratica();
        if (selectedPratica != null) {
            // Apri un dialog o un'altra vista per modificare la pratica
            Notification.show("Azione di modifica per la pratica " + selectedPratica.getId());
        }
    }

    private void handleAnalyzeAction() {
        // Logica per analizzare la pratica selezionata
        Pratica selectedPratica = getSelectedPratica();
        if (selectedPratica != null) {
            // Implementa l'analisi della pratica
            Notification.show("Azione di analisi per la pratica " + selectedPratica.getId());
        }
    }



    private void initForm() {

        praticaForm = new FormLayout();

        idField = new TextField("ID");
        idField.setReadOnly(true);

        companyNameField = new TextField("Nome Azienda");
        companyNameField.setReadOnly(true);



         sezioneField = new TextField("Sezione");
         questionField = new TextArea("Domanda");
        sezioneField.setReadOnly(true);
        questionField.setReadOnly(true);
        sourceField = List.of(new TextArea("Risposta"), new TextArea("Commento"));
        praticaForm.add(idField, companyNameField, sezioneField, questionField, sourceField.get(0), sourceField.get(1));
        praticaForm.setVisible(false);  // Nascondi la form finché non è selezionata una pratica

        add(praticaForm);
    }

    private void populateForm(Pratica pratica) {
        initForm();
        if (pratica != null) {
           // praticaForm.removeAll();  // Pulisci la form
            idField.setValue(String.valueOf(pratica.getId()));
            companyNameField.setValue(pratica.getCompanyName() == null ? "" : pratica.getCompanyName());
            //companyClusterField.setValue(pratica.getCompanyCluster().toString());

            Map<String,List<PraticaQuery>> stringListMap = pratica.getQuestionnarie();

            for (Map.Entry<String, List<PraticaQuery>> entry : stringListMap.entrySet()) {
                sezioneField.setValue(entry.getKey());
                for (PraticaQuery query : entry.getValue()) {
                    questionField.setValue(query.getIndicatore().getQuestion());
                    sourceField.get(0).setValue(query.getResponse() == null ? "" : query.getResponse());
                    sourceField.get(1).setValue(query.getComment() == null ? "" : query.getComment());
                }
            }
            praticaForm.setVisible(true);  // Mostra la form dei dettagli
        } else {
            praticaForm.setVisible(false);  // Nascondi la form se non c'è nessuna pratica selezionata
        }
    }



    private Icon createStatusIcon(boolean isCompleted) {
        Icon icon;
        if (isCompleted) {
            icon = VaadinIcon.CHECK.create();
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = VaadinIcon.CLOSE_SMALL.create();
            icon.getElement().getThemeList().add("badge error");
        }
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

}

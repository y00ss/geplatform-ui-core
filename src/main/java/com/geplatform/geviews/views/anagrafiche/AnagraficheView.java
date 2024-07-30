package com.geplatform.geviews.views.anagrafiche;

import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import com.vaadin.flow.component.notification.Notification;


@PageTitle("Anagrafiche")
@Route(value = "/anagrafiche", layout = MainLayout.class)
@RouteAlias(value = "/anagrafiche", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AnagraficheView extends VerticalLayout {

    public AnagraficheView() {
//        VerticalLayout layoutColumn2 = new VerticalLayout();
//        TextField textField = new TextField();
//        FormLayout formLayout2Col = new FormLayout();
//        TextField textField2 = new TextField();
//        TextField textField3 = new TextField();
//        TextField textField4 = new TextField();
//        TextField textField5 = new TextField();
//        TextField textField6 = new TextField();
//        TextField textField7 = new TextField();
//        NumberField numberField = new NumberField();
//        TextField textField8 = new TextField();
//        HorizontalLayout layoutRow = new HorizontalLayout();
//        Button buttonPrimary = new Button();
//        Button buttonSecondary = new Button();
//        getContent().setWidth("100%");
//        getContent().getStyle().set("flex-grow", "1");
//        getContent().setJustifyContentMode(JustifyContentMode.START);
//        getContent().setAlignItems(Alignment.CENTER);
//        layoutColumn2.addClassName(Gap.LARGE);
//        layoutColumn2.addClassName(Padding.XLARGE);
//        layoutColumn2.setWidth("100%");
//        layoutColumn2.setMaxWidth("800px");
//        layoutColumn2.setHeight("min-content");
//        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
//        layoutColumn2.setAlignItems(Alignment.START);
//
//        textField.setLabel("Ragione Sociale");
//        formLayout2Col.setWidth("100%");
//        textField2.setLabel("Partita Iva");
//        textField3.setLabel("Sede Legale");
//        textField4.setLabel("Sede Operativa");
//        textField4.setWidth("min-content");
//        textField5.setLabel("Codice Ateco");
//        textField5.setWidth("min-content");
//        textField6.setLabel("Industry di Riferimento");
//        textField6.setWidth("min-content");
//        textField7.setLabel("Altri Sistemi di Gestione");
//        textField7.setWidth("min-content");
//        numberField.setLabel("Numero Dipendenti");
//        numberField.setWidth("min-content");
//        textField8.setLabel("Composizione HR");
//        textField8.setWidth("min-content");
//        layoutRow.addClassName(Gap.MEDIUM);
//        layoutRow.setWidth("100%");
//        layoutRow.getStyle().set("flex-grow", "1");
//        buttonPrimary.setText("Save");
//        buttonPrimary.setWidth("min-content");
//        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        buttonSecondary.setText("Cancel");
//        buttonSecondary.setWidth("min-content");
//        getContent().add(layoutColumn2);
//
//        layoutColumn2.add(textField);
//        layoutColumn2.add(formLayout2Col);
//        formLayout2Col.add(textField2);
//        formLayout2Col.add(textField3);
//        formLayout2Col.add(textField4);
//        formLayout2Col.add(textField5);
//        formLayout2Col.add(textField6);
//        formLayout2Col.add(textField7);
//        formLayout2Col.add(numberField);
//        layoutColumn2.add(textField8);
//        layoutColumn2.add(layoutRow);
//        layoutRow.add(buttonPrimary);
//        layoutRow.add(buttonSecondary);

        // add log


        // Crea i campi della forma
        TextField firstNameField = new TextField("Nome");
        TextField lastNameField = new TextField("Cognome");

        // Crea il pulsante di salvataggio
        Button saveButton = new Button("Salva");

        // Crea il layout della forma
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstNameField, lastNameField, saveButton);

        // Aggiungi la forma al layout principale
        add(formLayout);

        // Crea il layout di conferma con il pulsante "Torna indietro"
        VerticalLayout confirmationLayout = new VerticalLayout();
        Button backButton = new Button("Torna indietro");

        // Listener per il pulsante di salvataggio
        saveButton.addClickListener(event -> {
            // Mostra un messaggio di conferma e sostituisci la forma
            Notification.show("Utente salvato", 3000, Notification.Position.MIDDLE);
            remove(formLayout);
            //confirmationLayout.add(new Notification("Utente salvato", 3000, Notification.Position.MIDDLE));
            confirmationLayout.add(backButton);
            add(confirmationLayout);
        });

        // Listener per il pulsante "Torna indietro"
        backButton.addClickListener(event -> {
            // Sostituisci il messaggio di conferma con la forma
            remove(confirmationLayout);
            add(formLayout);
        });
            }

}

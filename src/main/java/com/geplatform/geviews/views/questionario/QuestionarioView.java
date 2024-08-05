package com.geplatform.geviews.views.questionario;

import com.geplatform.geviews.data.questionario.Question;
import com.geplatform.geviews.data.questionario.DomandaRepository;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@Route(value = "/questionario", layout = MainLayout.class)
@RouteAlias(value = "/questionario", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@PageTitle("Questionario")
public class QuestionarioView extends VerticalLayout {
    private final DomandaRepository domandaRepository;
    private int currentIndex = 0;
    private List<Question> domande;
    private ProgressBar progressBar = new ProgressBar();

    public QuestionarioView(DomandaRepository domandaRepository) {
        this.domandaRepository = domandaRepository;
        this.domande = domandaRepository.findAll();
        progressBar.setMax(100);

        showQuestion();
        add(progressBar);
        updateProgress();
    }

    private void showQuestion() {
        if (currentIndex < domande.size()) {
            Question currentDomanda = domande.get(currentIndex);
            TextArea rispostaArea = new TextArea(currentDomanda.getQuestion());
            add(rispostaArea);

            Button nextButton = new Button("Next", e -> {
                currentIndex++;
                removeAll();
                showQuestion();
                updateProgress();
            });
            add(nextButton, progressBar);
        } else {
            add(progressBar);
        }
    }

    private void updateProgress() {
        double progress =  ((currentIndex * 100) / domande.size());
        progressBar.setValue(progress);

        NativeLabel progressBarLabelText = new NativeLabel("Processing Financials.xlsx");
        progressBarLabelText.setId("pblabel");
        // Associates the label with the progressbar for screen readers:
        progressBar.getElement().setAttribute("aria-labelledby", "pblabel");

        Span progressBarLabelValue = new Span("50%");
        HorizontalLayout progressBarLabel = new HorizontalLayout(progressBarLabelText, progressBarLabelValue);
        progressBarLabel.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(progressBarLabel, progressBar);

    }
}

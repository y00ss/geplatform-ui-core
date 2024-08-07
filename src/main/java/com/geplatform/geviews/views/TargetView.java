package com.geplatform.geviews.views;

import com.geplatform.geviews.services.AnagraficaService;
import com.geplatform.geviews.services.questionnaire.QuestionnaireService;
import com.geplatform.geviews.views.questionario.QuestionarioViewNew;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route("target-view")
public class TargetView extends VerticalLayout {


//    public TargetView(QuestionnaireService questionnaireService, AnagraficaService anagraficaService) {
//        this.questionnaireService = questionnaireService;
//        this.anagraficaService = anagraficaService;
//        Div container = new Div();
//        QuestionarioViewNew questionarioViewNew = new QuestionarioViewNew(this.questionnaireService, anagraficaService);
//        container.add(questionarioViewNew.getContent());
//        add(container);
//    }
}
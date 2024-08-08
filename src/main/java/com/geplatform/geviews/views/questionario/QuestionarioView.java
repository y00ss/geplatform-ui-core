package com.geplatform.geviews.views.questionario;

import com.geplatform.geviews.data.questionario.QuestionnaireRepository;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "/questionario", layout = MainLayout.class)
@RouteAlias(value = "/questionario", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@PageTitle("Pratica")
public class QuestionarioView extends VerticalLayout {
    public QuestionarioView(QuestionnaireRepository questionnaireRepository) {
    }
}

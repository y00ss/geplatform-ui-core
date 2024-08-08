package com.geplatform.geviews.views.questionario;


import com.geplatform.geviews.data.pratica.Pratica;
import com.geplatform.geviews.data.pratica.PraticaRepository;
import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@Route(value = "/questionario", layout = MainLayout.class)
@RouteAlias(value = "/questionario", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@PageTitle("Pratica")
public class QuestionarioView extends VerticalLayout {

    private final PraticaRepository praticaRepository;
    public QuestionarioView(PraticaRepository praticaRepository) {
        this.praticaRepository = praticaRepository;
        initGrid();
       }

    private void initGrid(){
        //List<Company> companies = anagraficaClient.getAllAnagrafiche();
        List<Pratica>   pratiche = praticaRepository.findAll();
        Grid<Pratica> grid = new Grid<>(Pratica.class);
        TextField filterText = new TextField();

        grid.addSelectionListener(selectionEvent -> {
            //
        });


        if (!pratiche.isEmpty()){
            grid.setItems(pratiche);
        }
        add(grid);

    }
}

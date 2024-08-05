package com.geplatform.geviews.views.anagrafiche;

import com.geplatform.geviews.clients.anagrafica.AnagraficaClient;
import com.geplatform.geviews.data.anagrafica.Anagrafica;
import com.geplatform.geviews.services.AnagraficaService;
import com.geplatform.geviews.views.MainLayout;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@PageTitle("Anagrafiche")
@Route(value = "/anagrafiche", layout = MainLayout.class)
@RouteAlias(value = "/anagrafiche", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AnagraficheView extends VerticalLayout {


    private final  AnagraficaClient anagraficaClient;

    private final AnagraficaService anagraficaService;


    @Autowired
    public AnagraficheView(AnagraficaClient anagraficaClient, AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
        addClassName("anagrafiche-search");

        this.anagraficaClient = anagraficaClient;

        initGrid();
    }

    private void initGrid(){
        //List<Company> companies = anagraficaClient.getAllAnagrafiche();
        List<Anagrafica> anagrafiche = anagraficaService.getAll();
        Grid<Anagrafica> grid = new Grid<>(Anagrafica.class);
        TextField filterText = new TextField();

        grid.addSelectionListener(selectionEvent -> {
            //
        });


        if (anagrafiche != null && !anagrafiche.isEmpty()){
            grid.setItems(anagrafiche);
        }
        add(grid);
    }

}

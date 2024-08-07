package com.geplatform.geviews.views.pratiche;

import com.geplatform.geviews.views.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Anagrafiche")
@Route(value = "/anagrafica", layout = MainLayout.class)
@RouteAlias(value = "/anagrafica", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class PraticaFormView {
}

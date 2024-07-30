package com.geplatform.geviews.views;

import com.geplatform.geviews.constants.UiConstants;
import com.geplatform.geviews.data.User;
import com.geplatform.geviews.security.AuthenticatedUser;
import com.geplatform.geviews.views.anagrafiche.AnagraficaFormView;
import com.geplatform.geviews.views.anagrafiche.AnagraficheView;
import com.geplatform.geviews.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;


    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();

    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span(UiConstants.APP_NAME);
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavMenu());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNavItem[] createNavMenuItems() {
        return new SideNavItem[] {
                getHomeNav(),
                getAnagraficheNav(),
             //   getValidazioneNav(),
              //  getDashboardNav()
        };
        //createTab("About", AboutView.class) };
    }

//    private  SideNavItem getDashboardNav() {
//        return new SideNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.USER.create());
//
//    }
//
//    private static SideNavItem getValidazioneNav() {
//        SideNavItem validazione = new SideNavItem("Validazione", DashboardView.class, LineAwesomeIcon.TEETH_SOLID.create());
//        return validazione;
//    }

    private static SideNavItem getAnagraficheNav() {

        SideNavItem anagrafiche = new SideNavItem(UiConstants.TITLE_ANAGRAFICHE, AnagraficheView.class, LineAwesomeIcon.USER.create());
        anagrafiche.addItem(new SideNavItem("Nuova", AnagraficaFormView.class, LineAwesomeIcon.PLUS_SOLID.create()));

        return anagrafiche;
    }

    private  SideNavItem getHomeNav() {
        return new SideNavItem(UiConstants.TITLE_HOME, HomeView.class, LineAwesomeIcon.HOME_SOLID.create());
    }

    private SideNav createNavMenu() {
        SideNav nav = new SideNav();
        SideNavItem[] items = createNavMenuItems();

        //todo aggiungere controllo accessi, anche se è gia gestito dal ruolo
        Arrays.stream(items)
                .forEach(nav::addItem);

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    private static Tab createTab(String text,
                                 Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}

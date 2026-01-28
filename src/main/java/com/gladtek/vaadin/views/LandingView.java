package com.gladtek.vaadin.views;
import com.gladtek.vaadin.components.AppFooter;
import com.gladtek.vaadin.components.Hero;
import com.gladtek.vaadin.components.NavBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
@Route("")
@PageTitle("Vaadin + Tailwind Landing Showcase")
public class LandingView  extends VerticalLayout{

    public LandingView() {
        setPadding(false);
        setSpacing(false);
        addClassNames("bg-slate-50");
        getElement().getStyle().set("overflow-x", "hidden");

        NavBar navbar = new NavBar();
        Hero hero = new Hero();
        AppFooter footer = new AppFooter();

        add(navbar, hero, footer);
    }
}

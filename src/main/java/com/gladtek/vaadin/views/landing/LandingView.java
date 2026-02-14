package com.gladtek.vaadin.views.landing;

import com.gladtek.vaadin.components.layout.AppFooter;
import com.gladtek.vaadin.components.layout.NavBar;
import com.gladtek.vaadin.components.layout.SkipLink;
import com.gladtek.vaadin.components.layout.ThemeSwitcher;
import com.gladtek.vaadin.components.section.*;
import com.gladtek.vaadin.services.LandingPageService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Direction;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("GladTek - Modern Enterprise Cloud Platform")
public class LandingView extends VerticalLayout {

    public LandingView(LandingPageService landingPageService) {
        setWidthFull();
        setPadding(false);
        setSpacing(false);
        addClassNames("bg-slate-50");
        getElement().getStyle().set("overflow-x", "hidden");

        SkipLink skipLink = new SkipLink();
        NavBar navbar = new NavBar();
        Hero hero = new Hero();
        Stats stats = new Stats();
        Features features = new Features(landingPageService.getFeatureService());
        Pricing pricing = new Pricing(landingPageService.getPricingService());
        Testimonials testimonials = new Testimonials(landingPageService.getTestimonialService());
        CTA cta = new CTA();
        AppFooter footer = new AppFooter();
        ThemeSwitcher themeSwitcher = new ThemeSwitcher();

        add(skipLink, navbar, hero, stats, features, pricing, testimonials, cta, footer, themeSwitcher);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (getLocale().getLanguage().equals("ar")) {
            UI.getCurrent().setDirection(Direction.RIGHT_TO_LEFT);
        } else {
            UI.getCurrent().setDirection(Direction.LEFT_TO_RIGHT);
        }
    }
}

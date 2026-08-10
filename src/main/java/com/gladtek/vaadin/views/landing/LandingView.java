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
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

@Route("")
public class LandingView extends VerticalLayout implements HasDynamicTitle {

    public LandingView(LandingPageService landingPageService) {
        setWidthFull();
        setPadding(false);
        setSpacing(false);
        addClassNames("bg-slate-50", "dark:bg-slate-950", "transition-colors");
        getElement().getStyle().set("overflow-x", "hidden");

        SkipLink skipLink = new SkipLink();
        NavBar navbar = new NavBar();
        Hero hero = new Hero();
        Stats stats = new Stats();
        Features features = new Features(landingPageService.getFeatureService());
        Pricing pricing = new Pricing(landingPageService.getPricingService());
        Testimonials testimonials = new Testimonials(landingPageService.getTestimonialService());
        CTA cta = new CTA();
        FAQ faq = new FAQ();
        AppFooter footer = new AppFooter();
        ThemeSwitcher themeSwitcher = new ThemeSwitcher();

        Main main = new Main();
        main.setWidthFull();
        main.add(hero, stats, features, pricing, testimonials, faq, cta);

        add(skipLink, navbar, main, footer, themeSwitcher);
    }

    @Override
    public String getPageTitle() {
        return getTranslation("page.title");
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

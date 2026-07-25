package com.gladtek.vaadin.components.section;

import com.vaadin.flow.component.html.*;

public class Hero extends Section {

    public Hero() {
        addClassNames("hero");
        setId("main-content");

        // Background Glows
        Div bgGlow1 = new Div();
        bgGlow1.addClassNames("hero-glow-1");
        bgGlow1.getElement().setAttribute("aria-hidden", "true");

        Div bgGlow2 = new Div();
        bgGlow2.addClassNames("hero-glow-2");
        bgGlow2.getElement().setAttribute("aria-hidden", "true");

        Div container = new Div();
        container.addClassNames("hero-container");

        Div content = new Div();
        content.addClassNames("hero-content");

        Span badge = new Span(getTranslation("hero.badge"));
        badge.addClassNames("hero-badge");

        H1 title = new H1(getTranslation("hero.title"));
        title.addClassNames("hero-title");

        Paragraph desc = new Paragraph(getTranslation("hero.description"));
        desc.addClassNames("hero-description");

        Div ctaGroup = new Div();
        ctaGroup.addClassNames("hero-cta-group");

        Anchor primaryBtn = new Anchor("https://www.gladtek.com", getTranslation("hero.primary"));
        primaryBtn.setTarget("_blank");
        primaryBtn.addClassNames("btn-primary");

        Anchor docBtn = new Anchor("#", getTranslation("hero.secondary"));
        docBtn.addClassNames("btn-secondary");

        ctaGroup.add(primaryBtn, docBtn);

        content.add(badge, title, desc, ctaGroup);

        // Interactive SaaS Dashboard Showcase Frame
        Div dashboardFrame = new Div();
        dashboardFrame.addClassNames("mt-12", "max-w-4xl", "mx-auto", "rounded-2xl", "glass-panel", "p-4", "sm:p-6", "shadow-2xl", "border", "border-slate-200", "dark:border-slate-800", "relative", "text-left");

        // Window Title & Control Bar
        Div windowBar = new Div();
        windowBar.addClassNames("flex", "items-center", "justify-between", "pb-4", "border-b", "border-slate-200", "dark:border-slate-800", "mb-6");

        Div windowDots = new Div();
        windowDots.addClassNames("flex", "gap-2");
        Div redDot = new Div(); redDot.addClassNames("w-3", "h-3", "rounded-full", "bg-rose-500");
        Div yellowDot = new Div(); yellowDot.addClassNames("w-3", "h-3", "rounded-full", "bg-amber-500");
        Div greenDot = new Div(); greenDot.addClassNames("w-3", "h-3", "rounded-full", "bg-emerald-500");
        windowDots.add(redDot, yellowDot, greenDot);

        Div navTabs = new Div();
        navTabs.addClassNames("flex", "gap-1", "bg-slate-200/60", "dark:bg-slate-900", "border", "border-slate-300/50", "dark:border-slate-800", "p-1", "rounded-lg", "text-xs");

        Div tabOverview = new Div(); tabOverview.setText("Overview"); tabOverview.setId("tab-overview");
        tabOverview.addClassNames("dashboard-tab-btn", "px-3", "py-1.5", "rounded-md", "cursor-pointer", "active-tab", "transition-colors");
        tabOverview.addClickListener(e -> getElement().executeJs("window.ThemeUtils.switchDashboardTab('overview')"));

        Div tabMetrics = new Div(); tabMetrics.setText("Metrics"); tabMetrics.setId("tab-metrics");
        tabMetrics.addClassNames("dashboard-tab-btn", "px-3", "py-1.5", "rounded-md", "cursor-pointer", "transition-colors");
        tabMetrics.addClickListener(e -> getElement().executeJs("window.ThemeUtils.switchDashboardTab('metrics')"));

        Div tabSecurity = new Div(); tabSecurity.setText("Security"); tabSecurity.setId("tab-security");
        tabSecurity.addClassNames("dashboard-tab-btn", "px-3", "py-1.5", "rounded-md", "cursor-pointer", "transition-colors");
        tabSecurity.addClickListener(e -> getElement().executeJs("window.ThemeUtils.switchDashboardTab('security')"));

        navTabs.add(tabOverview, tabMetrics, tabSecurity);
        windowBar.add(windowDots, navTabs);

        // Panel 1: Overview
        Div panelOverview = new Div(); panelOverview.setId("panel-overview"); panelOverview.addClassNames("dashboard-panel", "grid", "grid-cols-1", "sm:grid-cols-3", "gap-4");
        panelOverview.add(
            createMetricCard("Global Requests", "1.24M", "+18.2%", "bg-emerald-100 text-emerald-700 dark:bg-emerald-950 dark:text-emerald-400"),
            createMetricCard("API Latency", "14ms", "-4ms", "bg-sky-100 text-sky-700 dark:bg-sky-950 dark:text-sky-400"),
            createMetricCard("System Uptime", "99.99%", "Optimal", "bg-indigo-100 text-indigo-700 dark:bg-indigo-950 dark:text-indigo-400")
        );

        // Panel 2: Metrics
        Div panelMetrics = new Div(); panelMetrics.setId("panel-metrics"); panelMetrics.addClassNames("dashboard-panel", "hidden", "grid", "grid-cols-1", "sm:grid-cols-2", "gap-4");
        panelMetrics.add(
            createMetricCard("Peak Throughput", "84,200 req/s", "Record", "bg-amber-100 text-amber-700 dark:bg-amber-950 dark:text-amber-400"),
            createMetricCard("Edge Cache Hit", "99.4%", "+0.6%", "bg-emerald-100 text-emerald-700 dark:bg-emerald-950 dark:text-emerald-400")
        );

        // Panel 3: Security
        Div panelSecurity = new Div(); panelSecurity.setId("panel-security"); panelSecurity.addClassNames("dashboard-panel", "hidden", "grid", "grid-cols-1", "sm:grid-cols-2", "gap-4");
        panelSecurity.add(
            createMetricCard("Zero Trust Policy", "Enforced", "100%", "bg-emerald-100 text-emerald-700 dark:bg-emerald-950 dark:text-emerald-400"),
            createMetricCard("Threats Blocked", "0 Incidents", "Clean", "bg-indigo-100 text-indigo-700 dark:bg-indigo-950 dark:text-indigo-400")
        );

        dashboardFrame.add(windowBar, panelOverview, panelMetrics, panelSecurity);

        content.add(dashboardFrame);
        container.add(content);
        add(bgGlow1, bgGlow2, container);
    }

    private Div createMetricCard(String label, String value, String badgeText, String badgeStyle) {
        Div card = new Div();
        card.addClassNames("p-4", "rounded-xl", "bg-white/80", "dark:bg-slate-900/80", "border", "border-slate-200/60", "dark:border-slate-800", "shadow-sm");

        Div topRow = new Div();
        topRow.addClassNames("flex", "justify-between", "items-center", "mb-2");

        Span labelSpan = new Span(label);
        labelSpan.addClassNames("text-xs", "font-medium", "text-slate-500", "dark:text-slate-400");

        Span badgeSpan = new Span(badgeText);
        badgeSpan.addClassNames("text-[10px]", "font-bold", "px-2", "py-0.5", "rounded-full", badgeStyle);

        topRow.add(labelSpan, badgeSpan);

        Div valueDiv = new Div();
        valueDiv.setText(value);
        valueDiv.addClassNames("text-2xl", "font-extrabold", "text-slate-900", "dark:text-slate-100");

        card.add(topRow, valueDiv);
        return card;
    }
}

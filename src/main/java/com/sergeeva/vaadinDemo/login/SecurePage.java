package com.sergeeva.vaadinDemo.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SecurePage extends VerticalLayout implements View {

    public final static String NAME = "Secure";
    private Label secure;
    private Label currentUser;
    private Button otherSecure;
    private Button logout;

    public SecurePage() {
        otherSecure = new Button("OtherSecure");
        otherSecure.addClickListener(event->{
            Page.getCurrent().setUriFragment("!" + OtherSecurePage.NAME);
        });

        logout = new Button("LogOut");
        logout.addClickListener(event->{
            getUI().getNavigator().removeView(SecurePage.NAME);
            getUI().getNavigator().removeView(OtherSecurePage.NAME);
            VaadinSession.getCurrent().setAttribute("user", null);
            Page.getCurrent().setUriFragment("");
        });

        secure = new Label("secure");
        currentUser = new Label("Current User");
        addComponents(secure, currentUser, otherSecure, logout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        currentUser.setCaption("Current User: "
                + VaadinSession.getCurrent().getAttribute("user").toString());
    }
}

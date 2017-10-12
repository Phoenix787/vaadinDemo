package com.sergeeva.vaadinDemo.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class OtherSecurePage extends VerticalLayout implements View {
    public final static String NAME = "OtherSecure";
    private Label otherSecure;
    private Button mainsecure;

    public OtherSecurePage() {
        mainsecure = new Button("Main Secure Area");
        mainsecure.addClickListener(event->{
            Page.getCurrent().setUriFragment("!" + SecurePage.NAME);
        });
        otherSecure = new Label("Other Secure Page...");
        addComponents(otherSecure, mainsecure);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

package com.sergeeva.vaadinDemo.UI;

import com.sergeeva.vaadinDemo.auth.Authentication;
import com.sergeeva.vaadinDemo.login.LoginPage;
import com.sergeeva.vaadinDemo.login.OtherSecurePage;
import com.sergeeva.vaadinDemo.login.SecurePage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SpringUI
@Theme("valo")
public class VaadinLoginUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = VaadinLoginUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public static Authentication AUTH;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        AUTH = new Authentication();
        new Navigator(this, this);
        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().setErrorView(LoginPage.class);

        Page.getCurrent().addPopStateListener(event->{
            router(event.getUri());
        });
        router("");
    }

    private void router(String uri) {
        Notification.show(uri);
        if (getSession().getAttribute("user") != null) {
            getNavigator().addView(SecurePage.NAME, SecurePage.class);
            getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
            if (uri.equals("!OtherSecure")) {
                getNavigator().navigateTo(OtherSecurePage.NAME);
            }else {
                getNavigator().navigateTo(SecurePage.NAME);
            }
        }else {
            getNavigator().navigateTo(LoginPage.NAME);
        }
    }
}

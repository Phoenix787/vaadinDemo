package com.sergeeva.vaadinDemo.login;

import com.sergeeva.vaadinDemo.UI.VaadinLoginUI;
import com.sergeeva.vaadinDemo.auth.Authentication;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";

    public LoginPage() {
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        addComponent(panel);

        FormLayout content = new FormLayout();
        TextField userName = new TextField("Username");
        Binder<Authentication> binder = new Binder<>();
        binder.setBean(VaadinLoginUI.AUTH);
        binder.forField(userName)
                .withValidator(new StringLengthValidator("Too short", 5, 256))
                .bind(Authentication::getUserName, Authentication::setUserName);
        content.addComponent(userName);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);

        Button send = new Button("Enter");
        send.addClickListener(event->{
            if (VaadinLoginUI.AUTH.authenticate(userName.getValue(), password.getValue())) {
                VaadinSession.getCurrent().setAttribute("user", userName.getValue());
                getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
                getUI().getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
                Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
            }else{
                Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
            }
        });
        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

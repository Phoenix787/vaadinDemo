package com.sergeeva.vaadinDemo.auth;

import lombok.Data;

public class Authentication {
    private String userName;
    private String password;

    public Authentication() {
        setUserName("myUser");
        setPassword("mypass");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean authenticate(String userName, String password) {
        if (userName.equals(getUserName()) && password.equals(getPassword())) {
            return true;
        }
        return false;
    }
}

package com.website;

import org.teavm.flavour.templates.BindTemplate;

@BindTemplate("templates/hello.html")
public class HelloView {
    private String userName = "";

    public HelloView(String userName) {
        setUserName(userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

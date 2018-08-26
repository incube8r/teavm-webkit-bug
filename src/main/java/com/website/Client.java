package com.website;

import org.teavm.flavour.templates.BindTemplate;
import org.teavm.flavour.widgets.ApplicationTemplate;
import org.teavm.flavour.widgets.RouteBinder;

@BindTemplate("templates/master.html")
public class Client extends ApplicationTemplate implements HelloRoute {
    public static void main(String[] args) {
        Client client = new Client();
        new RouteBinder()
                .withDefault(HelloRoute.class, r -> r.index())
                .add(client)
                .update();

        client.bind("application-content");
    }

    @Override
    public void index() {
        setView(new IndexView());
    }

    @Override
    public void hello(String name) {
        setView(new HelloView(name));
    }

    @Override
    public void goodbye() {
    }
}
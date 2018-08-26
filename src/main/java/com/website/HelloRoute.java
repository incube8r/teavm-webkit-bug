package com.website;

import org.teavm.flavour.routing.Path;
import org.teavm.flavour.routing.PathParameter;
import org.teavm.flavour.routing.PathSet;
import org.teavm.flavour.routing.Route;

@PathSet
public interface HelloRoute extends Route {
    @Path("/")
    void index();

    @Path("/hello/{userName}")
    void hello(@PathParameter("userName") String name);

    @Path("/goodbye")
    void goodbye();
}
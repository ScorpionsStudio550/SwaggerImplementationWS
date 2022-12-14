package com.example.demo.app;

import com.example.demo.Main;
import com.example.demo.providers.GsonProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;

public class Application extends ResourceConfig {
    public Application(String myPackage, String host, String port) {
        super();

        String myPackages = String.format("%s, io.swagger.resources", myPackage);
        packages(myPackage);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setHost(String.format("%s:%s", host, port));
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage(myPackages);
        beanConfig.setScan(true);

        registerClasses(ApiListingResource.class);
        registerClasses(SwaggerSerializers.class);
        registerClasses(GsonProvider.class);

        register(new InstrumentedResourceMethodApplicationListener(Main.METRIC_REGISTRY));
    }
}
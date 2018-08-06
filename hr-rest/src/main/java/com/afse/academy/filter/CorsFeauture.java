package com.afse.academy.filter;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFeauture implements Feature {
    @Override
    public boolean configure(FeatureContext featureContext) {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        featureContext.register(corsFilter);
        return true;
    }
}
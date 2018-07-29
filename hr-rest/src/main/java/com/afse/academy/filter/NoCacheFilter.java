package com.afse.academy.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
public class NoCacheFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res)
    {
        if (req.getMethod().equals("GET")) {
            CacheControl cc = new CacheControl();
            cc.setNoStore(true);
            cc.setNoCache(true);
            res.getHeaders().add(HttpHeaders.CACHE_CONTROL, cc);
        }
    }
}

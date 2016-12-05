/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.security;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ianarbuckle
 */
@Provider
public class BasicAuthContainerFilter implements ContainerRequestFilter {
    
    //Generated key using random UUID
    private static final String API_KEY = "521197c4-bb29-11e6-a4a6-cec0c932ce01";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> apiKeys = headers.get("API_KEY");
        
        
        if(apiKeys == null || apiKeys.isEmpty() || apiKeys.get(0).equals(API_KEY)) {
            requestContext
                    .abortWith(Response
                                .status(Response.Status.UNAUTHORIZED)
                    .entity("Please provide a valid API key")
                    .build());
        }
    }
   
   
}

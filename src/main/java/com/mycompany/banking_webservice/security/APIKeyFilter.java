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
public class APIKeyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> apiKeys = headers.get("API_KEY");
        
        
        if(apiKeys == null || apiKeys.isEmpty() || apiKeys.get(0).equals("VALID_KEY")) {
            requestContext
                    .abortWith(Response
                                .status(Response.Status.UNAUTHORIZED)
                    .entity("Please provide a valid API key")
                    .build());
        }
    }
   
}

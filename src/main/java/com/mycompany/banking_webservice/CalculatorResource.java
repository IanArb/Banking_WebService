/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Conor
 */
@Path("/calculate")
public class CalculatorResource {
    
    //Get Method to add numbers
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayment(@Context UriInfo info){
        //this could also be done by using @QueryParam
        Gson gson = new Gson();
        
        Integer num1 = Integer.parseInt(info.getQueryParameters().getFirst("num1"));
	Integer num2 = Integer.parseInt(info.getQueryParameters().getFirst("num2"));
        Integer ans  = num1 + num2;
        //MortgageInfo mortgageInfo = new MortgageInfo(Double.parseDouble(principle), Double.parseDouble(interest), Integer.parseInt(term));

       return Response.status(200).entity(gson.toJson(ans)).build();
    }
    
    //post method to subtract ....taking json as the imput
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(String entity){
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
        Gson gson = new Gson();
        
        Integer num1 = obj.get("num1").getAsInt();
	Integer num2 = obj.get("num2").getAsInt();
        Integer ans  = num1 - num2;

       return Response.status(200).entity(gson.toJson(ans)).build();
    }
    
    //post method to subtract ....taking form data
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Object postMethod(@FormParam("num1") int num1, @FormParam("num2") int num2) {
        int ans = num1 - num2;
        Gson gson = new Gson();
        
        return Response.status(201).entity(gson.toJson(ans)).build();

    }
    
    //delete method to divide ..using pathparams ....unconvential use for delete
    @DELETE
    @Path("/{num1}/{num2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("num1") int num1, @PathParam("num2") int num2){
        int ans = num1 / num2;
        Gson gson = new Gson();
        
        return Response.status(200).entity(gson.toJson(ans)).build();
    }
    
    //put method to multiply usig query params
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("num1") int num1, @QueryParam("num2") int num2){
       int ans = num1 * num2;
        Gson gson = new Gson();
        
        return Response.status(201).entity(gson.toJson(ans)).build();
    }

}


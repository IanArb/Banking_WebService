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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Conor
 */

@Path("/customers")
public class Customers {
    static CustomerService users = new CustomerService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@DefaultValue("-1") @QueryParam("cust_id") int id){
       return Response.status(Response.Status.OK).entity(users.getUsers(id)).build();
    }
    
    @DELETE
    @Path("/{cust_id}")
    @Produces(MediaType.TEXT_HTML)
    public Response deleteUser(@PathParam("cust_id") int id){
       users.deleteUser(id);      
       return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String entity) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();

        return Response.status(Response.Status.CREATED).entity(users.addUser(name,address,email,phone)).build();
    }    
    
    @PUT
    @Path("/{cust_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String entity, @PathParam("cust_id") int id) {   
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();
        
        return Response.status(Response.Status.CREATED).entity(users.updateUser(id,name,address,email,phone)).build();
    }
    
}

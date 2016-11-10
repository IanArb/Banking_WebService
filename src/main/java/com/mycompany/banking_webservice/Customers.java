/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

@Path("/customers")
public class Customers {
    static CustomerService users = new CustomerService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@DefaultValue("-1") @QueryParam("cust_id") int id){
//        String name = info.getQueryParameters().getFirst("name");
       return Response.status(200).entity(users.getUsers(id)).build();
    }
    
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.TEXT_HTML)
//    public Response deleteUser(@PathParam("id") int id){
//       
//       Person removed = users.deleteUser(id);
//       String message = "<h1>Your have removed <u>"+removed.getName()+"</u> from the system.</h1>"
//                        + "<h2> In a production system DELETE would return 204: No Content "
//                        + "but I am displaying the message for demonstration purposes.</h2>";
//        
//       return Response.status(200).entity(message).build();
//    }
//    
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {        
//        return Response.status(201).entity(users.addUser(name, age)).build();
//    }
//    
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateUser(@FormParam("name") String name, @FormParam("age") int age, @PathParam("id") int id) {   
//            
//        return Response.status(201).entity(users.updateUser(name, age, id)).build();
//    }
    
}

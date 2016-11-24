/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.services.CustomerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.banking_webservice.models.Customer;
import java.util.List;
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
public class CustomersResource {
    CustomerService users = new CustomerService();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> getCustomer(@DefaultValue("-1") @QueryParam("cust_id") int id){
        return users.getUsers(id);
    }
    
    @DELETE
    @Path("/{cust_id}")
    public Response deleteCustomer(@PathParam("cust_id") int id){
       users.deleteUser(id);      
       return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer addCustomer(String entity) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
        
        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();

        return users.addUser(name,address,email,phone);
        
    }    
    
    @PUT
    @Path("/{cust_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer updateCustomer(String entity, @PathParam("cust_id") int id) {   
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);      
      
        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();
        
        return users.updateUser(id,name,address,email,phone);
    }    
}
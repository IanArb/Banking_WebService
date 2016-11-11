/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static com.mycompany.banking_webservice.Customers.users;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/accounts")
public class Accounts {
    static AccountsService accounts = new AccountsService();
   
    @GET
    @Path("/balance/{cust_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("cust_id") int id, @DefaultValue("-1") @QueryParam("account") int account_no){
       
        if(account_no > -1){
           return Response.status(Response.Status.OK).entity(accounts.getBalance(id, account_no)).build();
        }else{
           return Response.status(Response.Status.OK).entity(accounts.getBalance(id)).build();

        }
    }
    
    @DELETE
    @Path("/{cust_id}/{account_no}")
    public Response deleteUser(@PathParam("cust_id") int id, @PathParam("account_no") int account_no){
       accounts.deleteAccount(id, account_no);      
       return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String entity) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        int cust_id = obj.get("cust_id").getAsInt();
        int sort_code = obj.get("sort_code").getAsInt();

        return Response.status(Response.Status.CREATED).entity(accounts.addAccount(cust_id, sort_code)).build();
    }
          
    @POST
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transa(String entity, @PathParam("type") String type) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
      
        int cust_id = obj.get("cust_id").getAsInt();
        int account_no = obj.get("account_no").getAsInt();
        int amount = obj.get("amount").getAsInt();
        
        if(type.equalsIgnoreCase("withdrawal")){
            return Response.status(Response.Status.OK).entity(accounts.withdrawal(cust_id, account_no, amount)).build();
        }else if(type.equalsIgnoreCase("lodgement")){
            return Response.status(Response.Status.OK).entity(accounts.lodgement(cust_id, account_no, amount)).build();
        }else if(type.equalsIgnoreCase("transfer")){
            int cust_to = obj.get("cust_id_to").getAsInt();
            int account_to = obj.get("account_no_to").getAsInt();
            return Response.status(Response.Status.OK).entity(accounts.transfer(cust_to, account_to, cust_id, account_no, amount)).build();
        }else {
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }  
}

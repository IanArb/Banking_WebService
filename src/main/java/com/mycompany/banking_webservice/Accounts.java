/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
    @Produces(MediaType.TEXT_HTML)
    public Response deleteUser(@PathParam("cust_id") int id, @PathParam("account_no") int account_no){
       accounts.deleteAccount(id, account_no);      
       return Response.status(Response.Status.NO_CONTENT).build();
    }
}

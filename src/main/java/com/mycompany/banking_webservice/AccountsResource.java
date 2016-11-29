/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.services.AccountsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.banking_webservice.models.Account;
import com.mycompany.banking_webservice.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Conor
 */
@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class AccountsResource {
    AccountsService accounts = new AccountsService();
   
    @GET
    @Path("/balance/{cust_id}")
    public List<Account> getBalance(@PathParam("cust_id") int id){
        return accounts.getBalances(id);
    }
    
    @GET
    @Path("/balance/{cust_id}/{acc_no}")
    public Account getBalance(@PathParam("cust_id") int id, @PathParam("acc_no") int acc_no){
        return accounts.getBalance(acc_no);
    }  
//    
//    @DELETE
//    @Path("/{cust_id}/{account_no}")
//    public List<Account> getBalance(@PathParam("cust_id") int id, @DefaultValue("-1") @QueryParam("account") int account_no) {
//        return accounts.getBalance(id, account_no);
//    }
    
//    public Response deleteAccount(@PathParam("cust_id") int id, @PathParam("account_no") int account_no){
//       accounts.deleteAccount(id, account_no);      
//       return Response.status(Response.Status.NO_CONTENT).build();
//    }
    
    @DELETE
    @Path("/{account_no}")
    public void deleteAccount(@PathParam("account_no") int id) {
        accounts.deleteAccount(id);
    }
    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Account addAccount(String entity) {    
//        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
//
//        int cust_id = obj.get("cust_id").getAsInt();
//        int sort_code = obj.get("sort_code").getAsInt();
//
//        return accounts.addAccount(cust_id, sort_code);   
//    }
    
    @POST
    public Account addAccount(Account account) {
        return accounts.addAccount(account);
    }
  
          
    @POST
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Transaction> transaction(String entity, @PathParam("type") String type) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
        int account_no = obj.get("account_no").getAsInt();
        int amount = obj.get("amount").getAsInt();
        
        List<Transaction> response = new ArrayList<>();
        if(type.equalsIgnoreCase("withdrawal")){
            response.add(accounts.withdrawal(account_no, amount));
        }else if(type.equalsIgnoreCase("lodgement")){
            response.add(accounts.lodgement(account_no, amount));
        }else if(type.equalsIgnoreCase("transfer")){
            int account_to = obj.get("account_no_to").getAsInt();
            response = accounts.transfer(account_to, account_no, amount);
        }
        
        return response;
        
    }      

}

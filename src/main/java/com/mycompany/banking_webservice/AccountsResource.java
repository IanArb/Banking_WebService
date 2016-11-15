/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.services.AccountsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static com.mycompany.banking_webservice.CustomersResource.users;
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
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Conor
 */
@Path("/accounts")
public class AccountsResource {
    static AccountsService accounts = new AccountsService();
   
    @GET
    @Path("/balance/{cust_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBalance(@PathParam("cust_id") int id, @DefaultValue("-1") @QueryParam("account") int account_no,@DefaultValue("json") @QueryParam("type") String type){
       String response;
        if(account_no > -1){
           response = accounts.getBalance(id, account_no);
        }else{
           response = accounts.getBalance(id);
        }
        
        if(type.equalsIgnoreCase("xml")){
            response = jsonToXml(response,"account");
        }
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .type((type.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
                .build();
    }
    
    @DELETE
    @Path("/{cust_id}/{account_no}")
    public Response deleteAccount(@PathParam("cust_id") int id, @PathParam("account_no") int account_no){
       accounts.deleteAccount(id, account_no);      
       return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addAccount(String entity, @DefaultValue("json") @QueryParam("type") String type) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        int cust_id = obj.get("cust_id").getAsInt();
        int sort_code = obj.get("sort_code").getAsInt();

        String response = accounts.addAccount(cust_id, sort_code);
        
        if(type.equalsIgnoreCase("xml")){
            response = jsonToXml(response,"account");
        }
        
        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .type((type.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
                .build();
    }
  
          
    @POST
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response transaction(String entity, @PathParam("type") String type, @DefaultValue("json") @QueryParam("type") String returnType) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);
      
        int cust_id = obj.get("cust_id").getAsInt();
        int account_no = obj.get("account_no").getAsInt();
        int amount = obj.get("amount").getAsInt();
        String response = "";
        String root = "";
        
        if(type.equalsIgnoreCase("withdrawal")){
            response = accounts.withdrawal(cust_id, account_no, amount);
            root = "withdrawal";
        }else if(type.equalsIgnoreCase("lodgement")){
            response = accounts.lodgement(cust_id, account_no, amount);
            root = "lodgement";
        }else if(type.equalsIgnoreCase("transfer")){
            int cust_to = obj.get("cust_id_to").getAsInt();
            int account_to = obj.get("account_no_to").getAsInt();
            response = accounts.transfer(cust_to, account_to, cust_id, account_no, amount);
            root = "transfer";
        }
           
        if(returnType.equalsIgnoreCase("xml") && response.length() > 0 ){
            response = jsonToXml(response,root);
        }
        
        return Response
                .status((response.equalsIgnoreCase(""))? Response.Status.BAD_REQUEST : Response.Status.CREATED)
                .entity(response)
                .type((returnType.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
                .build();
        
    }  
    
    
    private String jsonToXml(String json, String rootElem){
        System.out.println(json);
      JSONObject obj;
      String xml = "";
        if(json.charAt(0) != '{'){
            json = "{"+rootElem+":"+json+"}";
        }
      
        obj = new JSONObject(json);
        xml = XML.toString(obj);
        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                    + "<"+rootElem+"s>"
                    +xml
                    +"</"+rootElem+"s>";
        return xml;
    }
    
}

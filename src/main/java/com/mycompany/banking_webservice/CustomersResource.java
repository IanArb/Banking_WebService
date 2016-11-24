/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.services.CustomerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Conor
 */

@Path("/customers")
public class CustomersResource {
    static CustomerService users = new CustomerService();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCustomer(@DefaultValue("-1") @QueryParam("cust_id") int id, @DefaultValue("json") @QueryParam("type") String type){
        String response;
        try {
            response = users.getUsers(id);
     
            if(type.equalsIgnoreCase("xml")){
                response = jsonToXml(response,"customer");
            }
            
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CustomersResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Error Unable to retrieve Customer";
        }
        
        
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .type((type.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
                .build();
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
    public Response addCustomer(String entity, @DefaultValue("json") @QueryParam("type") String type) {    
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();

        String response = users.addUser(name,address,email,phone);
        
        if(type.equalsIgnoreCase("xml")){
            response = jsonToXml(response,"customer");
        }
        
        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .type((type.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
                .build();
    }    
    
    @PUT
    @Path("/{cust_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomer(String entity, @PathParam("cust_id") int id, @DefaultValue("json") @QueryParam("type") String type) {   
        JsonObject obj = new Gson().fromJson(entity, JsonObject.class);

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String phone = obj.get("phone").getAsString();
                
        String response = users.updateUser(id,name,address,email,phone);
        
        if(type.equalsIgnoreCase("xml")){
            response = jsonToXml(response,"customer");
        }
        
        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .type((type.equalsIgnoreCase("xml"))? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON)
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

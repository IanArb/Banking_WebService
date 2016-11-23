/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.database.DatabaseManager;
import com.mycompany.banking_webservice.models.Person;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ianarbuckle
 */
@Path("/data")
public class TestResource {
    
    DatabaseManager testService = new DatabaseManager();
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> getAllCustomers() throws SQLException, NamingException {
       return testService.getAllCustomers();
    }
    
    @GET
    @Path("/{cust_id}")
    public Person getCustomer(@PathParam("cust_id") int id) throws SQLException, NamingException {
        return testService.getCustomer(id);
    }
}

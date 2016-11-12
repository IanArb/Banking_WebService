/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.models.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ian Arbuckle
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestDatabaseResource {
    
    //Initialising our connection to the db
    protected Connection getConnection() throws SQLException, NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc/DSTix");
        
        return dataSource.getConnection();
    }
    
    //Method to initialise our POJO 
    public Person getFromResultSet(ResultSet result) throws SQLException {
        Person person = new Person();
        
        int getId = result.getInt("_id");
        String getName = result.getString("name");
        String getAddress = result.getString("address");
        String getEmail = result.getString("email");
        String getPhone = result.getString("phone");
         
        person.setCust_id(getId);
        person.setName(getName);
        person.setAddress(getAddress);
        person.setEmail(getEmail);
        person.setPhone(getPhone);
        
        return person;
    }
    
    // A test method to pull out the Customer data (If you see an empty list, populate the table with data in MySQL)
    @GET
    public List getList() throws SQLException, NamingException {
        List person = new ArrayList<>();
        Connection db = getConnection();
        try {
            final String selectQuery = "SELECT * FROM Customer";
            PreparedStatement sqlStatement = db.prepareStatement(selectQuery);
            ResultSet result = sqlStatement.executeQuery();
            
            while(result.next()) {
                Person cust = getFromResultSet(result);
                person.add(cust);
            }
            return person;
        } finally {
            db.close();
        }
    }
}

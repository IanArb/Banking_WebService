/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.models.Person;
import com.mycompany.banking_webservice.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.mycompany.banking_webservice.services.DatabaseTestService;

/**
 *
 * @author Ian Arbuckle
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestDatabaseResource extends DatabaseManager {
    
    DatabaseTestService testService = new DatabaseTestService();
    
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
                Person cust = testService.getFromResultSet(result);
                person.add(cust);
            }
            return person;
        } finally {
            db.close();
        }
    }

    @Override
    protected Object getAllResults(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement getListQuery(Connection db, Object t) throws SQLException {
        String query = "SELECT * FROM Customers";
        return db.prepareStatement(query);
    }

    @Override
    protected PreparedStatement getInsertQuery(Connection db) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    protected PreparedStatement getSingleQuery(Connection db, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected PreparedStatement getUpdateQuery(Connection db, Object t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    protected PreparedStatement getDeleteQuery(Connection db, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

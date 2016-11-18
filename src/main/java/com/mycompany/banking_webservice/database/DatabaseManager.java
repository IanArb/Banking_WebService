/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ianarbuckle
 */
public abstract class DatabaseManager<T> {
    
    protected abstract T getAllResults(ResultSet rs) throws SQLException;
    
    protected abstract PreparedStatement getListQuery(Connection db, T t) throws SQLException;
    
    protected abstract PreparedStatement getInsertQuery(Connection db) throws SQLException;
    
    protected abstract PreparedStatement getSingleQuery(Connection db, int id) throws SQLException;
    
    protected abstract PreparedStatement getUpdateQuery(Connection db, T t, int id) throws SQLException;
    
    protected abstract PreparedStatement getDeleteQuery(Connection db, int id) throws SQLException;
    
    //Initialising our connection to the db
    protected Connection getConnection() throws SQLException, NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc/DSTix");
        
        return dataSource.getConnection();
    }
    
}

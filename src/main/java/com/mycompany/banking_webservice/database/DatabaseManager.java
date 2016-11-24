/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.database;

import com.mycompany.banking_webservice.models.Account;
import com.mycompany.banking_webservice.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ianarbuckle
 */
public class DatabaseManager {

    //Initialising our connection to the db
    protected Connection getConnection() throws SQLException, NamingException {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("jdbc/DSTix");

        return dataSource.getConnection();
    }
    
    public List<Customer> getAllCustomers() throws SQLException, NamingException {
        List customers = new ArrayList<>();
        Connection db = getConnection();
        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM Customer");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer person = getCustomers(rs);
                customers.add(person);
            }
            return customers;
        } finally {
            db.close();
        }
    }

    public Customer getCustomer(int id) throws SQLException, NamingException {
        Customer person = new Customer();
        Connection db = getConnection();
        try {
            PreparedStatement st = db.prepareStatement("SELECT * FROM Customer WHERE _id =" + id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                person = getCustomers(rs);
                person.getCust_id();
            }
            return person;
        } finally {
            db.close();
        }
    }

    public Customer getCustomers(ResultSet result) throws SQLException {
        Customer person = new Customer();

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

    public Account getAccounts(ResultSet result) throws SQLException {
        Account account = new Account();

        int getId = result.getInt("account_no");
        int getCid = result.getInt("cid");
        int getBalance = result.getInt("balance");
        int getSortcode = result.getInt("sort_code");

        account.setAccount_no(getId);
        account.setBalance(getBalance);
        account.setCid(getCid);
        account.setSort_code(getSortcode);

        return account;
    }

}

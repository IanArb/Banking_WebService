/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.models.Account;
import com.mycompany.banking_webservice.models.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ianarbuckle
 */
public class DatabaseTestService {
    
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
    
    public Account getAccountResultSet(ResultSet result) throws SQLException {
        Account account = new Account();
        
        int getId = result.getInt("acc_id");
        int getCid = result.getInt("cid");
        int getBalance = result.getInt("balance");
        int getSortCode = result.getInt("sort_code");
        
        account.setAccount_no(getId);
        account.setCid(getCid);
        account.setBalance(getBalance);
        account.setSort_code(getSortCode);
        
        return account;
    }
   
    
}

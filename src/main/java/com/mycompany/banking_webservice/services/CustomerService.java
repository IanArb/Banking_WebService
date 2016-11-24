/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.database.DatabaseManager;
import com.mycompany.banking_webservice.models.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Conor
 */
public class CustomerService {
    static ArrayList<Customer> people = new ArrayList<>();
    DatabaseManager db;
    
    public CustomerService(){
        db = new DatabaseManager();
        if(people.isEmpty()){    
            try {
                people = (ArrayList<Customer>) db.getAllCustomers();
            } catch (SQLException | NamingException ex) {}
        }
    }
    
    public List<Customer> getUsers(int id){
        DatabaseManager db = new DatabaseManager();
        List<Customer> customers = new ArrayList<>();
        if(id > -1){
            try {
                customers = (List<Customer>) db.getCustomer(id);
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                customers = (List<Customer>) db.getAllCustomers();
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return customers;
    }
  
    public void deleteUser(int id){
       for(Customer x: people){
            if(x.getCust_id() == id){
              people.remove(x);
              break;
            }
          }
    }
    
    public String addUser(String name, String address, String email, String phone){
        Gson gson = new GsonBuilder().create();
        // Temp code to add cust id.
        int high = 0;
        for(Customer x: people){
            if(x.getCust_id()>high){
                high = x.getCust_id();
            }
        }
        int id = high+1;
        
        Customer p = new Customer(id,name,address,email,phone);
        people.add(p);
        return gson.toJson(p);
    }
    
    public String updateUser(int id, String name, String address, String email, String phone){
        Gson gson = new GsonBuilder().create();
        Customer p = new Customer();
        
        for(Customer x: people){
            if(x.getCust_id() == id){
                p = x;
                people.remove(x);
                break;
            }
        }
        p.setAddress(address);
        p.setName(name);
        p.setEmail(email);
        p.setPhone(phone);

        people.add(p);
        
        return gson.toJson(p);
    }
}

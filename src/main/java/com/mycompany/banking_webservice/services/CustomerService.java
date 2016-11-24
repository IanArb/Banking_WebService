/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.database.DatabaseManager;
import com.mycompany.banking_webservice.models.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Conor
 */
public class CustomerService {
    static ArrayList<Person> people = new ArrayList<>();
    DatabaseManager db;
    
    public CustomerService(){
        db = new DatabaseManager();
        if(people.isEmpty()){    
            try {
                people = (ArrayList<Person>) db.getAllCustomers();
            } catch (SQLException | NamingException ex) {}
        }
    }
    
    public String getUsers(int id) throws SQLException, NamingException{
        Gson gson = new GsonBuilder().create();
        DatabaseManager db = new DatabaseManager();
        if(id > -1){
            Person p = db.getCustomer(id);
           if(p.getCust_id() == 0){
              return gson.toJson("No Results for Cutomer with id: "+id);
           }
            return gson.toJson(p);
        }
         return gson.toJson(db.getAllCustomers());
    }
   
    
    public void deleteUser(int id){
       for(Person x: people){
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
        for(Person x: people){
            if(x.getCust_id()>high){
                high = x.getCust_id();
            }
        }
        int id = high+1;
        
        Person p = new Person(id,name,address,email,phone);
        people.add(p);
        return gson.toJson(p);
    }
    
    public String updateUser(int id, String name, String address, String email, String phone){
        Gson gson = new GsonBuilder().create();
        Person p = new Person();
        
        for(Person x: people){
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

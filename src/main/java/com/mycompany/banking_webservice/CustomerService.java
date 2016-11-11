/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author Conor
 */
public class CustomerService {
    static ArrayList<Person> people = new ArrayList<>();
    
    public CustomerService(){
        if(people.isEmpty()){
            people.add(new Person(1,"Conor","Dublin","018657477","Conor@test.com"));
            people.add(new Person(2,"Jessie","Meath","018633477","Jessie@test.com")); 
            people.add(new Person(3,"Shane","Cork","018612747","Shane@test.com"));
            people.add(new Person(4,"Ben","Dublin","018651377","Ben@test.com"));
            
        }
    }
    
    public String getUsers(int id){
        Gson gson = new GsonBuilder().create();
        if(id > -1){
        ArrayList<Person> filtered = new ArrayList<>();
        for(Person x: people){
            if(x.getCust_id() == id){
                filtered.add(x);
            }
          }
        if(filtered.size() > 0){
            return gson.toJson(filtered);
        }else{
            return gson.toJson("No Results for Cutomer with id: "+id);
        }
        }
        return gson.toJson(people);
    }
   
    
    public int deleteUser(int id){
       for(Person x: people){
            if(x.getCust_id() == id){
              people.remove(x);
            }
          }
        return 204;
    }
    
    public String addUser(String name, String address, String email, String phone){
        Gson gson = new GsonBuilder().create();
        // Temp code to add cust id.
        int id = people.size();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.mycompany.banking_webservice.objects.Person;
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
            people.add(new Person(1,"Conor","Dublin","Conor@test.com", "018657477"));
            people.add(new Person(2,"Jessie","Meath","Jessie@test.com","018633477")); 
            people.add(new Person(3,"Shane","Cork","Shane@test.com","018612747"));
            people.add(new Person(4,"Ben","Dublin","Ben@test.com","018651377"));
            
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

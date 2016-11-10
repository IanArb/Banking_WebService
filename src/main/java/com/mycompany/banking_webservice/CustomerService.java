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
   
//    
//    public Person deleteUser(int id){
//        Person removed = people.remove(id);
//        return removed;
//    }
//    
//    public String addUser(String name,int age){
//        Gson gson = new GsonBuilder().create();
//        Person p = new Person(name,age);
//        people.add(p);
//        
//        return gson.toJson(p);
//    }
//    
//    public String updateUser(String name, int age, int id){
//        Gson gson = new GsonBuilder().create();
//        
//        Person p = people.remove(id);
//        p.setAge(age);
//        p.setName(name);
//        people.add(id, p);
//        
//        return gson.toJson(p);
//    }
}

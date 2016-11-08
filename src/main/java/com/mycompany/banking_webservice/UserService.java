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
public class UserService {
    static ArrayList<Person> people = new ArrayList<>();
    
    public UserService(){
        if(people.isEmpty()){
            people.add(new Person("Conor",20));
            people.add(new Person("Jessie",22));
            people.add(new Person("Shane",26));
            people.add(new Person("Ben",17));
            
        }
    }
    
    public String getUsers(String name){
        Gson gson = new GsonBuilder().create();
        if(name != null && name.length() > 0){
        ArrayList<Person> filtered = new ArrayList<>();
        for(Person x: people){
            if(x.getName().equalsIgnoreCase(name)){
                filtered.add(x);
            }
          }
        if(filtered.size() > 0){
            return gson.toJson(filtered);
        }else{
            return gson.toJson("No Results for: "+name);
        }
        }
        return gson.toJson(people);
    }
    
    public Person deleteUser(int id){
        Person removed = people.remove(id);
        return removed;
    }
    
    public String addUser(String name,int age){
        Gson gson = new GsonBuilder().create();
        Person p = new Person(name,age);
        people.add(p);
        
        return gson.toJson(p);
    }
    
    public String updateUser(String name, int age, int id){
        Gson gson = new GsonBuilder().create();
        
        Person p = people.remove(id);
        p.setAge(age);
        p.setName(name);
        people.add(id, p);
        
        return gson.toJson(p);
    }
}

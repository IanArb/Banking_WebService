/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Conor
 */
@XmlRootElement
public class Customer {
    private int cust_id;
    private String name,address,email,phone;

    public Customer(int cust_id, String name, String address, String email, String phone) {
       this.cust_id = cust_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    
    public Customer(String name, String address, String email, String phone) {
        this.cust_id = -1;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    
    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
     
}

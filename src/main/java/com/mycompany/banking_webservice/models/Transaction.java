/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Conor
 */
@Entity
@Table
@XmlRootElement
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int _id;
    private int amount, post_balance, account_no;
    private String type;

    public Transaction() {
    }

    public Transaction(int amount, int post_balance, int account_no, String type) {
        this.amount = amount;
        this.post_balance = post_balance;
        this.account_no = account_no;
        this.type = type;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPost_balance() {
        return post_balance;
    }

    public void setPost_balance(int post_balance) {
        this.post_balance = post_balance;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
        
    
}

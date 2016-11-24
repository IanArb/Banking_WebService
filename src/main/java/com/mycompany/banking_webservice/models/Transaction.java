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
public class Transaction {
    
    private int id, amount, post_amount, account_no;
    private String type;

    public Transaction() {
    }

    public Transaction(int id, int amount, int post_amount, int account_no, String type) {
        this.id = id;
        this.amount = amount;
        this.post_amount = post_amount;
        this.account_no = account_no;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPost_amount() {
        return post_amount;
    }

    public void setPost_amount(int post_amount) {
        this.post_amount = post_amount;
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

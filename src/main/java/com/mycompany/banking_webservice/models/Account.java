/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.models;

import java.io.Serializable;
import java.util.Random;
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
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int account_no;
    private int cid, balance, sort_code;
    
    Random random = new Random();
    int min = 1, max = 3;
    
    public Account(){
        this.account_no = -1;
        this.cid = -1;
        this.balance = -1;
        this.sort_code = min + random.nextInt(max - min) + 1;
    }
    
    public Account(int account_no, int cid, int balance, int sort_code) {
        this.account_no = account_no;
        this.cid = cid;
        this.balance = balance;
        this.sort_code = sort_code;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSort_code() {
        return sort_code;
    }

    public void setSort_code(int sort_code) {
        this.sort_code = sort_code;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.objects;

/**
 *
 * @author Conor
 */
public class Account {
    int account_no, cid, balance, sort_code;

    public Account(){
        
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

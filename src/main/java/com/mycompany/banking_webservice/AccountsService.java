/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.banking_webservice.objects.Account;
import java.util.ArrayList;

/**
 *
 * @author Conor
 */
public class AccountsService {
    static ArrayList<Account> accounts = new ArrayList<>();
    
     public AccountsService(){
        if(accounts.isEmpty()){
            accounts.add(new Account(1, 1, 10000, 1));
            accounts.add(new Account(2, 2, 20000, 1));
            accounts.add(new Account(3, 3, 15000, 1));
            accounts.add(new Account(4, 1, 5000, 1));
            accounts.add(new Account(5, 4, 10500, 1));
            accounts.add(new Account(6, 2, 20000, 1));

        }
    }
     
     public String getBalance(int cust_id){
         Gson gson = new GsonBuilder().create();
         ArrayList<Account> result = new ArrayList<>();
         
         for(Account x: accounts){
             if(x.getCid() == cust_id){
                 result.add(x);
             }
         }
         
         return gson.toJson(result);
     }
     
     public String getBalance(int cust_id,int account_no){
         Gson gson = new GsonBuilder().create();
         Account result = new Account();
         
         for(Account x: accounts){
             if(x.getCid() == cust_id && x.getAccount_no() == account_no){
                 result = x;
                 break;
             }
         }
         
         return gson.toJson(result);
     }
     
     public void deleteAccount(int cust_id, int account_no){
         for(Account x: accounts){
             if(x.getCid() == cust_id && x.getAccount_no() == account_no){
                 accounts.remove(x);
                 break;
             }
         }
     }
}

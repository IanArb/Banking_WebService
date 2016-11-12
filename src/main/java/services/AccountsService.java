/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.banking_webservice.models.Account;
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
     
     public String addAccount(int cust_id, int sort_code){
         Gson gson = new GsonBuilder().create();
         // Temp code to add account_no.
        int high = 0;
        for(Account x: accounts){
            if(x.getAccount_no()>high){
                high = x.getAccount_no();
            }
        }
        int no = high+1;
        
        Account a = new Account(no,cust_id,0,sort_code);
        accounts.add(a);
         
        return gson.toJson(a);
     }

    public String withdrawal(int cust_id, int account_no, int amount) {
        Gson gson = new GsonBuilder().create();
        Account a = transaction(cust_id,account_no, amount, 0);
        return gson.toJson(a);
    }
    
    public String lodgement(int cust_id, int account_no, int amount) {
        Gson gson = new GsonBuilder().create();
        Account a = transaction(cust_id,account_no, amount, 1);
        return gson.toJson(a);
    }
    
    public String transfer(int cust_to, int account_to, int cust_from, int account_from, int amount) {
        Gson gson = new GsonBuilder().create();
        ArrayList<Account> transfer = new ArrayList<>();
        Account out = transaction(cust_from,account_from, amount, 0);
        Account in = transaction(cust_to,account_to, amount, 1);
        transfer.add(out);
        transfer.add(in);
        return gson.toJson(transfer);
    }
    
    public Account transaction(int cust_id, int account_no, int amount, int type){
         Account a = new Account();
         
         for(Account x: accounts){
             if(x.getCid() == cust_id && x.getAccount_no() == account_no){
                 a = x;
                 accounts.remove(x);
                 break;
             }
         }
         
         int currBalance = a.getBalance();
         int newBalance = currBalance;
         if(type == 0){
            newBalance = currBalance - amount;
         }else if(type==1){
           newBalance = currBalance + amount;
         }
         a.setBalance(newBalance);
         accounts.add(a);
         return a;
    }
}

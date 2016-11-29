/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.database.PersistenceManager;
import com.mycompany.banking_webservice.models.Account;
import com.mycompany.banking_webservice.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Conor
 */
public class AccountsService {

    static ArrayList<Account> accounts = new ArrayList<>();

    PersistenceManager manager = new PersistenceManager();

    public AccountsService() {
//        if(accounts.isEmpty()){
//            accounts.add(new Account(1, 1, 10000, 1));
//            accounts.add(new Account(2, 2, 20000, 1));
//            accounts.add(new Account(3, 3, 15000, 1));
//            accounts.add(new Account(4, 1, 5000, 1));
//            accounts.add(new Account(5, 4, 10500, 1));
//            accounts.add(new Account(6, 2, 20000, 1));
//
//        }
    }

//     public List<Account> getBalance(int cust_id){
//         List<Account> result = new ArrayList<>();
//         
//         for(Account x: accounts){
//             if(x.getCid() == cust_id){
//                 result.add(x);
//             }
//         }        
//         return result;
//     }
    public List<Account> getBalances(int cust_id) {
        TypedQuery<Account> query = manager.getEntityManager()
                .createQuery("SELECT NEW com.mycompany.banking_webservice.models.Account(a.account_no, a.cid, a.balance, a.sort_code)"
                        + "FROM Account a"
                        + " WHERE a.cid = " + cust_id, Account.class);
        return query.getResultList();
    }

//    public List<Account> getBalance(int cust_id, int account_no) {
//        List<Account> all = getBalance(cust_id);
//        if (account_no == -1) {
//            return all;
//        }
//        List<Account> account = new ArrayList<>();
//        for (Account x : all) {
//            if (x.getAccount_no() == account_no) {
//                account.add(x);
//                break;
//            }
//        }
//        return account;
//    }
    
    public Account getBalance(int acc_no) {
        Account account = manager.getEntityManager().find(Account.class, acc_no);
        manager.closeTransaction();
        return account;
    }

    public void deleteAccount(int cust_id, int account_no) {
        for (Account x : accounts) {
            if (x.getCid() == cust_id && x.getAccount_no() == account_no) {
                accounts.remove(x);
                break;
            }
        }
    }

    public Account addAccount(int cust_id, int sort_code) {
        // Temp code to add account_no.
        int high = 0;
        for (Account x : accounts) {
            if (x.getAccount_no() > high) {
                high = x.getAccount_no();
            }
        }
        int no = high + 1;

        Account a = new Account(no, cust_id, 0, sort_code);
        accounts.add(a);
        return a;
    }

     public Account updateBalance(Account newA) {
        Account a = manager.getEntityManager().find(Account.class, newA.getAccount_no());
        if (a != null) {
            manager.startTransaction();
            a.setBalance(newA.getBalance());
            manager.commit();
            manager.closeTransaction();
        }
        return a;
    }
    
    
    
    public Transaction withdrawal(int account_no, int amount) {
        Transaction w = transaction(amount, account_no, "withdrawal");
        return w;
    }

    public Transaction lodgement(int account_no, int amount) {
        Transaction l = transaction(amount, account_no, "lodgement");
        return l;
    }

    public List<Transaction> transfer(int account_to, int account_from, int amount) {
        List<Transaction> transfer = new ArrayList<>();
        transfer.add(withdrawal(account_from, amount));
        transfer.add(lodgement(account_to, amount));
        return transfer;
    }

    public Transaction transaction(int amount, int account_no, String type) {
        Account a = getBalance(account_no);
        
        int currBalance = a.getBalance();
        int newBalance = currBalance;

        // Calculate New Balance
        if (type.equalsIgnoreCase("withdrawal")) {
            newBalance = currBalance - amount;
        } else if (type.equalsIgnoreCase("lodgement")) {
            newBalance = currBalance + amount;
        }

        // Update Account
        a.setBalance(newBalance);
        updateBalance(a);
              
        // Add Transaction
        Transaction t = new Transaction(amount, newBalance, account_no, type);
        Transaction test = manager.getEntityManager().find(Transaction.class, account_no);
        if (test == null) {
            manager.startTransaction();
            manager.persist(t);
            manager.commit();
            manager.closeTransaction();
        }
        return t;
    }
}

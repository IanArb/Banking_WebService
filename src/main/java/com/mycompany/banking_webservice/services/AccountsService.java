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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Conor
 */
public class AccountsService {

    PersistenceManager manager;

    public AccountsService() {
        manager = new PersistenceManager();
    }

    public List<Account> getBalances(int cust_id) {
        CriteriaBuilder cb = manager.getBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> rootEntry = cq.from(Account.class);
        CriteriaQuery<Account> all = cq.select(rootEntry);
        all.where(cb.equal(rootEntry.get("cid"), cust_id));
        TypedQuery<Account> allQuery = manager.getEntityManager().createQuery(cq);
        return allQuery.getResultList();
    }
    
    public Account getBalance(int acc_no) {
        Account a = manager.getEntityManager().find(Account.class, acc_no);
        manager.closeTransaction();
        return a;
    }

    public void deleteAccount(int account_no) {
        Account account = manager.getEntityManager().find(Account.class, account_no);
        if(account != null) {
            manager.startTransaction();
            manager.remove(account);
            manager.commit();
            manager.closeTransaction();
            
        }
    }
    
    public Account addAccount(Account account) {
        Account acc = manager.getEntityManager().find(Account.class, account.getAccount_no());
        if(acc == null) {
            manager.startTransaction();
            manager.persist(account);
            manager.commit();
            manager.closeTransaction();
        }
        return account;
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
                System.out.println("step 3");

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

    private Transaction transaction(int amount, int account_no, String type) {
        manager.openEm();
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
        manager.openEm();
        updateBalance(a);

        // Add Transaction
        Transaction t = new Transaction(amount, newBalance, account_no, type);

        manager.openEm();
        manager.startTransaction();
        manager.persist(t);
        manager.commit();
        manager.closeTransaction();

        return t;
    }

}

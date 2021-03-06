/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.database.PersistenceManager;
import com.mycompany.banking_webservice.models.Customer;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Conor
 */
public class CustomerService {
   
    PersistenceManager manager = new PersistenceManager();

    // Constructor
    public CustomerService(){
    }
    
    // Get All Customers
    public List<Customer> getCustomers() {
        CriteriaBuilder cb = manager.getBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = manager.createQuery(cq);
        return allQuery.getResultList();
    }
    
    // Get Specific Customer
    public Customer getCustomer(int id) {
        Customer c = manager.getEntityManager().find(Customer.class, id);
        manager.closeTransaction();
        return c;
    }
    
    // Add a New or Update a Customer
    public Customer addCustomer(Customer c) { 
        Customer test = manager.getEntityManager().find(Customer.class, c.getCust_id());
        if (test == null) {
            manager.startTransaction();
            manager.persist(c);   
            manager.commit();
            manager.closeTransaction();
        }
        return c;
    }
    
    public Customer editCustomer(int id, Customer newC) {
//        Customer c = em.find(Customer.class, id);
        Customer c = manager.getEntityManager().find(Customer.class, id);
        if (c != null) {
            manager.startTransaction();
            c.setName(newC.getName());
            c.setEmail(newC.getEmail());
            c.setPhone(newC.getPhone());
            c.setAddress(newC.getAddress());
            manager.commit();
            manager.closeTransaction();
        }
        return c;
    }
    
    // Delete a Customer
    public void deleteCustomer(int id) {
        Customer test = manager.getEntityManager().find(Customer.class, id);
        if (test !=null) {
            manager.startTransaction();
            manager.remove(test);
            manager.commit();
            manager.closeTransaction();
        }
    }


   //============================================= //
   //================= Old Code ================== //
   //============================================= //

/*
    public List<Customer> getUsers(int id){
        DatabaseManager db = new DatabaseManager();
        List<Customer> customers = new ArrayList<>();
        if(id > -1){
            try {
                customers.add(db.getCustomer(id));
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                customers = (List<Customer>) db.getAllCustomers();
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return customers;
    }
  
    public void deleteUser(int id){
       for(Customer x: customers){
            if(x.getCust_id() == id){
              customers.remove(x);
              break;
            }
          }
    }
    
    public Customer addUser(String name, String address, String email, String phone){
        // Temp code to add cust id.
        int high = 0;
        for(Customer x: customers){
            if(x.getCust_id()>high){
                high = x.getCust_id();
            }
        }
        int id = high+1;
        // Change code Above when db is implement to auto generate
        Customer c = new Customer(id,name,address,email,phone);
        customers.add(c);
        return c;
    }
    
    public Customer updateUser(int id, String name, String address, String email, String phone){
        Customer c = new Customer();
        
        for(Customer x: customers){
            if(x.getCust_id() == id){
                c = x;
                customers.remove(x);
                break;
            }
        }
        c.setAddress(address);
        c.setName(name);
        c.setEmail(email);
        c.setPhone(phone);

        customers.add(c);
        
        return c;
    }
    
    */
}

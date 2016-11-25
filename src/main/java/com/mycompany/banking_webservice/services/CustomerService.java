/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.services;

import com.mycompany.banking_webservice.models.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Conor
 */
public class CustomerService {
    
    // Entity Manager
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Customer");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();  

    // Constructor
    public CustomerService(){
    }
    
    // Get All Customers
    public List<Customer> getCustomers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    
    // Get Specific Customer
    public Customer getCustomer(int id) {
        Customer c = em.find(Customer.class, id);
        em.close();
        return c;
    }
    
    // Add a New or Update a Customer
    public Customer addCustomer(Customer c) { 
        Customer test = em.find(Customer.class, c.getCust_id());
        if (test == null) {
            tx.begin();
            em.persist(c);
            tx.commit();           
            em.close();
        }

        return c;
    }
    
    public Customer editCustomer(int id, Customer newC) {
        Customer c = em.find(Customer.class, id);
        if (c != null) {
            tx.begin();
            c.setName(newC.getName());
            c.setEmail(newC.getEmail());
            c.setPhone(newC.getPhone());
            c.setAddress(newC.getAddress());
            tx.commit();           
            em.close();
        }
        return c;
    }
    
    // Delete a Customer
    public void deleteCustomer(int id) {
        Customer test = em.find(Customer.class, id);
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ianarbuckle
 */
public class PersistenceManager {
    
    // Entity Manager
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;  
    
    public PersistenceManager() {
        emf = Persistence.createEntityManagerFactory("Bank");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    
    public void openEm(){
        if(!(em.isOpen())){
            em = emf.createEntityManager();
            tx = em.getTransaction();
        }
    }
    public EntityManager getEntityManager() {
        return em;
    }
    
    public void persist(Object o) {
        em.persist(o);
    }
    
    public void commit() {
        tx.commit();
    }
    
    public void startTransaction() {
        tx.begin();
    }
    
    public void closeTransaction() {
        em.close();
    }
    
    public void remove(Object o) {
        em.remove(o);
    }
    
    public CriteriaBuilder getBuilder() {
        return em.getCriteriaBuilder();
    }
    
    public TypedQuery createQuery(CriteriaQuery cq) {
        return em.createQuery(cq);
    }
    
}

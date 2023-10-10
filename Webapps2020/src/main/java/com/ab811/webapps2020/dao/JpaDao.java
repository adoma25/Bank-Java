/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import java.lang.reflect.ParameterizedType;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ab811
 */
public abstract class JpaDao implements Dao {

    /**
     *
     */
    protected Class entityClass;
    
    /**
     *
     */
    @PersistenceContext
    protected EntityManager em;
    
    /**
     *
     */
    public JpaDao() {
       
    }
    
    /**
     *
     * @param entity
     */
    @Override
    public void persist(Object entity) { em.persist(entity); }

    /**
     *
     * @param entity
     */
    @Override
    public void remove(Object entity) { em.remove(entity); }

}

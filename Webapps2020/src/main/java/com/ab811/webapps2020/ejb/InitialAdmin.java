/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ab811
 */
@Singleton
@Startup
public class InitialAdmin implements Serializable{
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private UserStorageService users;
    
    private final String name = "Administrator";
    
    private final LocalDate dateOfBirth = LocalDate.of(1995, 03, 28);
    
    private final String email = "admin@payme.com";
    
    private final String phoneNumber = "00447878389639";
    
    private final String username = "admin1";
    
    private final String password = "admin1";
    
    private final String currency = "GBP";
    
    private final String groupname = "admins";
    
    private final Address billing_address = new Address();
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        
        billing_address.setHouseNumber("50");
        billing_address.setAddressLine("Widdicombe Way");
        billing_address.setCity("Brighton");
        billing_address.setCountry("United-Kingdom");
        billing_address.setPostCode("BN23GH");
        
        if(!users.checkUserExists(username)){
            users.registerUser(name, dateOfBirth, billing_address, email, phoneNumber, username, password, currency, groupname);
        }
        
    }
}

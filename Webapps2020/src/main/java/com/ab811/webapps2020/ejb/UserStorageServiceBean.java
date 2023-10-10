/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import com.ab811.webapps2020.entity.Conversion;
import com.ab811.webapps2020.entity.SystemUser;
import com.ab811.webapps2020.entity.SystemUserGroup;
import com.ab811.webapps2020.dao.SystemUserDao;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
/**
 *
 * @author ab811
 */
@Stateless
public class UserStorageServiceBean  implements UserStorageService{
    
    @EJB
    UserServices services;
    
    @EJB
    private SystemUserDao systemUserDao;

    /**
     *
     */
    public UserStorageServiceBean() {
    }
    
    /**
     *
     * @param fullName
     * @param dateOfBirth
     * @param billingAddress
     * @param emailAddress
     * @param phoneNumber
     * @param userName
     * @param userPassword
     * @param currency
     */
    @Override
    public synchronized void registerUser(String fullName, LocalDate dateOfBirth, Address billingAddress, String emailAddress, String phoneNumber, String userName, String userPassword, String currency) {
        
        
        if(!checkUserExists(userName)){
            SystemUser sys_user;
        
            SystemUserGroup sys_user_group;
            
            Address billing_address;
            
            currency = currency.toUpperCase();

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                String password = userPassword;

                md.update(password.getBytes("UTF-8"));

                byte[] digest = md.digest();

                StringBuffer sb = new StringBuffer();

                for(int i=0; i < digest.length; i++){
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                String passwordToStoreInDb = sb.toString();
                
                ArrayList<Float> balances = defaultUserBalance(currency);
                
                
                sys_user = new SystemUser(services.getServerTime(), fullName, dateOfBirth, emailAddress, phoneNumber, userName, passwordToStoreInDb, currency, balances.get(0), balances.get(1), balances.get(2));

                sys_user_group = new SystemUserGroup("users", userName, sys_user);
                
                billing_address = new Address(billingAddress.getHouseNumber(), billingAddress.getAddressLine(), billingAddress.getPostCode(), billingAddress.getCity(), billingAddress.getCountry(), sys_user);
                
                sys_user.setSystemUserGroup(sys_user_group);
                
                sys_user.setBillingAddress(billing_address);
                
                systemUserDao.persist(sys_user);

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(UserStorageServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
            }
        
        
    }
    
    /**
     *
     * @param fullName
     * @param dateOfBirth
     * @param billingAddress
     * @param emailAddress
     * @param phoneNumber
     * @param userName
     * @param userPassword
     * @param currency
     * @param groupName
     */
    @Override
    public synchronized void registerUser(String fullName, LocalDate dateOfBirth, Address billingAddress, String emailAddress, String phoneNumber, String userName, String userPassword,String currency, String groupName) {
        
        if(!checkUserExists(userName)){
            
            SystemUser sys_user;

            SystemUserGroup sys_user_group;
            
            Address billing_address;
            
            currency = currency.toUpperCase();

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                String password = userPassword;

                md.update(password.getBytes("UTF-8"));

                byte[] digest = md.digest();

                StringBuffer sb = new StringBuffer();

                for(int i=0; i < digest.length; i++){
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                String passwordToStoreInDb = sb.toString();
                
                ArrayList<Float> balances = defaultUserBalance(currency);
                
                if(userName == "admin1"){
                    Timestamp tt = new Timestamp(System.currentTimeMillis());
                    sys_user = new SystemUser(tt, fullName, dateOfBirth, emailAddress, phoneNumber, userName, passwordToStoreInDb, currency, balances.get(0), balances.get(1), balances.get(2));
                }else{
                    sys_user = new SystemUser(services.getServerTime(), fullName, dateOfBirth, emailAddress, phoneNumber, userName, passwordToStoreInDb, currency, balances.get(0), balances.get(1), balances.get(2));
                }
                

                sys_user_group = new SystemUserGroup(groupName, userName, sys_user);
                
                billing_address = new Address(billingAddress.getHouseNumber(), billingAddress.getAddressLine(), billingAddress.getPostCode(), billingAddress.getCity(), billingAddress.getCountry(), sys_user);
                
                sys_user.setSystemUserGroup(sys_user_group);
                
                sys_user.setBillingAddress(billing_address);
                
                systemUserDao.persist(sys_user);
                

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(UserStorageServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /**
     *
     * @param userName
     * @return
     */
    @Override
    public synchronized Boolean checkUserExists(String userName){
        
        List<SystemUser> user = systemUserDao.checkIfUserExistsByUserName(userName);
        
        return !user.isEmpty();
    }
    
    /**
     *
     * @param Currency
     * @return
     */
    public synchronized ArrayList<Float> defaultUserBalance(String Currency){
        ArrayList balances = new ArrayList<Float>(3);
        float zero = 0;
        float gbp  = 1000;
        
        switch(Currency.toUpperCase()){
            case "GBP":
                balances.add(gbp);
                balances.add(zero);
                balances.add(zero);
                break;
            case "USD":
                float usd = convertCurrency("GBP", "USD", 1000.0).floatValue(); 
                balances.add(zero);
                balances.add(usd);
                balances.add(zero);
                break;
            case "EUR":
                float eur = convertCurrency("GBP", "EUR", 1000.0).floatValue();
                balances.add(zero);
                balances.add(zero);
                balances.add(eur);
                break;
            
        }
        return balances;
    }
    
    /**
     *
     * @param c1
     * @param c2
     * @param amount
     * @return
     */
    public synchronized Double convertCurrency(String c1, String c2, Double amount){
        
        String target = "http://localhost:10000/Webapps2020/resources/conversion/" + c1 + "/" + c2 + "/" + amount;
        
        Client client = ClientBuilder.newClient();
        
        Conversion conversion = client.target(target).request().get(Conversion.class);
        
        return conversion.getConversion();
    }
    
}

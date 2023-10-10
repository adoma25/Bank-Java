/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import com.ab811.webapps2020.entity.SystemUser;
import com.ab811.webapps2020.entity.Transactions;
import com.ab811.webapps2020.dao.SystemUserDao;
import com.ab811.webapps2020.dao.TransactionsDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ab811
 */
@RolesAllowed("admins")
@Stateless
public class AdministratorServicesBean {
    
    @EJB
    private UserStorageService users;
    
    @EJB
    private UserServices user_services;
    
    @EJB
    private SystemUserDao systemUserDao;
    
    @EJB
    private TransactionsDao transactionsDao;

    /**
     *
     */
    public AdministratorServicesBean() {
    }
    
    /**
     *
     * @param fullName
     * @param dateOfBirth
     * @param billingAddress
     * @param emailAddress
     * @param phoneNumber
     * @param userName
     * @param pass
     * @param currency
     * @param groupname
     */
    public synchronized void registerUser(String fullName, LocalDate dateOfBirth, Address billingAddress, String emailAddress, String phoneNumber, String userName, String pass, String currency, String groupname){
        
        users.registerUser(fullName, dateOfBirth, billingAddress, phoneNumber, emailAddress, userName, pass, currency, groupname);
    }
    
    /**
     *
     * @return
     */
    public synchronized List<Transactions> getAllTransactions(){
        
        return transactionsDao.findAllTransactions();
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getSrotedUsersTransactions(){
        List<Transactions> rawTransactionsList = getAllTransactions();
        ArrayList<String[]> sortedUsersTransactionsList = new ArrayList<String[]>();
        
        for(int i=0; i< rawTransactionsList.size(); i++){
            String[] transaction = new String[6];
            transaction[0] = rawTransactionsList.get(i).getId().toString();
            transaction[1] = rawTransactionsList.get(i).getType();
            switch(rawTransactionsList.get(i).getCurrency()){
                    case "GBP":
                        transaction[2] = String.valueOf("£" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "USD":
                        transaction[2] = String.valueOf("$" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "EUR":
                        transaction[2] = String.valueOf("€" + rawTransactionsList.get(i).getAmount());
                        break;
                }
            transaction[3] = user_services.getUserName(rawTransactionsList.get(i).getOriginUserId());
            transaction[4] = user_services.getUserName(rawTransactionsList.get(i).getDestinationUserId());
            transaction[5] = rawTransactionsList.get(i).getTimestamp().toString();
            sortedUsersTransactionsList.add(transaction);
            
        }
        
        return sortedUsersTransactionsList;
    }
    
    /**
     *
     * @return
     */
    public synchronized List<SystemUser> getAllUsers(){
        
        return systemUserDao.findAllUsers();
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getSortedUsersList(){
        List<SystemUser> rawUsersList = getAllUsers();
        ArrayList<String[]> sortedUsersList = new ArrayList<String[]>();
        
        for(int i=0; i < rawUsersList.size(); i++){
            String[] user = new String[6];
            user[0] = rawUsersList.get(i).getId().toString();
            user[1] = rawUsersList.get(i).getUsername();
            user[2] = rawUsersList.get(i).getFullName();
            user[3] = rawUsersList.get(i).getEmailAddress();
            double gbpB = rawUsersList.get(i).getGbp_balance();
            double usdB = rawUsersList.get(i).getUsd_balance();
            double eurB = rawUsersList.get(i).getEur_balance();
            double totalB;
            switch(rawUsersList.get(i).getDefaultCurrency().toUpperCase()){
                case "GBP":
                    totalB = gbpB + user_services.convertCurrency("USD", "GBP", usdB) + user_services.convertCurrency("EUR", "GBP", eurB);
                    user[4] = String.valueOf("£"+ totalB);
                    break;
                case "USD":
                    totalB = usdB + user_services.convertCurrency("GBP", "USD", gbpB) + user_services.convertCurrency("EUR", "USD", eurB);
                    user[4] = String.valueOf("$"+ totalB);
                    break;
                case "EUR":
                    totalB = eurB + user_services.convertCurrency("GBP", "EUR", gbpB) + user_services.convertCurrency("USD", "EUR", usdB);
                    user[4] = String.valueOf("€"+ totalB);
                    break;
            }
            user[5] = rawUsersList.get(i).getDateJoined().toString();
            
            sortedUsersList.add(user);
        }
        return sortedUsersList;
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getSortedRecentUsersTransactions(){
        ArrayList<String[]> sortedUserTransactionsList = getSrotedUsersTransactions();
        ArrayList<String[]> sortedRecentUsersTransactionsList = new ArrayList<String[]>();
        
        Collections.reverse(sortedUserTransactionsList);
        if (sortedUserTransactionsList.size() <= 10){
            for(int i=0; i<sortedUserTransactionsList.size(); i++){
                sortedUserTransactionsList.get(i)[5] = sortedUserTransactionsList.get(i)[5].substring(0, 10);
            }
            return sortedUserTransactionsList;
        }else{
            for (int i=0; i<10; i++){
                sortedRecentUsersTransactionsList.add(sortedUserTransactionsList.get(i));
                sortedRecentUsersTransactionsList.get(i)[5] = sortedRecentUsersTransactionsList.get(i)[5].substring(0, 10);
            }
        }
        
        return sortedRecentUsersTransactionsList;
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getRecentRegisteredUsers(){
        ArrayList<String[]> sortedAllRegisteredUsers = getSortedUsersList();
        ArrayList<String[]> sortedRecentRegisteredUsers = new ArrayList<String[]>();
        
        Collections.reverse(sortedAllRegisteredUsers);
        if (sortedAllRegisteredUsers.size() <= 10){
            for(int i=0; i<sortedAllRegisteredUsers.size(); i++){
                sortedAllRegisteredUsers.get(i)[5] = sortedAllRegisteredUsers.get(i)[5].substring(0, 10);
            }
            return sortedAllRegisteredUsers;
        }else{
            for (int i=0; i<10; i++){
                sortedRecentRegisteredUsers.add(sortedAllRegisteredUsers.get(i));
                sortedRecentRegisteredUsers.get(i)[5] = sortedRecentRegisteredUsers.get(i)[5].substring(0, 10);
            }
        }
        
        return sortedRecentRegisteredUsers;
    }
}

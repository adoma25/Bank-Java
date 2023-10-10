/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import com.ab811.webapps2020.entity.Conversion;
import com.ab811.webapps2020.entity.Transactions;
import com.ab811.webapps2020.dao.AddressDao;
import com.ab811.webapps2020.dao.SystemUserDao;
import com.ab811.webapps2020.dao.SystemUserGroupDao;
import com.ab811.webapps2020.dao.TransactionsDao;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
/**
 *
 * @author ab811
 */
@Stateless
public class UserServicesBean implements UserServices{
    
    @Resource
    private SessionContext session;
    
    @EJB
    private UserStorageService u_storage;
    
    @EJB
    private SystemUserDao systemUserDao;
    
    @EJB
    private TransactionsDao transactionsDao;
    
    @EJB
    private AddressDao addressDao;
    
    @EJB
    private SystemUserGroupDao systemUserGroupDao;

    /**
     * Default constructor.
     */
    public UserServicesBean() {
    }
    
    /******
     * 
     * METHODS THAT ACCESS THE DATA LAYER TO RETRIEVE/UPDATE DATA.
     * 
     ******/
    
    /**
     * Retrieves the given users specific currency balance.
     * @param userName Target user's username.
     * @param currency Desired currency balance.
     * @return Users balance in given currency.
     */
    
    public synchronized float getUserBalance(String userName, String currency){
        String c = currency.substring(0, 1).toUpperCase() + currency.substring(1).toLowerCase();
        return Float.parseFloat(systemUserDao.findUserBalanceByUserName(userName, c));
    }
    
    /**
     * Retrieves the users default currency.
     * @param userId Target user's userId.
     * @return The users default currency.
     */
    
    public synchronized String getUserCurrency(float userId){
        
        return systemUserDao.findUserDefaultCurrencyById(userId);
    }
    
    /**
     * Retrieves a users registered username.
     * @param userId Target user's userId.
     * @return Users username.
     */
    
    @Override
    public synchronized String getUserName(float userId){
        
        return systemUserDao.findUserNameByUserId(userId);
    }
    
    /**
     * Retrieves a users userId.
     * @param userName Target user's username.
     * @return Users userId.
     */
    
    public synchronized Long getUserId(String userName){
        
        return Long.parseLong(systemUserDao.findUserIdByUserName(userName));
    }
    
    /**
     * Retrieve user's Full Name.
     * @param userId Target user's userId.
     * @return Users Full Name.
     */
    
    public synchronized String getUserFullName(Long userId){
        
        return systemUserDao.findUserFullNameById(userId);
    }
    
    /**
     * Retrieve user's DateofBirth.
     * @param userId Target user's userId.
     * @return Users DateOfBIrth.
     */
    
    public synchronized String getUserDateOfBirth(Long userId){
        
        return systemUserDao.findUserDateOfBirthById(userId);
    }
    
    /**
     * Retrieve user's PhoneNumber.
     * @param userId Target user's userId.
     * @return Users Full Name.
     */
    
    public synchronized String getUserPhoneNumber(Long userId){
        
        return systemUserDao.findUserPhoneNumberById(userId);
    }
    
    /**
     * Retrieve user's Full Name.
     * @param userId Target user's userId.
     * @return Users Full Name.
     */
    
    public synchronized String getUserEmailAddress(Long userId){
        
        return systemUserDao.findUserEmailAddressById(userId);
    }
    
    /**
     * Retrieves user's Address.
     * @param userId Target user's userId
     * @return Target user Address
     */
    public synchronized Address getUserAddress(Long userId){
        
        return addressDao.findUseraddress(userId);
    }
    
    /**
     * Retrieves all the transactions that the user was involved in.
     * @param userId Target user's userId.
     * @return A list of Transactions class type containing all transactions the user was part of.
     */
    
    public synchronized List<Transactions> getUserTransactions(Long userId){
        
        return transactionsDao.findTransactionByUserId(userId);
    }
    
    /**
     * Updates a user's given currency balance to given amount.
     * @param amount Desired amount to update the balance to.
     * @param Currency Target currency balance.
     * @param user Target user.
     */
    
    private synchronized void updateUserBalance(float amount, String Currency, String user){
        
        String c = Currency.substring(0,1).toUpperCase() + Currency.substring(1).toLowerCase();
        systemUserDao.updateUserBalanceByUserName(user, c, amount);
    }
    
    /**
     * Send a given amount of specified currency from an origin sender in user's balance to a target receiver.
     * @param sender Origin user that will send the amount
     * @param amount Desired amount to send.
     * @param Currency Desired currency balance to send from.
     * @param receiver Target user that will receive the money.
     */
    
    public synchronized void sendMoney(String sender, float amount, String Currency, String receiver){
        
        Currency = Currency.toUpperCase();
        
        if(u_storage.checkUserExists(receiver)){
            
            if(getUserBalance(sender, Currency) >= amount){
            
                Transactions t;

                updateUserBalance(getUserBalance(sender, Currency)-amount, Currency, getCurrentUserName());

                updateUserBalance(getUserBalance(receiver, Currency)+amount, Currency, receiver);
                
                t = new Transactions("send", Currency, amount, getCurrentUserId(), getUserId(receiver),getServerTime());
                
                transactionsDao.persist(t);
            
            }
        }
    }
    
    /**
     * Creates a money request transaction from origin user to a specified target user with
     * specified currency and amount.
     * @param sender Origin Sender
     * @param amount Amount to request.
     * @param Currency Currency of the requested amount.
     * @param receiver Target user to receive the request.
     */
    
    public synchronized void requestMoney(String sender, float amount, String Currency, String receiver){
        
        Currency = Currency.toUpperCase();
        
        Transactions t = new Transactions("request", Currency, amount, getUserId(sender), getUserId(receiver),getServerTime());
        
        transactionsDao.persist(t);
    }
    
    /**
     * Exchanges amount from users currency balance to another currency balance
     * @param userId Target user's userId.
     * @param from Origin currency.
     * @param to Target currency.
     * @param amount Amount to be converted.
     */
    
    public synchronized void exchangeCurrency(float userId, String from, String to, Double amount){
        
        if( getUserBalance(getUserName(userId), from) >= amount){
            
            Double fromTo = convertCurrency(from, to, amount);
            
            updateUserBalance(getUserBalance(getUserName(userId), from) - amount.floatValue(), from, getUserName(userId));
            System.out.println(getUserBalance(getUserName(userId), from) + " " + from + " - " + amount);
            updateUserBalance(getUserBalance(getUserName(userId), to) + fromTo.floatValue(), to, getUserName(userId));
            System.out.println(getUserBalance(getUserName(userId), to) + " " + to + " + " + fromTo);
        }
    }
    
    /**
     *
     * @param userId
     * @param fullName
     */
    public synchronized void updateUserFullName(float userId, String fullName){
        
        systemUserDao.updateUserFullNameByUserName(fullName, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @param dateOfBirth
     */
    public synchronized void updateUserDateOfBirth(float userId, LocalDate dateOfBirth){
        
        systemUserDao.updateUserDateOfBirthByUserName(dateOfBirth, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @param emailAddress
     */
    public synchronized void updateUserEmailAddress(float userId, String emailAddress){
        
        systemUserDao.updateUserEmailAddressByUserName(emailAddress, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @param phoneNumber
     */
    public synchronized void updateUserPhoneNumber(float userId, String phoneNumber){
        
        systemUserDao.updateUserPhoneNumberByUserName(phoneNumber, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @param address
     */
    public synchronized void updateUserAddress(float userId, Address address){
        Long id = (long) userId;
        if(!"".equals(address.getHouseNumber())){
            addressDao.updateUserHouseNumber(id, address.getHouseNumber());
        } 
        if(!"".equals(address.getAddressLine())){
            addressDao.updateUserAddressLine(id, address.getAddressLine());
        } 
        if(!"".equals(address.getPostCode())){
            addressDao.updateUserPostCode(id, address.getPostCode());
        }
        if(!"".equals(address.getCity())){
            addressDao.updateUserCity(id, address.getCity());
        }
    }
    
    /**
     *
     * @param userId
     * @param userPassword
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public synchronized void updateUserPassword(float userId, String userPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
                String password = userPassword;
                md.update(password.getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for(int i=0; i < digest.length; i++){
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                String passwordToStoreInDb = sb.toString();
        systemUserDao.updateUserPasswordByUserName(passwordToStoreInDb, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @param currency
     */
    public synchronized void updateUserDefaultCurrency(float userId, String currency){
        
        systemUserDao.updateUserDefaultCurrencyByUserName(currency, getUserName(userId));
    }
    
    /**
     *
     * @param userId
     * @return
     */
    public synchronized boolean isUserAdmin(float userId){
         
        if("admins".equals(systemUserGroupDao.findUserRoleByUserName(getUserName(userId)))){
            return true;
        }
        return false;
    }
    
    /*****
     * 
     * CURRENT LOGGED IN USER SERVICES.
     * 
     *****/
    
    /**
     * Retrieves current logged in user id.
     * @return Current user id.
     */
    @Override
    public synchronized Long getCurrentUserId(){
        
        return getUserId(session.getCallerPrincipal().getName());
    }
    
    /**
     * Retrieves current logged in user Full Name.
     * @return Current user full name.
     */
    @Override
    public synchronized String getCurrentUserFullName(){
        
        return getUserFullName(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user Email Address.
     * @return Current user email address.
     */
    @Override
    public synchronized String getCurrentUserEmailAddress(){
        
        return getUserEmailAddress(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user Transactions.
     * @return Current user transactions.
     */
    @Override
    public synchronized List<Transactions> getCurrentUserTransactions(){
        
        return getUserTransactions(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user's userName.
     * @return Current users userName.
     */
    @Override
    public synchronized String getCurrentUserName(){
        
        return getUserName(getCurrentUserId());
    }
    
     /**
     * Retrieves current logged in user's DateOfBirth
     * @return Current users DateOfBirth.
     */
    @Override
    public synchronized String getCurrentUserDateOfBirth(){
        
        return getUserDateOfBirth(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user's phoneNumber
     * @return Current users phoneNumber.
     */
    @Override
    public synchronized String getCurrentUserPhoneNumber(){
        
        return getUserPhoneNumber(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user's Address
     * @return Current users address
     */
    @Override
    public synchronized Address getCurrentUserAddress(){
        
        return getUserAddress(getCurrentUserId());
    }
    
    /**
     * Retrieves current logged in user's British Pounds Balance.
     * @return Current user's GBP Balance.
     */
    @Override
    public synchronized float getCurrentUserGbpBalance(){
        
        return getUserBalance(getUserName(getCurrentUserId()), "Gbp");
    }
    
    /**
     * Retrieves current logged in user's United States Dollars Balance.
     * @return Current user's USD Balance.
     */
    @Override
    public synchronized float getCurrentUserUsdBalance(){
        
        return getUserBalance(getUserName(getCurrentUserId()), "Usd");
    }
    
    /**
     * Retrieves current logged in user's Euro Balance.
     * @return Current user's EUR Balance.
     */
    @Override
    public synchronized float getCurrentUserEurBalance(){
        
        return getUserBalance(getUserName(getCurrentUserId()), "Eur");
    }
    
    /**
     * Retrieves current logged in user's default currency.
     * @return Current user's default currency.
     */
    @Override
    public synchronized String getCurrentUserCurrency(){
        
        return getUserCurrency(getCurrentUserId());
    }
    
    /**
     * Retrieves the users total balance of all currencies converted to their default currency.
     * @return Estimated users total currencies balance in default currency.
     */
    @Override
    public synchronized String getCurrentUserTotalBalance(){
       
        return String.valueOf(getCurrencySymbole(getCurrentUserCurrency()) + getUserTotalBalance(getCurrentUserId()));
    }
    
    /**
     * Retrieves current logged in users transactions and sorts & prepares them for display.
     * @return ArrayList of Strings each containing a Transactions attributes Amount with its currency, Whether it was sent or received(+/-) and target user.
     */
    @Override
    public synchronized ArrayList<String[]> getSortedCurrentUserTransactions(){
        
        List<Transactions> rawTransactionsList = getCurrentUserTransactions();
        ArrayList<String[]> sortedUserTransactionsList = new ArrayList<String[]>();
        
        for(int i=0; i< rawTransactionsList.size(); i++){
            String[] transaction = new String[4];
            if(rawTransactionsList.get(i).getOriginUserId() == getCurrentUserId()){
                switch(rawTransactionsList.get(i).getCurrency()){
                    case "GBP":
                        transaction[0] = String.valueOf("£" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "USD":
                        transaction[0] = String.valueOf("$" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "EUR":
                        transaction[0] = String.valueOf("€" + rawTransactionsList.get(i).getAmount());
                        break;
                }
                
                if("send".equals(rawTransactionsList.get(i).getType())){
                    transaction[1] = "-";
                }else if("request".equals(rawTransactionsList.get(i).getType())){
                    transaction[1] = "Rs";
                }
                transaction[2] = getUserName(rawTransactionsList.get(i).getDestinationUserId());
                sortedUserTransactionsList.add(transaction);
            } else if(rawTransactionsList.get(i).getDestinationUserId() == getCurrentUserId()){
                switch(rawTransactionsList.get(i).getCurrency()){
                    case "GBP":
                        transaction[0] = String.valueOf("£" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "USD":
                        transaction[0] = String.valueOf("$" + rawTransactionsList.get(i).getAmount());
                        break;
                    case "EUR":
                        transaction[0] = String.valueOf("€" + rawTransactionsList.get(i).getAmount());
                        break;
                }
                if("send".equals(rawTransactionsList.get(i).getType())){
                    transaction[1] = "+";
                }else if("request".equals(rawTransactionsList.get(i).getType())){
                    transaction[1] = "Rr";
                }
                transaction[2] = getUserName(rawTransactionsList.get(i).getOriginUserId());
                sortedUserTransactionsList.add(transaction);
            }
            transaction[3] = rawTransactionsList.get(i).getTimestamp().toString();
        }
        
        
        return sortedUserTransactionsList;
    }
    
    /**
     * Retrieves current logged in user's last 10 transactions and sorts & prepares them for display.
     * @return ArrayList of Strings of size 10 or less. each containing a Transactions attributes Amount with its currency, Whether it was sent or received(+/-) and target user.
     */
    @Override
    public synchronized ArrayList<String[]> getSortedCurrentUserRecentTransactions(){
        ArrayList<String[]> sortedUserTransactionsList = getSortedCurrentUserTransactions();
        ArrayList<String[]> sortedRecentUserTransactionsList = new ArrayList<String[]>();
        
        Collections.reverse(sortedUserTransactionsList);
        if (sortedUserTransactionsList.size() <= 10){
            for(int i=0; i<sortedUserTransactionsList.size(); i++){
                sortedUserTransactionsList.get(i)[3] = sortedUserTransactionsList.get(i)[3].substring(0, 10);
            }
            return sortedUserTransactionsList;
        }else{
            for (int i=0; i<10; i++){
                sortedRecentUserTransactionsList.add(sortedUserTransactionsList.get(i));
                sortedRecentUserTransactionsList.get(i)[3] = sortedRecentUserTransactionsList.get(i)[3].substring(0, 10);
            }
        }
       
        
        return sortedRecentUserTransactionsList;
    }
    
    /**
     * Converts current logged in user's amount of a currency's balance to another currency balance.
     * @param from Origin currency.
     * @param to Target currency.
     * @param amount Amount to be converted
     */
    @Override
    public synchronized void currentUserExchangeCurrency(String from, String to, float amount){
        
        double amoun = amount;
        
        exchangeCurrency(getCurrentUserId(), from, to, amoun);
    }
    
    /**
     * Send a given amount of specified currency from current user's balance to a target receiver.
     * @param amount Desired amount to send.
     * @param Currency Desired currency balance to send from.
     * @param receiver Target user that will receive the money.
     */
    @Override
    public synchronized void currentUserSendMoney(float amount, String Currency, String receiver){
        
        sendMoney(getCurrentUserName(), amount, Currency, receiver);
    }
    
    /**
     * Creates a money request transaction from current logged in user to a specified target user with
     * specified currency and amount.
     * @param amount Amount to request.
     * @param Currency Currency of the requested amount.
     * @param receiver Target user to receive the request.
     */
    @Override
    public synchronized void currentUserRequestMoney(float amount, String Currency, String receiver){
        
        requestMoney(getCurrentUserName(), amount, Currency, receiver);
    }
    
    
    /**
     * 
     * @param fullName 
     */
    @Override
    public synchronized void updateCurrentUserFullName(String fullName){
        
        updateUserFullName(getCurrentUserId(), fullName);
    }
    
    /**
     *
     * @param dateOfBirth
     */
    @Override
    public synchronized void updateCurrentUserDateOfBirth(LocalDate dateOfBirth){
        
        updateUserDateOfBirth(getCurrentUserId(), dateOfBirth);
    }
    
    /**
     *
     * @param emailAddress
     */
    @Override
    public synchronized void updateCurrentUserEmailAddress(String emailAddress){
        
        updateUserEmailAddress(getCurrentUserId(), emailAddress);
    }
    
    /**
     *
     * @param phoneNumber
     */
    @Override
    public synchronized void updateCurrentUserPhoneNumber(String phoneNumber){
        
        updateUserPhoneNumber(getCurrentUserId(), phoneNumber);
    }
    
    /**
     *
     * @param address
     */
    @Override
    public synchronized void updateCurrentUserAddress(Address address){
        
        updateUserAddress(getCurrentUserId(), address);
    }
    
    /**
     *
     * @param password
     */
    @Override
    public synchronized void updateCurrentUserPassword(String password){
        
        try {
            updateUserPassword(getCurrentUserId(), password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServicesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserServicesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param currency
     */
    @Override
    public synchronized void updateCurrentUserDefaultCurrency(String currency){
        
        updateUserDefaultCurrency(getCurrentUserId(), currency);
    }
    
    /**
     *
     * @return
     */
    @Override
    public synchronized boolean isCurrentUserAdmin(){
        return isUserAdmin(getCurrentUserId());
    }
    
    
    
    /*****
     * 
     * CURRENCY CONVERSION API.
     * 
     *****/
    
    /**
     * Retrieves Currency1 amounts conversion to Currency2
     * @param c1 Origin Currency.
     * @param c2 Target Currency.
     * @param amount Desired amount.
     * @return c2 converted amount = c1 amount * c1 to c2 conversion rate. 
     */
    @Override
    public synchronized Double convertCurrency(String c1, String c2, Double amount){
        
        String target = "http://localhost:10000/Webapps2020/resources/conversion/" + c1 + "/" + c2 + "/" + amount;
        
        Client client = ClientBuilder.newClient();
        
        Conversion conversion = client.target(target).request().get(Conversion.class);
        
        return conversion.getConversion();
    }
    
    /**
     *
     * @return
     */
    @Override
    public synchronized Timestamp getServerTime(){
        
        String target = "http://localhost:10000/Webapps2020/resources/timestamp";
        
        Client client = ClientBuilder.newClient();
        
        Timestamp tstamp = client.target(target).request().get(java.sql.Timestamp.class);
        
        return tstamp;
    }
    
    /*****
     * 
     * GENERAL USER's SERVICES.
     * 
     *****/
    
    /**
     * Retrieves target users total balance of all currencies converted to their default currency.
     * @param userId Target user's userId.
     * @return Target user's estimated total of all currencies balances in their default currency.
     */
    public synchronized Float getUserTotalBalance(float userId){
        
        String currency = getUserCurrency(userId);
        float totalBalance = 0; 
        float gbpBalance = getUserBalance(getUserName(userId), "Gbp");
        float usdBalance = getUserBalance(getUserName(userId), "Usd");
        float eurBalance = getUserBalance(getUserName(userId), "Eur");
        switch(currency){
            case "GBP":
                totalBalance = (float) (gbpBalance + convertCurrency("USD", "GBP", Double.valueOf(usdBalance)) + convertCurrency("EUR", "GBP", Double.valueOf(eurBalance)));
                break;
            case "USD":
                totalBalance = (float) (usdBalance + convertCurrency("GBP", "USD", Double.valueOf(gbpBalance)) + convertCurrency("EUR", "USD", Double.valueOf(eurBalance)));
                break;
            case "EUR":
                totalBalance = (float) (eurBalance + convertCurrency("GBP", "EUR", Double.valueOf(gbpBalance)) + convertCurrency("USD", "EUR", Double.valueOf(usdBalance)));
                break;
        }
        
        return totalBalance;
    }
    
    /*****
     * 
     * GENERAL SERVICES.
     * 
     *****/
    
    /**
     * Retrieves a given currency's symbol.
     * @param Currency Target currency.
     * @return Target currency's symbol.
     */
    @Override
    public synchronized String getCurrencySymbole(String Currency){
        String symbol = null;
        switch(Currency){
            case "GBP":
                symbol = "£";
                break;
            case "USD":
                symbol = "$";
                break;
            case "EUR":
                symbol = "€";
                break;
        }
            
        return symbol;
    }
    
}

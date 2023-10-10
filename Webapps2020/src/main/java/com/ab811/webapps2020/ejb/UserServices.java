/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import com.ab811.webapps2020.entity.Transactions;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ab811
 */
@Local
public interface UserServices{

    /**
     *
     * @return
     */
    public Long getCurrentUserId();

    /**
     *
     * @return
     */
    public String getCurrentUserFullName();

    /**
     *
     * @return
     */
    public List<Transactions> getCurrentUserTransactions();

    /**
     *
     * @return
     */
    public String getCurrentUserName();

    /**
     *
     * @return
     */
    public float getCurrentUserGbpBalance();

    /**
     *
     * @return
     */
    public float getCurrentUserUsdBalance();

    /**
     *
     * @return
     */
    public float getCurrentUserEurBalance();

    /**
     *
     * @return
     */
    public String getCurrentUserCurrency();

    /**
     *
     * @return
     */
    public String getCurrentUserTotalBalance();

    /**
     *
     * @return
     */
    public ArrayList<String[]> getSortedCurrentUserTransactions();

    /**
     *
     * @return
     */
    public ArrayList<String[]> getSortedCurrentUserRecentTransactions();

    /**
     *
     * @param from
     * @param to
     * @param amount
     */
    public void currentUserExchangeCurrency(String from, String to, float amount);

    /**
     *
     * @param amount
     * @param Currency
     * @param receiver
     */
    public void currentUserSendMoney(float amount, String Currency, String receiver);

    /**
     *
     * @param amount
     * @param Currency
     * @param receiver
     */
    public void currentUserRequestMoney(float amount, String Currency, String receiver);

    /**
     *
     * @param Currency
     * @return
     */
    public String getCurrencySymbole(String Currency);

    /**
     *
     * @param c1
     * @param c2
     * @param amount
     * @return
     */
    public Double convertCurrency(String c1, String c2, Double amount);

    /**
     *
     * @param userId
     * @return
     */
    public String getUserName(float userId);

    /**
     *
     * @return
     */
    public String getCurrentUserEmailAddress();

    /**
     *
     * @return
     */
    public Address getCurrentUserAddress();

    /**
     *
     * @return
     */
    public String getCurrentUserPhoneNumber();

    /**
     *
     * @return
     */
    public String getCurrentUserDateOfBirth();

    /**
     *
     * @param fullName
     */
    public void updateCurrentUserFullName(String fullName);

    /**
     *
     * @param dateOfBirth
     */
    public void updateCurrentUserDateOfBirth(LocalDate dateOfBirth);

    /**
     *
     * @param emailAddress
     */
    public void updateCurrentUserEmailAddress(String emailAddress);

    /**
     *
     * @param phoneNumber
     */
    public void updateCurrentUserPhoneNumber(String phoneNumber);

    /**
     *
     * @param address
     */
    public void updateCurrentUserAddress(Address address);

    /**
     *
     * @param password
     */
    public void updateCurrentUserPassword(String password);

    /**
     *
     * @param currency
     */
    public void updateCurrentUserDefaultCurrency(String currency);

    /**
     *
     * @return
     */
    public Timestamp getServerTime();

    /**
     *
     * @return
     */
    public boolean isCurrentUserAdmin();
    
}

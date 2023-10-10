/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.SystemUser;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ab811
 */
public interface SystemUserDao extends Dao{

    /**
     *
     * @param userName
     * @return
     */
    String findUserIdByUserName(String userName);

    /**
     *
     * @param userId
     * @return
     */
    String findUserNameByUserId(float userId);

    /**
     *
     * @param userId
     * @return
     */
    String findUserPhoneNumberById(Long userId);

    /**
     *
     * @param userId
     * @return
     */
    String findUserDateOfBirthById(Long userId);

    /**
     *
     * @return
     */
    List<SystemUser> findAllUsers();

    /**
     *
     * @param userId
     * @return
     */
    String findUserFullNameById(Long userId);

    /**
     *
     * @param userId
     * @return
     */
    String findUserEmailAddressById(Long userId);

    /**
     *
     * @param userId
     * @return
     */
    String findUserDefaultCurrencyById(float userId);
    
    /**
     *
     * @param userName
     * @return
     */
    List<SystemUser> checkIfUserExistsByUserName(String userName);

    /**
     *
     * @param fullName
     * @param userName
     */
    void updateUserFullNameByUserName(String fullName, String userName);

    /**
     *
     * @param dateOfBirth
     * @param userName
     */
    void updateUserDateOfBirthByUserName(LocalDate dateOfBirth, String userName);

    /**
     *
     * @param emailAddress
     * @param userName
     */
    void updateUserEmailAddressByUserName(String emailAddress, String userName);

    /**
     *
     * @param phoneNumber
     * @param userName
     */
    void updateUserPhoneNumberByUserName(String phoneNumber, String userName);

    /**
     *
     * @param password
     * @param userName
     */
    void updateUserPasswordByUserName(String password, String userName);

    /**
     *
     * @param defaultCurrency
     * @param userName
     */
    void updateUserDefaultCurrencyByUserName(String defaultCurrency, String userName);

    /**
     *
     * @param userName
     * @param currency
     * @return
     */
    public String findUserBalanceByUserName(String userName, String currency);

    /**
     *
     * @param userName
     * @param currency
     * @param newBalance
     */
    public void updateUserBalanceByUserName(String userName, String currency, float newBalance);
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.ejb;

import com.ab811.webapps2020.entity.Address;
import java.time.LocalDate;
import javax.ejb.Local;

/**
 *
 * @author ab811
 */
@Local
public interface UserStorageService {

    /**
     *
     * @param userName
     * @return
     */
    public Boolean checkUserExists(String userName);

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
    public void registerUser(String fullName, LocalDate dateOfBirth, Address billingAddress, String emailAddress, String phoneNumber, String userName, String userPassword, String currency, String groupName);

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
    public void registerUser(String fullName, LocalDate dateOfBirth, Address billingAddress, String emailAddress, String phoneNumber, String userName, String userPassword, String currency);
    
    
}

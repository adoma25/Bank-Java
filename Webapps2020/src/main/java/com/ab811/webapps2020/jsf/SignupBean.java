/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.jsf;

import com.ab811.webapps2020.ejb.UserServices;
import com.ab811.webapps2020.ejb.UserStorageService;
import com.ab811.webapps2020.entity.Address;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ab811
 */
@Named
@RequestScoped
public class SignupBean {
    
    String fullName;
    
    LocalDate dateOfBirth;
    
    String emailAddress;
    
    String phoneNumber;
    
    String userName;
    
    String userPassword;
    
    String userConfirmPassword;
    
    String currency;
    
    Address billing_address;
    
    String houseNumber;
    
    String addressLine;
    
    String postCode;
    
    String city;
    
    String country;
    
    String dob;
    
    @EJB
    UserStorageService users;
    
    @EJB
    UserServices services;

    /**
     *
     */
    public SignupBean() {
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return
     */
    public Address getBilling_address() {
        return billing_address;
    }

    /**
     *
     * @return
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     *
     * @return
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     *
     * @return
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    /**
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param billing_address
     */
    public void setBilling_address(Address billing_address) {
        this.billing_address = billing_address;
    }

    /**
     *
     * @param houseNumber
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     *
     * @param addressLine
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     *
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @param dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    /**
     *
     * @return
     */
    public String signUp (){
        billing_address = new Address();
        billing_address.setAddressLine(addressLine);billing_address.setCity(city);billing_address.setCountry(country);billing_address.setHouseNumber(houseNumber);billing_address.setPostCode(postCode);
        dateOfBirth = LocalDate.parse(dob);
        users.registerUser(fullName, dateOfBirth, billing_address, emailAddress, phoneNumber, userName, userPassword, currency);
        return "login";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.jsf;

import com.ab811.webapps2020.ejb.AdministratorServicesBean;
import com.ab811.webapps2020.entity.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ab811
 */
@Named
@RequestScoped
public class AdministratorBean {
    
    String fullname;
    
    String emailAddress;
    
    String username;
    
    String password;
    
    String confirmPassword;
    
    String groupname;
    
    String currency;
    
    Address billingAddress;
    
    LocalDate dateOfBirth;
    
    String phoneNumber;
    
    String houseNumber;
    
    String addressLine;
    
    String postCode;
    
    String city;
    
    String country;
    
    String dob;
    
    @EJB
    AdministratorServicesBean services;

    /**
     *
     */
    public AdministratorBean() {
    }

    /**
     *
     * @param fullname
     * @param emailAddress
     * @param username
     * @param password
     */
    public AdministratorBean(String fullname, String emailAddress, String username, String password) {
        this.fullname = fullname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }
    
    /**
     *
     * @param fullname
     * @param emailAddress
     * @param username
     * @param password
     * @param currency
     * @param groupname
     */
    public AdministratorBean(String fullname, String emailAddress, String username, String password, String currency, String groupname) {
        this.fullname = fullname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.currency = currency;
        this.groupname = groupname;
    }

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname;
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
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public String getGroupname() {
        return groupname;
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
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getConfirmPassword() {
        return confirmPassword;
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
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
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
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @param groupname
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
     * @param billingAddress
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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
    
    /**
     *
     */
    public void registerUser(){
        if(!"".equals(postCode)){
            
            Address ad = new Address(); ad.setAddressLine(addressLine); ad.setCity(city); ad.setCountry(country); ad.setHouseNumber(houseNumber); ad.setPostCode(postCode);
            LocalDate dd = LocalDate.parse(dob);
            services.registerUser(fullname, dd, ad, emailAddress, phoneNumber, username, password, currency, groupname);
        }
        services.registerUser(fullname, dateOfBirth, billingAddress, emailAddress, phoneNumber, username, password, currency, groupname);
        
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String[]> getUsersTransactions(){
        
        return services.getSrotedUsersTransactions();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String[]> getRecentUsersTransactions(){
        
        return services.getSortedRecentUsersTransactions();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String[]> getRegisteredUsers(){
        
        return services.getSortedUsersList();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String[]> getRecentRegisteredUsers(){
        
        return services.getRecentRegisteredUsers();
    }
}

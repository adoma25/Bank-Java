/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.jsf;

import com.ab811.webapps2020.ejb.UserServices;
import com.ab811.webapps2020.entity.Address;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ab811
 */
@Named
@RequestScoped
public class UserService  implements Serializable{
    
    @EJB
    UserServices services;
    
    float amount;
    
    String currency;
    
    String receiver;
    
    String from;
    
    String to;
    
    String fullName;
    
    String phoneNumber;
    
    String emailAddress;
    
    String dob;
    
    String houseNo; String addressLine; String postCode; String city; String country;
    
    Date testDate;
    
    String newPassword; String confirmPassword;
    
    String deCur;

    /**
     *
     */
    public UserService() {
    }

    /**
     *
     * @return
     */
    public synchronized float getAmount() {
        return amount;
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
    public synchronized String getReceiver() {
        return receiver;
    }

    /**
     *
     * @param receiver
     */
    public synchronized void setReceiver(String receiver) {
        this.receiver = receiver;
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
     * @param amount
     */
    public synchronized void setAmount(float amount) {
        this.amount = amount;
    }
    
    /**
     *
     * @return
     */
    public synchronized String getTotalBalance(){
        
        return services.getCurrentUserTotalBalance();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getGbpBalance(){
        
        return String.valueOf(services.getCurrentUserGbpBalance());
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUsdBalance(){
        
        return String.valueOf(services.getCurrentUserUsdBalance());
    }
    
    /**
     *
     * @return
     */
    public synchronized String getEurBalance(){
        
        return String.valueOf(services.getCurrentUserEurBalance());
    }

    /**
     *
     * @return
     */
    public String getFrom() {
        return from;
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
    public String getTo() {
        return to;
    }

    /**
     *
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     *
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    /**
     *
     * @return
     */
    public Date getTestDate() {
        return testDate;
    }

    /**
     *
     * @param testDate
     */
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
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
     * @param dob
     */
    public void setDob(String dob) {
        this.dob = dob;
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
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @return
     */
    public String getHouseNo() {
        return houseNo;
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
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
     * @param houseNo
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
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
     * @return
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     *
     * @return
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     *
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return
     */
    public String getDeCur() {
        return deCur;
    }

    /**
     *
     * @param deCur
     */
    public void setDeCur(String deCur) {
        this.deCur = deCur;
    }
    
    /**
     *
     */
    public synchronized void sendMoney(){
        
        services.currentUserSendMoney(amount, currency, receiver);
    } 
    
    /**
     *
     */
    public synchronized void requestMoney(){
        
        services.currentUserRequestMoney(amount, currency, receiver);
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getTransactions(){
        
        return services.getSortedCurrentUserTransactions();
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String[]> getRecentTransactions(){
        
        return services.getSortedCurrentUserRecentTransactions();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUserFullName(){
        
        return services.getCurrentUserFullName();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUserEmailAddress(){
        
        return services.getCurrentUserEmailAddress();
    }
    
    /**
     *
     * @return
     */
    public synchronized Address getUserAddress(){
        
        return services.getCurrentUserAddress();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUserPhoneNumber(){
        
        return services.getCurrentUserPhoneNumber();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUserDateOfBirth(){
        
        return services.getCurrentUserDateOfBirth();
    }
    
    /**
     *
     * @return
     */
    public synchronized String getUserDefaultCurrency(){
        
        return services.getCurrentUserCurrency();
    }
    
    /**
     *
     */
    public synchronized void convertCurrency(){
        
        services.currentUserExchangeCurrency(from, to, amount);
    }
    
    /**
     *
     */
    public synchronized void updateUserFullName(){
        
        services.updateCurrentUserFullName(fullName);
    }
    
    /**
     *
     */
    public synchronized void updateUserEmailAddress(){
        
        services.updateCurrentUserEmailAddress(emailAddress);
    }
    
    /**
     *
     */
    public synchronized void updateUserPhoneNumber(){
        
        services.updateCurrentUserPhoneNumber(phoneNumber);
    }
    
    /**
     *
     */
    public synchronized void updateAddress(){
        Address address = new Address(houseNo, addressLine, postCode, city, "United-Kingdom");
        services.updateCurrentUserAddress(address);
    }
    
    /**
     *
     */
    public synchronized void updateUserDateOfBirth(){
        LocalDate DOB = LocalDate.parse(dob);
        services.updateCurrentUserDateOfBirth(DOB);
    }
    
    /**
     *
     */
    public synchronized void updateUserPersonalDetails(){
        if(!"".equals(dob) && !"".equals(fullName)){
            updateUserFullName(); updateUserDateOfBirth();
        } else if(!"".equals(dob) && "".equals(fullName)){
            updateUserDateOfBirth();
        } else if ("".equals(dob) && !"".equals(fullName)){
            updateUserFullName();
        }
    }
    
    /**
     *
     */
    public synchronized void updateContactDetails(){
        if(!"".equals(emailAddress)){
            updateUserEmailAddress();
        }
        if(!"".equals(phoneNumber)){
            updateUserPhoneNumber();
        }
    }
    
    /**
     *
     */
    public synchronized void updateUserPassword(){
        
        if(newPassword == null ? confirmPassword == null : newPassword.equals(confirmPassword)){
            services.updateCurrentUserPassword(newPassword);
        }
    }
    
    /**
     *
     */
    public synchronized void updateUserDefaultCurrency(){
        services.updateCurrentUserDefaultCurrency(deCur);
    }
    
    /**
     *
     * @return
     */
    public synchronized ArrayList<String> getNotifications(){
        ArrayList<String[]> recentTransactions = getRecentTransactions();
        ArrayList<String[]> newRecentTransactions = new ArrayList<String[]>();
        ArrayList<String> notifications = new ArrayList<String>();
        if(!"".equals(recentTransactions)){
            if(recentTransactions.size()>2){
                for(int i=0;i<2;i++){
                    newRecentTransactions.add(recentTransactions.get(i));
                }
            }else{
                newRecentTransactions = recentTransactions;
            }
            for(int i=0;i<newRecentTransactions.size();i++){
                if(newRecentTransactions.get(i)[1] == "+"){
                    notifications.add(newRecentTransactions.get(i)[2] + " Sent you " + newRecentTransactions.get(i)[0]);
                }else if(newRecentTransactions.get(i)[1] == "-"){
                    notifications.add("You sent " + newRecentTransactions.get(i)[0] + " to " + newRecentTransactions.get(i)[2]);
                }else if(newRecentTransactions.get(i)[1] == "Rs"){
                    notifications.add("You requested " + newRecentTransactions.get(i)[0] + " from " + newRecentTransactions.get(i)[2]);
                }else if(newRecentTransactions.get(i)[1] == "Rr"){
                    notifications.add(newRecentTransactions.get(i)[2] + " Requested " + newRecentTransactions.get(i)[0]);
                }
            }
            return notifications;
        }
        notifications.add("");
        return notifications;
    }
    
    /**
     *
     * @return
     */
    public synchronized boolean userHasNotifications(){
        boolean hasNotifications = true;
        if(getNotifications() == null){
            hasNotifications = false;
            return hasNotifications;
        }
        return hasNotifications;
    }
    
    /**
     *
     * @return
     */
    public synchronized boolean isUserAdmin(){
        return services.isCurrentUserAdmin();
    }
    
}

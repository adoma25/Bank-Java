 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ab811
 */
@NamedQueries( 
  value={
     @NamedQuery(name="checkUserExists", query="SELECT u FROM SystemUser u WHERE u.username = ?1"),
    @NamedQuery(name="getUserGbpBalance", query="SELECT u.gbp_balance FROM SystemUser u WHERE u.username = ?1"),
    @NamedQuery(name="getUserUsdBalance", query="SELECT u.usd_balance FROM SystemUser u WHERE u.username = ?1"),
    @NamedQuery(name="getUserEurBalance", query="SELECT u.eur_balance FROM SystemUser u WHERE u.username = ?1"),

    @NamedQuery(name="updateUserGbpBalance", query="UPDATE SystemUser u SET u.gbp_balance = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserUsdBalance", query="UPDATE SystemUser u SET u.usd_balance = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserEurBalance", query="UPDATE SystemUser u SET u.eur_balance = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserFullName", query="UPDATE SystemUser u SET u.fullName = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserDateOfBirth", query="UPDATE SystemUser u SET u.dateOfBirth = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserEmailAddress", query="UPDATE SystemUser u SET u.emailAddress = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserPhoneNumber", query="UPDATE SystemUser u SET u.phoneNumber = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserPassword", query="UPDATE SystemUser u SET u.password = ?1 WHERE u.username=?2"),
    @NamedQuery(name="updateUserDefaultCurrency", query="UPDATE SystemUser u SET u.defaultCurrency = ?1 WHERE u.username=?2"),

    @NamedQuery(name="getUserId", query="SELECT u.id FROM SystemUser u WHERE u.username = ?1"),
    @NamedQuery(name="getUserName", query="SELECT u.username FROM SystemUser u WHERE u.id = ?1"),
    @NamedQuery(name="getUserPhoneNumber", query="SELECT u.phoneNumber FROM SystemUser u WHERE u.id = ?1"),
    @NamedQuery(name="getUserDateOfBirth", query="SELECT u.dateOfBirth FROM SystemUser u WHERE u.id = ?1"),
    @NamedQuery(name="getAllUsers", query="SELECT u FROM SystemUser u"),
    @NamedQuery(name="getUserFullName", query="SELECT u.fullName FROM SystemUser u WHERE u.id = ?1"),
    @NamedQuery(name="getUserEmailAddress", query="SELECT u.emailAddress FROM SystemUser u WHERE u.id = ?1"),
    @NamedQuery(name="getUserDefaultCurrency", query="SELECT u.defaultCurrency FROM SystemUser u WHERE u.id = ?1")
  }
)

@Entity
public class SystemUser implements Serializable {

    @NotNull
    String fullName;
    
    @NotNull
    String emailAddress;
    
    @NotNull
    String username;
    
    @NotNull
    String password;
    
    @NotNull
    String defaultCurrency;
    
    @NotNull
    LocalDate dateOfBirth;
    
    @NotNull
    String phoneNumber;
    
    @NotNull
    float gbp_balance;
    
    @NotNull
    float usd_balance;
    
    @NotNull
    float eur_balance;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    SystemUserGroup systemUserGroup;
    
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    Address billingAddress;
    
    @NotNull
    private Timestamp dateJoined;

    /**
     *
     */
    public SystemUser() {
    }

    /**
     *
     * @param dateJoined
     * @param fullName
     * @param dateOfBirth
     * @param emailAddress
     * @param phoneNumber
     * @param username
     * @param password
     * @param defaultCurrency
     * @param gbp_balance
     * @param usd_balance
     * @param eur_balance
     */
    public SystemUser(Timestamp dateJoined, String fullName, LocalDate dateOfBirth, String emailAddress, String phoneNumber, String username, String password, String defaultCurrency, float gbp_balance, float usd_balance, float eur_balance) {
        this.dateJoined = dateJoined;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.defaultCurrency = defaultCurrency;
        this.gbp_balance = gbp_balance;
        this.usd_balance = usd_balance;
        this.eur_balance = eur_balance;
    }

    /**
     *
     * @param dateJoined
     * @param fullName
     * @param emailAddress
     * @param username
     * @param password
     * @param defaultCurrency
     * @param gbp_balance
     * @param usd_balance
     * @param eur_balance
     * @param systemUserGroup
     */
    public SystemUser(Timestamp dateJoined, String fullName, String emailAddress, String username, String password, String defaultCurrency, float gbp_balance, float usd_balance, float eur_balance, SystemUserGroup systemUserGroup) {
        this.dateJoined = dateJoined;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.defaultCurrency = defaultCurrency;
        this.gbp_balance = gbp_balance;
        this.usd_balance = usd_balance;
        this.eur_balance = eur_balance;
        this.systemUserGroup = systemUserGroup;
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
    public String getDefaultCurrency() {
        return defaultCurrency;
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
    public float getGbp_balance() {
        return gbp_balance;
    }

    /**
     *
     * @return
     */
    public float getUsd_balance() {
        return usd_balance;
    }

    /**
     *
     * @return
     */
    public float getEur_balance() {
        return eur_balance;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Timestamp getDateJoined() {
        return dateJoined;
    }

    /**
     *
     * @return
     */
    public SystemUserGroup getSystemUserGroup() {
        return systemUserGroup;
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

    /**
     *
     * @param defaultCurrency
     */
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
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
     * @param gbp_balance
     */
    public void setGbp_balance(float gbp_balance) {
        this.gbp_balance = gbp_balance;
    }

    /**
     *
     * @param usd_balance
     */
    public void setUsd_balance(float usd_balance) {
        this.usd_balance = usd_balance;
    }

    /**
     *
     * @param eur_balance
     */
    public void setEur_balance(float eur_balance) {
        this.eur_balance = eur_balance;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param systemUserGroup
     */
    public void setSystemUserGroup(SystemUserGroup systemUserGroup) {
        this.systemUserGroup = systemUserGroup;
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
     * @param dateJoined
     */
    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.fullName);
        hash = 89 * hash + Objects.hashCode(this.emailAddress);
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.password);
        hash = 89 * hash + Objects.hashCode(this.defaultCurrency);
        hash = 89 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 89 * hash + Objects.hashCode(this.phoneNumber);
        hash = 89 * hash + Float.floatToIntBits(this.gbp_balance);
        hash = 89 * hash + Float.floatToIntBits(this.usd_balance);
        hash = 89 * hash + Float.floatToIntBits(this.eur_balance);
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.systemUserGroup);
        hash = 89 * hash + Objects.hashCode(this.billingAddress);
        hash = 89 * hash + Objects.hashCode(this.dateJoined);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (Float.floatToIntBits(this.gbp_balance) != Float.floatToIntBits(other.gbp_balance)) {
            return false;
        }
        if (Float.floatToIntBits(this.usd_balance) != Float.floatToIntBits(other.usd_balance)) {
            return false;
        }
        if (Float.floatToIntBits(this.eur_balance) != Float.floatToIntBits(other.eur_balance)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.defaultCurrency, other.defaultCurrency)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.systemUserGroup, other.systemUserGroup)) {
            return false;
        }
        if (!Objects.equals(this.billingAddress, other.billingAddress)) {
            return false;
        }
        if (!Objects.equals(this.dateJoined, other.dateJoined)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUser{" + "fullName=" + fullName + ", emailAddress=" + emailAddress + ", username=" + username + ", password=" + password + ", defaultCurrency=" + defaultCurrency + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + ", gbp_balance=" + gbp_balance + ", usd_balance=" + usd_balance + ", eur_balance=" + eur_balance + ", id=" + id + ", systemUserGroup=" + systemUserGroup + ", billingAddress=" + billingAddress + ", dateJoined=" + dateJoined + '}';
    }
    
}

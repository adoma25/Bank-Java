/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ab811
 */
@NamedQueries( 
  value={
     @NamedQuery(name="getUserAddress",query="SELECT a FROM Address a WHERE a.id = ?1"),
    @NamedQuery(name="updateUserHouseNumber", query="UPDATE Address a SET a.houseNumber = ?1 WHERE a.id=?2"),
    @NamedQuery(name="updateUserAddressLine", query="UPDATE Address a SET a.addressLine = ?1 WHERE a.id=?2"),
    @NamedQuery(name="updateUserPostCode", query="UPDATE Address a SET a.postCode = ?1 WHERE a.id=?2"),
    @NamedQuery(name="updateUserCity", query="UPDATE Address a SET a.city = ?1 WHERE a.id=?2")
  }
)


@Entity
public class Address implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long id;
    
    @NotNull
    private String houseNumber;
    
    @NotNull
    private String addressLine;
    
    @NotNull
    private String postCode;
    
    @NotNull
    private String city;
    
    @NotNull
    private String country;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private SystemUser user;

    /**
     *
     */
    public Address() {
    }
    
    /**
     *
     * @param houseNumber
     * @param addressLine
     * @param postCode
     * @param city
     * @param country
     */
    public Address(String houseNumber, String addressLine, String postCode, String city, String country) {
        this.houseNumber = houseNumber;
        this.addressLine = addressLine;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    /**
     *
     * @param houseNumber
     * @param addressLine
     * @param postCode
     * @param city
     * @param country
     * @param user
     */
    public Address(String houseNumber, String addressLine, String postCode, String city, String country, SystemUser user) {
        this.houseNumber = houseNumber;
        this.addressLine = addressLine;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.user = user;
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
    public SystemUser getUser() {
        return user;
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
     * @param user
     */
    public void setUser(SystemUser user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.houseNumber);
        hash = 13 * hash + Objects.hashCode(this.addressLine);
        hash = 13 * hash + Objects.hashCode(this.postCode);
        hash = 13 * hash + Objects.hashCode(this.city);
        hash = 13 * hash + Objects.hashCode(this.country);
        hash = 13 * hash + Objects.hashCode(this.user);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        if (!Objects.equals(this.addressLine, other.addressLine)) {
            return false;
        }
        if (!Objects.equals(this.postCode, other.postCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", houseNumber=" + houseNumber + ", addressLine=" + addressLine + ", postCode=" + postCode + ", city=" + city + ", country=" + country + ", user=" + user + '}';
    }

   
}

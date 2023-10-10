/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ab811
 */
@NamedQueries( 
  value={
     @NamedQuery(name = "getUserTransactions", query = "SELECT t FROM Transactions t WHERE t.originUserId = ?1 OR t.destinationUserId = ?1"),
    @NamedQuery(name = "getAllTransactions", query = "SELECT t FROM Transactions t")
  }
)

@Entity
public class Transactions implements Serializable{

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "type")
    private String type;
    
    private String currency;
    
    @Column(name = "amount")
    private float amount;
    
    @Column(name = "origin_user_id")
    private Long originUserId;
    
    @Column(name = "destination_user_id")
    private Long destinationUserId;
    
    private Timestamp timestamp;

    /**
     *
     */
    public Transactions() {
    }

    /**
     *
     * @param type
     * @param currency
     * @param amount
     * @param originUserId
     * @param destinationUserId
     * @param timestamp
     */
    public Transactions(String type, String currency, float amount, Long originUserId, Long destinationUserId, Timestamp timestamp) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.originUserId = originUserId;
        this.destinationUserId = destinationUserId;
        this.timestamp = timestamp;
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
    public String getType() {
        return type;
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
    public float getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public Long getOriginUserId() {
        return originUserId;
    }

    /**
     *
     * @return
     */
    public Long getDestinationUserId() {
        return destinationUserId;
    }

    /**
     *
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     *
     * @param originUserId
     */
    public void setOriginUserId(Long originUserId) {
        this.originUserId = originUserId;
    }

    /**
     *
     * @param destinationUserId
     */
    public void setDestinationUserId(Long destinationUserId) {
        this.destinationUserId = destinationUserId;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + Objects.hashCode(this.currency);
        hash = 23 * hash + Float.floatToIntBits(this.amount);
        hash = 23 * hash + Objects.hashCode(this.originUserId);
        hash = 23 * hash + Objects.hashCode(this.destinationUserId);
        hash = 23 * hash + Objects.hashCode(this.timestamp);
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
        final Transactions other = (Transactions) obj;
        if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.originUserId, other.originUserId)) {
            return false;
        }
        if (!Objects.equals(this.destinationUserId, other.destinationUserId)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transactions{" + "id=" + id + ", type=" + type + ", currency=" + currency + ", amount=" + amount + ", originUserId=" + originUserId + ", destinationUserId=" + destinationUserId + ", timestamp=" + timestamp + '}';
    }

}

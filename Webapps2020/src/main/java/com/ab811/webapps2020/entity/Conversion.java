/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ab811
 */
@Entity
public class Conversion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String currency1;
    private String currency2;
    private double conversion;

    /**
     *
     */
    public Conversion() {
    }

    /**
     *
     * @param currency1
     * @param currency2
     * @param conversion
     */
    public Conversion(String currency1, String currency2, double conversion) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.conversion = conversion;
    }

    /**
     *
     * @return
     */
    public String getCurrency1() {
        return currency1;
    }

    /**
     *
     * @return
     */
    public String getCurrency2() {
        return currency2;
    }

    /**
     *
     * @return
     */
    public double getConversion() {
        return conversion;
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
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param currency1
     */
    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    /**
     *
     * @param currency2
     */
    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    /**
     *
     * @param conversion
     */
    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.currency1);
        hash = 17 * hash + Objects.hashCode(this.currency2);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.conversion) ^ (Double.doubleToLongBits(this.conversion) >>> 32));
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
        final Conversion other = (Conversion) obj;
        if (Double.doubleToLongBits(this.conversion) != Double.doubleToLongBits(other.conversion)) {
            return false;
        }
        if (!Objects.equals(this.currency1, other.currency1)) {
            return false;
        }
        if (!Objects.equals(this.currency2, other.currency2)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conversion{" + "id=" + id + ", currency1=" + currency1 + ", currency2=" + currency2 + ", conversion=" + conversion + '}';
    }

    
    
}

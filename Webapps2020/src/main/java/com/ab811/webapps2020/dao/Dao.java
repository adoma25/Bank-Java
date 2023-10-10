/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import javax.persistence.Entity;



/**
 *
 * @author ab811
 */
public interface Dao {

    /**
     *
     * @param e
     */
    void persist(Object e);

    /**
     *
     * @param e
     */
    void remove(Object e);
}

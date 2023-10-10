/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.Address;
import javax.ejb.Singleton;

/**
 *
 * @author ab811
 */
@Singleton
public class JpaAddressDao extends JpaDao implements AddressDao{

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public Address findUseraddress(Long userId) {
        return em.createNamedQuery("getUserAddress", Address.class).setParameter(1, userId).getSingleResult();
    }

    /**
     *
     * @param userId
     * @param houseNumber
     */
    @Override
    public void updateUserHouseNumber(Long userId, String houseNumber) {
        em.createNamedQuery("updateUserHouseNumber").setParameter(2, userId).setParameter(1, houseNumber).executeUpdate();
    }

    /**
     *
     * @param userId
     * @param postCode
     */
    @Override
    public void updateUserPostCode(Long userId, String postCode) {
        em.createNamedQuery("updateUserPostCode").setParameter(2, userId).setParameter(1, postCode).executeUpdate();
    }

    /**
     *
     * @param userId
     * @param city
     */
    @Override
    public void updateUserCity(Long userId, String city) {
        em.createNamedQuery("updateUserCity").setParameter(2, userId).setParameter(1, city).executeUpdate();
    }

    /**
     *
     * @param userId
     * @param addressLine
     */
    @Override
    public void updateUserAddressLine(Long userId, String addressLine) {
        em.createNamedQuery("updateUserAddressLine").setParameter(2, userId).setParameter(1, addressLine).executeUpdate();
    }
    
}

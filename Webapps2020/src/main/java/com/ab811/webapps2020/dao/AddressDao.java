/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.Address;

/**
 *
 * @author ab811
 */
public interface AddressDao extends Dao{

    /**
     *
     * @param userId
     * @return
     */
    Address findUseraddress(Long userId);

    /**
     *
     * @param userId
     * @param houseNumber
     */
    void updateUserHouseNumber(Long userId, String houseNumber);

    /**
     *
     * @param userId
     * @param postCode
     */
    void updateUserPostCode(Long userId, String postCode);

    /**
     *
     * @param userId
     * @param city
     */
    void updateUserCity(Long userId, String city);

    /**
     *
     * @param userId
     * @param addressLine
     */
    void updateUserAddressLine(Long userId, String addressLine);
}

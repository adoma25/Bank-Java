/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

/**
 *
 * @author ab811
 */
public interface SystemUserGroupDao extends Dao{

    /**
     *
     * @param userName
     * @return
     */
    String findUserRoleByUserName(String userName);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import javax.ejb.Singleton;

/**
 *
 * @author ab811
 */
@Singleton
public class JpaSystemUserGroupDao extends JpaDao implements SystemUserGroupDao {

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public String findUserRoleByUserName(String userName) {
        return em.createNamedQuery("getUserRole").setParameter(1, userName).getResultList().get(0).toString();
    }
    
}

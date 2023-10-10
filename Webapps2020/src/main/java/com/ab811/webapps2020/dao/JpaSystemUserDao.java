/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.SystemUser;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author ab811
 */
@Singleton
public class JpaSystemUserDao extends JpaDao implements SystemUserDao {

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public String findUserIdByUserName(String userName) {
        return (em.createNamedQuery("getUserId").setParameter(1, userName)).getResultList().get(0).toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserNameByUserId(float userId) {
        return em.createNamedQuery("getUserName").setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserPhoneNumberById(Long userId) {
        return em.createNamedQuery("getUserPhoneNumber").setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserDateOfBirthById(Long userId) {
        return em.createNamedQuery("getUserDateOfBirth").setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @return
     */
    @Override
    public List<SystemUser> findAllUsers() {
        return em.createNamedQuery("getAllUsers").getResultList();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserFullNameById(Long userId) {
        return em.createNamedQuery("getUserFullName").setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserEmailAddressById(Long userId) {
        return em.createNamedQuery("getUserEmailAddress").setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String findUserDefaultCurrencyById(float userId) {
        return (em.createNamedQuery("getUserDefaultCurrency")).setParameter(1, userId).getResultList().get(0).toString();
    }

    /**
     *
     * @param userName
     * @return
     */
    public String findUserGbpBalanceByUserName(String userName) {
        return (em.createNamedQuery("getUserGbpBalance")).setParameter(1, userName).getResultList().get(0).toString();
    }

    /**
     *
     * @param userName
     * @return
     */
    public String findUserUsdBalanceByUserName(String userName) {
        return (em.createNamedQuery("getUserUsdBalance")).setParameter(1, userName).getResultList().get(0).toString();
    }

    /**
     *
     * @param userName
     * @return
     */
    public String findUserEurBalanceByUserName(String userName) {
        return (em.createNamedQuery("getUserEurBalance")).setParameter(1, userName).getResultList().get(0).toString();
    }
    
    /**
     *
     * @param userName
     * @param currency
     * @return
     */
    @Override
    public String findUserBalanceByUserName(String userName, String currency){
        switch(currency){
            case "Gbp":
                return findUserGbpBalanceByUserName(userName);
            case "Usd":
                return findUserUsdBalanceByUserName(userName);
            case "Eur":
                return findUserEurBalanceByUserName(userName);
        }
        return "";
    }

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public List<SystemUser> checkIfUserExistsByUserName(String userName) {
        return (em.createNamedQuery("checkUserExists")).setParameter(1, userName).getResultList();
    }

    /**
     *
     * @param gbpBalance
     * @param userName
     */
    public void updateUserGbpBalanceByUserName(float gbpBalance, String userName) {
        Query q = em.createNamedQuery("updateUserGbpBalance");
        q.setParameter(1, gbpBalance);
        q.setParameter(2, userName);
        q.executeUpdate();
    }

    /**
     *
     * @param usdBalance
     * @param userName
     */
    public void updateUserUsdBalanceByUserName(float usdBalance, String userName) {
        Query q = em.createNamedQuery("updateUserUsdBalance");
        q.setParameter(1, usdBalance);
        q.setParameter(2, userName);
        q.executeUpdate();
    }

    /**
     *
     * @param eurBalance
     * @param userName
     */
    public void updateUserEurBalanceByUserName(float eurBalance, String userName) {
        Query q = em.createNamedQuery("updateUserEurBalance");
        q.setParameter(1, eurBalance);
        q.setParameter(2, userName);
        q.executeUpdate();
    }
    
    /**
     *
     * @param userName
     * @param currency
     * @param newBalance
     */
    @Override
    public void updateUserBalanceByUserName(String userName, String currency, float newBalance){
        switch(currency){
            case "Gbp":
                updateUserGbpBalanceByUserName(newBalance, userName);
                break;
            case "Usd":
                updateUserUsdBalanceByUserName(newBalance, userName);
                break;
            case "Eur":
                updateUserEurBalanceByUserName(newBalance, userName);
                break;
        }
    }

    /**
     *
     * @param fullName
     * @param userName
     */
    @Override
    public void updateUserFullNameByUserName(String fullName, String userName) {
        em.createNamedQuery("updateUserFullName").setParameter(2, userName).setParameter(1, fullName).executeUpdate();
    }

    /**
     *
     * @param dateOfBirth
     * @param userName
     */
    @Override
    public void updateUserDateOfBirthByUserName(LocalDate dateOfBirth, String userName) {
        em.createNamedQuery("updateUserDateOfBirth").setParameter(2, userName).setParameter(1, dateOfBirth).executeUpdate();
    }

    /**
     *
     * @param emailAddress
     * @param userName
     */
    @Override
    public void updateUserEmailAddressByUserName(String emailAddress, String userName) {
        em.createNamedQuery("updateUserEmailAddress").setParameter(2, userName).setParameter(1, emailAddress).executeUpdate();
    }

    /**
     *
     * @param phoneNumber
     * @param userName
     */
    @Override
    public void updateUserPhoneNumberByUserName(String phoneNumber, String userName) {
        em.createNamedQuery("updateUserPhoneNumber").setParameter(2, userName).setParameter(1, phoneNumber).executeUpdate();
    }

    /**
     *
     * @param password
     * @param userName
     */
    @Override
    public void updateUserPasswordByUserName(String password, String userName) {
        em.createNamedQuery("updateUserPassword").setParameter(2, userName).setParameter(1, password).executeUpdate();
    }

    /**
     *
     * @param defaultCurrency
     * @param userName
     */
    @Override
    public void updateUserDefaultCurrencyByUserName(String defaultCurrency, String userName) {
        em.createNamedQuery("updateUserDefaultCurrency").setParameter(2, userName).setParameter(1, defaultCurrency).executeUpdate();
    }
    
}

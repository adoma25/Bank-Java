/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.Transactions;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author ab811
 */
@Singleton
public class JpaTransactionsDao extends JpaDao implements TransactionsDao {

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<Transactions> findTransactionByUserId(Long userId) {
        return em.createNamedQuery("getUserTransactions").setParameter(1, userId).getResultList();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Transactions> findAllTransactions() {
        return em.createNamedQuery("getAllTransactions").getResultList();
    }
    
}

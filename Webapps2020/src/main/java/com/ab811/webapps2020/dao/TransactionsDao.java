/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.dao;

import com.ab811.webapps2020.entity.Transactions;
import java.util.List;

/**
 *
 * @author ab811
 */
public interface TransactionsDao extends Dao{

    /**
     *
     * @param userId
     * @return
     */
    List<Transactions> findTransactionByUserId(Long userId);

    /**
     *
     * @return
     */
    List<Transactions> findAllTransactions();
}

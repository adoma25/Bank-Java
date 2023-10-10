package com.ab811.webapps2020.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T06:30:59")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, Float> amount;
    public static volatile SingularAttribute<Transactions, Long> originUserId;
    public static volatile SingularAttribute<Transactions, String> currency;
    public static volatile SingularAttribute<Transactions, Long> id;
    public static volatile SingularAttribute<Transactions, String> type;
    public static volatile SingularAttribute<Transactions, Long> destinationUserId;
    public static volatile SingularAttribute<Transactions, Timestamp> timestamp;

}
package com.ab811.webapps2020.entity;

import com.ab811.webapps2020.entity.Address;
import com.ab811.webapps2020.entity.SystemUserGroup;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T06:30:59")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, Float> eur_balance;
    public static volatile SingularAttribute<SystemUser, Timestamp> dateJoined;
    public static volatile SingularAttribute<SystemUser, String> fullName;
    public static volatile SingularAttribute<SystemUser, LocalDate> dateOfBirth;
    public static volatile SingularAttribute<SystemUser, String> emailAddress;
    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, String> phoneNumber;
    public static volatile SingularAttribute<SystemUser, Float> usd_balance;
    public static volatile SingularAttribute<SystemUser, String> defaultCurrency;
    public static volatile SingularAttribute<SystemUser, Float> gbp_balance;
    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile SingularAttribute<SystemUser, Address> billingAddress;
    public static volatile SingularAttribute<SystemUser, SystemUserGroup> systemUserGroup;
    public static volatile SingularAttribute<SystemUser, String> username;

}
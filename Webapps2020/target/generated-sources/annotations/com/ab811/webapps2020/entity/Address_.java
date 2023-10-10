package com.ab811.webapps2020.entity;

import com.ab811.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T06:30:59")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> country;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> houseNumber;
    public static volatile SingularAttribute<Address, String> postCode;
    public static volatile SingularAttribute<Address, Long> id;
    public static volatile SingularAttribute<Address, String> addressLine;
    public static volatile SingularAttribute<Address, SystemUser> user;

}
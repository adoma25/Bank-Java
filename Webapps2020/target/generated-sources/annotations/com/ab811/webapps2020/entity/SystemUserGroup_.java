package com.ab811.webapps2020.entity;

import com.ab811.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T06:30:59")
@StaticMetamodel(SystemUserGroup.class)
public class SystemUserGroup_ { 

    public static volatile SingularAttribute<SystemUserGroup, Long> id;
    public static volatile SingularAttribute<SystemUserGroup, String> groupname;
    public static volatile SingularAttribute<SystemUserGroup, SystemUser> user;
    public static volatile SingularAttribute<SystemUserGroup, String> username;

}
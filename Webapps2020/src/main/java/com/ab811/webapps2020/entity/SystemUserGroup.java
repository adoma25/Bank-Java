/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ab811
 */
@Entity
@NamedQuery(name="getUserRole", query="SELECT g.groupname FROM SystemUserGroup g WHERE g.username = ?1")
public class SystemUserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_id")
    private Long id;
    
    @NotNull
    private String groupname;
    
    @NotNull
    private String username;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private SystemUser user;

    /**
     *
     */
    public SystemUserGroup() {
    }

    /**
     *
     * @param groupname
     * @param username
     * @param user
     */
    public SystemUserGroup(String groupname, String username, SystemUser user) {
        this.groupname = groupname;
        this.username = username;
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     *
     * @return
     */
    public SystemUser getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     *
     * @param groupname
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    
    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param user
     */
    public void setUser(SystemUser user) {
        this.user = user;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.groupname);
        hash = 19 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserGroup other = (SystemUserGroup) obj;
        if (!Objects.equals(this.groupname, other.groupname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

   
}

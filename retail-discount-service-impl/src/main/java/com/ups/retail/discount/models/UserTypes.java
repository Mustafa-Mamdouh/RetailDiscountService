/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ups.retail.discount.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmamdouh
 */
@Entity
@Table(name = "user_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTypes.findAll", query = "SELECT u FROM UserTypes u")
    , @NamedQuery(name = "UserTypes.findById", query = "SELECT u FROM UserTypes u WHERE u.id = :id")
    , @NamedQuery(name = "UserTypes.findByTypeName", query = "SELECT u FROM UserTypes u WHERE u.typeName = :typeName")
    , @NamedQuery(name = "UserTypes.findByDiscountValue", query = "SELECT u FROM UserTypes u WHERE u.discountValue = :discountValue")})
public class UserTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "type_name")
    private String typeName;
    @Basic(optional = false)
    @Column(name = "discount_value")
    private short discountValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userType")
    private Collection<User> usersCollection;

    public UserTypes() {
    }

    public UserTypes(Integer id) {
        this.id = id;
    }

    public UserTypes(Integer id, String typeName, short discountValue) {
        this.id = id;
        this.typeName = typeName;
        this.discountValue = discountValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public short getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(short discountValue) {
        this.discountValue = discountValue;
    }

    @XmlTransient
    public Collection<User> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<User> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTypes)) {
            return false;
        }
        UserTypes other = (UserTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxapplication1.UserTypes[ id=" + id + " ]";
    }
    
}

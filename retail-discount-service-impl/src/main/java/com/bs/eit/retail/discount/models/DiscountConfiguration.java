/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.eit.retail.discount.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmamdouh
 */
@Entity
@Table(name = "discount_configuration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiscountConfiguration.findAll", query = "SELECT d FROM DiscountConfiguration d")
    , @NamedQuery(name = "DiscountConfiguration.findById", query = "SELECT d FROM DiscountConfiguration d WHERE d.id = :id")
    , @NamedQuery(name = "DiscountConfiguration.findByConfigName", query = "SELECT d FROM DiscountConfiguration d WHERE d.configName = :configName")
    , @NamedQuery(name = "DiscountConfiguration.findByConfigValue", query = "SELECT d FROM DiscountConfiguration d WHERE d.configValue = :configValue")})
public class DiscountConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "config_name")
    private String configName;
    @Basic(optional = false)
    @Column(name = "config_value")
    private String configValue;

    public DiscountConfiguration() {
    }

    public DiscountConfiguration(Integer id) {
        this.id = id;
    }

    public DiscountConfiguration(Integer id, String configName, String configValue) {
        this.id = id;
        this.configName = configName;
        this.configValue = configValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
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
        if (!(object instanceof DiscountConfiguration)) {
            return false;
        }
        DiscountConfiguration other = (DiscountConfiguration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javafxapplication1.DiscountConfiguration[ id=" + id + " ]";
    }
    
}

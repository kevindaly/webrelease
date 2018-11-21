/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Cacheable(true)
@Table(name = "IMPACTED_COUNTRIES", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpactedCountries.findAll", query = "SELECT i FROM ImpactedCountries i"),
    @NamedQuery(name = "ImpactedCountries.findByCountryIsoId", query = "SELECT i FROM ImpactedCountries i WHERE i.countryIsoId = :countryIsoId"),
    @NamedQuery(name = "ImpactedCountries.findByRegionName", query = "SELECT i FROM ImpactedCountries i WHERE i.regionName = :regionName"),
    @NamedQuery(name = "ImpactedCountries.findByCountryName", query = "SELECT i FROM ImpactedCountries i WHERE i.countryName = :countryName")})
public class ImpactedCountries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "country_iso_id", nullable = false, length = 3)
    private String countryIsoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "region_name", nullable = false, length = 12)
    private String regionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "country_name", nullable = false, length = 128)
    private String countryName;

    public ImpactedCountries() {
    }

    public ImpactedCountries(String countryIsoId) {
        this.countryIsoId = countryIsoId;
    }

    public ImpactedCountries(String countryIsoId, String regionName, String countryName) {
        this.countryIsoId = countryIsoId;
        this.regionName = regionName;
        this.countryName = countryName;
    }

    public String getCountryIsoId() {
        return countryIsoId;
    }

    public void setCountryIsoId(String countryIsoId) {
        this.countryIsoId = countryIsoId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryIsoId != null ? countryIsoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpactedCountries)) {
            return false;
        }
        ImpactedCountries other = (ImpactedCountries) object;
        if ((this.countryIsoId == null && other.countryIsoId != null) || (this.countryIsoId != null && !this.countryIsoId.equals(other.countryIsoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.ImpactedCountries[ countryIsoId=" + countryIsoId + " ]";
    }
    
}

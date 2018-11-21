/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Table(name = "RELEASE_PROPERTIES", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReleaseProperties.findAll", query = "SELECT r FROM ReleaseProperties r"),
    @NamedQuery(name = "ReleaseProperties.findByIdProperties", query = "SELECT r FROM ReleaseProperties r WHERE r.idProperties = :idProperties"),
    @NamedQuery(name = "ReleaseProperties.findByPropertyType", query = "SELECT r FROM ReleaseProperties r WHERE r.propertyType = :propertyType"),
    @NamedQuery(name = "ReleaseProperties.findByPropertyName", query = "SELECT r FROM ReleaseProperties r WHERE r.propertyName = :propertyName"),
    @NamedQuery(name = "ReleaseProperties.findByPropertyValue", query = "SELECT r FROM ReleaseProperties r WHERE r.propertyValue = :propertyValue"),
    @NamedQuery(name = "ReleaseProperties.findByPropertyDescription", query = "SELECT r FROM ReleaseProperties r WHERE r.propertyDescription = :propertyDescription"),
    @NamedQuery(name = "ReleaseProperties.findByPropertyLocale", query = "SELECT r FROM ReleaseProperties r WHERE r.propertyLocale = :propertyLocale")})
public class ReleaseProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_properties", nullable = false, length = 48)
    private String idProperties;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "property_type", nullable = false, length = 32)
    private String propertyType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "property_name", nullable = false, length = 32)
    private String propertyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "property_value", nullable = false, length = 64)
    private String propertyValue;
    @Size(max = 128)
    @Column(name = "property_description", length = 128)
    private String propertyDescription;
    @Size(max = 12)
    @Column(name = "property_locale", length = 12)
    private String propertyLocale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionProperty", fetch = FetchType.EAGER)
    private List<ReleaseTypeQuestions> releaseTypeQuestionsList;

    public ReleaseProperties() {
    }

    public ReleaseProperties(String idProperties) {
        this.idProperties = idProperties;
    }

    public ReleaseProperties(String idProperties, String propertyType, String propertyName, String propertyValue) {
        this.idProperties = idProperties;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getIdProperties() {
        return idProperties;
    }

    public void setIdProperties(String idProperties) {
        this.idProperties = idProperties;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyLocale() {
        return propertyLocale;
    }

    public void setPropertyLocale(String propertyLocale) {
        this.propertyLocale = propertyLocale;
    }

    @XmlTransient
    public List<ReleaseTypeQuestions> getReleaseTypeQuestionsList() {
        return releaseTypeQuestionsList;
    }

    public void setReleaseTypeQuestionsList(List<ReleaseTypeQuestions> releaseTypeQuestionsList) {
        this.releaseTypeQuestionsList = releaseTypeQuestionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProperties != null ? idProperties.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleaseProperties)) {
            return false;
        }
        ReleaseProperties other = (ReleaseProperties) object;
        if ((this.idProperties == null && other.idProperties != null) || (this.idProperties != null && !this.idProperties.equals(other.idProperties))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.ReleaseProperties[ idProperties=" + idProperties + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "RELEASE_OPTIONS", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReleaseOptions.findAll", query = "SELECT r FROM ReleaseOptions r"),
    @NamedQuery(name = "ReleaseOptions.findByIdOptions", query = "SELECT r FROM ReleaseOptions r WHERE r.idOptions = :idOptions"),
    @NamedQuery(name = "ReleaseOptions.findByOptionType", query = "SELECT r FROM ReleaseOptions r WHERE r.optionType = :optionType"),
    @NamedQuery(name = "ReleaseOptions.findByOptionName", query = "SELECT r FROM ReleaseOptions r WHERE r.optionName = :optionName"),
    @NamedQuery(name = "ReleaseOptions.findByOptionValue", query = "SELECT r FROM ReleaseOptions r WHERE r.optionValue = :optionValue"),
    @NamedQuery(name = "ReleaseOptions.findByOptionDescription", query = "SELECT r FROM ReleaseOptions r WHERE r.optionDescription = :optionDescription")})
public class ReleaseOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_options", nullable = false, length = 48)
    private String idOptions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "option_type", nullable = false, length = 32)
    private String optionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "option_name", nullable = false, length = 32)
    private String optionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "option_value", nullable = false, length = 64)
    private String optionValue;
    @Size(max = 128)
    @Column(name = "option_description", length = 128)
    private String optionDescription;

    public ReleaseOptions() {
    }

    public ReleaseOptions(String idOptions) {
        this.idOptions = idOptions;
    }

    public ReleaseOptions(String idOptions, String optionType, String optionName, String optionValue) {
        this.idOptions = idOptions;
        this.optionType = optionType;
        this.optionName = optionName;
        this.optionValue = optionValue;
    }

    public String getIdOptions() {
        return idOptions;
    }

    public void setIdOptions(String idOptions) {
        this.idOptions = idOptions;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOptions != null ? idOptions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleaseOptions)) {
            return false;
        }
        ReleaseOptions other = (ReleaseOptions) object;
        if ((this.idOptions == null && other.idOptions != null) || (this.idOptions != null && !this.idOptions.equals(other.idOptions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.ReleaseOptions[ idOptions=" + idOptions + " ]";
    }
    
}

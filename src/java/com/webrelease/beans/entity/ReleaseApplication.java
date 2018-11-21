/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Cacheable(true)
@Table(name = "RELEASE_APPLICATION", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReleaseApplication.findAll", query = "SELECT r FROM ReleaseApplication r"),
    @NamedQuery(name = "ReleaseApplication.findByApplicationName", query = "SELECT r FROM ReleaseApplication r WHERE r.applicationName = :applicationName order by r.applicationName"),
    @NamedQuery(name = "ReleaseApplication.findByApplicationDescription", query = "SELECT r FROM ReleaseApplication r WHERE r.applicationDescription = :applicationDescription order by r.applicationName"),
    @NamedQuery(name = "ReleaseApplication.findByItsmTemplate", query = "SELECT r FROM ReleaseApplication r WHERE r.itsmTemplate = :itsmTemplate order by r.applicationName"),
    @NamedQuery(name = "ReleaseApplication.findByVersionTextRequired", query = "SELECT r FROM ReleaseApplication r WHERE r.versionTextRequired = :versionTextRequired order by r.applicationName")})
public class ReleaseApplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "application_name", nullable = false, length = 48)
    private String applicationName;
    @Size(max = 128)
    @Column(name = "application_description", length = 128)
    private String applicationDescription;
    @Size(max = 1024)
    @Column(name = "itsm_template", length = 1024)
    private String itsmTemplate;
    @Size(max = 3)
    @Column(name = "version_text_required", length = 3)
    private String versionTextRequired;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionType", fetch = FetchType.LAZY)
    private List<ReleaseTypeQuestions> releaseTypeQuestionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationName", fetch = FetchType.LAZY)
    private List<ReleaseManager> releaseManagerList;

    public ReleaseApplication() {
    }

    public ReleaseApplication(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getItsmTemplate() {
        return itsmTemplate;
    }

    public void setItsmTemplate(String itsmTemplate) {
        this.itsmTemplate = itsmTemplate;
    }

    public String getVersionTextRequired() {
        return versionTextRequired;
    }

    public void setVersionTextRequired(String versionTextRequired) {
        this.versionTextRequired = versionTextRequired;
    }

    @XmlTransient
    public List<ReleaseTypeQuestions> getReleaseTypeQuestionsList() {
        return releaseTypeQuestionsList;
    }

    public void setReleaseTypeQuestionsList(List<ReleaseTypeQuestions> releaseTypeQuestionsList) {
        this.releaseTypeQuestionsList = releaseTypeQuestionsList;
    }

    @XmlTransient
    public List<ReleaseManager> getReleaseManagerList() {
        return releaseManagerList;
    }

    public void setReleaseManagerList(List<ReleaseManager> releaseManagerList) {
        this.releaseManagerList = releaseManagerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationName != null ? applicationName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleaseApplication)) {
            return false;
        }
        ReleaseApplication other = (ReleaseApplication) object;
        if ((this.applicationName == null && other.applicationName != null) || (this.applicationName != null && !this.applicationName.equals(other.applicationName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  applicationName ;
    }
    
}

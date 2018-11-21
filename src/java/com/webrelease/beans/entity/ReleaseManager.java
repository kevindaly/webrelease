/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Cacheable(false)
@Table(name = "RELEASE_MANAGER", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReleaseManager.findAll", query = "SELECT r FROM ReleaseManager r"),
    @NamedQuery(name = "ReleaseManager.findByIdReleaseNumber", query = "SELECT r FROM ReleaseManager r WHERE r.idReleaseNumber = :idReleaseNumber"),
    @NamedQuery(name = "ReleaseManager.findByVersionNumber", query = "SELECT r FROM ReleaseManager r WHERE r.versionNumber = :versionNumber"),
    @NamedQuery(name = "ReleaseManager.findByPrimarySupportAd", query = "SELECT r FROM ReleaseManager r WHERE r.primarySupportAd = :primarySupportAd"),
    @NamedQuery(name = "ReleaseManager.findByRegionsImpacted", query = "SELECT r FROM ReleaseManager r WHERE r.regionsImpacted = :regionsImpacted"),
    @NamedQuery(name = "ReleaseManager.findByDbaTask", query = "SELECT r FROM ReleaseManager r WHERE r.dbaTask = :dbaTask"),
    @NamedQuery(name = "ReleaseManager.findByWorkRequestedBy", query = "SELECT r FROM ReleaseManager r WHERE r.workRequestedBy = :workRequestedBy"),
    @NamedQuery(name = "ReleaseManager.findByStatusInd", query = "SELECT r FROM ReleaseManager r WHERE r.statusInd = :statusInd"),
    @NamedQuery(name = "ReleaseManager.findByRrmApproved", query = "SELECT r FROM ReleaseManager r WHERE r.rrmApproved = :rrmApproved"),
    @NamedQuery(name = "ReleaseManager.findByRrmApprovedAutomation", query = "SELECT r FROM ReleaseManager r WHERE r.rrmApprovedAutomation = :rrmApprovedAutomation"),
    @NamedQuery(name = "ReleaseManager.findByReleaseStart", query = "SELECT r FROM ReleaseManager r WHERE r.releaseStart = :releaseStart"),
    @NamedQuery(name = "ReleaseManager.findByReleaseEnd", query = "SELECT r FROM ReleaseManager r WHERE r.releaseEnd = :releaseEnd"),
    @NamedQuery(name = "ReleaseManager.findByChangeType", query = "SELECT r FROM ReleaseManager r WHERE r.changeType = :changeType"),
    @NamedQuery(name = "ReleaseManager.findByReleaseType", query = "SELECT r FROM ReleaseManager r WHERE r.releaseType = :releaseType"),
    @NamedQuery(name = "ReleaseManager.findByRrmContact", query = "SELECT r FROM ReleaseManager r WHERE r.rrmContact = :rrmContact"),
    @NamedQuery(name = "ReleaseManager.findByReleaseId", query = "SELECT r FROM ReleaseManager r WHERE r.releaseId = :releaseId")})

@EntityListeners(ReleaseManagerListener.class)
public class ReleaseManager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 48)
    @Column(name = "id_release_number", nullable = false, length = 48)
    private String idReleaseNumber;
    @Basic(optional = false)

    @Size(min = 1, max = 12)
    @Column(name = "version_number", nullable = false, length = 12)
    private String versionNumber;
    @Basic(optional = false)
    
    @Size(min = 1, max = 128)
    @Column(name = "primary_support_ad", nullable = false, length = 128)
    private String primarySupportAd;
    @Basic(optional = false)
    
    @Size(min = 1, max = 32)
    @Column(name = "regions_impacted", nullable = false, length = 32)
    private String regionsImpacted;
    @Basic(optional = false)
    
    @Size(min = 1, max = 3)
    @Column(name = "dba_task", nullable = false, length = 3)
    private String dbaTask;
    @Basic(optional = false)

    @Size(min = 1, max = 10)
    @Column(name = "work_requested_by", nullable = false, length = 10)
    private String workRequestedBy;
    @Basic(optional = false)
    
    @Size(min = 1, max = 12)
    @Column(name = "status_ind", nullable = false, length = 12)
    private String statusInd;
    @Size(max = 3)
    @Column(name = "rrm_approved", length = 3)
    private String rrmApproved;
    @Size(max = 3)
    @Column(name = "rrm_approved_automation", length = 3)
    private String rrmApprovedAutomation;
    @Column(name = "release_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseStart;
    @Column(name = "release_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseEnd;
    @Size(max = 32)
    @Column(name = "change_type", length = 32)
    private String changeType;
    @Size(max = 12)
    @Column(name = "release_type", length = 12)
    private String releaseType;
    @Size(max = 48)
    @Column(name = "rrm_contact", length = 48)
    private String rrmContact;
    @Size(max = 64)
    @Column(name = "release_id", length = 64)
    private String releaseId;
    @OneToMany(mappedBy = "releaseId", fetch = FetchType.LAZY)
    private List<UnixSa> unixSaList;
    @OneToMany(mappedBy = "releaseId", fetch = FetchType.LAZY)
    private List<TextArea> textAreaList;
    @OneToMany(mappedBy = "releaseId", fetch = FetchType.LAZY)
    private List<Attachments> attachmentsList;
    @JoinColumn(name = "application_name", referencedColumnName = "application_name", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ReleaseApplication applicationName;
    @OneToMany(mappedBy = "releaseId", fetch = FetchType.LAZY)
    private List<DbaTaskDetails> dbaTaskDetailsList;

    public ReleaseManager() {
    }

    public ReleaseManager(String idReleaseNumber) {
        this.idReleaseNumber = idReleaseNumber;
    }

    public ReleaseManager(String idReleaseNumber, String versionNumber, String primarySupportAd, String regionsImpacted, String dbaTask, String workRequestedBy, String statusInd) {
        this.idReleaseNumber = idReleaseNumber;
        this.versionNumber = versionNumber;
        this.primarySupportAd = primarySupportAd;
        this.regionsImpacted = regionsImpacted;
        this.dbaTask = dbaTask;
        this.workRequestedBy = workRequestedBy;
        this.statusInd = statusInd;
    }

    public String getIdReleaseNumber() {
        return idReleaseNumber;
    }

    public void setIdReleaseNumber(String idReleaseNumber) {
        this.idReleaseNumber = idReleaseNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getPrimarySupportAd() {
        return primarySupportAd;
    }

    public void setPrimarySupportAd(String primarySupportAd) {
        this.primarySupportAd = primarySupportAd;
    }

    public String getRegionsImpacted() {
        return regionsImpacted;
    }

    public void setRegionsImpacted(String regionsImpacted) {
        this.regionsImpacted = regionsImpacted;
    }

    public String getDbaTask() {
        return dbaTask;
    }

    public void setDbaTask(String dbaTask) {
        this.dbaTask = dbaTask;
    }

    public String getWorkRequestedBy() {
        return workRequestedBy;
    }

    public void setWorkRequestedBy(String workRequestedBy) {
        this.workRequestedBy = workRequestedBy;
    }

    public String getStatusInd() {
        return statusInd;
    }

    public void setStatusInd(String statusInd) {
        this.statusInd = statusInd;
    }

    public String getRrmApproved() {
        return rrmApproved;
    }

    public void setRrmApproved(String rrmApproved) {
        this.rrmApproved = rrmApproved;
    }

    public String getRrmApprovedAutomation() {
        return rrmApprovedAutomation;
    }

    public void setRrmApprovedAutomation(String rrmApprovedAutomation) {
        this.rrmApprovedAutomation = rrmApprovedAutomation;
    }

    public Date getReleaseStart() {
        return releaseStart;
    }

    public void setReleaseStart(Date releaseStart) {
        this.releaseStart = releaseStart;
    }

    public Date getReleaseEnd() {
        return releaseEnd;
    }

    public void setReleaseEnd(Date releaseEnd) {
        this.releaseEnd = releaseEnd;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getRrmContact() {
        return rrmContact;
    }

    public void setRrmContact(String rrmContact) {
        this.rrmContact = rrmContact;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }

    @XmlTransient
    public List<UnixSa> getUnixSaList() {
        return unixSaList;
    }

    public void setUnixSaList(List<UnixSa> unixSaList) {
        this.unixSaList = unixSaList;
    }

    @XmlTransient
    public List<TextArea> getTextAreaList() {
        return textAreaList;
    }

    public void setTextAreaList(List<TextArea> textAreaList) {
        this.textAreaList = textAreaList;
    }

    @XmlTransient
    public List<Attachments> getAttachmentsList() {
        return attachmentsList;
    }

    public void setAttachmentsList(List<Attachments> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }

    public ReleaseApplication getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(ReleaseApplication applicationName) {
        this.applicationName = applicationName;
    }

    @XmlTransient
    public List<DbaTaskDetails> getDbaTaskDetailsList() {
        return dbaTaskDetailsList;
    }

    public void setDbaTaskDetailsList(List<DbaTaskDetails> dbaTaskDetailsList) {
        this.dbaTaskDetailsList = dbaTaskDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReleaseNumber != null ? idReleaseNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleaseManager)) {
            return false;
        }
        ReleaseManager other = (ReleaseManager) object;
        if ((this.idReleaseNumber == null && other.idReleaseNumber != null) || (this.idReleaseNumber != null && !this.idReleaseNumber.equals(other.idReleaseNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.ReleaseManager[ idReleaseNumber=" + idReleaseNumber + " ]";
    }
    
}

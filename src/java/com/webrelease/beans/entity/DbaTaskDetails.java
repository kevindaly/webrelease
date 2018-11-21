/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Cacheable(false)
@Table(name = "DBA_TASK_DETAILS", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DbaTaskDetails.findAll", query = "SELECT d FROM DbaTaskDetails d"),
    @NamedQuery(name = "DbaTaskDetails.findByPhasedId", query = "SELECT d FROM DbaTaskDetails d WHERE d.phasedId = :phasedId"),
    @NamedQuery(name = "DbaTaskDetails.findByPhased", query = "SELECT d FROM DbaTaskDetails d WHERE d.phased = :phased"),
    @NamedQuery(name = "DbaTaskDetails.findByTaskStart", query = "SELECT d FROM DbaTaskDetails d WHERE d.taskStart = :taskStart"),
    @NamedQuery(name = "DbaTaskDetails.findByTaskEnd", query = "SELECT d FROM DbaTaskDetails d WHERE d.taskEnd = :taskEnd"),
    @NamedQuery(name = "DbaTaskDetails.findByTestServer", query = "SELECT d FROM DbaTaskDetails d WHERE d.testServer = :testServer"),
    @NamedQuery(name = "DbaTaskDetails.findByListOfSpEbf", query = "SELECT d FROM DbaTaskDetails d WHERE d.listOfSpEbf = :listOfSpEbf"),
    @NamedQuery(name = "DbaTaskDetails.findBySpEbfLabel", query = "SELECT d FROM DbaTaskDetails d WHERE d.spEbfLabel = :spEbfLabel")})

@EntityListeners(DbaTaskDetailsListener.class)
public class DbaTaskDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "phased_id", nullable = false, length = 48)
    private String phasedId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "phased_", nullable = false, length = 3)
    private String phased;
    @Column(name = "task_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskStart;
    @Column(name = "task_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskEnd;
    @Size(max = 255)
    @Column(name = "test_server", length = 255)
    private String testServer;
    @Size(max = 2048)
    @Column(name = "list_of_sp_ebf", length = 2048)
    private String listOfSpEbf;
    @Size(max = 128)
    @Column(name = "sp_ebf_label", length = 128)
    private String spEbfLabel;
    @JoinColumn(name = "release_id", referencedColumnName = "id_release_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReleaseManager releaseId;

    public DbaTaskDetails() {
    }

    public DbaTaskDetails(String phasedId) {
        this.phasedId = phasedId;
    }

    public DbaTaskDetails(String phasedId, String phased) {
        this.phasedId = phasedId;
        this.phased = phased;
    }

    public String getPhasedId() {
        return phasedId;
    }

    public void setPhasedId(String phasedId) {
        this.phasedId = phasedId;
    }

    public String getPhased() {
        return phased;
    }

    public void setPhased(String phased) {
        this.phased = phased;
    }

    public Date getTaskStart() {
        return taskStart;
    }

    public void setTaskStart(Date taskStart) {
        this.taskStart = taskStart;
    }

    public Date getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(Date taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getTestServer() {
        return testServer;
    }

    public void setTestServer(String testServer) {
        this.testServer = testServer;
    }

    public String getListOfSpEbf() {
        return listOfSpEbf;
    }

    public void setListOfSpEbf(String listOfSpEbf) {
        this.listOfSpEbf = listOfSpEbf;
    }

    public String getSpEbfLabel() {
        return spEbfLabel;
    }

    public void setSpEbfLabel(String spEbfLabel) {
        this.spEbfLabel = spEbfLabel;
    }

    public ReleaseManager getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(ReleaseManager releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phasedId != null ? phasedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbaTaskDetails)) {
            return false;
        }
        DbaTaskDetails other = (DbaTaskDetails) object;
        if ((this.phasedId == null && other.phasedId != null) || (this.phasedId != null && !this.phasedId.equals(other.phasedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.DbaTaskDetails[ phasedId=" + phasedId + " ]";
    }
    
}

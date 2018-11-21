/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "UNIX_SA", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnixSa.findAll", query = "SELECT u FROM UnixSa u"),
    @NamedQuery(name = "UnixSa.findByIdTask", query = "SELECT u FROM UnixSa u WHERE u.idTask = :idTask"),
    @NamedQuery(name = "UnixSa.findByTaskDetails", query = "SELECT u FROM UnixSa u WHERE u.taskDetails = :taskDetails"),
    @NamedQuery(name = "UnixSa.findByTaskStartTime", query = "SELECT u FROM UnixSa u WHERE u.taskStartTime = :taskStartTime"),
    @NamedQuery(name = "UnixSa.findByTaskEndTime", query = "SELECT u FROM UnixSa u WHERE u.taskEndTime = :taskEndTime")})

@EntityListeners(UnixSaListener.class)

public class UnixSa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_task", nullable = false, length = 48)
    private String idTask;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "task_details", nullable = false, length = 255)
    private String taskDetails;
    @Column(name = "task_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskStartTime;
    @Column(name = "task_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskEndTime;
    @JoinColumn(name = "release_id", referencedColumnName = "id_release_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReleaseManager releaseId;

    public UnixSa() {
    }

    public UnixSa(String idTask) {
        this.idTask = idTask;
    }

    public UnixSa(String idTask, String taskDetails) {
        this.idTask = idTask;
        this.taskDetails = taskDetails;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
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
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnixSa)) {
            return false;
        }
        UnixSa other = (UnixSa) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.UnixSa[ idTask=" + idTask + " ]";
    }
    
}
